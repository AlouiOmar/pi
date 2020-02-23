/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.velo.entities.Annonce;
import edu.velo.services.AnnonceServices;
import edu.velo.util.Vars;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Aloui Omar
 */
public class AfficherAnnonceController implements Initializable {

    /**
     * Initializes the controller class.
     */
         AnnonceServices as = new AnnonceServices();
         Annonce a=new Annonce();
         
         @FXML
         private Label cause;
         @FXML
         private Label labelCause;
         @FXML
         private Label prix;
         @FXML
         private Label type;
         @FXML
         private Label type1;
         @FXML
         private Label labelTvelo;
         @FXML
         private Label titre;
         @FXML
         private Label categorie;
         @FXML
         private Label couleur;
         @FXML
         private Label labelCouleur;
         @FXML
         private Label description;
         @FXML
         private Label date;
         @FXML
         private Label date1;
         @FXML
         private Label labelTel;
         @FXML
         private ImageView imgd;
         @FXML
         private JFXDrawer drawer;
         @FXML
         private JFXHamburger hamburger;
         @FXML
         private JFXButton btModif;
         @FXML
         private JFXButton btSupp;
         @FXML
         private JFXButton btSignal;
         @FXML
         private JFXButton btSignal1;
         @FXML
         private JFXButton btStat;
         @FXML
         private JFXButton btAdmin;
         @FXML
         private JFXButton btTel;
         @FXML
         private VBox box;
         @FXML
         private JFXTextField tf;
         private Image img;
         private String tel;
         private String sc;
         private List<String> causeList = new ArrayList<String>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        a=as.getAnnonce(Vars.current_annonce.getIda());
        
//        UtilisateurService us=new UtilisateurService();
//        Utilisateur u=us.getUser(Vars.current_annonce.getIdu());
         tel="+216 22 424 717";
         labelTel.setText(tel);
        labelTel.setVisible(false);
        labelCause.setVisible(false);
                causeList=as.getCause(Vars.current_annonce.getIda());
                sc="";
                causeList.forEach(d->{
            sc+=d+"\n";
            
        });
                cause.setText(sc);
                System.out.println(sc);
        cause.setVisible(false);

        Class<?> clazz = this.getClass();
      
        //File file = new File("/edu/velo/images/baseline_add_black_18dp.png");
 
        //Image image = new Image(file.toURI().toString());

                       BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File("C:\\codenameone\\velo11\\src\\edu\\velo\\uploads\\"+a.getPhoto()));
        } catch (IOException ex) {
            Logger.getLogger(AfficherListeAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
                WritableImage imageImported = SwingFXUtils.toFXImage(bufferedImage, null);
            imgd.setImage(imageImported);

        
//        img = new javafx.scene.image.Image("/edu/velo/uploads/"+a.getPhoto());
//            imgd.setImage(img);
        //imgd = new ImageView("/edu/velo/images/baseline_add_black_18dp.png");        
        //imgd.setImage(image);

       //
            
            if(Vars.current_user.getRole().equals("admin") && !sc.equals("")){
                labelCause.setVisible(true);
                cause.setVisible(true);
            }
            
            
            if(!a.getCategorie().equals("Vélo")){
                labelTvelo.setVisible(false);
                type1.setVisible(false);
                couleur.setVisible(false);
                labelCouleur.setVisible(false);
                
            }
            btSignal.setDisable(true);
            if(Vars.current_user.getRole().equals("user") || Vars.current_user.getRole().equals("admin")){
                btSignal.setDisable(false);
            }
            btModif.setVisible(false);
            btSupp.setVisible(false);
            if(Vars.current_user.getIdu()==a.getIdu() || Vars.current_user.getRole().equals("admin")){
                btModif.setVisible(true);
                btSupp.setVisible(true);
                

            }
            
        btAdmin.setVisible(false);
        btSignal1.setVisible(false);
        btStat.setVisible(false);
        if(Vars.current_user.getRole().equals("admin")){
         btAdmin.setVisible(true);
         btSignal1.setVisible(true);   
         btStat.setVisible(true);   
        }
            
        drawer.setSidePane(box);
         HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
               
                    type.setText(String.valueOf(a.getType()));
                    type1.setText(String.valueOf(a.getTypevelo()));
                    prix.setText(String.valueOf(a.getPrix()));
                    titre.setText(String.valueOf(a.getTitre()));
                    categorie.setText(String.valueOf(a.getCategorie()));
                    couleur.setText(String.valueOf(a.getCouleur()));
                    description.setText(String.valueOf(a.getDescription()));
                    date.setText(String.valueOf(a.getDate()));
                    date1.setText(String.valueOf(a.getDatep()));

    }    
     @FXML
    private void modifier(ActionEvent event) {
        try {
            Parent p3=FXMLLoader.load(getClass().getResource("/edu/velo/gui/ModifierAnnonce.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p3);
            
            stage.setTitle("Modifier Annonce");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {

        try {
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            //alert.setTitle("Confirmation Dialog");
           // alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Voulez vous supprimer cette annonce ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                Vars.current_choice=2;
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
            as.supprimerAnnonce(a);
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Mes Annoces");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
            } else {
                // ... user chose CANCEL or closed the dialog
                System.out.println("annulation de suppresion");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AfficherAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void clicki(ActionEvent event) {System.out.println("clickeeeeeeeeeeed");
     try {
            Parent p6=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p6);
            
            stage.setTitle("liste de mes annonces");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherListeAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void mesannonces(ActionEvent event) {
        try {
            Vars.current_choice=2;
            Parent p6=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p6);
            
            stage.setTitle("liste de mes annonces");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherListeAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void recherche(ActionEvent event) {
        try {
            Vars.current_choice= 3;
            Vars.current_type=tf.getText();
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("liste des annonce par description");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherListeAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
      @FXML
    private void ajouter(ActionEvent event) {
        try {
            Parent p3=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AjouterAnnonce.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p3);
            
            stage.setTitle("Ajouter Annonce");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void signaler(ActionEvent event) {
        try {
            
//            as.signalerAnnnoce(Vars.current_annonce.getIda());
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("Nous regrettons tout inconvénient que cela pourrait vous causer et nous prenons cette affaire au sérieux. vous allez être redériger vers la liste des annoces!");
//
//            alert.showAndWait();
            
            List<String> choices = new ArrayList<>();
            choices.add("Contenu indésirable");
            choices.add("Harcèlement");
            choices.add("Discours haineux");
            choices.add("Nudité");
            choices.add("Violence");
            choices.add("Autre");

            ChoiceDialog<String> dialog = new ChoiceDialog<>("Contenu indésirable", choices);
            dialog.setTitle("Signaler l'annonce aux administrateurs");
            dialog.setHeaderText("Dites aux administrateurs ce qui ne va pas avec cette annonce. \nPersonne d’autre ne verra votre nom ou le contenu de votre signalement.");
            dialog.setContentText("Choisir la raison:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                
                System.out.println("Your choice: " + result.get());
                as.signalerAnnnoce(Vars.current_annonce.getIda(),result.get());
                Parent p3=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p3);
            
            stage.setTitle("Ajouter Annonce");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AfficherAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       @FXML
    private void trieParDate(ActionEvent event) {
        try {
            Vars.current_choice=4;
            Parent p6=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p6);
            
            stage.setTitle("liste des annonces par date");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherListeAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
         @FXML
    private void trieParPrix(ActionEvent event) {
        try {
            Vars.current_choice=5;
            Parent p6=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p6);
            
            stage.setTitle("liste des annonces par prix");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherListeAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @FXML
    private void listeAnnonceSignalee(ActionEvent event) {
        try {
            Vars.current_choice=6;
            Parent p6=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p6);
            
            stage.setTitle("liste des annonces signalées");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherListeAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void afficherAnnonces(ActionEvent event) {
        try {
            Vars.current_choice=0;
            Parent p6=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p6);
            
            stage.setTitle("Liste des annonces");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherListeAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  @FXML
    private void afficherAdminAnnonces(ActionEvent event) {
        try {
            Vars.current_choice=7;
            Parent p6=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p6);
            
            stage.setTitle("Liste des annonces");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherListeAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
           @FXML
    private void afficherStatistique(ActionEvent event) {
        try {
            
            Parent p6=FXMLLoader.load(getClass().getResource("/edu/velo/gui/StatistiqueAnnonce.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p6);
            
            stage.setTitle("Liste des statistiques");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherListeAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
           @FXML
    private void afficherNum(ActionEvent event) {
        
        btTel.setVisible(false);
        labelTel.setVisible(true);
        
    }
}
