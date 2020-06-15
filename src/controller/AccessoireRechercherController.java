/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.ServiceAccessoire;
import com.twilio.Twilio;
import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.AUTH_TOKEN;
import com.twilio.http.Response;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.Accessoire;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Map;
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
public class AccessoireRechercherController implements Initializable {

    @FXML
    private TextField tf_nom;
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
    @FXML
    private Button tf_partager;
    @FXML
    private ImageView tf_image_view;
    @FXML
    private Button retour;
     Accessoire a;
     Facebook facebook;
    @FXML
    private ImageView att_img;
    @FXML
    private ImageView img_modifier;
    @FXML
    private ImageView back_img;
    @FXML
    private AnchorPane ancho_id;
    @FXML
    private ImageView img_pdf;
     
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       Platform.runLater(() -> {

            System.out.println(a);
            tf_nom.setText(a.getNom_P());
            tf_marque.setText(a.getMarque_P());
            tf_couleur.setText(a.getCouleur_P());
            tf_prix.setText(String.valueOf(a.getPrix_P()));
            tf_photo.setText(a.getPhoto_P());

            FileInputStream input;
            try {
                input = new FileInputStream("C:\\\\wamp64\\\\www\\\\" + a.getPhoto_P() + "");
                Image image = new Image(input);
                tf_image_view.setImage(image);
            } catch (FileNotFoundException ex) {
                System.out.println("error");
            }

        });
  
        
        
        
    }    

    @FXML
    private void modifier_photo(ActionEvent event) {
        
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
    private void ModifierAccessoire(ActionEvent event) throws FileNotFoundException {
        
         Accessoire acc = new Accessoire();
        if (acc == null) {

            System.out.println("choisir un accessoire");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modify accessory");
            alert.setHeaderText(null);
            alert.setContentText("the accessory is not modified !");

            alert.showAndWait();
        } else {

            acc.setNom_P(tf_nom.getText());
            acc.setMarque_P(tf_marque.getText());
            acc.setCouleur_P(tf_couleur.getText());
            acc.setPrix_P(Float.valueOf(tf_prix.getText()));
            acc.setPhoto_P(tf_photo.getText());
            
              try {
               ServiceAccessoire sv=new ServiceAccessoire();
            sv.UpdateAccessoire(acc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
           

        System.out.println("Modification terminé");
           
           String title = a.getPhoto_P();
        System.out.println(title);
        FileInputStream input = new FileInputStream("C:\\wamp64\\www\\" + title + "");
        //  Image image = new Image("C:\\wamp64\\www\\" +title+"") {};
        // ImageView view = new ImageView(input);
        Image image = new Image(input);
        ImageView view = new ImageView();
        tf_image_view.setImage(image);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Accessoire enregistré avec succès.");
        alert.setHeaderText(null);
        alert.setContentText("Le produit " + a.getNom_P() + " has been modified.");
        alert.showAndWait();
             
      
         
        }
        
    }
    
    
    
    

    private void partager(ActionEvent event) {
       
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+21629704752"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "The product  was created successfully.")
                .create();

        System.out.println(message.getSid());
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Please get refer to your WhatsApp!");
        alert.setHeaderText("Please get refer to your WhatsApp!");
        alert.setContentText("The product was created successfully ");

        alert.showAndWait();

        
        
        
        
}
    

    
    
     public void setAccessoire(Accessoire al) {
        this.a = al;
    }

    @FXML
    private void RetourAfficherAccessoire(ActionEvent event) throws IOException{
        
         Parent root = FXMLLoader.load(getClass().getResource("/gui/Afficher.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();

        
        
        
    }
    
   
    @FXML
    private void partager_Pdf(ActionEvent event) {
        
        pdf();
    }
    
    
     void pdf() {
 System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.ancho_id;
   
    
           job.printPage(root);
           job.endJob();
            
       

  }
    }
    
    
    
}
