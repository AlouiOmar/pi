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
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pi_javafx.userentites.Bcrypt;
import pi_javafx.userentites.fos_user;
import pi_javafx.userentites.Vars;
import pi_javafx.userservices.ServiceUser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Modifier_UtilisateurController implements Initializable {

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
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField age;
    @FXML
    private TextField telephone;
   
  
    @FXML
    private Label picture;
    @FXML
    private Button btnpicture;
    @FXML
    private Label role;
    @FXML
    private ChoiceBox<String> choix;
    @FXML
    private Button btinscription;
    
    fos_user e;

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
         
         
         
         choix.getItems().add("utilisateur");
        choix.getItems().add("administrateur");
        
        
         Platform.runLater(()->{
            System.out.println(e);
               nom.setText(e.getUsername());
               prenom.setText(e.getFirstname());
               email.setText(e.getEmail());
               age.setText(Integer.toString(e.getAge()));
               telephone.setText(Integer.toString(e.getTelephone()));
               btnpicture.setText(e.getPhoto());
               choix.setValue(e.getRoles());
               

       
    });    
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
    private void bt_photo(ActionEvent event) {
        
            //         Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Selectionner une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String path = "C:\\xampp\\htdocs";
        btnpicture.setText(file.getName());
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    
     public boolean verifierchamps () throws SQLException
    {
        String fnom = nom.getText ();
        String fprenom = prenom.getText ();
        String femail = email.getText ();
       
        String fage = age.getText ();
        String ftelephone = telephone.getText ();
        String fphoto =btnpicture.getText();
          ServiceUser u = new ServiceUser();
            List<fos_user> k = u.getUtilisateurs();
          Pattern p = Pattern.compile("[a-zA-Z][a-zA-Z0-9._]*@[a-zA-Z]+([.][a-zA-Z]+)+") ;  
          Pattern p1 = Pattern.compile("[a-zA-Z]") ;           
          Pattern p3 = Pattern.compile( "[0-9]" );

          
          
          
          Matcher m =p.matcher(femail);
          Matcher m1 =p3.matcher(fnom);
          Matcher m2 =p3.matcher(fprenom);
          Matcher m3 =p1.matcher(fage);
           Matcher m4 =p3.matcher(ftelephone);
           
           
           
            
                // vérifie les champs vides
                if (fnom.trim (). equals ("") || fprenom.trim (). equals ("") || femail.trim ().equals ("")
                 || fage.trim (). equals ("") || ftelephone.trim (). equals (""))
                {
                    
                     Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("champs sont vides");
            alert.showAndWait();
            
             return false;
                }
               
               
                //controle de saisie l'age
               else if(Integer.parseInt(fage) <10 || Integer.parseInt(fage)>100 ||
                       m3.find() && m.group().equals(fage)  )
               {
               
                Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText(" l'age invalide  ");
            alert.showAndWait();
                    return false;
               }
              
              
                //controle de saisie email
                else if(!(m.find() && m.group().equals(femail)))
                    
                        {
            Alert alert = new  Alert(Alert.AlertType.WARNING);        
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText(" email invalide  ");
            alert.showAndWait();
                    return false;
                        
                        }
                
                 //controle de saisie prenom et nom doit etre de chaine de caractère 
                
                 else if(m1.find() || m2.find())
                    
                        {
            Alert alert = new  Alert(Alert.AlertType.WARNING);        
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText(" prenom or nom doit etre chaine de caractère   ");
            alert.showAndWait();
                    return false;
                        
                        }
                
                
                
//                // controle de saisie de l'existance par email
//              
//                 else if(k.stream().anyMatch(e->e.getEmail().equals(femail)))
//                 {
//            Alert alert = new  Alert(Alert.AlertType.WARNING);
//            alert.setTitle("warning");
//            alert.setHeaderText(null);
//            alert.setContentText(" email est existe deja  ");
//            alert.showAndWait();
//                    return false;    
//                 }
                 
                 
                 
                 
                   // controle de saisie  téléphone
              
                 else if( !(ftelephone.startsWith("2") || ftelephone.startsWith("5")  || 
                         ftelephone.startsWith("4")|| ftelephone.startsWith("9") || ftelephone.startsWith("7"))
                       || ( ! m4.find() )   ) 
                         
                     
                 {
                 
                    Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText(" numero téléphone invalide ");
            alert.showAndWait();
                    return false;    

                 }             
                return true;           
        } 
    @FXML
    private void modifier_utilisateur(ActionEvent event) throws SQLException, IOException {

        
         ServiceUser su = new ServiceUser();
         fos_user user = new fos_user();
     
         String fnom = nom.getText ();
         String fprenom = prenom.getText ();
         String femail = email.getText();
        
         String  fage= age.getText ();
         String  ftelephone= telephone.getText ();
         String fphoto =btnpicture.getText();
         
         
        
// vérifie si les données sont vides
         if (verifierchamps ())
         {
             
             user.setUsername(fnom);
             user.setUsername_canonical(fnom);
             user.setEmail(femail);
             user.setEmail_canonical(femail);
             user.setEnabled(e.getEnabled());
             user.setSalt(e.getSalt());
             user.setPassword(e.getPassword());
             user.setAge(Integer.parseInt(fage));
             user.setTelephone(Integer.parseInt(ftelephone));
             user.setPhoto(fphoto);
             
           if (choix.getValue().equals("utilisateur"))
                {
                    user.setRoles("a:0:{}");
                }
          if (choix.getValue().equals("administrateur"))
                {
                   user.setRoles("a:1:{i:0;s:10:\"ROLE_ADMIN\";}"); 
                }   
          
             System.out.println(user);
             
          
         su.updateUtilisateur(user);
         
         
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succés!");
            alert.setHeaderText(null);
            alert.setContentText("Modification de l'utilisateur réalisée!");
            alert.showAndWait();
            
            
            
             Stage stage = new Stage();
        Parent home = FXMLLoader.load(getClass().getResource("/pi_javafx/gui/Afficher_Utilisateur.fxml")); 
        Scene hoomescene = new Scene(home);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(hoomescene);
         app_stage.show();
 
                
    }
    }
    
    
    public void setEvent(fos_user e1){
        this.e=e1;
    }
}
