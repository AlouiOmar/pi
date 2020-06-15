/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import edu.velo.userentites.Bcrypt;
import edu.velo.userentites.Utilisateur;
import edu.velo.util.Vars;
import edu.velo.userservices.ServiceUser;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLInscriptionController implements Initializable {
    
 private Connection cnx;   
 @FXML private Label   role;
@FXML private TextField nom;
@FXML private TextField prenom;
@FXML private TextField email;
@FXML private PasswordField password1;
@FXML private PasswordField password2;
@FXML private TextField age;
@FXML private TextField telephone;
@FXML private ChoiceBox<String > choix  ;
@FXML private Button btinscription;
//private ImageView image;
//    @FXML
//    private Label picture;
    @FXML
    private Button btnpicture;
    @FXML
    private Label picture;


    

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
            List<Utilisateur> k = u.getUtilisateurs();
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
    // méthode d'insertion photo 
    public void bt_photo(ActionEvent event ){
    
               
           
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
        
        
        
        
    
    
    
    // valider inscription:
    @FXML
   public void  inscription ( ActionEvent event) throws SQLException, IOException {                                                 
         ServiceUser su = new ServiceUser();
         Utilisateur user = new Utilisateur();
     
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
             
             user.setNom(fnom);
             user.setPrenom(fprenom);
             user.setEmail(femail);
             user.setPassword(p1crypte);
             user.setAge(Integer.parseInt(fage));
             user.setTelephone(Integer.parseInt(ftelephone));
             user.setPhoto(fphoto);
             
           if (choix.getValue().equals("utilisateur"))
                {
                    user.setRole("utilisateur");
                }
          if (choix.getValue().equals("administrareur"))
                {
                   user.setRole("administrareur"); 
                }
          
          
 
          
                 System.out.println(user.toString());
          
           
                 su.addUtilisateur(user);
                 
                      
           if (Vars.current_user.getRole().equals("visiteur"))
                {
                   user.setRole("utilisateur"); 
                }
 
                 if(user.getRole().equals("administrateur"))
                 {
                 
        Parent tableview = FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));     
        Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
                 }
                 
                 
                 else if (user.getRole().equals("utilisateur")){
                     
                 Parent tableview = FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
    
    
   
        
        Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
                
                }
                }
                 
         }        
         
       
        
                   
                          
    

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         choix.setValue("utilisateur");
         choix.getItems().add("utilisateur");
         choix.getItems().add("administrareur");
      
      
      
    choix.setVisible(false);
    role.setVisible(false);
    
    
//    if(Vars.current_user.getRole().equals("administrateur")){
//    choix.setVisible(true);
//    role.setVisible(true);
//    }
    }
    
    
}
