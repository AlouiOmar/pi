/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velo;

import entities.Location;
import Services.ServiceLocation;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nahawnd
 */
public class FXMLSearchController implements Initializable {

       private Connection cnx;
  
    
//      ServiceLocation SL = new ServiceLocation();        
ObservableList la = FXCollections.observableArrayList();
    @FXML
    private TextField name_rech;
    @FXML
    private Button submit;
    
       ServiceLocation SL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
            SL = new ServiceLocation();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAddLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

//    @FXML
//    private void rechercher(ActionEvent event) throws SQLException {
//              String text = name_rech.getText();
//              ObservableList<Location> la = FXCollections.observableArrayList();
//              for(Location l: SL.RechLocation(text))
//                  la.add(l);
////            tableview.setItems(la);
//    }
//    
    
}
