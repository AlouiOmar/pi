/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import edu.velo.entities.Annonce;
import edu.velo.services.AnnonceServices;
import edu.velo.util.Email;
import edu.velo.util.Vars;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import org.apache.commons.text.RandomStringGenerator;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import pi_javafx.Controller.ProfilController;
import pi_javafx.userentites.fos_user;
/**
 * FXML Controller class
 *
 * @author Aloui Omar
 */
public class AjouterAnnonceController implements Initializable {

    /**
     * Initializes the controller class.
     */
     AnnonceServices as = new AnnonceServices();
     Email email=new Email();
         Annonce a=new Annonce();
          @FXML
         private JFXComboBox ty1;
         @FXML
         private Label tv;
         @FXML
         private Label couleur1;
         @FXML
         private Label lcat;
         @FXML
         private Label lgv;
         @FXML
         private Label lty;
         @FXML
         private Label ldate;
         @FXML
         private Label lprix;
         @FXML
         private Label ldescription;
         @FXML
         private JFXComboBox cat; 
          @FXML
         private JFXComboBox gv;
         @FXML
         private JFXTextField prix;
         @FXML
         private JFXTextField type;
         @FXML
         private JFXTextField titre;
         @FXML
         private JFXTextField pr;
         @FXML
         private JFXTextField categorie;
         @FXML
         private JFXTextField couleur;
         @FXML
         private JFXTextArea description;
         @FXML
         private JFXDatePicker date;
         @FXML
         private JFXDrawer drawer;
         @FXML
         private JFXHamburger hamburger;
         @FXML
         private VBox box;
         @FXML
         private JFXTextField tf;
         @FXML
         private ImageView imaged;
         @FXML
         private JFXComboBox ty;
         @FXML
         private JFXButton btSignal1;
         @FXML
         private JFXButton btStat;
         @FXML
         private JFXButton btAdmin;
        
         private String imageUrl;
         private File file1;
         private String filename;
         @FXML
         private Label ltitre;
         @FXML
         private Label lup;
         @FXML
         private JFXButton up;
         @FXML
    private ComboBox<String> cbx;
    @FXML
    private Circle cir;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("date "+date.getValue());
        System.out.println(filename);
        System.out.println("prix "+prix.getText()); 
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
        btAdmin.setVisible(false);
        btSignal1.setVisible(false);
        btStat.setVisible(false);
        if(pi_javafx.userentites.Vars.current_user.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
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
         
                     ty1.getItems().addAll("Tout chemin","Hollandais","Tout terrain","Enfant","Course","Pliant","Couché","Remorques");
                    ty1.setDisable(true);       
                    couleur.setDisable(true);
         cat.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                if(cat.getValue().equals("Vélo"))
                    {
                    ty1.setDisable(false);       
                    couleur.setDisable(false);

                    }else if(cat.getValue().equals("Pièce de rechange"))
                    {
                    ty1.setDisable(true);       
                    couleur.setDisable(true);

                    }else if(cat.getValue().equals("Accessoire"))
                    {
                    ty1.setDisable(true);       
                    couleur.setDisable(true);

                    }
                System.out.println("new value : "+newValue);
            }
            
        });
         
         ty.getItems().addAll("Vente","Location");
//            ty.getItems().add("location");
            
            cat.getItems().addAll("Vélo","Pièce de rechange","Accessoire");
//            cat.getItems().add("piece");
//            cat.getItems().add("accessoire");
            gv.getItems().addAll("Ariana","Béja","Ben Arous","Bizerte","Gabès","Gafsa","Jendouba","Kairouan","Kasserine","Kébili","Le Kef","Mahdia","La Manouba","Médenine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine","Tozeur","Tunis","Zaghouan");
    }    
    @FXML
    private void AjouterAnnonce(ActionEvent event) throws IOException, JSONException {
       
        
        
//         if(ty.getValue().equals("vente"))
//        {a.setType("vente");}
//         else if(ty.getValue().equals("location"))
//        {a.setType("locaton");}
//        a.setType(type.getText());
       
//        if(cat.getValue().equals("velo"))
//        {
//        a.setCategorie("velo");
//        
//        }
//        else if(cat.getValue().equals("piece"))
//        {
//             a.setCategorie("velo");
//        }
//        else{ a.setCategorie("accessoire");}
    
//        a.setCategorie( categorie.getText());
        
       
        
        
        //############server#######################
//        String charset = "UTF-8";
//            String requestURL = "http://localhost:3000/upload";
// 
//       
//            MultiPart multipart = new MultiPart(requestURL,charset);
//             
//            multipart.addHeaderField("User-Agent", "CodeJava");
//            multipart.addHeaderField("Test-Header", "Header-Value");
//             
//            multipart.addFormField("description", "Cool Pictures");
//           // multipart.addFormField("photo", file1.getAbsolutePath());
//                           FileBody data = new FileBody(fileToUse);
//
//            multipart.addFilePart("photo", new File(file1.getAbsolutePath()));
//             System.out.println("fileUpload "+file1.getAbsolutePath());
//
//            List<String> response = multipart.finish();
//             
//            System.out.println("SERVER REPLIED:");
//             
//            for (String line : response) {
//                System.out.println(line);}
        
        
       
        //#########################################
            titre.setStyle(" -jfx-focus-color: #4059a9 ");
            ltitre.setStyle("-fx-text-fill: #000000");
            cat.setStyle(" -jfx-focus-color: #4059a9 ");
            lcat.setStyle("-fx-text-fill: #000000");
            gv.setStyle(" -jfx-focus-color: #4059a9 ");
            lgv.setStyle("-fx-text-fill: #000000");
            ty.setStyle(" -jfx-focus-color: #4059a9 ");
            lty.setStyle("-fx-text-fill: #000000");
            date.setStyle(" -jfx-focus-color: #4059a9 ");
            ldate.setStyle("-fx-text-fill: #000000");    
            prix.setStyle(" -jfx-focus-color: #4059a9 ");
            lprix.setStyle("-fx-text-fill: #000000");
            ty1.setStyle(" -jfx-focus-color: #4059a9 ");
            tv.setStyle("-fx-text-fill: #000000");
            couleur.setStyle(" -jfx-focus-color: #4059a9 ");
            couleur1.setStyle("-fx-text-fill: #000000");
            up.setStyle(" -fx-background-color:  #2196F3 ");
            lup.setStyle("-fx-text-fill: #000000");
            description.setStyle(" -jfx-focus-color: #4059a9 ");
            ldescription.setStyle("-fx-text-fill: #000000");
        if(verifierChamps()){
                            a.setTitre(titre.getText());

                    a.setPrix(Double.parseDouble(prix.getText()));
                            a.setType((String) ty.getValue());
        a.setPhoto(filename);

        a.setCategorie((String) cat.getValue());
        a.setGouvernorat((String) gv.getValue());
        System.out.println(cat.getValue());
          a.setCouleur(couleur.getText());
        a.setActive(true);
        LocalDate dd = date.getValue();
        Date dated = Date.from(dd.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date dateds = new java.sql.Date(dated.getTime());
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        a.setDatep(date);
        a.setDate(dateds);
        a.setTypevelo((String) ty1.getValue());
        a.setDescription(description.getText()); 

        if(cat.getValue().equals("Vélo"))
                    {
                    as.AjouterVeloAnnonce(a,pi_javafx.userentites.Vars.current_user.getId_user());

                    }
        if(cat.getValue().equals("Pièce de rechange") || cat.getValue().equals("Accessoire"))
                    {
                                as.AjouterAnnonce(a,pi_javafx.userentites.Vars.current_user.getId_user());

                    }
        String txtDate=new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(new Date());
        String contenuMessage="Votre annonce dont le titre est "+a.getTitre()+" de type "+a.getType()+" a été ajoutée avec succés au "+txtDate;
        String objetMessage="Confirmation de l'ajout de l'annonce";
        String emailDestination=pi_javafx.userentites.Vars.current_user.getEmail();
        email.envoyer(emailDestination, objetMessage, contenuMessage);
            Vars.current_choice=2;
            Parent p4=FXMLLoader.load(getClass().getResource("/edu/velo/gui/AfficherListeAnnonces.fxml"));
            Stage stage=new Stage();
            Scene scene = new Scene(p4);
            
            stage.setTitle("Mes Annonces");
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();}

    }
    
    
        @FXML
    private void annuler(ActionEvent event) {
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
    private void clicki(ActionEvent event) {
        
        System.out.println("clickeeeeeeeeeeed");
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
    private File importBtn(ActionEvent event) throws JSONException {
        
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose image");
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            File file = fileChooser.showOpenDialog(null);
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage imageImported = SwingFXUtils.toFXImage(bufferedImage, null);
            imaged.setImage(imageImported);
            imageUrl = file.getAbsolutePath();
            System.out.println("image url import btn"+imageUrl.toString());
            file1=new File(imageUrl);
            
             HttpClient httpclient = new DefaultHttpClient();
              HttpPost httppost = new HttpPost("http://localhost:3000/upload");

              httppost.addHeader("Accept", "application/json");

              File fileToUse = new File(file1.getAbsolutePath()); //e.g. /temp/dinnerplate-special.jpg
              FileBody data = new FileBody(fileToUse);

              String file_type = "JPG" ;
              String description = "Oppa Gangnam Style";
              String folder_id = "-1";
              String source = "MYCOMPUTER" ;

              MultipartEntity reqEntity = new MultipartEntity();
           
              reqEntity.addPart("photo", data);

              httppost.setEntity(reqEntity);

             HttpResponse response = httpclient.execute(httppost);
             System.out.println( response ) ;

             HttpEntity resEntity = response.getEntity();
             System.out.println( resEntity ) ;
              //System.out.println( "entity"+EntityUtils.toString(resEntity) );
              String af=EntityUtils.toString(resEntity);
              System.out.println("af##### "+af);
              JSONObject jo=new JSONObject(af);
              //jo=(JSONObject) response;
              //System.out.println(jo.toString());
              filename=(String) jo.get("filename");
              System.out.println(filename);
              System.out.println("filename: "+jo.get("filename"));
              
              EntityUtils.consume(resEntity);
              httpclient.getConnectionManager().shutdown();
            
            
            return file;
            
           
        } catch (IOException ex) {
            Logger.getLogger(AfficherAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
    
    public String createUploadedImage(){
        String title="";
        try {
            RandomStringGenerator randomStringGenerator =
            new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .filteredBy(DIGITS)
                .build();
            title=randomStringGenerator.generate(16).toLowerCase(); 
            File file = new File("C:\\codenameone\\velo11\\src\\edu\\velo\\uploads"+title+".jpg");
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(imaged.getImage(), null);
            ImageIO.write(renderedImage,"png",file);
        } catch (IOException ex) {
            Logger.getLogger(AfficherAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return title;
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
    
    private boolean verifierChamps () {
       String titre5="";
       titre5=titre.getText();
       String categorie5="";
       categorie5=(String) cat.getValue();
       String gouvernorat5="";
       gouvernorat5=(String) gv.getValue();
       String type5="";
       type5=(String) ty.getValue();
       String date5="";
       if(date.getValue()!=null){
       LocalDate d=date.getValue();
       String pattern = "dd/MM/yyyy";
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
       date5= d.format(formatter);}
       String prix5="";
       prix5=prix.getText();
       String typeVelo5="";
       typeVelo5=(String) ty1.getValue();
       String couleur5="";
       couleur5=couleur.getText();
       String photo5 =filename;
       String description5="";
       description5=description.getText();
        System.out.println("test verifierChamps() =================>>"+titre5+categorie5+gouvernorat5+type5+date5+prix5+typeVelo5+couleur5+photo5+description5);
        
        
            
            
        if(titre5.equals(""))
        {
            titre.setStyle(" -jfx-focus-color: #FF0000 ");
            ltitre.setStyle("-fx-text-fill: #FF0000");
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Champ titre est vide");
            alert.showAndWait();
            
             return false;

        }
        if(cat.getValue()==null)
        {
            cat.setStyle(" -jfx-focus-color: #FF0000 ");
            lcat.setStyle("-fx-text-fill: #FF0000");
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Champ catégorie est vide");
            alert.showAndWait();
            
             return false;
        }
        if(gv.getValue()==null)
        {
            gv.setStyle(" -jfx-focus-color: #FF0000 ");
            lgv.setStyle("-fx-text-fill: #FF0000");
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Champ gouvernorat est vide");
            alert.showAndWait();
            
             return false;
        }
        if(ty.getValue()==null)
        {
            ty.setStyle(" -jfx-focus-color: #FF0000 ");
            lty.setStyle("-fx-text-fill: #FF0000");
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Champ type est vide");
            alert.showAndWait();
            
            return false;
        }
        if(date.getValue()==null)
        {
            date.setStyle(" -jfx-focus-color: #FF0000 ");
            ldate.setStyle("-fx-text-fill: #FF0000");
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Champ date est vide");
            alert.showAndWait();
            
            return false;
        }
        if((prix.getText()).equals(""))
        {
            prix.setStyle(" -jfx-focus-color: #FF0000 ");
            lprix.setStyle("-fx-text-fill: #FF0000");
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Champ prix est vide");
            alert.showAndWait();
            
            return false;
        }
        try
        {
          Double.parseDouble(prix.getText());
        }
        catch(NumberFormatException e)
        {
          //not a double
            prix.setStyle(" -jfx-focus-color: #FF0000 ");
            lprix.setStyle("-fx-text-fill: #FF0000");
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Saisir un prix valide les caractères spéciaux et alphabétiques ne sont pas autorisés !");
            alert.showAndWait();
            
            return false;
        }
       
        if(categorie5.equals("Vélo")){
        if(ty1.getValue()==null)
        {
            ty1.setStyle(" -jfx-focus-color: #FF0000 ");
            tv.setStyle("-fx-text-fill: #FF0000");
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Champ type vélo est vide");
            alert.showAndWait(); 
            
            return false;
        }
        if((couleur.getText()).equals(""))
        {
            couleur.setStyle(" -jfx-focus-color: #FF0000 ");
            couleur1.setStyle("-fx-text-fill: #FF0000");
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Champ couleur est vide");
            alert.showAndWait(); 
            
            return false;
        }}
        if(filename==null)
        {
            up.setStyle(" -fx-background-color: #FF0000 ");
            lup.setStyle("-fx-text-fill: #FF0000");
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir une photo");
            alert.showAndWait(); 
            
            return false; 
        }
        if((description.getText()).equals(""))
        {
            description.setStyle(" -jfx-focus-color: #FF0000 ");
            ldescription.setStyle("-fx-text-fill: #FF0000");
            Alert alert = new  Alert(Alert.AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Champ description est vide");
            alert.showAndWait(); 
            
            return false;
        }
        
        //||categorie5.equals("")||gouvernorat5.equals("")||type5.equals("")||date5.equals("")||prix5.equals("")||typeVelo5.equals("")||couleur5.equals("")||description5.equals("")
        
        
        return true;
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
