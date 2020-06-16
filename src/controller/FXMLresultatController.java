/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Location;
import Services.ServiceLocation;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import controller.FXMLListLocationController;

/**
 * FXML Controller class
 *
 * @author nahawnd
 */
public class FXMLresultatController implements Initializable {

    @FXML
    private Label labelRentList;
    @FXML
    private TableView<?> tableview;
    @FXML
    private TableColumn<?, ?> tcTittre;
    @FXML
    private TableColumn<?, ?> tcPrix;
    @FXML
    private TableColumn<?, ?> tcLieu;
    @FXML
    private TableColumn<?, ?> tcPhoto;
 private Connection cnx;
    ServiceLocation SL;
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
//           
            tcLieu.setCellValueFactory(new PropertyValueFactory<>("Lieu"));
            tcPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            tcPhoto.setCellValueFactory(new PropertyValueFactory<>("Photo"));
//            tableview.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLListLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
}
