/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.test;

import edu.velo.entities.Annonce;
import edu.velo.services.AnnonceServices;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Aloui Omar
 */
public class Main extends Application {
    
    public void start(Stage primaryStage) throws SQLException {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
        Parent root=null;
        try {
//            DealServices ds = new DealServices();
//            Deal d=ds.AfficherDeal(26);
//            UserServices us = new  UserServices();
//            FosUser u = new FosUser();
//            u.setId(1);
//            
//            Vars.current_choice=2;
          //  Vars.current_user=u;
//            Vars.current_deal=d;
////Vars.current_admin=1;

//       root = FXMLLoader.load(getClass().getResource("/edu/bonplan/gui/AfficherReservation.fxml"));
            root = FXMLLoader.load(getClass().getResource("/edu/velo/gui/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

Scene scene = new Scene(root);
        
        primaryStage.setTitle("Acceuil");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
