/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.interfaces;

import edu.velo.entities.Annonce;
import java.util.List;

/**
 *
 * @author Aloui Omar
 */
public interface AnnonceServicesInterface {
    
    
    public List<Annonce> afficherAnnonces();
    public List<Annonce> afficherAdminAnnonces();
    public void AjouterAnnonce(Annonce a, int idu);
    public void AjouterVeloAnnonce(Annonce a, int idu);
    public Annonce getAnnonce(int ida);
    public void signalerAnnnoce(int ida,String c);
    public void signalerAnnnoce(Annonce a);
    public void modifierAnnonce(Annonce a);
    public void supprimerAnnonce(Annonce a);
    public void supprimerAnnonce(int id);
    public List<Annonce> listerAnnonceSignler();
    public void DesactiverAnnonce(Annonce a);
    public void ActiverAnnonce(Annonce a);
    public List<Annonce> RechercheCategorieAnnonces(String c);
    
    
}
