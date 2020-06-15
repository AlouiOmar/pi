/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
  
import Services.ServiceAccessoire;
import com.mysql.jdbc.Connection;
import entities.Accessoire;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raef
 */
public class AfficherAccessoireController implements Initializable {

    
     Connection cnx;
    ServiceAccessoire sa;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_afficher;
    @FXML
    private TableView<Accessoire> tf_table;
    @FXML
    private TableColumn<Accessoire, String> tf_nom;
    @FXML
    private TableColumn<Accessoire, String> tf_type;
    @FXML
    private TableColumn<Accessoire, String> tf_marque;
    
    @FXML
    private TableColumn<Accessoire, String> tf_couleur;
    @FXML
    private TableColumn<Accessoire, Float> tf_prix;
    @FXML
    private TableColumn<Accessoire, Date> tf_date;
    @FXML
    private TableColumn<Accessoire, String> tf_photo;
    @FXML
    private ImageView imgaffiche;
    @FXML
    private ImageView imgsupprimer;
    @FXML
    private Button refrech_price_;
    @FXML
    private TextField f11;
    @FXML
    private TextField f22;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       try {
            sa = new ServiceAccessoire();
        

           ArrayList<Accessoire> la;
       
            la = (ArrayList<Accessoire>) sa.getAccessoires();
            ObservableList<Accessoire> data = FXCollections.observableArrayList(la);
            tf_nom.setCellValueFactory(new PropertyValueFactory<>("nom_P"));
            tf_type.setCellValueFactory(new PropertyValueFactory<>("type_P"));
            tf_marque.setCellValueFactory(new PropertyValueFactory<>("marque_P"));
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
    private void Supprimer_accessoire(ActionEvent event) throws SQLException {
        
        Accessoire a=tf_table.getSelectionModel().getSelectedItem();
        
        if(a==null){
        
           System.out.println("Veillez choisir un accessoire");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("choose an accessory");
            alert.setHeaderText(null);
            alert.setContentText("please choose the accessory to delete !");

            alert.showAndWait();
     
        }else {
        
         sa=new ServiceAccessoire();
        String nom_P=a.getNom_P();
        
         try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Remove accessory..");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete! " + a.getNom_P());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    sa.deleteAccessoire(a);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("DELETE ACCESSORY");
                    alert1.setHeaderText(null);
                    alert1.setContentText("The accessory is removed");

                    alert1.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        loadData();
        refresh_Accessoire();
    }}

    @FXML
    private void Afficher_accessoire(ActionEvent event) throws IOException {
        
        Accessoire a = tf_table.getSelectionModel().getSelectedItem();
        if (a == null) {
            System.out.println("choose accessory");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning ");
            alert.setHeaderText("Please choose an accessory");
            alert.setContentText("Please choose an accessory");

            alert.showAndWait();
    }
      else {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/gui/AccessoireRechercher.fxml"));
            Scene scene = new Scene(loader.load());
            AccessoireRechercherController ct = loader.getController();
             ct.setAccessoire(a);

            Stage stageAff = new Stage();
            stageAff.setScene(scene);

            stageAff.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        }
        
    }
    
    
    
    
     private void refresh_Accessoire() throws SQLException {
         try {
            sa = new ServiceAccessoire();
        

           ArrayList<Accessoire> la;
       
            la = (ArrayList<Accessoire>) sa.getAccessoires();
            ObservableList<Accessoire> data = FXCollections.observableArrayList(la);
            tf_nom.setCellValueFactory(new PropertyValueFactory<>("nom_P"));
            tf_type.setCellValueFactory(new PropertyValueFactory<>("type_P"));
            tf_marque.setCellValueFactory(new PropertyValueFactory<>("marque_P"));
            tf_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur_P"));
            tf_prix.setCellValueFactory(new PropertyValueFactory<>("prix_P"));
            tf_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            tf_photo.setCellValueFactory(new PropertyValueFactory<>("photo_P"));
            tf_table.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVeloController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
     
   public void loadData() throws SQLException{
    ObservableList<Accessoire> dataaa = null;

    dataaa = FXCollections.observableArrayList(new ServiceAccessoire().getAccessoires());
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
            sa = new ServiceAccessoire();
        

           ArrayList<Accessoire> la;
       
            la = (ArrayList<Accessoire>) sa.FiltrerAccessoireByprix(f1, f2);
            ObservableList<Accessoire> data = FXCollections.observableArrayList(la);
            tf_nom.setCellValueFactory(new PropertyValueFactory<>("nom_P"));
            tf_type.setCellValueFactory(new PropertyValueFactory<>("type_P"));
            tf_marque.setCellValueFactory(new PropertyValueFactory<>("marque_P"));
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
