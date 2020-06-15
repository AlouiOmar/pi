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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javax.imageio.ImageIO;
import pi_javafx.userentites.Post;
import pi_javafx.userentites.fos_user;
import pi_javafx.userentites.Vars;
import pi_javafx.userservices.ServicePost;
import pi_javafx.userservices.ServiceUser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Afficher_PostController implements Initializable {

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
    private ImageView img8;
    @FXML
    private Button affichepost;
    
     @FXML  private TableView<Post> tablepost;
     @FXML
    private TableColumn<Post, String> fctitle;
    @FXML
    private TableColumn<Post, String> fcdescription;
    @FXML
    private TableColumn<Post, String> fcpostdate;
    @FXML
    private TableColumn<Post, Integer> fccreator;
    @FXML
    private TableColumn<Post, Integer> fcrating;
    @FXML
    private TableColumn<Post, ImageView> fcphoto;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private TextField chercher;
    @FXML
    private Button refresh;
    @FXML
    private Button btnchercher;
 ServicePost u = new ServicePost();        
          ObservableList listuu    = FXCollections.observableArrayList();
          
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
   
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
             
           bufferedImage=ImageIO.read(new File("C:\\xampp\\htdocs\\pi\\web\\uploads\\user\\"+Vars.current_user.getPhoto()));
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
         
        
         
         
        
      
         // récuperer les données a partir de  la base 
          ObservableList<Post> listu  = FXCollections.observableArrayList();
     try {
       
         for(Post bb: u.getTrier())
            
         {   
             listu.add(bb);
         }
          
     } catch (SQLException ex) {
         Logger.getLogger(Afficher_PostController.class.getName()).log(Level.SEVERE, null, ex);
     }
         
       
            //mettre les données dans la table view:    
         fctitle.setCellValueFactory(new PropertyValueFactory<>("title"));
         fcdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
          fccreator.setCellValueFactory(new PropertyValueFactory<>("creator"));
         fcpostdate.setCellValueFactory(new PropertyValueFactory<>("postdate"));
    fcrating.setCellValueFactory(new PropertyValueFactory<>("rating"));
     String path = "C:\\xampp\\htdocs\\img";
        
        
    fcphoto.setCellValueFactory(new PropertyValueFactory<>("img"));
    
    
        tablepost.setItems(listu);
        
        
        
        
        
        
    
    }    

    @FXML
    private void acc(ActionEvent event) {
    }

    @FXML
    private void affiche(ActionEvent event) {
    }

    @FXML
    private void affichepost(ActionEvent event) {
    }

    @FXML
    private void Ajouter_Utilisateur(ActionEvent event) {
    }

    @FXML
    private void deleteUtilisateur(ActionEvent event) {
    }

    @FXML
    private void Refresh_Action(ActionEvent event) {
    }

    @FXML
    private void Recherche_Utilisateur(ActionEvent event) {
    }
    
}
