/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.ServiceAccessoire;
import com.mysql.jdbc.Connection;

import Services.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.MaConnection;
import entities.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.embed.swing.SwingFXUtils;

import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Raef
 */
public class AfficherVeloController implements Initializable {
    Connection cnx;
    ServiceVelo sv;
    @FXML
    private Button btn_afficher;
    @FXML
    private TableView<Produit> tf_table;
    @FXML
    private TableColumn<Produit, String> tf_nom;
    @FXML
    private TableColumn<Produit, String> tf_marque;
    @FXML
    private TableColumn<Produit, String> tf_cat;
    @FXML
    private TableColumn<Produit, String> tf_couleur;
    @FXML
    private TableColumn<Produit, Float> tf_prix;
    @FXML
    private TableColumn<Produit, Date> tf_date;
    @FXML
    private TableColumn<Produit, String> tf_photo;
    @FXML
    private Button btn_supprimer;
    @FXML
    private ImageView img_affiche;
    @FXML
    private ImageView supp_img;
    @FXML
    private Button refrech_price_;
    @FXML
    private TextField f11;
    @FXML
    private TextField f22;
    private ScrollPane scrallPane;
    @FXML
    private TableColumn<Produit, Integer> tf_tel;
    
  
  
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        try {
            sv = new ServiceVelo();
        

           ArrayList<Produit> lv;
       
            lv = (ArrayList<Produit>) sv.getVelos();
            ObservableList<Produit> data = FXCollections.observableArrayList(lv);
            tf_nom.setCellValueFactory(new PropertyValueFactory<>("nom_P"));
            tf_marque.setCellValueFactory(new PropertyValueFactory<>("marque_P"));
            tf_cat.setCellValueFactory(new PropertyValueFactory<>("categorie_P"));
            tf_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur_P"));
            tf_prix.setCellValueFactory(new PropertyValueFactory<>("prix_P"));
            tf_date.setCellValueFactory(new PropertyValueFactory<>("date"));
             tf_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            tf_photo.setCellValueFactory(new PropertyValueFactory<>("photo_P"));
            tf_table.setItems(data);
          
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVeloController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
    }    

    @FXML
    private void Supprimer_velo(ActionEvent event)throws SQLException, IOException {
        
        
        Produit v=tf_table.getSelectionModel().getSelectedItem();
        
        if(v==null){
        
           System.out.println("Veillez choisir un vélo");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("choose velo");
            alert.setHeaderText(null);
            alert.setContentText("please choose the bike to delete !");

            alert.showAndWait();
     
        }else {
        
        ServiceVelo sv=new ServiceVelo();
        String nom_P = v.getNom_P();
        
         try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Remove plant..");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete! " + v.getNom_P());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    sv.deleteVelo(v);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("DELETE VELO");
                    alert1.setHeaderText(null);
                    alert1.setContentText("the bike is removed");

                    alert1.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
         loadData();
         refresh_velo();
        }
        
 
        
        
    }
    
    
    public void loadData(){
    ObservableList<Produit> data = null;

        try {
            data = FXCollections.observableArrayList(new ServiceVelo().getVelos());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
     private void refresh_velo() throws SQLException {
         try {
            sv = new ServiceVelo();
        

           ArrayList<Produit> lv;
       
            lv = (ArrayList<Produit>) sv.getVelos();
            ObservableList<Produit> data = FXCollections.observableArrayList(lv);
            tf_nom.setCellValueFactory(new PropertyValueFactory<>("nom_P"));

            tf_marque.setCellValueFactory(new PropertyValueFactory<>("marque_P"));
            tf_cat.setCellValueFactory(new PropertyValueFactory<>("categorie_P"));
            tf_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur_P"));
            tf_prix.setCellValueFactory(new PropertyValueFactory<>("prix_P"));
            tf_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                        tf_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            tf_photo.setCellValueFactory(new PropertyValueFactory<>("photo_P"));
            tf_table.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVeloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Afficher_velo(ActionEvent event) throws IOException {
        
        
        Produit v = tf_table.getSelectionModel().getSelectedItem();
        if (v == null) {
            System.out.println("choose bike");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning ");
            alert.setHeaderText("Please choose bike");
            alert.setContentText("Please choose bike");

            alert.showAndWait();
    }
       else {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/gui/VeloRechercher.fxml"));
            Scene scene = new Scene(loader.load());
            VeloRechercherController ct = loader.getController();
            ct.setVelo(v);
            Stage stageAff = new Stage();
            stageAff.setScene(scene);

            stageAff.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        }
    

    
    }

    @FXML
    private void refrech_price_action(ActionEvent event) {
        try {
            refrech_price(Float.parseFloat(f11.getText()), Float.parseFloat(f22.getText()));

        } catch (Exception e) {
        }
    }
      
        
    
    
    
    private void refrech_price(float f1 ,float f2) {
        
     
           try {
            sv = new ServiceVelo();
        

           ArrayList<Produit> la;
       
            la = (ArrayList<Produit>) sv.FiltrerVeloByprix(f1, f2);
            ObservableList<Produit> data = FXCollections.observableArrayList(la);
            tf_nom.setCellValueFactory(new PropertyValueFactory<>("nom_P"));
        
            tf_marque.setCellValueFactory(new PropertyValueFactory<>("marque_P"));
            tf_cat.setCellValueFactory(new PropertyValueFactory<>("categorie_P"));
            tf_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur_P"));
            tf_prix.setCellValueFactory(new PropertyValueFactory<>("prix_P"));
            tf_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                tf_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            tf_photo.setCellValueFactory(new PropertyValueFactory<>("photo_P"));
            tf_table.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVeloController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }

    
    public void displayAll(List<Produit> lp) {
        VBox vbox = new VBox();
        int i = 0;
        int j = 0;
        HBox hbox = new HBox();
        for (Produit p : lp) {
            if (i == 0) {
                hbox = new HBox();
                hbox.setSpacing(10);
            }
            //Product current = lp.get(i);
            //System.out.println(current);

            Pane postpane = new Pane();
            postpane.setPrefHeight(256);
            postpane.setPrefWidth(200);

            final ImageView postback = new ImageView();
            postback.setFitHeight(256);
            postback.setFitWidth(200);
            Image back = new Image("/img/images.png");

            final ImageView productImage = new ImageView();
            productImage.setLayoutX(7);
            productImage.setLayoutY(8);
            productImage.setFitHeight(190);
            productImage.setFitWidth(190);
            productImage.setImage(back);
            postpane.getChildren().add(productImage);
            System.out.println(p.getPhoto_P());
            
            
            
            FileInputStream thumb = null;
            try {
                thumb = new FileInputStream("C:\\wamp64\\www\\" + p.getPhoto_P());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AfficherVeloController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image imagee = new Image(thumb);
            

            final ImageView thumbnail = new ImageView();
            thumbnail.setLayoutX(15);
            thumbnail.setLayoutY(15);
            thumbnail.setFitHeight(100);
            thumbnail.setFitWidth(100);
            thumbnail.setImage(imagee);
            thumbnail.setOpacity(1);

            final Label nameLabel = new Label(p.getNom_P());
            System.out.println(nameLabel);
            postpane.getChildren().add(nameLabel);
            nameLabel.setLayoutX(15);
            nameLabel.setLayoutY(125);
            nameLabel.setTextFill(Color.web("#fff"));
            nameLabel.setFont(new Font("Arial", 30));
            
        
             Label priceLabel = new Label();
             priceLabel.setText(String.valueOf(p.getPrix_P()));
            System.out.println(priceLabel);
            postpane.getChildren().add(priceLabel);
            nameLabel.setLayoutX(30);
            nameLabel.setLayoutY(140);
            nameLabel.setTextFill(Color.web("#fff"));
            nameLabel.setFont(new Font("Arial", 30));
         //   nameLabel.setVisible();

            
            
            

           
           
            postpane.getChildren().add(thumbnail);

            //   productPane.getChildren().add(postpane);
            hbox.getChildren().add(postpane);
            i++;
            j++;
            if (i > 4) {
                i = 0;
                vbox.getChildren().add(hbox);
            }
            if (lp.size() - j == 0 && j % 5 != 0) {
                vbox.getChildren().add(hbox);
            }
        }
//            for (int i = 0; i < lp.size(); i++) {
//               
//      
//            }
        scrallPane.setContent(vbox);

    }
    
    
    void pdf() {
 System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.tf_table ;
    
           job.printPage(root);
           job.endJob();
            
       

  }}

    private void PdfAction(ActionEvent event) {
        
        pdf();
    }
    
    
    
    
}