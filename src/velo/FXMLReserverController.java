/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velo;

import Entite.Reservation;
import Services.ServiceLocation;
import Services.ServiceReservation;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
        SR = new ServiceReservation();
    }

    public boolean verifierchamps() {
        Date fdateDeb = Date.valueOf(dateDeb.getValue());
        Date fdateFin = Date.valueOf(dateFin.getValue());

         ServiceReservation r = new ServiceReservation ();  
        Pattern p = Pattern.compile("[a-zA-Z]");

   

        // verification des champs vides
        if (fdateDeb.trim (). equals ("") || fdateFin.trim ().equals ("")) {
            JOptionPane.showMessageDialog(null, "One or more fields are empty", "Empty fields", 2);
            return false;

        }
////                else if ( Date.valueOf(dateDeb.getValue() ) > Date.valueOf(dateFin.getValue() ) ){
//                 JOptionPane.showMessageDialog (null, "check date","date", 2);
//                    return false;             
//}           
        return true;
    }

    @FXML
    private void reserver(ActionEvent event) throws IOException {
        if (verifierchamps() == true) {
            System.out.println( "Date deb" + dateDeb.getValue() + " Date fin" + dateFin.getValue());

            Reservation r = new Reservation(1, Date.valueOf(dateDeb.getValue()), Date.valueOf(dateFin.getValue()));
            try {
                SR.addLouer(r);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Reservation ");
                alert1.setHeaderText(null);
                alert1.setContentText("Reservation added ");

                alert1.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
 Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLHome.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        }

        try {
            Notification.sendNotification("add succeed", TrayIcon.MessageType.ERROR);
        } catch (AWTException ex) {
            Logger.getLogger(FXMLReserverController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FXMLReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLLocation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

}
