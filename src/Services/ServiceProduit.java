/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IservicesProduit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MaConnection;
import entities.Produit;
import java.sql.PreparedStatement;

/**
 *
 * @author Raef
 */
public class ServiceProduit implements IservicesProduit {

    Connection cnx = MaConnection.getInstance().getConnection();
    Statement stm;

    public ServiceProduit() {
        try {
            stm = cnx.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override

    public void deleteProduit(Produit p) throws SQLException {

        String requete = "DELETE FROM produit WHERE id_P =?";
        PreparedStatement r = cnx.prepareStatement(requete);
        r.setInt(2, p.getId_P());
        r.executeUpdate();
        System.out.println("testsup");

    }

    @Override
    public void deleteProduitt(int id) throws SQLException {

        String requete = "DELETE FROM produit WHERE id_P == " + id + "";
        PreparedStatement r = cnx.prepareStatement(requete);
        r.setInt(0, id);
        r.executeUpdate();
        System.out.println("testsup");
    }

    
    }

   

  
    
   

