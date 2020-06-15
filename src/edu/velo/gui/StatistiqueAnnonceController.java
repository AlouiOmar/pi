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
import edu.velo.gui.StatController;
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
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
//import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javax.imageio.ImageIO;
import pi_javafx.Controller.ProfilController;
import pi_javafx.userentites.fos_user;

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
    private JFXButton btStat1;
    @FXML
    private JFXButton btAdmin;
    @FXML
    private ComboBox<String> cbx;
    @FXML
    private Circle cir;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(cbx.getValue()==null){
           cbx.setValue(pi_javafx.userentites.Vars.current_user.getUsername());
         cbx.getItems().add("Mon Profil");
         cbx.getItems().add("déconnexion");}
          // récupérer photo a partie de base de données
         ImageView img = new ImageView();
         BufferedImage bufferedImage1 =null;
         try{
             
           bufferedImage1=ImageIO.read(new File("C:\\xampp\\htdocs\\pi\\web\\uploads\\user\\"+pi_javafx.userentites.Vars.current_user.getPhoto()));
//             System.out.println(bufferedImage);
           
         } catch (IOException ex) {
          Logger.getLogger(pi_javafx.Controller.AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
      }
         
         WritableImage imageImported1 = SwingFXUtils.toFXImage(bufferedImage1, null);
         //   image_connect.setImage(imageImported);
            cir.setFill(new ImagePattern(imageImported1));
         scrollPane.setFitToWidth(true);

        btAdmin.setVisible(false);
        btSignal.setVisible(false);
        btStat.setVisible(false);
        if(pi_javafx.userentites.Vars.current_user.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
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
         
           final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : nre.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                e -> {
                    double total = 0;
                    for (PieChart.Data d : nre.getData()) {
                        total += d.getPieValue();
                    }
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    String text = String.format("%.1f%%", 100*data.getPieValue()/total) ;
                    caption.setText(text);
                 }
                );
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
    private void afficherProduit(ActionEvent event) {
        try {
            
            Parent p6=FXMLLoader.load(getClass().getResource("/gui/GestionProduit.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p6);
            
            stage.setTitle("Produit");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherListeAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
                @FXML
    private void afficherStat1(ActionEvent event) {

        StatController s = new StatController();
                    Stage stage=new Stage();

            s.start(stage);
    }
    
    
                 @FXML
    private void afficherStat2(ActionEvent event) {

        StatOne s = new StatOne ();
                    Stage stage=new Stage();

            s.start(stage);
    }
                 @FXML
    private void afficherStat3(ActionEvent event) {

        StatTwo s = new StatTwo ();
                    Stage stage=new Stage();

            s.start(stage);
    }
                 @FXML
    private void afficherStat4(ActionEvent event) {

        StatThree s = new StatThree ();
                    Stage stage=new Stage();

            s.start(stage);
    }
     @FXML
    private void acc(ActionEvent event) throws IOException {
        
         if(cbx.getValue().equals("déconnexion"))
        {
          Stage stage = new Stage();
        Parent home = FXMLLoader.load(getClass().getResource("/pi_javafx/gui/Login.fxml")); 
        Scene hoomescene = new Scene(home);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(hoomescene);
         app_stage.show();
        
        }
         
         if(cbx.getValue().equals("Mon Profil"))
             
         {
        fos_user e=pi_javafx.userentites.Vars.current_user;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi_javafx/gui/Profil.fxml"));
        Scene scene=new Scene(loader.load());
        ProfilController   mc= loader.getController();
        mc.setEvent(e);
        Stage stageAff=new Stage();
        stageAff.setScene(scene);
        stageAff.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        
        
    }
    }
    
    @FXML
    private void affiche(ActionEvent event) throws IOException {
          Parent tableview = FXMLLoader.load(getClass().getResource("/pi_javafx/gui/Afficher_Utilisateur.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();   
    }
}
