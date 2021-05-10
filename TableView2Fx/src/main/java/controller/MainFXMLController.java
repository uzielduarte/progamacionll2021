/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import leen.tableview2fx.App;

/**
 * FXML Controller class
 *
 * @author Sistemas-08
 */
public class MainFXMLController implements Initializable {

    @FXML
    public Button btnProducto;
    @FXML
    public VBox vBoxCenter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void btnProductoAction(ActionEvent event)
    {
        try {
            Parent node = App.loadFXML("ProductoFXML");
            vBoxCenter.getChildren().clear();
            vBoxCenter.getChildren().add(node);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
}
