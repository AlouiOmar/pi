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
public interface IseviceAccessoire {

    public void addAccessoire(Produit p) throws SQLException;

    public List<Produit> getAccessoires() throws SQLException;

    public Produit getAccessoireById(int ID) throws SQLException;

    public void UpdateAccessoire(Produit r) throws SQLException;

    public void deleteAccessoire(Produit a) throws SQLException;

    public List<Produit> FiltrerAccessoireByCouleur(String color) throws SQLException;

    public List<Produit> FiltrerAccessoireByprix(float f1, float f2) throws SQLException;
}
