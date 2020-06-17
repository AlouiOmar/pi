/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_javafx.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.mail.Session;
import pi_javafx.userentites.Bcrypt;
import pi_javafx.userentites.Email;
import pi_javafx.userentites.fos_user;
import pi_javafx.userentites.Vars;
import pi_javafx.userservices.ServiceUser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class LoginController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private TextField email;
    @FXML
    private Button vv;
    @FXML
    private PasswordField password;
    @FXML
    private Button inscrire;
    @FXML
    private Button forgotten;

    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Image imagev1 = new Image("/pi_javafx/icons/pic.jpg");
        img.setImage(imagev1);
      
        
    }    

    @FXML
    private void Connexion(ActionEvent event) throws IOException {
          
             String temail = email.getText ();
   String  tpassword = password.getText ();
   
    
                  ServiceUser ui = new ServiceUser();
             fos_user z = ui.Verficier_Connexion(temail);
            
             ServiceUser ui1 = new ServiceUser();
            
          //vérification de saisie d'email et mdp   
             if (temail.equals ("") || tpassword.trim (). equals (""))
        {
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText(" email ou password est vide  ");
            alert.showAndWait();
        }
            
         if (Bcrypt.checkpw(tpassword, z.getPassword()))
            {       
         try
         {
        
            
            
        
             
          Vars.current_user = ui1.getById(z.getId_user());
             System.out.println(Vars.current_user.toString());
             
             if( Vars.current_user.getRoles().equals("utilisateur"))
             {
             
                    Stage stage = new Stage();
                    Parent home = FXMLLoader.load(getClass().getResource("/pi_javafx/gui/Acceuil.fxml"));
                
                    Scene hoomescene = new Scene(home);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(hoomescene);
                    app_stage.show();
             }
             
             else if( Vars.current_user.getRoles().equals("administrateur")){
                 
                Stage stage = new Stage();
                    Parent home = FXMLLoader.load(getClass().getResource("/pi_javafx/gui/Acceuil_Administrateur.fxml"));
                    
                
                    Scene hoomescene = new Scene(home);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(hoomescene);
                    app_stage.show();
             
             
             
             }
             

             }
             
       catch (SQLException ex) 
                {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }  
       
        }
         else
         {
         
          Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText(" email ou password incorret  ");
            alert.showAndWait();
         
         }
             
        
    }

    @FXML
    private void inscrire(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("/pi_javafx/gui/Inscrire.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        
    }

    @FXML
    private void btnforgot(ActionEvent event) throws Exception {
        
            String temail = email.getText ();
          ServiceUser ui = new ServiceUser();
             fos_user z = ui.Verficier_Connexion(temail);
        
       Session s1 = null;
        Random r = new Random();
         String s = Integer.toString(r.nextInt()) ;      
        System.out.println(s);
       Email.prepareMesssage(s1,s,temail);
       try{
        Email.sendEmail(temail);
       }
       catch (SQLException ex) {
            System.out.println(ex);
        }

        System.out.println(s);
            System.out.println("dfsdfsg");
        
    }

   
}
