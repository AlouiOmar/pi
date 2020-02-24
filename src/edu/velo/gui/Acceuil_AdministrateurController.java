/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javax.imageio.ImageIO;
import edu.velo.userentites.Utilisateur;
import edu.velo.util.Vars;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Acceuil_AdministrateurController implements Initializable {

    @FXML
    private Circle circleAdm;
    @FXML
    private Label lbNomPreAdm;
    @FXML
    private ComboBox<String> btnAdm;

    /**
     * Initializes the controller class.
     */
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    if( Vars.current_user.getRole().equals("administrateur"))
        btnAdm.setValue(Vars.current_user.getNom()+" "+Vars.current_user.getPrenom());
    
         btnAdm.getItems().add("Profil");
         btnAdm.getItems().add("Deconnecté");
         
           // récupérer photo a partie de base de données
         ImageView img = new ImageView();
         BufferedImage bufferedImage =null;
         try{
             
           bufferedImage=ImageIO.read(new File("C:\\xampp\\htdocs\\"+Vars.current_user.getPhoto()));
//             System.out.println(bufferedImage);
           
         } catch (IOException ex) {
          Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
      }
         
         WritableImage imageImported = SwingFXUtils.toFXImage(bufferedImage, null);
         //   image_connect.setImage(imageImported);
            circleAdm.setFill(new ImagePattern(imageImported));
    }    
    
}
