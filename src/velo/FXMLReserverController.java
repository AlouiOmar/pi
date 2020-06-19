/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velo;

import entities.Reservation;
import Services.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nahawnd
 */
public class FXMLReserverController implements Initializable {
private Connection cnx;
    ServiceReservation SR;
    
    @FXML
    private Label labelBykeRent;
    @FXML
    private Button submit;
    @FXML
    private Button exit;
    @FXML
    private DatePicker dateDeb;
    @FXML
    private DatePicker dateFin;
    @FXML
    private TextField titre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        SR = new ServiceReservation();
    } catch (SQLException ex) {
        Logger.getLogger(FXMLReserverController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    

    @FXML
    private void reserver(ActionEvent event) {
          System.out.println("titre "+titre.getText()
                  +"Date deb"+dateDeb.getValue()
                  +" Date fin"+dateFin.getValue());
        
    Reservation r = new Reservation(
            titre.getText(),
            Date.valueOf(dateDeb.getValue()),
            Date.valueOf(dateFin.getValue()));
        try {
            SR.addLouer(r);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("ajout terminé");
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLLocation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
    
    
      public boolean verifierchamps() throws SQLException {
            String ftitre = titre.getText();
          //         vérifie les champs vides
        if (ftitre.trim().equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Check fields !");
            alert.showAndWait();
            return false;
        }
        
          else if (java.sql.Date.valueOf(dateDeb.getValue()).after(java.sql.Date.valueOf(dateFin.getValue()))) {   
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialogue");
            alert.setHeaderText(null);
            alert.setContentText("dates invalide");
            alert.showAndWait(); 
          }
        return true;
      }
      
}
