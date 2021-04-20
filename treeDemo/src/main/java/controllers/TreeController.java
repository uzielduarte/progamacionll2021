/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import views.FrmTree;

/**
 *
 * @author UZIEL
 */
public class TreeController
{
    private FrmTree frmTree;
    private DefaultTreeModel defaultTreeModel;
    private DefaultMutableTreeNode root;

    public TreeController(FrmTree frmTree)
    {
        this.frmTree = frmTree;
        initComponent();
    }
    
    public void initComponent()
    {
        root = new DefaultMutableTreeNode("Accounting", true);
        defaultTreeModel = new DefaultTreeModel(root);
        
        frmTree.getTreeAccount().setModel(defaultTreeModel);
        frmTree.getTreeAccount().setExpandsSelectedPaths(true);
        
        // Botones del panel sur del FrmTree
        frmTree.getBtnAdd().addActionListener((e) ->
        {
            btnAddActionListener(e);
        });
        
        frmTree.getBtnRemove().addActionListener((e) ->
        {
            btnRemoveActionListener(e);
        });
        
        frmTree.getBtnRemoveAll().addActionListener((e) ->
        {
            btnRomoveAllActionListener(e);
        });
        
        // Popupmenu; Menus items. de otros componentes del FrmTree
        frmTree.getMniAdd().addActionListener((e) ->
        {
            btnAddActionListener(e);
        });
        
        frmTree.getMniAdd().addActionListener((e) ->
        {
            btnRemoveActionListener(e);
        });
    }

    private void btnAddActionListener(ActionEvent e)
    {
        DefaultMutableTreeNode node = getSelectedNode();
        
        if(node == null)
            return;
        
        String accountName = JOptionPane.showInputDialog(null, "Account name", "Input Dialog", JOptionPane.INFORMATION_MESSAGE);
        
        if(accountName == null || accountName.isEmpty() || accountName.isBlank())
            return;
        
        int childCount = node.getChildCount();
        DefaultMutableTreeNode child = new DefaultMutableTreeNode(accountName);
        defaultTreeModel.insertNodeInto(child, node, childCount);
    }
    
    private DefaultMutableTreeNode getSelectedNode()
    {
        TreePath treePath = frmTree.getTreeAccount().getSelectionPath();
        
        if(treePath == null)
            return null;
        return (DefaultMutableTreeNode) treePath.getLastPathComponent();
    }

    private void btnRemoveActionListener(ActionEvent e)
    {
        DefaultMutableTreeNode node = getSelectedNode();
        
        if(node == null || node.isRoot())
            return;
        defaultTreeModel.removeNodeFromParent(node);
    }

    private void btnRomoveAllActionListener(ActionEvent e)
    {
        root.removeAllChildren();
        defaultTreeModel.reload();
    }
}
