/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfx;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Raef
 */
public class PidevFx extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
      //  if(){
    Parent root=FXMLLoader.load(getClass().getResource("/gui/GestionProduit.fxml"));
    Scene scene=new Scene(root);
    stage.setScene(scene);
    stage.show();
//        }else { Parent root=FXMLLoader.load(getClass().getResource("/gui/GestionProduit.fxml"));
//    Scene scene=new Scene(root);
//    stage.setScene(scene);
//    stage.show();
//}
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}