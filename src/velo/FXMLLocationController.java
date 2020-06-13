/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velo;

import Entite.Location;
import Services.ServiceLocation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nahawnd
 */
public class FXMLLocationController implements Initializable {
  private Connection cnx;
    ServiceLocation SL;
    @FXML
    private Button rent;
    @FXML
    private Label labelProductDisplay;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      SL = new ServiceLocation();
      
//      Location l = new Location(1, titre.getText(), lieu.getText(),Float.valueOf(prix.getText()),lieu.getText());
//        try {
//            SL.getById(id);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
        
    }    

    @FXML
    private void reserver(ActionEvent event) throws IOException {
         Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLReserver.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void rent_list(ActionEvent event) throws IOException {
       Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLListLocation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();  
    }
    
}
