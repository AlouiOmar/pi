/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractList;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import edu.velo.userentites.Utilisateur;
import edu.velo.userservices.ServiceUser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ListeUtilisateursController implements Initializable {
    
 private Connection cnx; 

  @FXML private TableView<Utilisateur> tableview;
//        @FXML private TableColumn<Utilisateur,Integer> tid;
         @FXML private TableColumn<Utilisateur,String> tcnom;
         @FXML private TableColumn<Utilisateur,String> tcprenom;
         @FXML private TableColumn<Utilisateur,String> tcemail;
         @FXML private TableColumn<Utilisateur,String> tcpwd;
         @FXML private TableColumn<Utilisateur,Integer> tcage;
         @FXML private TableColumn<Utilisateur,Integer> tctelephone;
         @FXML private TableColumn<Utilisateur,String> tcrole;
         @FXML private Label labelliste;
         @FXML private TextField chercher;
         @FXML private Button btnchercher;
          
     @FXML private Button ajouter;
      
         @FXML private Button supprimer;
        @FXML
                
                
  ServiceUser u = new ServiceUser();        
ObservableList listuu = FXCollections.observableArrayList();
      
          
     //methode supprimer utilisateur:
        public void deleteUtilisateur() throws SQLException
                
    {
     ObservableList<Utilisateur> SelectedRows, allpeople;
     
     allpeople = tableview.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tableview.getSelectionModel().getSelectedItems();
     
     for(Utilisateur gg:SelectedRows){
          Alert alert = new  Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation Dialoge");
            alert.setHeaderText(null);
            alert.setContentText(" are you sur to delete");
            Optional <ButtonType> action = alert.showAndWait();
            
            if(action.get() == ButtonType.OK ){
                allpeople.remove(gg);
                u.deleteUtilisateur(gg);
                
     }
           
       
     }
  
    }
      //fin methode supprimer utilisateur 
        
        
     
      
        
        
        
        // changer les information dans la base de données
        
     public void Change_Nom(TableColumn.CellEditEvent bb) throws SQLException{
     Utilisateur utilisateurselected = tableview.getSelectionModel().getSelectedItem();
     utilisateurselected.setNom(bb.getNewValue().toString());
     u.updateUtilisateur(utilisateurselected);
     }
     
      public void Change_Prenom(TableColumn.CellEditEvent b) throws SQLException{
     Utilisateur utilisateurselected = tableview.getSelectionModel().getSelectedItem();
     utilisateurselected.setPrenom(b.getNewValue().toString());
     u.updateUtilisateur(utilisateurselected);
     }   
        
   
     public void Change_Email(TableColumn.CellEditEvent b) throws SQLException{
     Utilisateur utilisateurselected = tableview.getSelectionModel().getSelectedItem();
     
     utilisateurselected.setEmail(b.getNewValue().toString());
     
     u.updateUtilisateur(utilisateurselected);
     }
     
     
      public void Change_Age(TableColumn.CellEditEvent b) throws SQLException{
     Utilisateur utilisateurselected = tableview.getSelectionModel().getSelectedItem();
     utilisateurselected.setAge(Integer.parseInt(b.getNewValue().toString()));
     u.updateUtilisateur(utilisateurselected);
     }
    
      
       public void Change_Telephone(TableColumn.CellEditEvent b) throws SQLException{
     Utilisateur utilisateurselected = tableview.getSelectionModel().getSelectedItem();
     utilisateurselected.setTelephone(Integer.parseInt(b.getNewValue().toString()));
     u.updateUtilisateur(utilisateurselected);
     }
      
       public void Change_Password(TableColumn.CellEditEvent b) throws SQLException{
     Utilisateur utilisateurselected = tableview.getSelectionModel().getSelectedItem();
     utilisateurselected.setPassword(b.getNewValue().toString());
     u.updateUtilisateur(utilisateurselected);
     }  
       
      public void Change_Role(TableColumn.CellEditEvent bb) throws SQLException{
     Utilisateur utilisateurselected = tableview.getSelectionModel().getSelectedItem();
      utilisateurselected.setRole(bb.getNewValue().toString());
     u.updateUtilisateur(utilisateurselected);
     }
      
      
      
      
      
      
      
      // methode ajouter :    
      public void Ajouter_Utilisateur(ActionEvent event) throws IOException, SQLException{
          //afficher l'interface inscription:
          
    Parent tableview = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
       
      
      
      
      
      
      
      
         // methode chercher :
      
        public void Recherche_Utilisateur( ) throws SQLException{
            String text = chercher.getText();
              ObservableList<Utilisateur> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(Utilisateur bb: u.RechercherUtilisateur(text))
             listu.add(bb);
            tableview.setItems(listu); 
     
     } catch (SQLException ex) {
         Logger.getLogger(ListeUtilisateursController.class.getName()).log(Level.SEVERE, null, ex);
     }   
 
       }
      
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
        
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
       // récuperer les données a partir de  la base 
          ObservableList<Utilisateur> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(Utilisateur bb: u.getTrier())
             listu.add(bb);
          
     } catch (SQLException ex) {
         Logger.getLogger(ListeUtilisateursController.class.getName()).log(Level.SEVERE, null, ex);
     }
         
//        try{
//    String req="SELECT * FROM user";
//            Statement s=project_pi.utile.Connection.getInstance().getConnection().createStatement();
//            ResultSet rs=s.executeQuery(req);
//            while(rs.next()){
//                
//        listuu.add( new Utilisateur(rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),
//             rs.getInt("age"),rs.getInt("telephone")
//        ,rs.getString("role"),rs.getString("password")));
//            }
//            
//        }    catch (SQLException ex) {
//         Logger.getLogger(ListeUtilisateursController.class.getName()).log(Level.SEVERE, null, ex);
//     }
            




         //mettre les données dans la table view:    
         tcnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         tcprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         tcemail.setCellValueFactory(new PropertyValueFactory<>("email"));
         tcpwd.setCellValueFactory(new PropertyValueFactory<>("password"));
         tcage.setCellValueFactory(new PropertyValueFactory<>("age"));
         tctelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
         tcrole.setCellValueFactory(new PropertyValueFactory<>("role"));
    
 
         tableview.setItems(listu);
         
         
         
         //autorisation de changer les cellules de table view:
          tableview.setEditable(true);
      tcnom.setCellFactory(TextFieldTableCell.forTableColumn());
      tcemail.setCellFactory(TextFieldTableCell.forTableColumn());
      tcprenom.setCellFactory(TextFieldTableCell.forTableColumn());
      tcage.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      tctelephone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      tcpwd.setCellFactory(TextFieldTableCell.forTableColumn());
      tcrole.setCellFactory(TextFieldTableCell.forTableColumn());
}
}
      
