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
import javafx.scene.control.*;
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
public class AcceuilController implements Initializable {
  @FXML private Label nom_connect;
//    @FXML
//    private ImageView image_connect;
    @FXML
    private Circle cir;
    /**
     * Initializes the controller class.
     */
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
        
        if (Vars.current_user.getRole().equals("utilisateur"))
        {
        nom_connect.setText(Vars.current_user.getNom()+" "+Vars.current_user.getPrenom());
        
        }
        
         if (Vars.current_user.getRole().equals("administrateur"))
        {
        nom_connect.setText(Vars.current_user.getNom()+" "+Vars.current_user.getPrenom());        
        }
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
            cir.setFill(new ImagePattern(imageImported));
    }    
    
}
