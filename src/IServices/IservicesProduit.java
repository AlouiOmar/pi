/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import entities.Produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Raef
 */
public interface IservicesProduit {


    public void deleteProduitt(int id) throws SQLException;

    public void deleteProduit(Produit p) throws SQLException;
}
