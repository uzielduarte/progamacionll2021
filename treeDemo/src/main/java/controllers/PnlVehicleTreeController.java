/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import backend.dao.implementation.JsonVehicleDaoImpl;
import backend.pojo.Vehicle;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import jdk.nashorn.internal.runtime.JSType;
import panels.PnlFields;
import panels.PnlVehicleTree;

/**
 *
 * @author UZIEL
 */
public class PnlVehicleTreeController
{
    private PnlVehicleTree pnlVehicleTree;
    private PnlFields pnlFields;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root;
    
    JsonVehicleDaoImpl jvdi;
    List<Vehicle> vehicles = null;
    DefaultMutableTreeNode rootMake;

    public PnlVehicleTreeController(PnlVehicleTree pnlVehicleTree)
    {
        this.pnlVehicleTree = pnlVehicleTree;
        iniComponent();
    }

    private void iniComponent()
    {
        root = new DefaultMutableTreeNode("Vehicles", true);
        treeModel = new DefaultTreeModel(root);
        
        pnlVehicleTree.getTreeAccount().setModel(treeModel);
        pnlVehicleTree.getTreeAccount().setExpandsSelectedPaths(true);
        
        try
        {
            jvdi = new JsonVehicleDaoImpl();
            vehicles  = (List<Vehicle>) jvdi.getAll();
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(PnlVehicleTreeController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex)
            {
                Logger.getLogger(PnlVehicleTreeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        // Agrupo los carros por la marca
        Map<String, List<Vehicle>> vehiclesByMakes
                = vehicles.stream()
                .collect(Collectors.groupingBy(Vehicle::getMake));
 
        // Instancio el nodo rootMake en el cual estaran las marcas
        rootMake = new DefaultMutableTreeNode("Makes");

        for (Map.Entry<String, List<Vehicle>> entry: vehiclesByMakes.entrySet())
        {
            DefaultMutableTreeNode childMake = new DefaultMutableTreeNode(entry.getKey());
            
            for(Vehicle v: entry.getValue())
            {
                DefaultMutableTreeNode vehicleOfMake = new DefaultMutableTreeNode(v.getStockNumber());
                
                treeModel.insertNodeInto(vehicleOfMake, childMake, childMake.getChildCount());
            }
            treeModel.insertNodeInto(childMake, rootMake, rootMake.getChildCount());
        }
        //inserto el nodo rootMake al nodo root(Vehicle)
        treeModel.insertNodeInto(rootMake, root, root.getChildCount());
        
        pnlVehicleTree.getTreeAccount().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                treeAccountMouseListener(e);
            }
        });
    }
    
    public void treeAccountMouseListener(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON3 ){
             
            TreePath c =  pnlVehicleTree.getTreeAccount().getPathForLocation(e.getX(), e.getY());
            if(c == null){
                return;
            }
            
            String selected = c.getLastPathComponent().toString();
            
            if(selected.matches("[+-]?\\d*(\\.\\d+)?"))
                showVehicle(Integer.parseInt(selected));
        }
    }
    
    private void showVehicle(int stockNumer)
    {
        if (pnlFields == null)
            pnlFields = new PnlFields();
        for(Vehicle v: vehicles)
        {
            if(v.getStockNumber() == stockNumer)
            {
                pnlFields.getTxtStockNumber().setText(v.getStockNumber() + "");
                pnlFields.getTxtYear().setText(v.getYear() + "");
                pnlFields.getTxtMake().setText(v.getMake());
                pnlFields.getTxtModel().setText(v.getModel());
                pnlFields.getTxtStyle().setText(v.getStyle());
                pnlFields.getTxtVin().setText(v.getVin());
                pnlFields.getTxtEColor().setText(v.getExteriorColor());
                pnlFields.getTxtIColor().setText(v.getInteriorColor());
                pnlFields.getTxtMiles().setText(v.getMiles());
                pnlFields.getTxtPrice().setText(v.getPrice() + "");
                pnlFields.getTxtEngine().setText(v.getEngine());
                pnlFields.getTxtImagePath().setText(v.getImage());
                pnlFields.getTxtStatus().setText(v.getStatus());
                //Vehicle.Transmission [] trans = Vehicle.Transmission.values();
                pnlFields.getTxtTransmission().setText(v.getTransmission().toString());

                pnlVehicleTree.getPnlData().removeAll();
                pnlVehicleTree.getPnlData().add(pnlFields, BorderLayout.CENTER);
                pnlVehicleTree.getPnlData().repaint();
                
  
                break;
            }
        }
    }
}
