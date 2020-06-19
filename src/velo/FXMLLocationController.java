/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velo;

import entities.Location;
import Services.ServiceLocation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
      try {
          SL = new ServiceLocation();
          
//      Location l = new Location(1, titre.getText(), lieu.getText(),Float.valueOf(prix.getText()),lieu.getText());
//        try {
//            SL.getById(id);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
      } catch (SQLException ex) {
          Logger.getLogger(FXMLLocationController.class.getName()).log(Level.SEVERE, null, ex);
      }
        
    }    

    @FXML
    private void update(ActionEvent event) throws IOException {
         Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLUpdateLocation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void supp (ActionEvent event) throws IOException, SQLException {
//        Location l = tableview.getSelectionModel().getSelectedItem();
//
//        if (l == null) {
//            System.out.println("choose a rent");
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("choose a rent");
//            alert.setHeaderText(null);
//            alert.setContentText("please choose the rent to delete !");
//            alert.showAndWait();
//        } else {
//            SL = new ServiceLocation();
//            String titre = l.getTitre();
//
//            try {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Remove rent..");
//                alert.setHeaderText(null);
//                alert.setContentText("Are you sure you want to delete! " + l.getTitre());
//                Optional<ButtonType> action = alert.showAndWait();
//                if (action.get() == ButtonType.OK) {
//                    // System.out.println("sup1");
//                    SL.deleteLocation(l);
//                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//                    alert1.setTitle("DELETE Rent");
//                    alert1.setHeaderText(null);
//                    alert1.setContentText("The rent is removed");
//                    alert1.showAndWait();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            loadData();
////            refresh_Location();
//        }
    }
    public void loadData() throws SQLException {
        ObservableList<Location> dataaa = null;
        dataaa = FXCollections.observableArrayList(new ServiceLocation().getLocations());
    }
}
