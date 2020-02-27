/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.ServiceRechange;
import com.mysql.jdbc.Connection;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.MaConnection;
import entities.*;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raef
 */
public class AfficherRechangeController implements Initializable {
    Connection cnx;
    ServiceRechange sr;
    @FXML
    private Button btn_afficher;
    @FXML
    private TableView<Rechange> tf_table;
    @FXML
    private TableColumn<Rechange, String> tf_nom;
    @FXML
    private TableColumn<Rechange, String> tf_type;
    @FXML
    private TableColumn<Rechange, String> tf_marque;
    @FXML
    private TableColumn<Rechange, Float> tf_prix;
    @FXML
    private TableColumn<Rechange, Date> tf_date;
    @FXML
    private TableColumn<Rechange, String> tf_photo;
    @FXML
    private Button btn_supprimer;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private TextField f11;
    @FXML
    private TextField f22;
    @FXML
    private Button refrech_price_;
    
  
  
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        try {
            sr = new ServiceRechange();
        

           ArrayList<Rechange> lr;
       
            lr = (ArrayList<Rechange>) sr.getRechanges();
            ObservableList<Rechange> data = FXCollections.observableArrayList(lr);
            tf_nom.setCellValueFactory(new PropertyValueFactory<>("nom_P"));
            tf_type.setCellValueFactory(new PropertyValueFactory<>("type_P"));
            tf_marque.setCellValueFactory(new PropertyValueFactory<>("marque_P"));
            tf_prix.setCellValueFactory(new PropertyValueFactory<>("prix_P"));
            tf_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            tf_photo.setCellValueFactory(new PropertyValueFactory<>("photo_P"));
            tf_table.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVeloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    @FXML
    private void Afficher_Rechange(ActionEvent event) throws IOException {
        
        
        Rechange r = tf_table.getSelectionModel().getSelectedItem();
        if (r == null) {
            System.out.println("choose spart part");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning ");
            alert.setHeaderText("Please spart part");
            alert.setContentText("Please spart part");

            alert.showAndWait();
    }
        
        else {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/gui/RechangeRechercher.fxml"));
            Scene scene = new Scene(loader.load());
            RechangeRechercherController ct = loader.getController();
             ct.setRechange(r);

            Stage stageAff = new Stage();
            stageAff.setScene(scene);

            stageAff.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        }
    
}

    @FXML
    private void Supprimer_Rechnage(ActionEvent event) throws SQLException {
        
        
        Rechange r=tf_table.getSelectionModel().getSelectedItem();
        
        if(r==null){
        
           System.out.println("Veillez choisir une pièce de réchange");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("choose spare part");
            alert.setHeaderText(null);
            alert.setContentText("please choose the spare part to delete !");

            alert.showAndWait();
     
        }else {
        
         sr=new ServiceRechange();
        String nom_P=r.getNom_P();
        
         try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Remove spare part..");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete! " + r.getNom_P());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    sr.deleteRechange(r);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("DELETE SPART PART");
                    alert1.setHeaderText(null);
                    alert1.setContentText("The spare part is removed");

                    alert1.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
         loadData();
         refresh_Rechange();
        }
        
 
        
        
    }
    
    
    public void loadData() throws SQLException{
    ObservableList<Rechange> dataa = null;

    dataa = FXCollections.observableArrayList(new ServiceRechange().getRechanges());
    }
    
    
    
     private void refresh_Rechange() throws SQLException {
         try {
            sr = new ServiceRechange();
        

           ArrayList<Rechange> lr;
       
            lr = (ArrayList<Rechange>) sr.getRechanges();
            ObservableList<Rechange> data = FXCollections.observableArrayList(lr);
            tf_nom.setCellValueFactory(new PropertyValueFactory<>("nom_P"));
            tf_type.setCellValueFactory(new PropertyValueFactory<>("type_P"));
            tf_marque.setCellValueFactory(new PropertyValueFactory<>("marque_P"));
            tf_prix.setCellValueFactory(new PropertyValueFactory<>("prix_P"));
            tf_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            tf_photo.setCellValueFactory(new PropertyValueFactory<>("photo_P"));
            tf_table.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVeloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
     
     
     
     
     
     
     
    @FXML
    private void refrech_price_action(ActionEvent event) {
        try {
            refrech_price(Float.parseFloat(f11.getText()), Float.parseFloat(f22.getText()));

        } catch (Exception e) {
        }
    }
      
        
    
    
    
    private void refrech_price(float f1 ,float f2) {
        
     try {
            sr = new ServiceRechange();
        

           ArrayList<Rechange> lr;
       
            lr = (ArrayList<Rechange>) sr.FiltrerRechangeByprix(f1, f2);
            ObservableList<Rechange> data = FXCollections.observableArrayList(lr);
            tf_nom.setCellValueFactory(new PropertyValueFactory<>("nom_P"));
            tf_type.setCellValueFactory(new PropertyValueFactory<>("type_P"));
            tf_marque.setCellValueFactory(new PropertyValueFactory<>("marque_P"));
            tf_prix.setCellValueFactory(new PropertyValueFactory<>("prix_P"));
            tf_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            tf_photo.setCellValueFactory(new PropertyValueFactory<>("photo_P"));
            tf_table.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVeloController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    
    
}