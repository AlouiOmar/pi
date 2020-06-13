/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velo;

import Entite.Location;
import Services.ServiceLocation;
import static java.awt.SystemColor.window;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nahawnd
 */
public class FXMLSearchController implements Initializable {

       private Connection cnx;
  
    
      ServiceLocation SL = new ServiceLocation();        
ObservableList la = FXCollections.observableArrayList();
    @FXML
    private TextField name_rech;
    @FXML
    private Button submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         SL = new ServiceLocation();
    }    
    @FXML
    private void rechercher(ActionEvent event) throws IOException {
              String text = name_rech.getText();
              ObservableList<Location> la = FXCollections.observableArrayList();
     try {
        
         
         for(Location l: SL.RechLocation(text))
             la.add(l);
         
     } catch (SQLException ex) {
         Logger.getLogger(FXMLSearchController.class.getName()).log(Level.SEVERE, null, ex);
     } 
    
       Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLresultat.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }  
}
