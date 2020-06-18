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
public interface IseviceVelo {

    public void addVelo(Produit p) throws SQLException;

    public List<Produit> getVelos() throws SQLException;

    public Produit getVeloById(int ID) throws SQLException;

    public List<Produit> getVeloByCategorie(String category) throws SQLException;

    public void UpdateVelo(Produit p) throws SQLException;

    public void deleteVelo(Produit v) throws SQLException;

    public List<Produit> FiltrerVeloByCategorie(String category) throws SQLException;

    public List<Produit> FiltrerVeloByprix(float f1, float f2) throws SQLException;
}
