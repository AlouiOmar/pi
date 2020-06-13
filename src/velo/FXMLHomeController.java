/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velo;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nahawnd
 */
public class FXMLHomeController implements Initializable {

    @FXML
    private Label labelRentMenu;
    @FXML
    private Button Add_Rent;
    @FXML
    private Button Rent_list;
    @FXML
    private Button search;
    @FXML
    private Button Rent;
    @FXML
    private AnchorPane AnchroPane;
    @FXML
    private Button reservation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Add_Location(ActionEvent event) throws IOException {
         Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLAddLocation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void ListLocation(ActionEvent event) throws IOException {
         Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLListLocation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void rechercher(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLSearch.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void rerserver(ActionEvent event) throws IOException {
         Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLReserver.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void list_reservation(ActionEvent event) throws IOException {
            Parent tableview = FXMLLoader.load(getClass().getResource("/GUI/FXMLListReservation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
}
