/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.ServiceRechange;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import entities.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Raef
 */
public class AfficherRechangeUserController implements Initializable {

    @FXML
    private ScrollPane scrallPane;
    @FXML
    private AnchorPane productDetails;
    @FXML
    private Button btnCloseDetails;
    @FXML
    private Pane paneDetails;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
            Services.ServiceRechange sv = new ServiceRechange();
        

           ArrayList<Produit> lr;
       
            lr = (ArrayList<Produit>) sv.getRechanges();
            
            displayAll(lr);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRechangeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         productDetails.setVisible(false);
        
    }    
    
    
      public void displayAll(List<Produit> lp) {
      
            VBox vbox = new VBox();
//        int i = 0;
//        int j = 0;
//        HBox hbox = new HBox();
        for (Produit p : lp) {
//            if (i == 0) {
//                hbox = new HBox();
//                hbox.setSpacing(10);
//            }
            //Product current = lp.get(i);
            //System.out.println(current);

            Pane postpane = new Pane();
            postpane.setPrefHeight(256);
            postpane.setPrefWidth(500);
          
            final ImageView postback = new ImageView();
            postback.setFitHeight(256);
            postback.setFitWidth(500);
            Image back = new Image("/img/dd.png");
            
            
            final ImageView productImage = new ImageView();
            productImage.setLayoutX(7);
            productImage.setLayoutY(8);
            productImage.setFitHeight(190);
            productImage.setFitWidth(700);
            productImage.setImage(back);
            postpane.getChildren().add(productImage);
            System.out.println(p.getPhoto_P());
            
            FileInputStream thumb = null;
            try {
                thumb = new FileInputStream("C:\\wamp64\\www\\PiSymfony\\web\\public\\uploads\\" + p.getPhoto_P());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AfficherVeloController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image imagee = new Image(thumb);
            
            final ImageView thumbnail = new ImageView();
            thumbnail.setLayoutX(15);
            thumbnail.setLayoutY(15);
            thumbnail.setFitHeight(170);
            thumbnail.setFitWidth(170);
            thumbnail.setImage(imagee);
            thumbnail.setOpacity(1);
            
            final Label nameLabel = new Label("Nom :  "+p.getNom_P());
            System.out.println(nameLabel);
            postpane.getChildren().add(nameLabel);
            nameLabel.setLayoutX(200);
            nameLabel.setLayoutY(15);
            nameLabel.setTextFill(Color.web("#fff"));
            nameLabel.setFont(new Font("Arial", 30));
            
            Label priceLabel = new Label();
            priceLabel.setText("Prix :  "+String.valueOf(p.getPrix_P()));
            System.out.println(priceLabel);
            postpane.getChildren().add(priceLabel);
            priceLabel.setLayoutX(200);
            priceLabel.setLayoutY(50);
            priceLabel.setTextFill(Color.web("#fff"));
            priceLabel.setFont(new Font("Arial", 20));
            //   nameLabel.setVisible();

            postpane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    productDetails.setVisible(true);
                    paneDetails.getChildren().clear();
                    Label l = new Label(p.getNom_P());
                    l.setLayoutX(10);
                    
                    l.setTextFill(Color.web("#fff"));
                    l.setFont(new Font("Arial", 30));
                    Label l2 = new Label(String.valueOf(p.getPrix_P()) + "DT");
                    l2.setLayoutX(10);
                    l2.setLayoutY(40);
                    l2.setTextFill(Color.web("#fff"));
                    l2.setFont(new Font("Arial", 30));
                    paneDetails.getChildren().addAll(l, l2);
                    createQR(p, productDetails);
                    
                }
            });
            
            postpane.getChildren().add(thumbnail);

            //   productPane.getChildren().add(postpane);
          //  hbox.getChildren().add(postpane);
            
            
                vbox.getChildren().add(postpane);
            
            
        }
//            for (int i = 0; i < lp.size(); i++) {
//               
//      
//            }
        scrallPane.setContent(vbox);
          
          
    
}
      
  public void createQR(Produit p, AnchorPane root) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = p.toString();
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(java.awt.Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(java.awt.Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            System.err.println("error de code qr");
        }
        
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
        root.getChildren().add(qrView);
    }
    
    @FXML
    private void btnCloseDetailsAction(ActionEvent event) {
        productDetails.setVisible(false);
    }
    
    
}
