/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Velo;
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
public class ServiceVelo {
    Connection cnx = MaConnection.getInstance().getConnection();
    Statement stm;
    user loggeduser;

    public ServiceVelo() throws SQLException {
        
            stm = cnx.createStatement();
            //loggeduser=new user(0, "fff", "eee");
       
    }
    
    
    
       public void addVelo(Velo p) throws SQLException {
         java.sql.Date date=new java.sql.Date(new java.util.Date().getTime());
        String querry = " INSERT INTO `produit` "
                + "(`id_P`,`id_U`,`nom_P`, `type_P`, `marque_P`, `categorie_P`, `couleur_P`, `prix_P`, `photo_P`,`date`) "
                + "VALUES (NULL, '" + 0 + "','" 
                + p.getNom_P() + "', '" 
                + p.getType_P() + "', '" 
                + p.getMarque_P() + "', '" 
                + p.getCategorie_P() + "', '" 
                + p.getCouleur_P() + "', '" 
                + p.getPrix_P() + "', '" 
                + p.getPhoto_P() + "','" 
                + date + "')";
        stm.executeUpdate(querry);
       }
        
       public List<Velo> getVelos() throws SQLException {
       String query = "SELECT * FROM `produit` WHERE type_P = 'velo'";
        ResultSet rst = stm.executeQuery(query);
        List<Velo> velos = new ArrayList<>();
        while (rst.next()) {
           
            Velo v2 = new Velo();
    
            v2.setNom_P(rst.getString("nom_P"));
            v2.setType_P(rst.getString("type_P"));
            v2.setMarque_P(rst.getString("marque_P"));
            v2.setCategorie_P(rst.getString("categorie_P"));
            v2.setCouleur_P(rst.getString("couleur_P"));
            v2.setPrix_P(rst.getFloat("prix_P"));
            v2.setPhoto_P(rst.getString("photo_P"));
            v2.setDate(rst.getDate("date"));
            velos.add(v2);
        }
        return velos;
    }
       
       public Velo getVeloById(int ID) throws SQLException {
        String querry = "SELECT * FROM `produit` WHERE `id_P` = " + ID + "";
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(querry);
        Velo v = new Velo();
        if (rst.next()) {
             
            v.setNom_P(rst.getString("nom_P"));
            v.setType_P(rst.getString("type_P"));
            v.setCategorie_P(rst.getString("categorie_P"));
            v.setCouleur_P(rst.getString("couleur_P"));
            v.setPrix_P(rst.getFloat("prix_P"));
            v.setPhoto_P(rst.getString("photo_P"));
            v.setDate(rst.getDate("date"));
        }
        return v;
    }
       
       
       
       
       
       
       

       
             
       public void UpdateVelo(Velo p) throws SQLException {
        PreparedStatement pla = cnx.prepareStatement("update produit set   nom_P=?,marque_P=?,categorie_P=?,couleur_P=?, prix_P=?, photo_P=? where nom_P=?");
        pla.setString(1, p.getNom_P());
      
        pla.setString(2, p.getMarque_P());
        pla.setString(3, p.getCategorie_P());
        pla.setString(4, p.getCouleur_P());
        pla.setFloat(5, p.getPrix_P());
        pla.setString(6, p.getPhoto_P());
        
       

        pla.setString(7, p.getNom_P());
     

        pla.executeUpdate();

    }
       

       
       public void deleteVelo(Velo v) throws SQLException {

        String querry = "DELETE FROM produit WHERE nom_P =?";
        PreparedStatement d = cnx.prepareStatement(querry);
        d.setString(1, v.getNom_P());
        d.executeUpdate();
        System.out.println("testsup");

    }
       
       
       
       public List<Velo> FiltrerVeloByCategorie(String category) throws SQLException {
        String querry = "SELECT * FROM `produit`WHERE type_P = 'velo'";
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(querry);
        List<Velo> listV =new ArrayList<>();
        List<Velo> listcat =new ArrayList<>();
        
        while (rst.next()) {
            Velo v = new Velo();
          
            v.setNom_P(rst.getString("nom_P"));
            v.setType_P(rst.getString("type_P"));
            v.setCategorie_P(rst.getString("categorie_P"));
            v.setCouleur_P(rst.getString("couleur_P"));
            v.setPrix_P(rst.getFloat("prix_P"));
            v.setPhoto_P(rst.getString("photo_P"));
            v.setDate(rst.getDate("date"));
            listV.add(v);            
            listcat=listV.stream().filter(velo ->velo.getCategorie_P().equals(category)).collect(Collectors.toList());     
        
        }
        return listcat;
           
    }
       
       
       public List<Velo> FiltrerVeloByprix(float f1, float f2) throws SQLException{
       String querry = "SELECT * FROM produit WHERE type_P='velo' AND prix_P BETWEEN " + f1 + " AND " + f2 ;
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(querry);
        List<Velo> ListPrix = new ArrayList<>();
      
          while (rst.next()) {
            Velo v = new Velo();
            v.setId_P(rst.getInt("id_P"));
            v.setNom_P(rst.getString("nom_P"));
            v.setType_P(rst.getString("type_P"));
            v.setMarque_P(rst.getString("marque_P"));
            v.setCategorie_P(rst.getString("categorie_P"));
            v.setCouleur_P(rst.getString("couleur_P"));
            v.setPrix_P(rst.getFloat("prix_P"));
            v.setPhoto_P(rst.getString("photo_P"));
            v.setDate(rst.getDate("date"));
            ListPrix.add(v);
          
          }
          
          return ListPrix;
       }
       
       
     
       
       
       
      
 
    }
    
    
    
    
    
