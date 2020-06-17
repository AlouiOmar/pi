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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pi_javafx.userentites.fos_user;
import pi_javafx.userentites.Vars;
import pi_javafx.userservices.ServiceUser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Afficher_UtilisateurController implements Initializable {
     private Connection cnx; 
     
     
    ObservableList<fos_user> data;

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
      @FXML private TableView<fos_user> tableview;
//        @FXML private TableColumn<Utilisateur,Integer> tid;
         @FXML private TableColumn<fos_user,String> tcnom;
         @FXML private TableColumn<fos_user,String> tcprenom;
         @FXML private TableColumn<fos_user,String> tcemail;
         @FXML private TableColumn<fos_user,Integer> tcage;
         @FXML private TableColumn<fos_user,Integer> tctelephone;
         @FXML private TableColumn<fos_user,String> tcrole;
         
          ServiceUser u = new ServiceUser();        
          ObservableList listuu = FXCollections.observableArrayList();
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private TextField chercher;
    @FXML
    private Button btnchercher;
    @FXML
    private Button modifier;
    @FXML
    private Button refresh;
    @FXML
    private Label labelliste;
    
    
     @FXML
    private ChoiceBox<String> condition;
    @FXML
    private Button affichepost;
    @FXML
    private ImageView img8;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb)  {
       
       
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
         
        
         
         
        
      
         // récuperer les données a partir de  la base 
          ObservableList<fos_user> listu  = FXCollections.observableArrayList();
     try {
       
         for(fos_user bb: u.getTrier())
         {   listu.add(bb);}
          
     } catch (SQLException ex) {
         Logger.getLogger(Afficher_UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
     }
         

            //mettre les données dans la table view:    
         tcnom.setCellValueFactory(new PropertyValueFactory<>("username"));
         tcprenom.setCellValueFactory(new PropertyValueFactory<>("firstname"));
         tcemail.setCellValueFactory(new PropertyValueFactory<>("email"));
               tcage.setCellValueFactory(new PropertyValueFactory<>("age"));
         tctelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
         tcrole.setCellValueFactory(new PropertyValueFactory<>("roles"));
         tableview.setItems(listu);
         
    
         
         
         
         
         
         
         
         
         
    }    

    @FXML
    private void affiche(ActionEvent event) throws IOException {
       Parent tableview = FXMLLoader.load(getClass().getResource("/pi_javafx/gui/Afficher_Utilisateur.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();   
        
        
    }

    
    @FXML
    private void Ajouter_Utilisateur(ActionEvent event) throws IOException {
        
         //afficher l'interface inscription:
          
    Parent tableview = FXMLLoader.load(getClass().getResource("/pi_javafx/gui/Ajouter_Utilisateur.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void deleteUtilisateur(ActionEvent event) throws SQLException {
            ObservableList<fos_user> SelectedRows, allpeople;
     
     allpeople = tableview.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tableview.getSelectionModel().getSelectedItems();
     
     for(fos_user gg:SelectedRows){
          Alert alert = new  Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation Dialoge");
            alert.setHeaderText(null);
            alert.setContentText(gg.getUsername()+ "   are you sur to delete");
            Optional <ButtonType> action = alert.showAndWait();
            
            if(action.get() == ButtonType.OK ){
                allpeople.remove(gg);
                u.deleteUtilisateur(gg); 
                
                  
                
                
     }   
     }
   
    }

    @FXML
    private void Recherche_Utilisateur(ActionEvent event) throws SQLException {

               String text = chercher.getText();
              ObservableList<fos_user> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(fos_user bb: u.RechercherUtilisateur(text))
             listu.add(bb);
            tableview.setItems(listu); 
     
     } catch (SQLException ex) {
         Logger.getLogger(Afficher_UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
     }  
    }

    @FXML
    private void Modifier_Utilisateur(ActionEvent event) throws IOException {
                     fos_user e=tableview.getSelectionModel().getSelectedItem();

if(e==null){
        
           System.out.println("Aucun utilisateur séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun utilisateur séléctionné");

            alert.showAndWait();
     
        }else {
    
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi_javafx/gui/Modifier_Utilisateur.fxml"));
        Scene scene=new Scene(loader.load());
        Modifier_UtilisateurController mc= loader.getController();
        mc.setEvent(e);
        Stage stageAff=new Stage();
        stageAff.setScene(scene);
        stageAff.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        
        
    }
    }

    @FXML
    private void Refresh_Action(ActionEvent event) {
              ObservableList<fos_user> listu  = FXCollections.observableArrayList();
     try {
       
         for(fos_user bb: u.getTrier())
         {   listu.add(bb);}
          
     } catch (SQLException ex) {
         Logger.getLogger(Afficher_UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
     }
     
     
         tableview.setItems(listu);
        
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

    @FXML
    private void affichepost(ActionEvent event) {
    }

             
         
         
         
         
         
         
         }
    
