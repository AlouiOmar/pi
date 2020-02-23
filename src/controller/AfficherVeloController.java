/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mysql.jdbc.Connection;

import Services.ServiceVelo;
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
public class AfficherVeloController implements Initializable {
    Connection cnx;
    ServiceVelo sv;
    @FXML
    private Button btn_afficher;
    @FXML
    private TableView<Velo> tf_table;
    @FXML
    private TableColumn<Velo, String> tf_nom;
    @FXML
    private TableColumn<Velo, String> tf_type;
    @FXML
    private TableColumn<Velo, String> tf_marque;
    @FXML
    private TableColumn<Velo, String> tf_cat;
    @FXML
    private TableColumn<Velo, String> tf_couleur;
    @FXML
    private TableColumn<Velo, Float> tf_prix;
    @FXML
    private TableColumn<Velo, Date> tf_date;
    @FXML
    private TableColumn<Velo, String> tf_photo;
    @FXML
    private Button btn_supprimer;
    @FXML
    private ImageView img_affiche;
    @FXML
    private ImageView supp_img;
    @FXML
    private Button refrech_price_;
    @FXML
    private TextField f11;
    @FXML
    private TextField f22;
    
  
  
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        try {
            sv = new ServiceVelo();
        

           ArrayList<Velo> lv;
       
            lv = (ArrayList<Velo>) sv.getVelos();
            ObservableList<Velo> data = FXCollections.observableArrayList(lv);
            tf_nom.setCellValueFactory(new PropertyValueFactory<>("nom_P"));
            tf_type.setCellValueFactory(new PropertyValueFactory<>("type_P"));
            tf_marque.setCellValueFactory(new PropertyValueFactory<>("marque_P"));
            tf_cat.setCellValueFactory(new PropertyValueFactory<>("categorie_P"));
            tf_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur_P"));
            tf_prix.setCellValueFactory(new PropertyValueFactory<>("prix_P"));
            tf_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            tf_photo.setCellValueFactory(new PropertyValueFactory<>("photo_P"));
            tf_table.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVeloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Supprimer_velo(ActionEvent event)throws SQLException, IOException {
        
        
        Velo v=tf_table.getSelectionModel().getSelectedItem();
        
        if(v==null){
        
           System.out.println("Veillez choisir un vélo");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("choose velo");
            alert.setHeaderText(null);
            alert.setContentText("please choose the bike to delete !");

            alert.showAndWait();
     
        }else {
        
        ServiceVelo sv=new ServiceVelo();
        String nom_P = v.getNom_P();
        
         try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Remove plant..");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete! " + v.getNom_P());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    sv.deleteVelo(v);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("DELETE VELO");
                    alert1.setHeaderText(null);
                    alert1.setContentText("the bike is removed");

                    alert1.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
         loadData();
         refresh_velo();
        }
        
 
        
        
    }
    
    
    public void loadData(){
    ObservableList<Velo> data = null;

        try {
            data = FXCollections.observableArrayList(new ServiceVelo().getVelos());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
     private void refresh_velo() throws SQLException {
         try {
            sv = new ServiceVelo();
        

           ArrayList<Velo> lv;
       
            lv = (ArrayList<Velo>) sv.getVelos();
            ObservableList<Velo> data = FXCollections.observableArrayList(lv);
            tf_nom.setCellValueFactory(new PropertyValueFactory<>("nom_P"));
            tf_type.setCellValueFactory(new PropertyValueFactory<>("type_P"));
            tf_marque.setCellValueFactory(new PropertyValueFactory<>("marque_P"));
            tf_cat.setCellValueFactory(new PropertyValueFactory<>("categorie_P"));
            tf_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur_P"));
            tf_prix.setCellValueFactory(new PropertyValueFactory<>("prix_P"));
            tf_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            tf_photo.setCellValueFactory(new PropertyValueFactory<>("photo_P"));
            tf_table.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVeloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Afficher_velo(ActionEvent event) throws IOException {
        
        
        Velo v = tf_table.getSelectionModel().getSelectedItem();
        if (v == null) {
            System.out.println("choose bike");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning ");
            alert.setHeaderText("Please choose bike");
            alert.setContentText("Please choose bike");

            alert.showAndWait();
    }
       else {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../gui/VeloRechercher.fxml"));
            Scene scene = new Scene(loader.load());
            VeloRechercherController ct = loader.getController();
            ct.setVelo(v);

            Stage stageAff = new Stage();
            stageAff.setScene(scene);

            stageAff.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

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
            sv = new ServiceVelo();
        

           ArrayList<Velo> lv;
       
            lv = (ArrayList<Velo>) sv.FiltrerVeloByprix(f1, f2);
            ObservableList<Velo> data = FXCollections.observableArrayList(lv);
            tf_nom.setCellValueFactory(new PropertyValueFactory<>("nom_P"));
            tf_type.setCellValueFactory(new PropertyValueFactory<>("type_P"));
            tf_marque.setCellValueFactory(new PropertyValueFactory<>("marque_P"));
            tf_cat.setCellValueFactory(new PropertyValueFactory<>("categorie_P"));
            tf_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur_P"));
            tf_prix.setCellValueFactory(new PropertyValueFactory<>("prix_P"));
            tf_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            tf_photo.setCellValueFactory(new PropertyValueFactory<>("photo_P"));
            tf_table.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVeloController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    
    
}