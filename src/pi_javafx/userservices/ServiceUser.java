/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_javafx.userservices;

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
import pi_javafx.userentites.fos_user;
import pi_javafx.userinterface.IServiceUtilisateur;
import pi_javafx.utile.Connection;

/**
 *
 * @author USER
 */
public class ServiceUser implements IServiceUtilisateur {
//    private FileInputStream fis;
//    private File file;

   
    private Connection cnx;
     @Override
    public List<fos_user> getUtilisateurs()  throws SQLException {
  
        List<fos_user> listu = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM fos_user";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {    
            fos_user a = new fos_user();
            a.setId_user(rs.getInt("id"));
            a.setUsername(rs.getString("username"));
            a.setEmail(rs.getString("email"));
            a.setRoles(rs.getString("roles"));
            a.setFirstname(rs.getString("firstname"));
            a.setTelephone(rs.getInt("telephone"));
            a.setAge(rs.getInt("age"));
            a.setPhoto(rs.getString("photo"));
                listu.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listu;
    }

  
   
   public fos_user getById(int id) throws SQLException{
        fos_user a=new fos_user();
        try {
            String req="SELECT * FROM fos_user where id='"+id+"'";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
 
         
            a.setUsername(rs.getString("username"));
            a.setEmail(rs.getString("email"));
            a.setRoles(rs.getString("roles"));
            a.setFirstname(rs.getString("firstname"));
            a.setTelephone(rs.getInt("telephone"));
            a.setAge(rs.getInt("age"));
            a.setPhoto(rs.getString("photo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    @Override
   public void deleteUtilisateur(fos_user p) throws SQLException{
      
            String req="DELETE FROM fos_user WHERE username=?";
            PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(req);
            ps.setString(1,p.getUsername());
            ps.executeUpdate();
        } 
    

    @Override
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
     
    

    @Override
      public void updateUtilisateur(fos_user p) throws SQLException{
 
         try {

          Statement s=Connection.getInstance().getConnection().createStatement();
        String query = "UPDATE `fos_user` SET `username` = '"+ p.getUsername()
                + "', `username_canonical` = '"+ p.getUsername_canonical()
                + "', `email` = '"+ p.getEmail()
                + "', `email_canonical` = '"+ p.getEmail_canonical()
                +"', `enabled` = '"+ p.getEnabled()
                +"', `salt` = '"+ p.getSalt()
                +"', `roles` = '"+ p.getRoles()
                +"', `password`= '"+ p.getPassword()
                +"', `telephone`= '"+ p.getTelephone()
                +"', `age`= '"+ p.getAge()
                +"', `firstname`= '"+ p.getFirstname()
                +"', `photo`= '"+ p.getPhoto()
                 +"', `enabled`= '"+ 1
                + "' WHERE `fos_user`.`id` = "+ p.getId_user();
                
        s.executeUpdate(query);
//            Alert alert  = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Dialogue information");
//            alert.setHeaderText(null);
//            alert.setContentText("User     "+p.getNom()+" est modifié avec succées");
//            alert.showAndWait();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      
      
      
     
      
     
    

    @Override
    public void addUtilisateur(fos_user p) throws SQLException {       
        try {
            
            
            String req="INSERT INTO fos_user (username, username_canonical, email, email_canonical, enabled, roles, password, photo,telephone,age,firstname) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
             PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(req);
   
            ps.setString(1,p.getUsername());
            ps.setString(2,p.getUsername_canonical());
            ps.setString(3,p.getEmail());
            ps.setString(4,p.getEmail_canonical());
            ps.setInt(5,p.getEnabled());
            ps.setString(6,p.getRoles());
            ps.setString(7,p.getPassword());
            ps.setString(8,p.getPhoto());
            ps.setInt(9,p.getTelephone());
            ps.setInt(10,p.getAge());
            ps.setString(11,p.getFirstname());
  
            
            Alert alert = new  Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information dialogue");
            alert.setHeaderText(null);
            alert.setContentText("user  "+p.getUsername()+"  est crée avec succées");
            alert.showAndWait();

            ps.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
      
        }}
    
     public List<fos_user> RechercherUtilisateur(String nom) throws SQLException{

     List<fos_user> listrecherche = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM fos_user WHERE username='"+nom+"'";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {    
            fos_user a = new fos_user();
            a.setId_user(rs.getInt("id"));
            a.setUsername(rs.getString("username"));
            a.setEmail(rs.getString("email"));
            a.setRoles(rs.getString("roles"));
            a.setFirstname(rs.getString("firstname"));
            a.setTelephone(rs.getInt("telephone"));
            a.setAge(rs.getInt("age"));
            a.setPhoto(rs.getString("photo"));
                listrecherche.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
    }
     
     
     
     
     
      public List<fos_user> getTrier() throws SQLException{
            List<fos_user> listTri = new ArrayList<>();
            
        try {            
            String requete = "select * from fos_user ORDER BY username ASC";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(requete);

            while (rs.next()) {
                
                fos_user a = new fos_user();
               a.setId_user(rs.getInt("id"));
            a.setUsername(rs.getString("username"));
            a.setEmail(rs.getString("email"));
            a.setRoles(rs.getString("roles"));
            a.setFirstname(rs.getString("firstname"));
            a.setTelephone(rs.getInt("telephone"));
            a.setAge(rs.getInt("age"));
            a.setPhoto(rs.getString("photo"));
                listTri.add(a);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
          return listTri;
    }
  
      public fos_user Verficier_Connexion(String email) {
          
        fos_user a = new fos_user();

        
        String req="SELECT * FROM fos_user where email='"+email+"'";

        try {
          Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
//            ResultSet rs=ps.executeQuery(req);

            while (rs.next()) {
            a.setId_user(rs.getInt("id"));
            a.setUsername(rs.getString("username"));
            a.setEmail(rs.getString("email"));
            a.setRoles(rs.getString("roles"));
            a.setFirstname(rs.getString("firstname"));
            a.setTelephone(rs.getInt("telephone"));
            a.setAge(rs.getInt("age"));
            a.setPhoto(rs.getString("photo"));
            a.setPassword(rs.getString("password"));
            
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


         
 
