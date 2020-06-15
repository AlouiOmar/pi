/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import edu.velo.entities.Annonce;
import edu.velo.entities.User;
import edu.velo.services.AnnonceServices;
import edu.velo.util.Vars;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
public class AfficherListeAnnoncesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXComboBox cb;
    @FXML
    private JFXListView<Pane> gr;
    private JFXListView<Pane> gr1;
    private User u;
    private Pane ap;
    private ObservableList<Pane> dd = FXCollections.observableArrayList();
    private AnnonceServices as = new AnnonceServices();
    User u1=new User(1,"aloui","omar","alouiomar1997@gmail.com","admin");
    User u2=new User(2,"aloui","omar","omar.aloui@esprit.tn","user");
    //private AdminAnnonceServices ds = new AdminAnnonceServices();
    private List<Annonce> maliste;
    private javafx.scene.image.Image img;
    @FXML
    private Menu logout;
    @FXML
    private Menu acceuil;
    @FXML
    private Menu plan;
    @FXML
    private Menu annonce;
    @FXML
    private JFXTextField tf;
    @FXML
    private Button rechv;
    @FXML
    private Button dc;
    @FXML
    private Button aa;
    @FXML
    private Button pp;
    @FXML
    private Button ddd;
    @FXML
    private Button scsc;
    @FXML
    private Button reservation;
    @FXML
    private Button mesannonces;
    @FXML
    private Menu enseigne;
    @FXML
    private Button cc1;
    @FXML
    private Button statistic;
    @FXML
    private AnchorPane xx;
    @FXML
    private Menu Reservation;
    @FXML
    private Button rr;
    @FXML
    private JFXButton btSignal;
    @FXML
    private JFXButton btAdmin;
    @FXML
    private JFXButton btStat;
    @FXML
    private ComboBox<String> cbx;
    @FXML
    private Circle cir;
   
      
     public Pane createPane(Annonce a ) {
        //Vars.current_user;
        Pane p = new Pane();
        ImageView imgd=new ImageView();
//         System.out.println("cbx"+cbx.getValue());
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
          
        Label titre = new Label();
        Label type = new Label();
        Label description = new Label();
        Label prix = new Label();
        HBox hbox=new HBox();
        HBox hbox1=new HBox();
        JFXButton aff =new JFXButton("Afficher");
        aff.setLayoutX(200);
        aff.setLayoutY(200); 
        aff.setVisible(true);
        aff.setStyle("-fx-background-color: #2196F3;-fx-text-fill: #ffffff; -fx-font-size: 14px;    -fx-cursor: hand;");
        
        aff.setOnAction((ActionEvent event) -> {  try {
            Vars.current_annonce=a;
            Parent p3=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherAnnonce.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p3);
            
            stage.setTitle("Afficher Annonce");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();

        } catch (IOException ex) {
            Logger.getLogger(AfficherAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        });
        JFXButton des =new JFXButton("Désactiver");
        des.setLayoutX(150);
        des.setLayoutY(150); 
        des.setVisible(false);
        if(pi_javafx.userentites.Vars.current_user.getId_user()==a.getIdu() || pi_javafx.userentites.Vars.current_user.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
        des.setVisible(a.isActive());
        }
        des.setStyle("-fx-background-color: #2196F3;-fx-text-fill: #ffffff; -fx-font-size: 14px;    -fx-cursor: hand;");

        des.setOnAction((ActionEvent event) -> {  try {
            as.DesactiverAnnonce(a);
            Parent p8=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p8);
            
            stage.setTitle("Afficher Annonce");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        });
        
        JFXButton act =new JFXButton("Activer");
        act.setLayoutX(150);
        act.setLayoutY(150); 
        act.setVisible(false);
//        if(Vars.current_user.getId_user()==a.getIdu() || pi_javafx.userentites.Vars.current_user.getRoles().equals("a:1:{i:0;s:10:"ROLE_ADMIN";}")){
        act.setVisible(!a.isActive());
//     }
        act.setStyle("-fx-background-color: #2196F3;-fx-text-fill: #ffffff; -fx-font-size: 14px;    -fx-cursor: hand;");

        act.setOnAction((ActionEvent event) -> {  try {
            as.ActiverAnnonce(a);
            Parent p8=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
            
            
            Stage stage=new Stage();
            Scene scene = new Scene(p8);
            
            stage.setTitle("Afficher Annonce");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AfficherAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        });
        
        hbox.setSpacing(10);
        hbox.getChildren().add(aff);
        if(a.isActive())
        hbox.getChildren().add(des);
        if(!a.isActive())
        hbox.getChildren().add(act);
        
        titre.setText("Titre : "+a.getTitre());
        titre.setWrapText(false);
        titre.setPrefSize(435, 50);
        titre.setLayoutX(150);
        titre.setLayoutY(1);
        titre.setMaxWidth(435);
        
        type.setText("Type : "+a.getType());
        type.setWrapText(false);
        type.setPrefSize(435, 50);
        type.setLayoutX(150);
        type.setLayoutY(1);
        type.setMaxWidth(435);
        
        description.setText("Date : "+String.valueOf(a.getDate()));
        description.setWrapText(false);
        description.setPrefSize(435, 50);
        description.setMaxWidth(435);
        description.setLayoutX(150);
        description.setLayoutY(13);
        
        prix.setText("Prix : "+a.getPrix()+"\n");
        prix.setWrapText(false);
        prix.setPrefSize(435, 50);
        prix.setMaxWidth(435);
        prix.setLayoutX(150);
        prix.setLayoutY(26);
        
        // An image file on the hard drive.
        File file = new File("C:/Users/Aloui Omar/Downloads/uploads/uploads/myphoto.jpg");
 
        // --> file:/C:/MyImages/myphoto.jpg
        String localUrl = null;
        try {
            localUrl = file.toURI().toURL().toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(AfficherListeAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        Image image = new Image(localUrl);
        imgd = new ImageView(image);
        
        
        //String hhh=d.getImageId().getAlt().substring(0,d.getImageId().getAlt().length()-1);
        
     // img = new javafx.scene.image.Image("http://127.0.0.1/bonplan/Projet_pidev/web/uplods/images/"+d.getImageId().getUrl()+".png");
                    BufferedImage bufferedImage = null;
        try {
            //System.out.println("-------------------------------------"+a.getPhoto());
            bufferedImage = ImageIO.read(new File("C:\\xampp\\htdocs\\pi\\web\\uploads\\annonce\\"+a.getPhoto()));
        } catch (IOException ex) {
            Logger.getLogger(AfficherListeAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
                WritableImage imageImported = SwingFXUtils.toFXImage(bufferedImage, null);
            imgd.setImage(imageImported);

//         System.out.println("/edu/velo/uploads/"+a.getPhoto());
//        img = new javafx.scene.image.Image("/edu/velo/uploads/"+a.getPhoto());
//            imgd.setImage(img);
//        imgd.setImage(img);
        imgd.setFitHeight(250);
        imgd.setFitWidth(350);
//        if (Vars.current_admin==1)
//        {
//            aff.setVisible(true);
//            des.setVisible(true);
//        }
        gr.setItems(dd); 
        
//        gr1.setItems(dd); 
        
        VBox vbox=new VBox();
        
//        vbox.getChildren().add(imgd);     
        vbox.getChildren().add(titre);
        vbox.getChildren().add(type);
        vbox.getChildren().add(description);
        vbox.getChildren().add(prix);
        vbox.getChildren().add(hbox);
//        p.getChildren().add(aff);
//        p.getChildren().add(des);
                hbox1.setSpacing(10);

        hbox1.getChildren().add(imgd);
        hbox1.getChildren().add(vbox);
        p.getChildren().add(hbox1);
        return p;
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        Vars.current_user=u1;
        
        btAdmin.setVisible(false);
        btSignal.setVisible(false);
        btStat.setVisible(false);
//        Vars.current_user.setId_user(pi_javafx.userentites.Vars.current_user.getId_user());
        pi_javafx.userentites.Vars.current_user.getRoles();
         System.out.println("######"+pi_javafx.userentites.Vars.current_user.getRoles()+"id"+pi_javafx.userentites.Vars.current_user.getId_user());
//         if(!pi_javafx.userentites.Vars.current_user.getRoles().equals("a:0:{})")){
//        Vars.current_user.setRole("a:1:{i:0;s:10:"ROLE_ADMIN";}");
//    }
        if(pi_javafx.userentites.Vars.current_user.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
         btAdmin.setVisible(true);
         btSignal.setVisible(true);   
         btStat.setVisible(true);   
        }
                     
                            if(Vars.current_choice== 0)
                            { 
                                System.out.println("current choice"+Vars.current_choice);
                                
                                maliste = as.afficherAnnonces();}
                            else if(Vars.current_choice== 1){
                                System.out.println("current choice"+Vars.current_choice);
                               

                            maliste=as.RechercheCategorieAnnonces(Vars.current_type);
                            } else if(Vars.current_choice== 2){
                                System.out.println("current choice"+Vars.current_choice);
//                            maliste.removeAll(maliste);
                            maliste=as.afficherUserAnnonces(pi_javafx.userentites.Vars.current_user.getId_user());
                            }else if(Vars.current_choice== 3){
                                System.out.println("current choice"+Vars.current_choice);

                            maliste=as.RechercheAnnonces(Vars.current_type);
                            }else if(Vars.current_choice== 4){
                                System.out.println("current choice"+Vars.current_choice);

                            maliste=as.listerAnnonceParDate();
                            }else if(Vars.current_choice== 5){
                                System.out.println("current choice"+Vars.current_choice);

                            maliste=as.listerAnnonceParPrix();
                            }else if(Vars.current_choice== 6){
                                System.out.println("current choice"+Vars.current_choice);

                            maliste=as.listerAnnonceSignler();
                            }else if(Vars.current_choice== 7){
                                System.out.println("current choice"+Vars.current_choice);

                            maliste=as.afficherAdminAnnonces();
                            }


        List<Annonce> c = as.getCategorie();
        c.forEach(e->{
            cb.getItems().add(e.getCategorie());
        });
        cb.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                Vars.current_choice= 1;
                Vars.current_type=newValue;
                System.out.println("new value : "+newValue);
                System.out.println(cb.getValue());
            }
            
        });
        maliste.forEach(d->{
            Pane p = new Pane();
            p = createPane(d);
            dd.add(p);
        });
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
    private void afficherProd(ActionEvent event) {
        try {
            
            Parent p6=FXMLLoader.load(getClass().getResource("/gui/GestionProduit.fxml"));
            
            
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
    private void afficherLocation(ActionEvent event) {
        try {
            
            Parent p6=FXMLLoader.load(getClass().getResource("/gui/FXMLHome.fxml"));
            
            
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
    private void afficherevent(ActionEvent event) {
        try {
            
            Parent p6=FXMLLoader.load(getClass().getResource("/eventsa/Evenement.fxml"));
            
            
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
    private void affichercircuit(ActionEvent event) {
        try {
            
            Parent p6=FXMLLoader.load(getClass().getResource("/gui/MainHome.fxml"));
            
            
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
