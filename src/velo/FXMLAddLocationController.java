/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velo;

import entities.Location;
import Services.ServiceLocation;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private DatePicker dateCreation;
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
    
//    ServiceLocation SL = new ServiceLocation();
ServiceLocation SL;
    Location l = new Location();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
            ServiceLocation SL = new ServiceLocation();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAddLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
           String ftitre = titre.getText();
        String flieu = lieu.getText();
        String fprix = prix.getText();
        String fphoto = photo.getText();
    }

    @FXML
    private void add_location(ActionEvent event) throws SQLException, FileNotFoundException {
////     if (verifierchamps()){
        System.out.println("titre " + titre.getText()
                + "lieu " + lieu.getText()
                + "prix" + prix.getText()
                + " photo " + photo.getText() );
//                + "Date creation" + dateCreation.getValue()
                
        Location l = new Location(
                titre.getText(),
                lieu.getText(),
                Float.valueOf(prix.getText())
//                Date.valueOf(dateCreation.getValue()),
//                photo.getText()
        );
      try {
            SL.addLocation(l);
      } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        System.out.println("ajout terminé");

    try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajout");
                alert.setHeaderText(null);
                alert.setContentText("publication ajoutée " + l.getTitre());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                   Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLHome.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
//                    alert1.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLHome.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void upload_photo(ActionEvent event
    ) {
        Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select picture");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(primary);
        String path = "C:\\wamp64\\www";
//        photo.setText(file.getName());
        if (file != null) {
            try {
//                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean verifierchamps() throws SQLException {
        String ftitre = titre.getText();
//       Date fdateCreation = dateCreation.getText();
        String flieu = lieu.getText();
        String fprix = prix.getText();
        String fphoto = photo.getText();

        ServiceLocation l = new ServiceLocation();
        List<Location> k = l.getLocations();
        Pattern p = Pattern.compile("[a-zA-Z][a-zA-Z0-9._]*@[a-zA-Z]+([.][a-zA-Z]+)+");
        Pattern p1 = Pattern.compile("[a-zA-Z]");
        Pattern p3 = Pattern.compile("[0-9]");

        Matcher m1 = p1.matcher(ftitre);
        Matcher m2 = p1.matcher(flieu);
        Matcher m3 = p3.matcher(fprix);
        Matcher m4 = p3.matcher(fphoto);
//        Matcher m5 = p3.matcher(fdateCreation);

//         vérifie les champs vides
        if (ftitre.trim().equals("")
                || flieu.trim().equals("")
                || fprix.trim().equals("")
                || fphoto.trim().equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Check fields !");
            alert.showAndWait();
            return false;
        }
        return true;

    }
}
