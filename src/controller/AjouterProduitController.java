/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Velo;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Services.ServiceVelo;
import com.twilio.Twilio;
import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.AUTH_TOKEN;
import com.twilio.rest.api.v2010.account.Message;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import utils.MaConnection;

/**
 * FXML Controller class
 *
 * @author Raef
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_cat;
    @FXML
    private TextField tf_marque;
    @FXML
    private TextField tf_couleur;
    @FXML
    private TextField tf_prix;
    @FXML
    private Button tf_photo;
    @FXML
    private Button tf_ajouter;
    @FXML
    private Button tf_nettoyer;
    @FXML
    private TextField tf_type;
    @FXML
    private DatePicker tf_date;
    public Connection cnx;
    @FXML
    private ImageView add;
    @FXML
    private ImageView clear;
    @FXML
    private ImageView attache;
    
     private  String ACCOUNT_SID= "AC4f79c0e150ee4006a4ce3aa4fb7590e1";
    private  String AUTH_TOKEN = "ccebe5e123b2f145fc5598d8151685e5";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
//        try {
//        } catch (SQLException ex) {
//            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
      
        
    }  
    
     public AjouterProduitController() throws SQLException {
      
    }

    @FXML
    private void ConfirmerAjout(ActionEvent event) throws SQLException {
       if (tf_nom.getText().length() == 0 || tf_type.getText().length() == 0 || tf_marque.getText().length() == 0 || tf_couleur.getText().length() == 0 || tf_cat.getText().length() == 0 || tf_prix.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("some field are empty !!");
            alert.showAndWait();  }
            if (Float.parseFloat(tf_prix.getText())<0){
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("veuillez saisir un valeur positif !!");
            alert3.showAndWait();  
            }
      
        
        else{
        Velo v= new Velo(1, 2, tf_nom.getText(), tf_type.getText(),tf_marque.getText(), tf_cat.getText(), tf_couleur.getText(), Float.valueOf(tf_prix.getText()),tf_photo.getText(), Date.valueOf(tf_date.getValue()));
       
        
        try {
                             ServiceVelo sv= new ServiceVelo();

            sv.addVelo(v);
            
            
   
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Ajout terminé");
    alert.setHeaderText(null);
   alert.setContentText("La vélo : " +v.getNom_P()+"  est ajoutée avec succès ");
    alert.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
                 
  
        System.out.println("ajout terminé");
        }
 
                }
     
    }

    @FXML
    private void attacher_photo(ActionEvent event) {
        
        Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(primary);
        String path = "C:\\wamp64\\www";
        tf_photo.setText(file.getName());
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    @FXML
    private void clear_fields(ActionEvent event) {
        
        tf_nom.clear();
        tf_type.clear();
        tf_marque.clear();
        tf_cat.clear();
        tf_couleur.clear();
        tf_prix.clear();

        System.out.println(" cleear test");
    }
    
}
