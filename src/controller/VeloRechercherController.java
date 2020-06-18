/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.ServiceVelo;
import com.twilio.Twilio;
import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.AUTH_TOKEN;
import com.twilio.rest.api.v2010.account.Message;
import entities.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Raef
 */
public class VeloRechercherController implements Initializable {

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
    private Button tf_modifier;
    private TextField tf_type;
    private DatePicker tf_date;
    @FXML
    private ImageView tf_image_view;
    @FXML
    private Button retour;

    Produit v;
    @FXML
    private ImageView img_idd;
    @FXML
    private Button tf_PDf;
    @FXML
    private AnchorPane ancho_ID;
    @FXML
    private ImageView id_pdf;
    @FXML
    private TextField tf_tel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Platform.runLater(() -> {

            System.out.println(v);
            tf_nom.setText(v.getNom_P());
            tf_marque.setText(v.getMarque_P());
            tf_cat.setText(v.getCategorie_P());
            tf_couleur.setText(v.getCouleur_P());
            tf_prix.setText(String.valueOf(v.getPrix_P()));
            tf_photo.setText(v.getPhoto_P());
tf_tel.setText(String.valueOf(v.getTel()));
            FileInputStream input;
            try {
                input = new FileInputStream("C:\\\\wamp64\\\\www\\\\PiSymfony\\\\web\\\\public\\\\uploads\\\\" + v.getPhoto_P() + "");
                Image image = new Image(input);
                tf_image_view.setImage(image);
            } catch (FileNotFoundException ex) {
                System.out.println("error");
            }

        });

    }

    @FXML
    private void ModifierVelo(ActionEvent event) throws SQLException, FileNotFoundException {
        Produit vel = new Produit();
        if (vel == null) {

            System.out.println("choisir un Velo");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modify bike");
            alert.setHeaderText(null);
            alert.setContentText("the bike is not modified !");

            alert.showAndWait();
        } else {

            vel.setNom_P(tf_nom.getText());
            vel.setMarque_P(tf_marque.getText());
            vel.setCategorie_P(tf_cat.getText());
            vel.setCouleur_P(tf_couleur.getText());
            vel.setPrix_P(Float.valueOf(tf_prix.getText()));
            vel.setPhoto_P(tf_photo.getText());
            vel.setTel(Integer.valueOf(tf_tel.getText()));
              try {
               ServiceVelo sv=new ServiceVelo();
            sv.UpdateVelo(vel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
           

        System.out.println("modification terminé");
           
           String title = v.getPhoto_P();
        System.out.println(title);
        FileInputStream input = new FileInputStream("C:\\wamp64\\www\\PiSymfony\\web\\public\\uploads\\" + title + "");
        //  Image image = new Image("C:\\wamp64\\www\\" +title+"") {};
        // ImageView view = new ImageView(input);
        Image image = new Image(input);
        ImageView view = new ImageView();
        tf_image_view.setImage(image);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vélo enregistré avec succès.");
        alert.setHeaderText(null);
        alert.setContentText("Le produit " + v.getNom_P() + " has been modified.");
        alert.showAndWait();
             
      
         
        }
    }


    @FXML
    private void RetourAfficherVelo(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("/gui/Afficher.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void modifier_photo(ActionEvent event) {

        Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(primary);
        String path = "C:\\wamp64\\www\\PiSymfony\\web\\public\\uploads";
        tf_photo.setText(file.getName());
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setVelo(Produit vl) {
        this.v = vl;
    }

    
    
    
    void pdf() {
 System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.ancho_ID;
   
    
           job.printPage(root);
           job.endJob();
            
       

  }
    }

    @FXML
    private void PDFAction(ActionEvent event) {
        
         pdf();
    }
    
    
    
    
    
    
    
    
}