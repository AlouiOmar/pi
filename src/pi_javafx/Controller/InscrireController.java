/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_javafx.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pi_javafx.userentites.Bcrypt;
import pi_javafx.userentites.fos_user;
import pi_javafx.userentites.Vars;
import pi_javafx.userservices.ServiceUser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class InscrireController implements Initializable {

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
    private Button btinscription;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private Label picture;
    @FXML
    private Button btnpicture;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    @FXML
    private void bt_photo(ActionEvent event)
    
    {
        //         Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Selectionner une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String path = "C:\\xampp\\htdocs\\pi\\web\\uploads\\user";
        btnpicture.setText(file.getName());
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         Parent tableview = FXMLLoader.load(getClass().getResource("/pi_javafx/gui/Login.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
      public boolean verifierchamps () throws SQLException
    {
        String fnom = nom.getText ();
        String fprenom = prenom.getText ();
        String femail = email.getText ();
        String fpass1  = password1.getText ();
        String fpass2  = password2.getText ();
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
                   || fpass1.trim (). equals ("") || fpass2.trim (). equals ("") || fage.trim (). equals ("") || ftelephone.trim (). equals (""))
                {
                    
                     Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("champs sont vides");
            alert.showAndWait();
            
             return false;
                }
                //controle de saisie de deux mot de passe
               else if (!fpass1.equals(fpass2)) {
            
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("mot de passe invalide ");
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
                
                
                
                // controle de saisie de l'existance par email
              
                 else if(k.stream().anyMatch(e->e.getEmail().equals(femail)))
                 {
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText(" email est existe deja  ");
            alert.showAndWait();
                    return false;    
                 }
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
    private void inscription(ActionEvent event) throws SQLException {
          ServiceUser su = new ServiceUser();
         fos_user user = new fos_user();
     
         String fnom = nom.getText ();
         String fprenom = prenom.getText ();
         String femail = email.getText();
         String  fpass1= password1.getText ();
         String  fpass2= password2.getText ();
         String  fage= age.getText ();
         String  ftelephone= telephone.getText ();
         String fphoto =btnpicture.getText();
         String p1crypte = Bcrypt.hashpw(fpass1, Bcrypt.gensalt());
         
        
// vérifie si les données sont vides
         if (verifierchamps ())
         {
            
             user.setUsername(fnom);
             user.setUsername_canonical(fnom);
             user.setFirstname(fprenom);
             user.setEmail(femail);
             user.setEmail_canonical(femail);
             user.setPassword(p1crypte);
             user.setAge(Integer.parseInt(fage));
             user.setTelephone(Integer.parseInt(ftelephone));
             user.setPhoto(fphoto);
             user.setRoles("a:0:{}");
             user.setEnabled(1);

                 System.out.println(user.toString());
                 //on a ajouter les information dans la base de données
                 su.addUtilisateur(user);
                 
        
                   
                 
                }
                }
                 

    }
    

