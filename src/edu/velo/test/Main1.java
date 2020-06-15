/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.test;

import edu.velo.entities.Annonce;
import edu.velo.services.AnnonceServices;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aloui Omar
 */
public class Main1 {
    
    
    
    
       public static void main(String[] args) throws SQLException {
     
     AnnonceServices as=new AnnonceServices();
     
        List<Annonce> maliste = new ArrayList<>();     
        List<Annonce> maveloliste = new ArrayList<>();     
        maliste=as.afficherAnnonces();
        maveloliste=as.afficherAdminAnnonces();
                 System.out.println("###################");
                 maliste.stream().forEach(System.out::println);
                 System.out.println("#########velo##########");
                 maveloliste.stream().forEach(System.out::println);
                 System.out.println("###################");

        
         System.out.println("###################");
        // maveloliste.stream().forEach(System.out::println);
         System.out.println("###################");
         Annonce a=new Annonce("velo","velo12","velo de course",155,"img",new java.sql.Date(118,04,22),"location","course","bleu");
         Annonce a1=new Annonce("accessoire","velo12","velo de course1",155,"img",new java.sql.Date(2009,06,22),"location");
                 System.out.println("###################");
         System.out.println("#######Ajout d'annonce############");

       //  as.AjouterVeloAnnonce(a, 1);
        // as.AjouterAnnonce(a1, 1);
        System.out.println("###################");
       // as.supprimerAnnonce(7);
        a=as.getAnnonce(4);
        a.setCategorie("velo modif");
        as.modifierAnnonce(a);
        as.signalerAnnnoce(a);
        as.signalerAnnnoce(a);
        as.ActiverAnnonce(a);
           System.out.println("ee"+a.toString());
        maliste=as.RechercheCategorieAnnonces("velo");
           System.out.println("#########Recherche###########");
           maliste.stream().forEach(System.out::println);

     }
}
