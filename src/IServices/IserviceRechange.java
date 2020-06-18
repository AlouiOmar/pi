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
public interface IserviceRechange {

    public void addRechange(Produit p) throws SQLException;

    public List<Produit> getRechanges() throws SQLException;

    public Produit getRechangeById(int ID) throws SQLException;

    public void UpdateRechange(Produit r) throws SQLException;

    public void deleteRechange(Produit r) throws SQLException;

    
    public List<Produit> FiltrerRechangeByprix(float f1, float f2) throws SQLException;
}
