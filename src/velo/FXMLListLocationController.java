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
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nahawnd
 */
public class FXMLListLocationController implements Initializable {

    private Connection cnx;
    ServiceLocation SL;

    @FXML
    private Label labelRentList;

    @FXML
    private TableView<Location> tableview;

    @FXML
    private TableColumn<?, ?> tcTittre;

    @FXML
    private TableColumn<?, ?> tcPrix;
    @FXML
    private Button afficher;
    @FXML
    private Button delete;
    @FXML
    private Button Rent;
    @FXML
    private Button update;
    @FXML
    private Button home;
    @FXML
    private TableColumn<?, ?> tcLieu;
    @FXML
    private TableColumn<?, ?> tcPhoto;
    private TableColumn<?, ?> tcDateCreation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            SL = new ServiceLocation();

            ArrayList<Location> ll;
            ll = (ArrayList<Location>) SL.getLocations();
            ObservableList<Location> data = FXCollections.observableArrayList(ll);
            tcTittre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tcLieu.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
            tcPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            tcPhoto.setCellValueFactory(new PropertyValueFactory<>("Photo"));
//            tcDateCreation.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
            tableview.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLListLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void affiche_location(ActionEvent event) throws IOException {

        Location l = tableview.getSelectionModel().getSelectedItem();
        if (l == null) {
            System.out.println("choose a rent");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning ");
            alert.setHeaderText("Please choose a  rent");
            alert.showAndWait();
        }
        Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLLocation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();

    }

    private void refresh_Location() throws SQLException {
        try {
            SL = new ServiceLocation();

            ArrayList<Location> la;
            la = (ArrayList<Location>) SL.getLocations();
            ObservableList<Location> data = FXCollections.observableArrayList(la);
            tcTittre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tcLieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            tcPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            tcPhoto.setCellValueFactory(new PropertyValueFactory<>("Photo"));
            tcDateCreation.setCellValueFactory(new PropertyValueFactory<>("dateDeb"));
            tableview.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLListLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supp(ActionEvent event) throws SQLException {
        Location l = tableview.getSelectionModel().getSelectedItem();

        if (l == null) {
            System.out.println("choose a rent");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("choose a rent");
            alert.setHeaderText(null);
            alert.setContentText("veuillez selectionner une location !");
            alert.showAndWait();
        } else {
            SL = new ServiceLocation();
            String titre = l.getTitre();

            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Remove rent..");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete! " + l.getTitre());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    SL.deleteLocation(l);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("DELETE Rent");
                    alert1.setHeaderText(null);
                    alert1.setContentText("The rent is removed");
                    alert1.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            loadData();
            refresh_Location();
        }
    }

    public void loadData() throws SQLException {
        ObservableList<Location> dataaa = null;
        dataaa = FXCollections.observableArrayList(new ServiceLocation().getLocations());
    }

    @FXML
    private void add_Rent(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLReserver.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void update(ActionEvent event) throws IOException {
        Location l = tableview.getSelectionModel().getSelectedItem();
        if (l == null) {
            System.out.println("choose a rent");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning ");
            alert.setHeaderText("Please choose a  rent");
            alert.showAndWait();
        }
        Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLUpdateLocation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void home(ActionEvent event) throws IOException{
 
              
         Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLHomr.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    

}
