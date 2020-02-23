/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Rechange;
import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import utils.MaConnection;

/**
 *
 * @author Raef
 */
public class ServiceRechange {
    Connection cnx = MaConnection.getInstance().getConnection();
    Statement stm;
    user loggeduser;


    public ServiceRechange() throws SQLException {
       
            stm = cnx.createStatement();
           loggeduser=new user(0, "fff", "eee");
    }
    
    public void addRechange(Rechange p) throws SQLException {
java.sql.Date date=new java.sql.Date(new java.util.Date().getTime());
        String querry = " INSERT INTO `produit` "
                + "(`id_P`,`id_U`,`nom_P`, `type_P`, `marque_P`, `prix_P`, `photo_P`,`date`) "
                + "VALUES (NULL, '" + loggeduser.getId_U()+ "','" 
                + p.getNom_P() + "', '" 
                + p.getType_P() + "', '" 
                + p.getMarque_P() + "', '" 
                + p.getPrix_P() + "', '" 
                + p.getPhoto_P() + "','" 
                + date + "')";
        stm.executeUpdate(querry);

    }
    
    
    public List<Rechange> getRechanges() throws SQLException{
    
    String query = "SELECT * FROM `produit` WHERE type_P = 'rechange'";
        ResultSet rst = stm.executeQuery(query);
        List<Rechange> rechanges = new ArrayList<>();
        while (rst.next()) {
             Rechange r2 = new Rechange();
            r2.setNom_P(rst.getString("nom_P"));
            r2.setType_P(rst.getString("type_P"));
            r2.setMarque_P(rst.getString("marque_P"));
            r2.setPrix_P(rst.getFloat("prix_P"));
            r2.setPhoto_P(rst.getString("photo_P"));
            r2.setDate(rst.getDate("date"));
            rechanges.add(r2);
        }
        return rechanges;
    
    }
    
    
    
      public Rechange getRechangeById(int ID) throws SQLException {
        String querry = "SELECT * FROM `produit` WHERE `id_P` = " + ID + "";
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(querry);
        Rechange r = new Rechange();
        if (rst.next()) {
          r.setId_P(rst.getInt("id_P"));
            r.setNom_P(rst.getString("nom_P"));
            r.setType_P(rst.getString("type_P"));
            r.setPrix_P(rst.getFloat("prix_P"));
            r.setPhoto_P(rst.getString("photo_P"));
            r.setDate(rst.getDate("date"));
        }
        return r;
    }
    
    
    
     public void UpdateRechange(Rechange r) throws SQLException{
     
     PreparedStatement pla = cnx.prepareStatement("update produit set   nom_P=?,marque_P=?, prix_P=?, photo_P=? where nom_P=?");
        pla.setString(1, r.getNom_P());
        pla.setString(2, r.getMarque_P());
        pla.setFloat(3, r.getPrix_P());
        pla.setString(4, r.getPhoto_P());
        pla.setString(5, r.getNom_P());
       

        pla.executeUpdate();
     
     }
     
     
     
     public void deleteRechange(Rechange r) throws SQLException {

        String querry = "DELETE FROM produit WHERE nom_P =?";
        PreparedStatement d = cnx.prepareStatement(querry);
        d.setString(1, r.getNom_P());
        d.executeUpdate();
        System.out.println("testsup");

    }
     
   
     public List<Rechange> FiltrerRechangeByMarque(String marque) throws SQLException {
        String querry = "SELECT * FROM `produit`WHERE type_P = 'rechange'";
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(querry);
        List<Rechange> listR =new ArrayList<>();
        List<Rechange> listmar =new ArrayList<>();
       
        while (rst.next()) {
            Rechange r = new Rechange();
           r.setId_P(rst.getInt("id_P"));
            r.setNom_P(rst.getString("nom_P"));
            r.setType_P(rst.getString("type_P"));
            r.setMarque_P(rst.getString("marque_P"));
            r.setPrix_P(rst.getFloat("prix_P"));
            r.setPhoto_P(rst.getString("photo_P"));
            r.setDate(rst.getDate("date"));
            listR.add(r);            
            listmar=listR.stream().filter(rech ->rech.getMarque_P().equals(marque)).collect(Collectors.toList());     
        
        }
        return listmar;
           
    }
     
     
     
      
        public List<Rechange> FiltrerRechangeByprix(float f1, float f2) throws SQLException{
       String querry = "SELECT * FROM produit WHERE type_P='rechange' AND prix_P BETWEEN " + f1 + " AND " + f2 ;
       Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(querry);
        List<Rechange> ListPrix = new ArrayList<>();
        
          while (rst.next()) {
            Rechange r = new Rechange();
            r.setId_P(rst.getInt("id_P"));
            r.setNom_P(rst.getString("nom_P"));
            r.setType_P(rst.getString("type_P"));
            r.setMarque_P(rst.getString("marque_P"));
            r.setPrix_P(rst.getFloat("prix_P"));
            r.setPhoto_P(rst.getString("photo_P"));
            r.setDate(rst.getDate("date"));
            ListPrix.add(r);
          
          }
          
          return ListPrix;
       }
     
     
     
}
