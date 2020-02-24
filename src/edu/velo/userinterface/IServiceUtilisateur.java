/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.userinterface;

import java.sql.SQLException;
import java.util.List;
import edu.velo.userentites.Utilisateur;

/**
 *
 * @author USER
 */
public interface IServiceUtilisateur {
    
    public void addUtilisateur(Utilisateur p) throws SQLException;

    public List<Utilisateur> getUtilisateurs() throws SQLException;

    public Utilisateur getById(int id) throws SQLException;

    public void deleteUtilisateur(Utilisateur p) throws SQLException;

    public void deleteUtilisateur_ById(int id) throws SQLException;

    public void updateUtilisateur(Utilisateur p) throws SQLException;
    
     

    
    public List<Utilisateur> RechercherUtilisateur(String nom) throws SQLException;
    
    public List<Utilisateur> getTrier()  throws SQLException ;

}
