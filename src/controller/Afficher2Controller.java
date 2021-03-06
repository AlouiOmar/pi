/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raef
 */
public class Afficher2Controller implements Initializable {

    @FXML
    private Button back_gestion_;
    @FXML
    private Button bntAfficherVelo;
    @FXML
    private Button btnAfficherAccessoire;
    @FXML
    private Button btnAfficherRechange;
    @FXML
    private Separator separateur;
    @FXML
    private ImageView img_back;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label aff_Act;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void bnt_Afficher_Velo_Action(ActionEvent event) throws IOException {
        
        
         AnchorPane pane=FXMLLoader.load(getClass().getResource("/gui/AfficherVeloUser.fxml"));
        mainPane.getChildren().setAll(pane);
        
        aff_Act.setText("Afficher vélos");
    }

    @FXML
    private void btn_Afficher_Accessoire_Action(ActionEvent event)throws IOException {
        
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/gui/AfficherAccessoireUser.fxml"));
        mainPane.getChildren().setAll(pane);
        aff_Act.setText("Afficher Accessoires");
    }

    @FXML
    private void btn_Afficher_Rechange_Action(ActionEvent event) throws IOException{
        
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/gui/AfficherRechangeUser.fxml"));
        mainPane.getChildren().setAll(pane);
        aff_Act.setText("Afficher pièces de rechange");
        
    }

    
    @FXML
    private void back_gestion_action(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/gui/GestionProduit2.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
        
}
