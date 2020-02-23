/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.ServiceVelo;
import entities.Velo;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raef
 */
public class VeloRechercherController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_cat;
    @FXML
    private TextField tf_marque;
    @FXML
    private TextField tf_couleur;
    @FXML
    private TextField tf_prix;
    @FXML
    private Button tf_photo;
    @FXML
    private Button tf_modifier;
    private TextField tf_type;
    private DatePicker tf_date;
    @FXML
    private ImageView tf_image_view;
    @FXML
    private Button retour;

    Velo v;
    @FXML
    private Button tf_partager;
private Facebook facebook;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Platform.runLater(() -> {

            System.out.println(v);
            tf_nom.setText(v.getNom_P());
            tf_marque.setText(v.getMarque_P());
            tf_cat.setText(v.getCategorie_P());
            tf_couleur.setText(v.getCouleur_P());
            tf_prix.setText(String.valueOf(v.getPrix_P()));
            tf_photo.setText(v.getPhoto_P());

            FileInputStream input;
            try {
                input = new FileInputStream("C:\\\\wamp64\\\\www\\\\" + v.getPhoto_P() + "");
                Image image = new Image(input);
                tf_image_view.setImage(image);
            } catch (FileNotFoundException ex) {
                System.out.println("error");
            }

        });

    }

    @FXML
    private void ModifierVelo(ActionEvent event) throws SQLException, FileNotFoundException {
        Velo vel = new Velo();
        if (vel == null) {

            System.out.println("choisir un Velo");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modify bike");
            alert.setHeaderText(null);
            alert.setContentText("the bike is not modified !");

            alert.showAndWait();
        } else {

            vel.setNom_P(tf_nom.getText());
            vel.setMarque_P(tf_marque.getText());
            vel.setCategorie_P(tf_cat.getText());
            vel.setCouleur_P(tf_couleur.getText());
            vel.setPrix_P(Float.valueOf(tf_prix.getText()));
            vel.setPhoto_P(tf_photo.getText());
            
              try {
               ServiceVelo sv=new ServiceVelo();
            sv.UpdateVelo(vel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
           

        System.out.println("modification terminé");
           
           String title = v.getPhoto_P();
        System.out.println(title);
        FileInputStream input = new FileInputStream("C:\\wamp64\\www\\" + title + "");
        //  Image image = new Image("C:\\wamp64\\www\\" +title+"") {};
        // ImageView view = new ImageView(input);
        Image image = new Image(input);
        ImageView view = new ImageView();
        tf_image_view.setImage(image);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vélo enregistré avec succès.");
        alert.setHeaderText(null);
        alert.setContentText("Le produit " + v.getNom_P() + " has been modified.");
        alert.showAndWait();
             
      
         
        }
    }


    @FXML
    private void RetourAfficherVelo(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("../gui/Afficher.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void modifier_photo(ActionEvent event) {

        Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(primary);
        String path = "C:\\wamp64\\www";
        tf_photo.setText(file.getName());
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setVelo(Velo vl) {
        this.v = vl;
    }

    @FXML
    private void partagerFBAction(ActionEvent event) throws FacebookException, SQLException{
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Share on Facebook");
        alert.setHeaderText("do you want to share on facebook!");
        alert.setContentText(null);

        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No");

        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
       
            facebook = new FacebookFactory().getInstance();

            facebook.setOAuthAppId("", "");
            String accessTokenString = "485353618815021|8kIxZ2cfDJK1_boXSGui9yDL2Es";
            AccessToken accessToken = new AccessToken(accessTokenString);
            //set access token
            facebook.setOAuthAccessToken(accessToken);
          
            try {
                facebook.postStatusMessage("La vélo " + v.getNom_P() + " est avec type " +v.getType_P()
                        + " et de categorie " + v.getCategorie_P() + " est disponible");
                System.out.println("qqqqqqqqqqqqqqqqqqqqqqqq");
            } catch (FacebookException fe) {
//                Alert alertt= new Alert(Alert.AlertType.INFORMATION);
////                alert.setTitle("Already share!");
////                alert.setHeaderText(null);
////                alert.setContentText("The bike  " +v.getNom_P() + " has already been shared!.");
//
//                alertt.showAndWait();
                System.out.println(fe);
            }
        } else if (result.get() == buttonTypeTwo) {
            
           alert.hide();

        }

        
    }

}
