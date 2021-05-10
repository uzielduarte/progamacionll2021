/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Producto;

/**
 * FXML Controller class
 *
 * @author Sistemas-08
 */
public class ProductoFXMLController implements Initializable {

    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtDescripcion;
    @FXML
    public TextField txtPrecio;
    @FXML
    public TextField txtImage;
    @FXML
    public Spinner<Integer> spnCantidad;
    @FXML
    public Button btnBuscar;
    @FXML
    public Button btnAgregar;
    @FXML
    public Button btnEliminar;
    @FXML
    public TableView<Producto> tblProducto;
    @FXML
    public TableColumn<Producto, String> tblCId;
    @FXML
    public TableColumn<Producto, String> tblCNombre;
    @FXML
    public TableColumn<Producto, String> tblCDescripcion;
    @FXML
    public TableColumn<Producto, String> tblCCantidad;
    @FXML
    public TableColumn<Producto, String> tblCPrecio;
    
    public ObservableList<Producto> productos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productos = FXCollections.observableArrayList();
        productos.add(new Producto(1, "Cocacola", "Vitamina", 2, 2.0f, "no image"));
        tblCId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tblCNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        tblCDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        tblCCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        tblCPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        spnCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1));
        tblProducto.setItems(productos);
    }    

    @FXML
    public void btnBuscarAction(ActionEvent event) {
    }

    @FXML
    public void btnAgregarAction(ActionEvent event) {
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        int cantidad = Integer.parseInt(spnCantidad.getValue().toString());
        float precio = Float.parseFloat(txtPrecio.getText());
        String image = txtImage.getText();
        
        Producto last = productos.get(productos.size() - 1);
        int id = 0;
        if(last == null)   
            id = 1;
        Producto p = new Producto(last.getId() + 1, nombre, descripcion, cantidad, precio, image);
        
        productos.add(p);
    }

    @FXML
    public void btnEliminarAction(ActionEvent event) {
    }
    
}
