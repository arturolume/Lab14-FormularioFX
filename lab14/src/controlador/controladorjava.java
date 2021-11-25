
package controlador;

import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lab14.modelotabla;
//import lab14.tablacontrolador;
import java.sql.*;


public class controladorjava implements Initializable {
    
    @FXML
    private TextField txfcodigo;
    
    @FXML
    private TextField txfapellidos;
    
    @FXML
    private TextField txfnombres;
    
    @FXML
    private TextField txfdireccion;
    
    @FXML
    private TextField txfdistrito;
    
    @FXML
    private Button btnregistrar;
    
    @FXML
    private Button btnmostrar;
    
    //s
    @FXML
    private TableView<modelotabla> table;
    @FXML
    private TableColumn<modelotabla, String> col_codigo;
    @FXML
    private TableColumn<modelotabla, String> col_nombres;
    @FXML
    private TableColumn<modelotabla, String> col_apellidos;
    @FXML
    private TableColumn<modelotabla, String> col_direccion;
    @FXML
    private TableColumn<modelotabla, String> col_distrito;
    
    ObservableList<modelotabla> oblist = FXCollections.observableArrayList();
    //ss
    
    
    
        
    @FXML
    private void eventKey(KeyEvent event){
        
        Object evt = event.getSource();
        
        if(evt.equals(txfcodigo)){
            
            if(event.getCharacter().equals("")){
                event.consume();
            }
            
        }else if(evt.equals(txfapellidos)){
            
            if(event.getCharacter().equals("")){
                event.consume();
            }
            
        }else if(evt.equals(txfnombres)){
            
            if(event.getCharacter().equals("")){
                event.consume();
            }
            
        }else if(evt.equals(txfdireccion)){
            
            if(event.getCharacter().equals("")){
                event.consume();
            }
            
        }else if(evt.equals(txfdistrito)){
            
            if(event.getCharacter().equals("")){
                event.consume();
            }
            
        }
        
    }
    
    @FXML
    private void eventAction(ActionEvent event){
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //s
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Matricula","root","Reyes562");
            CallableStatement procedimiento = con.prepareCall("{ call Mostrar_Alumnos()}");
            procedimiento.execute();
            ResultSet rs = procedimiento.executeQuery();
            while (rs.next()){
                oblist.add(new modelotabla(rs.getString("codigo"), rs.getString("apellidos"),
                        rs.getString("nombres"), rs.getString("direccion"), rs.getString("distrito")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(controladorjava.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        col_codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        col_nombres.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        col_apellidos.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        col_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        col_distrito.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        
        table.setItems(oblist);
        //ss
    }
}
