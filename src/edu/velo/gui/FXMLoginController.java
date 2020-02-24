/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.gui;

import edu.velo.userentites.Bcrypt;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import edu.velo.userentites.Utilisateur;
import edu.velo.userservices.ServiceUser;
import edu.velo.util.Connection;
import javafx.event.ActionEvent;
import edu.velo.util.Vars;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLoginController implements Initializable {
//@FXML private Label conf;   
@FXML private TextField email;
@FXML private PasswordField password;    
@FXML private TextField btconnexion;
@FXML private Button vv;
@FXML private Button inscrire;
@FXML private Label mdpoublie;

//ResultSet rs = null; 
        
       
  
//        
//        <
      
        
        
          public void Connexion( ActionEvent event ) throws SQLException, IOException{
            
            
             String temail = email.getText ();
   String  tpassword = password.getText ();
   
    
                  ServiceUser ui = new ServiceUser();
             Utilisateur z = ui.Verficier_Connexion(temail);
            
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
         try{
        
            
            
        
             
          Vars.current_user = ui1.getById(z.getId_user());
             System.out.println(Vars.current_user.toString());
             
             if( Vars.current_user.getRole().equals("utilisateur"))
             {
             
    Stage stage = new Stage();
                    Parent home = FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
                
                    Scene hoomescene = new Scene(home);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(hoomescene);
                    app_stage.show();
             }
             
             else if( Vars.current_user.getRole().equals("administrateur")){
                Stage stage = new Stage();
                    Parent home = FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
                
                    Scene hoomescene = new Scene(home);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(hoomescene);
                    app_stage.show();
             
             
             
             }
             
//             else{
//             Stage stage = new Stage();
//                    Parent home = FXMLLoader.load(getClass().getResource("/project_pi/ListUtilisateur.fxml"));
//                
//                    Scene hoomescene = new Scene(home);
//                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                    app_stage.setScene(hoomescene);
//                    app_stage.show();
//             
//             
//             }
//        Parent hh = FXMLLoader.load(getClass().getResource("ListUtilisateur.fxml"));
//    
// 
//        
//        Scene sceneview = new Scene(hh);
//        
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(sceneview);
//        window.show();
             }
             
       catch (SQLException ex) 
                {
                Logger.getLogger(FXMLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }  
       
        }
        
        



// affiche un message si les champs nom d'utilisateur ou mot de passe sont vides
//        if (email.getText ().trim (). equals ("") || password.getText ().trim (). equals (""))
//        {
//            Alert alert = new  Alert(Alert.AlertType.WARNING);
//            alert.setTitle("warning");
//            alert.setHeaderText(null);
//            alert.setContentText(" email ou password est vide  ");
//            alert.showAndWait();
//        }
//        
//        else
//        {
//        
//        try {
//            Utilisateur u = new Utilisateur();
//            String query = "SELECT * FROM user where email=?  and password=? ";
//            PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(query);
//            
//            ps.setString(1,email.getText () );
//            ps.setString(2,password.getText ());
//        ResultSet rs =  ps.executeQuery();
//          
//          
//          if (rs.next())
//          {
//              
//           
//               Alert alert = new  Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("information de dialogue");
//            alert.setHeaderText(null);
//            alert.setContentText(" vous avez authentifié avec succées  ");
//            alert.showAndWait(); 
//            
//             Vars.current_user = ;
//            
//             Parent hh = FXMLLoader.load(getClass().getResource("ListUtilisateur.fxml"));
//    
// 
//        
//        Scene sceneview = new Scene(hh);
//        
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(sceneview);
//        window.show();
//          }
//          else {
//                Alert alert = new  Alert(Alert.AlertType.WARNING);
//            alert.setTitle("information de dialogue");
//            alert.setHeaderText(null);
//            alert.setContentText(" les champs invalides ");
//            alert.showAndWait();  
//          }
//          
//            
//            
//        }   catch (SQLException ex) {
//                Logger.getLogger(FXMLoginController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
          
        }



// affiche un message si les champs nom d'utilisateur ou mot de passe sont vides
//        if (email.getText ().trim (). equals ("") || password.getText ().trim (). equals (""))
//        {
//            Alert alert = new  Alert(Alert.AlertType.WARNING);
//            alert.setTitle("warning");
//            alert.setHeaderText(null);
//            alert.setContentText(" email ou password est vide  ");
//            alert.showAndWait();
//        }
//        
//        else
//        {
//        
//        try {
//            Utilisateur u = new Utilisateur();
//            String query = "SELECT * FROM user where email=?  and password=? ";
//            PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(query);
//            
//            ps.setString(1,email.getText () );
//            ps.setString(2,password.getText ());
//        ResultSet rs =  ps.executeQuery();
//          
//          
//          if (rs.next())
//          {
//              
//           
//               Alert alert = new  Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("information de dialogue");
//            alert.setHeaderText(null);
//            alert.setContentText(" vous avez authentifié avec succées  ");
//            alert.showAndWait(); 
//            
//             Vars.current_user = ;
//            
//             Parent hh = FXMLLoader.load(getClass().getResource("ListUtilisateur.fxml"));
//    
// 
//        
//        Scene sceneview = new Scene(hh);
//        
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(sceneview);
//        window.show();
//          }
//          else {
//                Alert alert = new  Alert(Alert.AlertType.WARNING);
//            alert.setTitle("information de dialogue");
//            alert.setHeaderText(null);
//            alert.setContentText(" les champs invalides ");
//            alert.showAndWait();  
//          }
//          
//            
//            
//        }   catch (SQLException ex) {
//                Logger.getLogger(FXMLoginController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
          
            
        
    public void inscrire( ActionEvent event) throws IOException{
    
    Parent tableview = FXMLLoader.load(getClass().getResource("/edu/velo/gui/Inscription.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    
    
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Vars.current_user=new Utilisateur();
        Vars.current_user.setRole("visiteur");
    }    
    
}
