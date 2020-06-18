/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import IServices.IseviceAccessoire;
import entities.Fos_user;
import entities.Produit;
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
    Fos_user loggeduser;
    public ServiceAccessoire() throws SQLException {
        
            stm = cnx.createStatement();
          //  loggeduser=new user(0, "fff", "eee");
       
    }

@Override
    public void addAccessoire(Produit p) throws SQLException {
         java.sql.Date date=new java.sql.Date(new java.util.Date().getTime());
        String querry = " INSERT INTO `produit` "
                + "(`id_P`,`nom_P`, `marque_P`, `categorie_P`, `couleur_P`, `prix_P`,`date`,`photo_P`,`typeP`,`userId`,`tel`) "
                + "VALUES (NULL,'" 
                + p.getNom_P() + "', '" 
                + p.getMarque_P() + "', '" 
                + p.getCategorie_P() + "', '" 
                + p.getCouleur_P() + "', '" 
                + p.getPrix_P() + "','" 
                + date + "', '" 
                + p.getPhoto_P() + "','" 
                + p.getType_P() + "','" 
                + 1+ "','" 
                + p.getTel() + "')";
        stm.executeUpdate(querry);
       }
        
@Override
       public List<Produit> getAccessoires() throws SQLException {
       String query = "SELECT * FROM `produit` WHERE typeP = 2";
        ResultSet rst = stm.executeQuery(query);
        List<Produit> velos = new ArrayList<>();
        while (rst.next()) {
           
            Produit v2 = new Produit();
    
            v2.setNom_P(rst.getString("nom_P"));
          
            v2.setMarque_P(rst.getString("marque_P"));
            v2.setCategorie_P(rst.getString("categorie_P"));
            v2.setCouleur_P(rst.getString("couleur_P"));
            v2.setPrix_P(rst.getFloat("prix_P"));
             v2.setDate(rst.getDate("date"));
            v2.setPhoto_P(rst.getString("photo_P"));
             v2.setType_P(rst.getInt("typeP"));
              v2.setTel(rst.getInt("tel"));
            velos.add(v2);
        }
        return velos;
    }

    @Override
  public void UpdateAccessoire(Produit p) throws SQLException {
        PreparedStatement pla = cnx.prepareStatement("update produit set   nom_P=?,marque_P=?,categorie_P=?,couleur_P=?, prix_P=?,tel=?, photo_P=? where nom_P=?");
        pla.setString(1, p.getNom_P());
      
        pla.setString(2, p.getMarque_P());
        pla.setString(3, p.getCategorie_P());
        pla.setString(4, p.getCouleur_P());
        pla.setFloat(5, p.getPrix_P());
        pla.setInt(6, p.getTel());
        pla.setString(7, p.getPhoto_P());
        
       

        pla.setString(8, p.getNom_P());
     

        pla.executeUpdate();

    }
       

       
@Override
       public void deleteAccessoire(Produit v) throws SQLException {

        String querry = "DELETE FROM produit WHERE nom_P =?";
        PreparedStatement d = cnx.prepareStatement(querry);
        d.setString(1, v.getNom_P());
        d.executeUpdate();
        System.out.println("testsup");

    }
       

    
    
     
@Override
       public List<Produit> FiltrerAccessoireByCouleur(String category) throws SQLException {
        String querry = "SELECT * FROM `produit`WHERE typeP = 2";
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(querry);
        List<Produit> listV =new ArrayList<>();
        List<Produit> listcat =new ArrayList<>();
        
        while (rst.next()) {
            Produit v = new Produit();
          
            v.setNom_P(rst.getString("nom_P"));
            v.setCategorie_P(rst.getString("categorie_P"));
            v.setCouleur_P(rst.getString("couleur_P"));
            v.setPrix_P(rst.getFloat("prix_P"));
            v.setPhoto_P(rst.getString("photo_P"));
            v.setDate(rst.getDate("date"));
            v.setTel(rst.getInt("tel"));
            listV.add(v);            
            listcat=listV.stream().filter(velo ->velo.getCategorie_P().equals(category)).collect(Collectors.toList());     
        
        }
        return listcat;
           
    }
       
       
@Override
       public List<Produit> FiltrerAccessoireByprix(float f1, float f2) throws SQLException{
       String querry = "SELECT * FROM produit WHERE type_P='2' AND prix_P BETWEEN " + f1 + " AND " + f2 ;
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(querry);
        List<Produit> ListPrix = new ArrayList<>();
      
          while (rst.next()) {
            Produit v = new Produit();
            v.setId_P(rst.getInt("id_P"));
            v.setNom_P(rst.getString("nom_P"));
            v.setMarque_P(rst.getString("marque_P"));
            v.setCategorie_P(rst.getString("categorie_P"));
            v.setCouleur_P(rst.getString("couleur_P"));
            v.setPrix_P(rst.getFloat("prix_P"));
            v.setPhoto_P(rst.getString("photo_P"));
            v.setDate(rst.getDate("date"));
            v.setTel(rst.getInt("tel"));
            ListPrix.add(v);
          
          }
          
          return ListPrix;
       }

    
       
       
@Override
     public Produit getAccessoireById(int ID) throws SQLException {
        String querry = "SELECT * FROM `produit` WHERE `id_P` = " + ID + "";
        stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(querry);
        Produit v = new Produit();
        if (rst.next()) {
             
            v.setNom_P(rst.getString("nom_P"));
              v.setMarque_P(rst.getString("marque_P"));
            v.setCategorie_P(rst.getString("categorie_P"));
            v.setCouleur_P(rst.getString("couleur_P"));
            v.setPrix_P(rst.getFloat("prix_P"));
            v.setPhoto_P(rst.getString("photo_P"));
            v.setDate(rst.getDate("date"));
            v.setTel(rst.getInt("tel"));
        }
        return v;
    }

   
}
