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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raef
 */
public class GestionProduitController implements Initializable {

    @FXML
    private ImageView add_img;
    @FXML
    private ImageView search_img;
    @FXML
    private Button ajouter;
    @FXML
    private Button rechercher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouerAction(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Ajouter.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        
    }

    @FXML
    private void RechercherAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Afficher.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        
        
    }

    
}
