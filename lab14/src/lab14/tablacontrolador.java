
package lab14;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tablacontrolador implements Initializable {
    
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        
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
            Logger.getLogger(tablacontrolador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        col_codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        col_nombres.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        col_apellidos.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        col_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        col_distrito.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        
        table.setItems(oblist);
        
    }
    
}
