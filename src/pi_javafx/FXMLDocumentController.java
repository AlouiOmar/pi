/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_javafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author USER
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
   private ImageView image;
    
      
    @FXML
    private Label labelb;
  
    
   
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");         
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Image imagev = new Image("/pi_javafx/icons/mohamed khalil boutar.jpg");
       
        image.setImage(imagev);
      
        
    }    

   
    @FXML
    private void bb(ActionEvent event) {
        labelb.setText("khalil");
    }
    
}
