/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_javafx.Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pi_javafx.userentites.Vars;
import pi_javafx.userentites.fos_user;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Acceuil_Administrateur implements Initializable {

    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;
    @FXML
    private ImageView img7;
    @FXML
    private Circle cir;
    @FXML
    private ComboBox<String> cbx;
    @FXML
    private Button affiche;
    @FXML
    private Button affichepost;
    @FXML
    private ImageView img8;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          Image image1 = new Image("/pi_javafx/icons/home.png");
       
        img1.setImage(image1);
        
         Image image2 = new Image("/pi_javafx/icons/produit.png");
       
        img2.setImage(image2);
        
        Image image3 = new Image("/pi_javafx/icons/annonce.png");
       
        img3.setImage(image3);
        
        Image image4 = new Image("/pi_javafx/icons/location.png");
       
        img4.setImage(image4);
        
        Image image5 = new Image("/pi_javafx/icons/evenement.png");
       
        img5.setImage(image5);
        
        Image image6 = new Image("/pi_javafx/icons/circuit.png");
       
        img6.setImage(image6);
        
        Image image7 = new Image("/pi_javafx/icons/utilisateur.png");
       
        img7.setImage(image7);
                
        Image image8 = new Image("/pi_javafx/icons/unnamed.jpg");
       
        img8.setImage(image8);
        
         // récupérer photo a partie de base de données
         ImageView img = new ImageView();
         BufferedImage bufferedImage =null;
         try{
             
           bufferedImage=ImageIO.read(new File("C:\\xampp\\htdocs\\final_pi\\web\\uplods\\post\\"+Vars.current_user.getPhoto()));
//             System.out.println(bufferedImage);
           
         } catch (IOException ex) {
          Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
      }
         
         WritableImage imageImported = SwingFXUtils.toFXImage(bufferedImage, null);
         //   image_connect.setImage(imageImported);
            cir.setFill(new ImagePattern(imageImported));
            
            
            
         cbx.setValue(Vars.current_user.getUsername());
         cbx.getItems().add("Mon Profil");
         cbx.getItems().add("déconnexion");
         
         
         
         
        
    }    
    
    

    @FXML
    public void affiche(ActionEvent event) throws IOException {
        
        Parent tableview = FXMLLoader.load(getClass().getResource("/pi_javafx/gui/Afficher_Utilisateur.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void affichepost(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("/pi_javafx/gui/Afficher_Post.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
    
 @FXML
    private void acc(ActionEvent event) throws IOException {
        
         if(cbx.getValue().equals("déconnexion"))
        {
          Stage stage = new Stage();
        Parent home = FXMLLoader.load(getClass().getResource("/pi_javafx/gui/Login.fxml")); 
        Scene hoomescene = new Scene(home);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(hoomescene);
         app_stage.show();
        
        }
         
         if(cbx.getValue().equals("Mon Profil"))
             
         {
        fos_user e=Vars.current_user;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi_javafx/gui/Profil.fxml"));
        Scene scene=new Scene(loader.load());
        ProfilController   mc= loader.getController();
        mc.setEvent(e);
        Stage stageAff=new Stage();
        stageAff.setScene(scene);
        stageAff.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        
        
    }
    }
    
}
