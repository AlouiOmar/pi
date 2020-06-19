/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velo;

import entities.Location;
import Services.ServiceLocation;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author nahawnd
 */
public class FXMLUpdateLocationController implements Initializable {

    private Connection cnx;

    ServiceLocation SL;

    @FXML
    private Button submit;
    @FXML
    private Button cancel;

    @FXML
    private Label labelRentUpdate;

    @FXML
    private TextField lieu;
    @FXML
    private TextField prix;
    @FXML
    private TextField titre;
    @FXML
    private Button photo;
//    @FXML
//    private TableColumn<?, ?> tcTitle;
//    @FXML
//    private TableColumn<?, ?> tcLieu;
//    @FXML
//    private TableColumn<?, ?> tcPrix;
//    @FXML
//    private TableColumn<?, ?> tcPhoto;
    private int id;
    private PreparedStatement pst;
    private ResultSet rs;
    Location l = new Location();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            SL = new ServiceLocation();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLUpdateLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            SL.getById(id);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLUpdateLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String ftitre = titre.getText();
        String flieu = lieu.getText();
        String fprix = prix.getText();
        String fphoto = photo.getText();

    }

    @FXML
    private void update(ActionEvent event) throws AWTException, MalformedURLException, FileNotFoundException, SQLException {
   
        if (l == null) {

            System.out.println("choose a rent");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Rent updated");
            alert.setHeaderText(null);
            alert.setContentText("Rent updated!");

            alert.showAndWait();
        } else {
//
//            l.setTitre(titre.getText());
//            l.setLieu(lieu.getText());
//            l.setPrix(Float.valueOf(prix.getText()));
//            l.setPhoto(photo.getText());
//            
//              try {
////               ServiceLocation SL =new ServiceLocation();
//            SL.updateLocation(l);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println("Updated successufully ");
//           
//        String title = l.getPhoto();
//        System.out.println(title);
//        FileInputStream input = new FileInputStream("C:\\wamp64\\www\\" + title + "");
//        //  Image image = new Image("C:\\wamp64\\www\\" +title+"") {};
//        // ImageView view = new ImageView(input);
//        
////        
////        Image image = new Image (input);
////        ImageView view = new ImageView();
////        photo.setPhoto(image);
//        
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Vélo enregistré avec succès.");
//        alert.setHeaderText(null);
//        alert.setContentText("Rent " + l.getTitre() + " has been modified.");
//        alert.showAndWait();
//   
//        }
//        try {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Update");
//            alert.setHeaderText(null);
//
//            Optional<ButtonType> action = alert.showAndWait();
//            if (action.get() == ButtonType.OK) {
//                // System.out.println("sup1");
//                SL.updateLocation(l);
//                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//                alert1.setTitle("Rent updated ");
//                alert1.setHeaderText(null);
//                alert1.setContentText("Rent updated ");
//                alert1.showAndWait();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            Notification.sendNotification("update succeed", TrayIcon.MessageType.ERROR);
//        } catch (AWTException | MalformedURLException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
// 
        cnx = MyConnection.getInstance().getConnection();
        String sql = "update location set titre=?,lieu=?,prix=?,photo=? where id=?";
        pst = cnx.prepareStatement(sql);
        pst.setString(1, titre.getText());
        pst.setString(2, lieu.getText());
        pst.setString(3, prix.getText());
        pst.setString(4, photo.getText());
//        pst.execute();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText(null);
        alert.setContentText("Publication ajoutée avec succées");
        alert.showAndWait();
        pst.close();
        clearFields();
//        close(t);
     
//        try {
//                SL.updateLocation(l);
//                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//                alert1.setTitle("Rent ");
//                alert1.setHeaderText(null);
//                alert1.setContentText("Rent added ");
//
//                alert1.showAndWait();
//            } catch (SQLException e) {
//                e.printStackTrace();
//                System.out.println(e.getMessage());
//            }
    }
    }
//private void close(Event e){
//        final Node n=(Node)e.getSource();
//        final  Stage s=(Stage)n.getScene().getWindow();
//        s.close();
//    }
    
  private void clearFields(){
        titre.setText(null);
        lieu.setText(null);
       prix.setText(null);
        photo.setText(null);
        
    }
  
     
    @FXML
        private void Rent_list(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLListLocation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

}
