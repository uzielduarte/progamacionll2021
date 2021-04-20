/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import backend.dao.implementation.JsonVehicleDaoImpl;
import backend.pojo.Vehicle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import panels.PnlVehicleTree;

/**
 *
 * @author UZIEL
 */
public class PnlVehicleTreeController
{
    private PnlVehicleTree pnlVehicleTree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root;
    
    JsonVehicleDaoImpl jvdi;
    List<Vehicle> vehicles = null;

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
        
        List<String> makes = new ArrayList<String>(0);
        
        makes.add(vehicles.get(0).getMake());
        
        for(Vehicle v: vehicles)
        {
            boolean isAdded = false;
            
            for(String make: makes)
            {
                if(v.getMake().equalsIgnoreCase(make))
                {
                    isAdded = true;
                    break;
                }
            }
            if(!isAdded)
                makes.add(v.getMake());
        }
        
        DefaultMutableTreeNode rootMake = new DefaultMutableTreeNode("Makes");
        
        for(String m: makes)
        {
            DefaultMutableTreeNode childMake = new DefaultMutableTreeNode(m);
            treeModel.insertNodeInto(childMake, rootMake, rootMake.getChildCount());
            
        }
        treeModel.insertNodeInto(rootMake, root, root.getChildCount());
        
        List<String> models = new ArrayList<String>(0);
        
        models.add(vehicles.get(0).getModel());
        
        for(Vehicle v: vehicles)
        {
            boolean isAdded = false;
            
            for(String model: models)
            {
                if(v.getModel().equalsIgnoreCase(model))
                {
                    isAdded = true;
                    break;
                }
            }
            if(!isAdded)
                models.add(v.getModel());
        }
        
        DefaultMutableTreeNode rootModel = new DefaultMutableTreeNode("Model");
        
        for(String m: models)
        {
            DefaultMutableTreeNode childModel = new DefaultMutableTreeNode(m);
            treeModel.insertNodeInto(childModel, rootModel, rootModel.getChildCount());
            
        }
        treeModel.insertNodeInto(rootModel, root, root.getChildCount());
    }
    
    
}
