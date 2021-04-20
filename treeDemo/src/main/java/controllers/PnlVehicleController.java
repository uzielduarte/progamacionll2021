/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import backend.dao.implementation.JsonVehicleDaoImpl;
import backend.pojo.Vehicle;
import backend.pojo.VehicleSubModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
//import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import panels.PnlVehicle;

/**
 *
 * @author UZIEL
 */
public class PnlVehicleController extends java.util.Observable
{
    private PnlVehicle pnlVehicle;
    private JsonVehicleDaoImpl jvdao;
    private List<VehicleSubModel> vSubModels;
    private Gson gson;
    private DefaultComboBoxModel cmbModelMake;
    private DefaultComboBoxModel cmbModelModel;
    private DefaultComboBoxModel cmbModelEColor;
    private DefaultComboBoxModel cmbModelIColor;
    private DefaultComboBoxModel cmbModelStatus;
    private String status[] = new String[]{"Active","Mantainance","Not available"};
    private JFileChooser fileChooser;
    private boolean isNew = true;
    private boolean isUpdate = false;
    private int vehicleIdToEdit;
    private Vehicle vehicle;
    
    public void setVehicleIdToEdit(int vehicleIdToEdit)
    {
        this.vehicleIdToEdit = vehicleIdToEdit;
    }

    public void setIsNew(boolean isNew)
    {
        this.isNew = isNew;
        isUpdate = false;
    }

    public void setIsUpdate(boolean isUpdate)
    {
        this.isUpdate = isUpdate;
        isNew = false;
    }

    public PnlVehicleController(PnlVehicle pnlVehicle) throws FileNotFoundException
    {
        this.pnlVehicle = pnlVehicle;
        initComponent();
    }
    
    private void initComponent() throws FileNotFoundException
    {
        jvdao = new JsonVehicleDaoImpl();
        gson = new Gson();
        
        JsonReader jreader = new JsonReader(new BufferedReader(
        new InputStreamReader(getClass().getResourceAsStream("/jsons/vehicleData.json"))));
        
        Type listType = new TypeToken<ArrayList<VehicleSubModel>>(){}.getType();
        
        vSubModels = gson.fromJson(jreader, listType);
        
        List<String> makes = vSubModels.stream().map(x -> x.getMake()).collect(Collectors.toList());
        List<String> models = vSubModels.stream().map(x -> x.getModel()).collect(Collectors.toList());
        List<String> colors = vSubModels.stream().map(x -> x.getColor()).collect(Collectors.toList());
        
        cmbModelMake = new DefaultComboBoxModel(makes.toArray());
        cmbModelModel = new DefaultComboBoxModel(models.toArray());
        cmbModelEColor = new DefaultComboBoxModel(colors.toArray());
        cmbModelIColor = new DefaultComboBoxModel(colors.toArray());
        cmbModelStatus = new DefaultComboBoxModel(status);
        
        pnlVehicle.getCmbMake().setModel(cmbModelMake);
        pnlVehicle.getCmbModel().setModel(cmbModelModel);
        pnlVehicle.getCmbEColor().setModel(cmbModelEColor);
        pnlVehicle.getCmbIColor().setModel(cmbModelIColor);
        pnlVehicle.getCmbStatus().setModel(cmbModelStatus);    
        
        pnlVehicle.getBtnSave().addActionListener(((e) -> {
            btnSaveActionListener(e);
        }));
        
        pnlVehicle.getBtnBrowse().addActionListener(((e) -> {
            btnBrowseActionListener(e);
        }));
    }
    
    private void btnSaveActionListener(ActionEvent e)
    {
        int stock, year;
        String make, model, style, vin, eColor, iColor, miles, engine, image, status;
        float price;
        Vehicle.Transmission transmission;
        
        stock = Integer.parseInt(pnlVehicle.getTxtStock().getText());
        year = Integer.parseInt(pnlVehicle.getSpnYear().getModel().getValue().toString());
        make = pnlVehicle.getCmbMake().getSelectedItem().toString();
        model = pnlVehicle.getCmbModel().getSelectedItem().toString();
        style = pnlVehicle.getTxtStyle().getText();
        vin = pnlVehicle.getFmtVin().getText();
        eColor = pnlVehicle.getCmbEColor().getSelectedItem().toString();
        iColor = pnlVehicle.getCmbIColor().getSelectedItem().toString();
        miles = pnlVehicle.getSpnMiles().getModel().getValue().toString();
        price = Float.parseFloat(pnlVehicle.getSpnPrice().getModel().getValue().toString());
        transmission = pnlVehicle.getRbtnAut().isSelected()?
                Vehicle.Transmission.AUTOMATIC:Vehicle.Transmission.MANUAL;
        engine = pnlVehicle.getTxtEngine().getText();
        image = pnlVehicle.getTxtImage().getText();
        status = pnlVehicle.getCmbStatus().getSelectedItem().toString();
        
        vehicle = new Vehicle(stock,year, make, model, style, vin, eColor, iColor, miles, price, transmission, engine, image, status);
        
        try {
            vehicleValidation(vehicle);
            
            if(isNew)
            {   
                jvdao.create(vehicle);
                setChanged();
                notifyObservers(vehicle);
                JOptionPane.showMessageDialog(null, "Vehicle saved sucessfully.",
                "Saved message",JOptionPane.INFORMATION_MESSAGE);
                
            }
            
            if(isUpdate)
            {
                vehicle.setId(vehicleIdToEdit);
                jvdao.update(vehicle);
                setChanged();
                notifyObservers(isUpdate);
             
            JOptionPane.showMessageDialog(null, "Vehicle updated sucessfully.",
                    "Updating message",JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(PnlVehicleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), 
                    "Error Message", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(PnlVehicleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void btnBrowseActionListener(ActionEvent e){
        fileChooser = new JFileChooser();
        
        int option = fileChooser.showOpenDialog(null);
        
        if(option == JFileChooser.CANCEL_OPTION){
            return;
        }
        
        File imageFile = fileChooser.getSelectedFile();
        pnlVehicle.getTxtImage().setText(imageFile.getPath());        
    }
    
    private void vehicleValidation(Vehicle v) throws Exception{
        if(v.getStockNumber() <=0){
            throw new Exception("StockNumber can not be less or equal to zero.");
        }
        
        if( v.getVin().isEmpty() || v.getVin().isBlank()){
            throw new Exception("Vin number can not be empty or blank.");
        }
        
        if(v.getEngine().isBlank() || v.getEngine().isEmpty()){
            throw new Exception("Engine can not be empty or blank.");
        }
    }
    
}
