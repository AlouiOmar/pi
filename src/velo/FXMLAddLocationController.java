/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velo;

import Entite.Location;
import Services.ServiceLocation;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author nahawnd
 */
public class FXMLAddLocationController implements Initializable {

    @FXML
    private Button submit;
    @FXML
    private Button annuler;

    @FXML
    private Label labelRentAdd;

    @FXML
    private TextField lieu;
    @FXML
    private TextField prix;
    @FXML
    private TextField titre;
    @FXML
    private Button photo;

    private Connection cnx;
    ServiceLocation SL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SL = new ServiceLocation();
    }

    public boolean verifierchamps() throws SQLException {
        String ftitre = titre.getText();
        String flieu = lieu.getText();
        String fprix = prix.getText();
        String fphoto = photo.getText();
        //Integer frating = rating.getText();
        //Date fdateCreation = Date.valueOf(dateFin.getValue())

        ServiceLocation l = new ServiceLocation();
        Pattern p1 = Pattern.compile("[a-zA-Z]");
        Pattern p2 = Pattern.compile("[0-9]");

        Matcher m1 = p1.matcher(ftitre);
        Matcher m2 = p1.matcher(flieu);
        ;
        Matcher m4 = p2.matcher(fprix);

        // vérifie les champs vides
        if (ftitre.trim().equals("") || flieu.trim().equals("")
                || fprix.trim().equals("") || fphoto.trim().equals("")) {

    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle ("warning");
    alert.setHeaderText (null);
    alert.setContentText ("Check fields !");
    alert.showAndWait ();
   
        
            JOptionPane.showMessageDialog(null, "One or more fields are empty", "Empty fields", 2);
            return false;
        } else if (Float.parseFloat(fprix) < 0) {
            JOptionPane.showMessageDialog(null, "check for price", "price", 2);
            return false;
        }

        return true;
    }

    @FXML
    private void add_location(ActionEvent event) throws SQLException, IOException {

 
//       
  if (verifierchamps() == true) {
            System.out.println("titre " + titre.getText()  + "lieu " + lieu.getText() + "prix  " + prix.getText() + " photo " + photo.getText());

               Location l = new Location(1, titre.getText(), lieu.getText(), Float.valueOf(prix.getText()), photo.getText() );
            try {
                SL.addLocation(l);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Rent ");
                alert1.setHeaderText(null);
                alert1.setContentText("Rent added ");

                alert1.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            
             try {
            Notification.sendNotification("add succeed", TrayIcon.MessageType.ERROR);
        } catch (AWTException ex) {
            Logger.getLogger(FXMLReserverController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FXMLReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }

 Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLListLocation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        }
    }

    @FXML
    private void upload_photo(ActionEvent event) {
        Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select picture");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(primary);
        String path = "C:\\wamp64\\www";
        photo.setText(file.getName());
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

     @FXML
    private void home(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLHome.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
}
