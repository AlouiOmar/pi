/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.userservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import edu.velo.userentites.Utilisateur;
import edu.velo.userinterface.IServiceUtilisateur;
import edu.velo.util.Connection;

/**
 *
 * @author USER
 */
public class ServiceUser  {
//    private FileInputStream fis;
//    private File file;

   
    private Connection cnx;
//     @Override
    public List<Utilisateur> getUtilisateurs()  throws SQLException {
  
        List<Utilisateur> listu = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM user";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {    
            Utilisateur a = new Utilisateur();
            a.setId_user(rs.getInt("id"));
            a.setNom(rs.getString("nom"));
            a.setPrenom(rs.getString("prenom"));
            a.setTelephone(rs.getInt("telephone"));
            a.setEmail(rs.getString("email"));
            a.setRole(rs.getString("role"));
            a.setAge(rs.getInt("age"));
            a.setPassword(rs.getString("password"));  
            a.setPhoto(rs.getString("photo"));
                listu.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listu;
    }

  
   
    
   public Utilisateur getById(int id) throws SQLException{
        Utilisateur a=new Utilisateur();
        try {
            String req="SELECT * FROM user where id='"+id+"'";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
 
            a.setId_user(rs.getInt("id"));
            a.setNom(rs.getString("nom"));
            a.setPrenom(rs.getString("prenom"));
            a.setTelephone(rs.getInt("telephone"));
            a.setEmail(rs.getString("email"));
            a.setRole(rs.getString("role"));
            a.setAge(rs.getInt("age"));
            a.setPassword(rs.getString("password"));        
            a.setPhoto(rs.getString("photo"));        
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

//    @Override
   public void deleteUtilisateur(Utilisateur p) throws SQLException{
        try {
            String req="DELETE FROM user WHERE id=?";
            PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(req);
            ps.setInt(1,p.getId_user());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Override
    public void deleteUtilisateur_ById(int id) throws SQLException {
             try {
            String req="DELETE FROM user WHERE id=?";
            PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    

//    @Override
      public void updateUtilisateur(Utilisateur p) throws SQLException{
 
         try {

            String req="UPDATE user SET  nom = ?, prenom = ?, email = ?, age = ?, telephone = ?, password = ?, role = ?, photo = ?  WHERE user.`id` = ?";
           PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(req);     
            ps.setString(1,p.getNom());
            ps.setString(2,p.getPrenom());
            ps.setString(3,p.getEmail());
            ps.setInt(4,p.getAge());
            ps.setInt(5,p.getTelephone());
            ps.setString(6,p.getPassword());
            ps.setString(7,p.getRole());
            ps.setString(8,p.getPhoto());
            ps.setInt(9,p.getId_user());
            ps.executeUpdate();
//            Alert alert  = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Dialogue information");
//            alert.setHeaderText(null);
//            alert.setContentText("User     "+p.getNom()+" est modifié avec succées");
//            alert.showAndWait();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      
      
      
     
      
     
    

//    @Override
    public void addUtilisateur(Utilisateur p) throws SQLException {       
        try {
            
            
            String req="INSERT INTO user (nom, prenom, email, age, telephone, role, password, photo) VALUES (?,?,?,?,?,?,?,?)";
             PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(req);
   
            ps.setString(1,p.getNom());
            ps.setString(2,p.getPrenom());
            ps.setString(3,p.getEmail());
            ps.setInt(4,p.getAge());
            ps.setInt(5,p.getTelephone());
            ps.setString(6,p.getRole());
            ps.setString(7,p.getPassword());
            ps.setString(8,p.getPhoto());
          
//            fis= new FileInputStream(file);
            
            
            Alert alert = new  Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information dialogue");
            alert.setHeaderText(null);
            alert.setContentText("user  "+p.getNom()+"  est crée avec succées");
            alert.showAndWait();
            
            
            
            ps.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
      
        }}
    
     public List<Utilisateur> RechercherUtilisateur(String nom) throws SQLException{

     List<Utilisateur> listrecherche = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM user WHERE nom='"+nom+"'";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {    
            Utilisateur a = new Utilisateur();
            a.setId_user(rs.getInt("id"));
            a.setNom(rs.getString("nom"));
            a.setPrenom(rs.getString("prenom"));
            a.setTelephone(rs.getInt("telephone"));
            a.setEmail(rs.getString("email"));
            a.setRole(rs.getString("role"));
            a.setAge(rs.getInt("age"));
            a.setPassword(rs.getString("password"));  
            a.setPhoto(rs.getString("photo"));
                listrecherche.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
    }
     
     
     
     
     
      public List<Utilisateur> getTrier() throws SQLException{
            List<Utilisateur> listTri = new ArrayList<>();
            
        try {            
            String requete = "select * from user ORDER BY nom ASC";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(requete);

            while (rs.next()) {
                
                Utilisateur a = new Utilisateur();
                a.setId_user(rs.getInt("id"));
                a.setNom(rs.getString("nom"));
                a.setPrenom(rs.getString("prenom"));
                a.setTelephone(rs.getInt("telephone"));
                a.setEmail(rs.getString("email"));
                a.setRole(rs.getString("role"));
                a.setAge(rs.getInt("age"));
                a.setPassword(rs.getString("password"));
                a.setPhoto(rs.getString("photo"));
                listTri.add(a);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
          return listTri;
    }
      
     /* public Utilisateur Verficier_Connexion(String email,String pass) {
          
        Utilisateur a = new Utilisateur();

        
        String req="SELECT * FROM user where email='"+email+"'and password= '"+pass+"'";

        try {
          Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
//            ResultSet rs=ps.executeQuery(req);

            while (rs.next()) {
            a.setId_user(rs.getInt("id"));
            a.setNom(rs.getString("nom"));
            a.setPrenom(rs.getString("prenom"));
            a.setTelephone(rs.getInt("telephone"));
            a.setEmail(rs.getString("email"));
            a.setRole(rs.getString("role"));
            a.setAge(rs.getInt("age"));
            a.setPassword(rs.getString("password"));  
            a.setPhoto(rs.getString("photo"));
            
            }

//      public Utilisateur Verficier_Connexion(String email,String pass) throws SQLException{
//             Utilisateur a = new Utilisateur();
//      
//        
//        try {
//            
//        
//            String req="SELECT * FROM user where email='"+email +"' and password='"+pass +"'";
//            Statement s=Connection.getInstance().getConnection().createStatement();
//              
//            ResultSet rs=s.executeQuery(req);
//            while(rs.next())
//            {    
//       
//            a.setId_user(rs.getInt("id"));
//            a.setNom(rs.getString("nom"));
//            a.setPrenom(rs.getString("prenom"));
//            a.setTelephone(rs.getInt("telephone"));
//            a.setEmail(rs.getString("email"));
//            a.setRole(rs.getString("role"));
//            a.setAge(rs.getInt("age"));
//            a.setPassword(rs.getString("password"));         
//
//            
//            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
         return a;
       
      
    }
     
     
     
      */
     
     public Utilisateur Verficier_Connexion(String email) {
          
        Utilisateur a = new Utilisateur();

        
        String req="SELECT * FROM user where email='"+email+"'";

        try {
          Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
//            ResultSet rs=ps.executeQuery(req);

            while (rs.next()) {
            a.setId_user(rs.getInt("id"));
            a.setNom(rs.getString("nom"));
            a.setPrenom(rs.getString("prenom"));
            a.setTelephone(rs.getInt("telephone"));
            a.setEmail(rs.getString("email"));
            a.setRole(rs.getString("role"));
            a.setAge(rs.getInt("age"));
            a.setPassword(rs.getString("password"));  
            a.setPhoto(rs.getString("photo"));
            
            }

//      public Utilisateur Verficier_Connexion(String email,String pass) throws SQLException{
//             Utilisateur a = new Utilisateur();
//      
//        
//        try {
//            
//        
//            String req="SELECT * FROM user where email='"+email +"' and password='"+pass +"'";
//            Statement s=Connection.getInstance().getConnection().createStatement();
//              
//            ResultSet rs=s.executeQuery(req);
//            while(rs.next())
//            {    
//       
//            a.setId_user(rs.getInt("id"));
//            a.setNom(rs.getString("nom"));
//            a.setPrenom(rs.getString("prenom"));
//            a.setTelephone(rs.getInt("telephone"));
//            a.setEmail(rs.getString("email"));
//            a.setRole(rs.getString("role"));
//            a.setAge(rs.getInt("age"));
//            a.setPassword(rs.getString("password"));         
//
//            
//            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
         return a;
       
      
    }

     
     }


         
 
