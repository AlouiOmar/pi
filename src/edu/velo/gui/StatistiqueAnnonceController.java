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
import edu.velo.entities.Stat;
import edu.velo.services.AnnonceServices;
import edu.velo.util.Vars;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aloui Omar
 */
public class StatistiqueAnnonceController implements Initializable {

    /**
     * Initializes the controller class.
     */
  
    
    @FXML
    private PieChart ndv;
    @FXML
    private PieChart nde;
    @FXML
    private PieChart nrv;
    @FXML
    private PieChart nre;
    private List<Stat> lndv =new ArrayList<Stat>();
    private List<Stat> lnde =new ArrayList<Stat>();
    private List<Stat> lnrv =new ArrayList<Stat>();
    private List<Stat> lnre =new ArrayList<Stat>();
    @FXML
    private Menu logout;
    @FXML
    private Button dc;
    @FXML
    private Menu acceuil;
    @FXML
    private Button aa;
    @FXML
    private Menu plan;
    @FXML
    private Button pp;
    @FXML
    private Menu deal;
    @FXML
    private Button ddd;
    @FXML
    private Menu enseigne;
    @FXML
    private Button cc1;
    @FXML
    private Menu Reservation;
    @FXML
    private Button rr;
    @FXML
    private JFXTextField tf;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private VBox box;
    @FXML 
    ScrollPane scrollPane;
    @FXML
    private JFXButton btSignal;
    @FXML
    private JFXButton btStat;
    @FXML
    private JFXButton btAdmin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         scrollPane.setFitToWidth(true);

        btAdmin.setVisible(false);
        btSignal.setVisible(false);
        btStat.setVisible(false);
        if(Vars.current_user.getRole().equals("administrateur")){
         btAdmin.setVisible(true);
         btSignal.setVisible(true);   
         btStat.setVisible(true);   
        }
        AnnonceServices ads=new AnnonceServices();
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
        lndv=ads.StatisticNdv();
        ObservableList<PieChart.Data> chartndv =  FXCollections.observableArrayList();
        lndv.forEach(v->{
            
                chartndv.add(new PieChart.Data(v.getNom(), v.getNbr()));
                
        });       
         ndv.setTitle("Nombre des annonces signalées par Cause");
         ndv.setData(chartndv);
         ndv.setLabelLineLength(10);
         ndv.setLegendSide(Side.LEFT);
         
         
         
         
        lnde=ads.StatisticNde();
        ObservableList<PieChart.Data> chartnde =  FXCollections.observableArrayList();
        lnde.forEach(e->{
            
                chartnde.add(new PieChart.Data(e.getNom(), e.getNbr()));
                
        });       
         nde.setTitle("Nombre des annonces signalées par Catégorie");
         nde.setData(chartnde);
         nde.setLabelLineLength(10);
         nde.setLegendSide(Side.LEFT);
        
        lnrv=ads.StatisticNrv();
        ObservableList<PieChart.Data> chartnrv =  FXCollections.observableArrayList();
        lnrv.forEach(r->{
            
                chartnrv.add(new PieChart.Data(r.getNom(), r.getNbr()));
                
        });       
         nrv.setTitle("Nombre des annoces publiées par Type");
         nrv.setData(chartnrv);
         nrv.setLabelLineLength(10);
         nrv.setLegendSide(Side.LEFT);
        
        lnre=ads.StatisticNre();
        ObservableList<PieChart.Data> chartnre =  FXCollections.observableArrayList();
        lnre.forEach(v->{
            
                chartnre.add(new PieChart.Data(v.getNom(), v.getNbr()));
                
        });       
         nre.setTitle("Nombre des annonces publiées par Gouvernorat");
         nre.setData(chartnre);
         nre.setLabelLineLength(10);
         nre.setLegendSide(Side.LEFT);
        
        
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
}
