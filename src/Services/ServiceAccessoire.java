/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Accessoire;
import entities.user;
import IServices.IseviceAccessoire;
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
public class ServiceAccessoire implements IseviceAccessoire {

    Connection cnx = MaConnection.getInstance().getConnection();
    Statement stm;
    user loggeduser;
    public ServiceAccessoire() throws SQLException {
        
            stm = cnx.createStatement();
            loggeduser=new user(0, "fff", "eee");
        
    }

    @Override
    public void addAccessoire(Accessoire p) throws SQLException {
        java.sql.Date date=new java.sql.Date(new java.util.Date().getTime());
        String querry = " INSERT INTO `produit` "
                + "(`id_P`,`id_U`,`nom_P`, `type_P`, `marque_P`, `couleur_P`, `prix_P`, `photo_P`,`date`) "
                + "VALUES (NULL, '" + loggeduser.getId_U()+ "','" 
                + p.getNom_P() + "', '" 
                + p.getType_P() + "', '" 
                + p.getMarque_P() + "', '" 
                + p.getCouleur_P() + "', '" 
                + p.getPrix_P() + "', '" 
                + p.getPhoto_P() + "','" 
                + date + "')";
        stm.executeUpdate(querry);
    }

    @Override
    public List<Accessoire> getAccessoires() throws SQLException {

        String query = "SELECT * FROM `produit` WHERE type_P = 'accessoire'";
        ResultSet rst = stm.executeQuery(query);
        List<Accessoire> accessoires = new ArrayList<>();
        while (rst.next()) {
            Accessoire a2 = new Accessoire();
            a2.setNom_P(rst.getString("nom_P"));
            a2.setType_P(rst.getString("type_P"));
            a2.setMarque_P(rst.getString("marque_P"));
            a2.setCouleur_P(rst.getString("couleur_P"));
            a2.setPrix_P(rst.getFloat("prix_P"));
            a2.setPhoto_P(rst.getString("photo_P"));
            a2.setDate(rst.getDate("date"));
            accessoires.add(a2);
        }
        return accessoires;
    }
    
    
    @Override
     public Accessoire getAccessoireById(int ID) throws SQLException {
        String querry = "SELECT * FROM `produit` WHERE `id_P` = " + ID + "";
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(querry);
        Accessoire a = new Accessoire();
        if (rst.next()) {
          
            a.setNom_P(rst.getString("nom_P"));
            a.setType_P(rst.getString("type_P"));
            a.setCouleur_P(rst.getString("couleur_P"));
            a.setPrix_P(rst.getFloat("prix_P"));
            a.setPhoto_P(rst.getString("photo_P"));
            a.setDate(rst.getDate("date"));
        }
        return a;
    }

    @Override
    public void UpdateAccessoire(Accessoire p) throws SQLException {
        PreparedStatement pla = cnx.prepareStatement("update produit set   nom_P=?,marque_P=?,couleur_P=?, prix_P=?, photo_P=? where nom_P=?");
        pla.setString(1, p.getNom_P());
        pla.setString(2, p.getMarque_P());
        pla.setString(3, p.getCouleur_P());
        pla.setFloat(4, p.getPrix_P());
        pla.setString(5, p.getPhoto_P());

        pla.setString(6, p.getNom_P());

        pla.executeUpdate();

    }

    @Override
    public void deleteAccessoire(Accessoire a) throws SQLException {

        String querry = "DELETE FROM produit WHERE nom_P =?";
        PreparedStatement d = cnx.prepareStatement(querry);
        d.setString(1, a.getNom_P());
        d.executeUpdate();
        System.out.println("testsup");

    }
    
    
    @Override
      public List<Accessoire> FiltrerAccessoireByCouleur(String color) throws SQLException {
        String querry = "SELECT * FROM `produit`WHERE type_P = 'accessoire'";
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(querry);
        List<Accessoire> listA =new ArrayList<>();
        List<Accessoire> listc =new ArrayList<>();
        
        while (rst.next()) {
            Accessoire a = new Accessoire();
           a.setId_P(rst.getInt("id_P"));
            a.setNom_P(rst.getString("nom_P"));
            a.setType_P(rst.getString("type_P"));
            a.setCouleur_P(rst.getString("couleur_P"));
            a.setPrix_P(rst.getFloat("prix_P"));
            a.setPhoto_P(rst.getString("photo_P"));
            a.setDate(rst.getDate("date"));
            listA.add(a);            
            listc=listA.stream().filter(acc ->acc.getCouleur_P().equals(color)).collect(Collectors.toList());
        
        }
        return listc;
           
    }
   
      
      
      
        public List<Accessoire> FiltrerAccessoireByprix(float f1, float f2) throws SQLException{
       String querry = "SELECT * FROM produit WHERE type_P='accessoire' AND prix_P BETWEEN " + f1 + " AND " + f2 ;
       Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(querry);
        List<Accessoire> ListPrix = new ArrayList<>();
        
          while (rst.next()) {
            Accessoire a = new Accessoire();
            a.setId_P(rst.getInt("id_P"));
            a.setNom_P(rst.getString("nom_P"));
            a.setType_P(rst.getString("type_P"));
            a.setCouleur_P(rst.getString("couleur_P"));
            a.setPrix_P(rst.getFloat("prix_P"));
            a.setPhoto_P(rst.getString("photo_P"));
            a.setDate(rst.getDate("date"));
            ListPrix.add(a);
          
          }
          
          return ListPrix;
       }

}
