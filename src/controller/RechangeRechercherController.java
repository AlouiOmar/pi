/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.ServiceRechange;
import com.twilio.Twilio;
import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.AUTH_TOKEN;
import com.twilio.http.Response;
import com.twilio.rest.api.v2010.account.Message;
import entities.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Map;
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
public class RechangeRechercherController implements Initializable {

    
      public static final String ACCOUNT_SID = "AC4f79c0e150ee4006a4ce3aa4fb7590e1";
    public static final String AUTH_TOKEN = "ccebe5e123b2f145fc5598d8151685e5";

    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_marque;
    @FXML
    private TextField tf_prix;
    @FXML
    private Button tf_photo;
    @FXML
    private Button tf_modifier;
    @FXML
    private ImageView tf_image_view;
    @FXML
    private Button retour;
    Produit r;
    private AnchorPane ancho_id;
    @FXML
    private AnchorPane ancho_ID;
    @FXML
    private TextField tf_cat;
    @FXML
    private TextField tf_couleur;
    @FXML
    private TextField tf_tel;
    @FXML
    private Button tf_PDf;
    @FXML
    private ImageView img_idd;
    @FXML
    private ImageView id_pdf;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           Platform.runLater(() -> {

            System.out.println(r);
            tf_nom.setText(r.getNom_P());
            tf_marque.setText(r.getMarque_P());
            tf_cat.setText(r.getCategorie_P());
            tf_couleur.setText(r.getCouleur_P());
            tf_prix.setText(String.valueOf(r.getPrix_P()));
            tf_photo.setText(r.getPhoto_P());
tf_tel.setText(String.valueOf(r.getTel()));
            FileInputStream input;
            try {
                input = new FileInputStream("C:\\\\wamp64\\\\www\\\\PiSymfony\\\\web\\\\public\\\\uploads\\\\" + r.getPhoto_P() + "");
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

    
    
   
    @FXML
    private void ModifierVelo(ActionEvent event) throws FileNotFoundException {
           Produit rech = new Produit();
        if (rech == null) {

            System.out.println("choisir une pièce de rechange");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modify spare part");
            alert.setHeaderText(null);
            alert.setContentText("the spare part is not modified !");

            alert.showAndWait();
        } else {

            rech.setNom_P(tf_nom.getText());
            rech.setMarque_P(tf_marque.getText());
          rech.setCategorie_P(tf_cat.getText());
            rech.setCouleur_P(tf_couleur.getText());
            rech.setPrix_P(Float.valueOf(tf_prix.getText()));
            rech.setPhoto_P(tf_photo.getText());
            rech.setTel(Integer.valueOf(tf_tel.getText()));
            
              try {
               ServiceRechange sv=new ServiceRechange();
            sv.UpdateRechange(rech);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
           

        System.out.println("Modification terminé");
           
           String title = r.getPhoto_P();
        System.out.println(title);
        FileInputStream input = new FileInputStream("C:\\wamp64\\www\\PiSymfony\\web\\public\\uploads\\" + title + "");
        //  Image image = new Image("C:\\wamp64\\www\\" +title+"") {};
        // ImageView view = new ImageView(input);
        Image image = new Image(input);
        ImageView view = new ImageView();
        tf_image_view.setImage(image);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Accessoire enregistré avec succès.");
        alert.setHeaderText(null);
        alert.setContentText("Le produit " + r.getNom_P() + " has been modified.");
        alert.showAndWait();
             
      
         
        }
        
    }


   
    
    
    public void setRechange(Produit rl) {
        this.r = rl;
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
    private void PDFAction(ActionEvent event) {     pdf();
                
        
        
        
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
