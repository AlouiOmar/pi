/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velo;

import entities.Reservation;
import Services.ServiceReservation;
import java.net.URL;
import java.sql.Connection;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author nahawnd
 */
public class FXMLListReservationController implements Initializable {

    private Connection cnx;
    ServiceReservation SR;
    @FXML
    private Label labelReservationlist;
    @FXML
    private TableView<Reservation> tableview;
    @FXML
    private TableColumn<?, ?> tcTitre;
    @FXML
    private TableColumn<?, ?> tcDateDeb;
    @FXML
    private TableColumn<?, ?> tcDateEnd;
    @FXML
    private Button reject;
    @FXML
    private Button accept;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            SR = new ServiceReservation();

            ArrayList<Reservation> ll;
            ll = (ArrayList<Reservation>) SR.getLouers();
            ObservableList<Reservation> data = FXCollections.observableArrayList(ll);
            tcTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
//            tcDateDeb.setCellValueFactory(new PropertyValueFactory<>("dateDeb"));
//            tcDateEnd.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            tableview.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refuser(ActionEvent event) throws SQLException {
        Reservation r = tableview.getSelectionModel().getSelectedItem();

        if (r == null) {
            System.out.println("choose a reservation");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("choose a reservation");
            alert.setHeaderText(null);
            alert.setContentText("Choose a reservation to reject !");
            alert.showAndWait();
        } else {
            SR = new ServiceReservation();
            String titre = r.getTitre();

            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Reject reservation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to decline it! " + r.getTitre());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    SR.deleteLouer(r);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("DELETE Reservation");
                    alert1.setHeaderText(null);
                    alert1.setContentText("The reservation is removed");
                    alert1.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            loadData();
            refresh_Reservation();
        }
    }

    public void loadData() throws SQLException {
        ObservableList<Reservation> dataaa = null;
        dataaa = FXCollections.observableArrayList(new ServiceReservation().getLouers());
    }

    private void refresh_Reservation() throws SQLException {
        try {
            SR = new ServiceReservation();

            ArrayList<Reservation> lr;
            lr = (ArrayList<Reservation>) SR.getLouers();
            ObservableList<Reservation> data = FXCollections.observableArrayList(lr);
            tcTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tcDateDeb.setCellValueFactory(new PropertyValueFactory<>("dateDeb"));
            tcDateEnd.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            tableview.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLListReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void accepter(ActionEvent event)  throws SQLException {
         Reservation r = tableview.getSelectionModel().getSelectedItem();

        if (r == null) {
            System.out.println("Alert");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("Choisir une reservation !");
            alert.showAndWait();
        } else {
            SR = new ServiceReservation();
            String titre = r.getTitre();

    }
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
 Optional<ButtonType> action = alert.showAndWait();
      if (action.get() == ButtonType.OK) {
                    SR.deleteLouer(r);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succées");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Demande de reservation acceptée");
                    alert1.showAndWait();
                }
         
        
        
        
        
        
        
    }
}
