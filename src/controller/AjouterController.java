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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raef
 */
public class AjouterController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button bntAjouterVelo;
    @FXML
    private Button btnAccessoire;
    @FXML
    private Button btnRechange;
    @FXML
    private Separator separateur;
    @FXML
    private Button btnRechange1;
    @FXML
    private ImageView back;
    @FXML
    private Label id_prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void bntAjouterVeloAction(ActionEvent event) throws IOException {
        
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/gui/AjouterProduit.fxml"));
        mainPane.getChildren().setAll(pane);
        id_prod.setText("Ajouter Vélo");
    }

    @FXML
    private void btnAccessoireAction(ActionEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/gui/AjouterAccessoire.fxml"));
        mainPane.getChildren().setAll(pane);
         id_prod.setText("Ajouter Accessoire");
    }

    @FXML
    private void btnRechangeAction(ActionEvent event) throws IOException {
        
       AnchorPane pane=FXMLLoader.load(getClass().getResource("/gui/AjouterRechange.fxml"));
        mainPane.getChildren().setAll(pane);  
         id_prod.setText("Ajouter pièce de rechange");
    }

    @FXML
    private void retour_action(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/gui/GestionProduit.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}
