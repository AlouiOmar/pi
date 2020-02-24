/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.services;

import edu.velo.entities.Annonce;
import edu.velo.entities.Archive;
import edu.velo.entities.Stat;
import edu.velo.entities.User;
import edu.velo.util.DataSource;
import edu.velo.interfaces.AnnonceServicesInterface;
import edu.velo.userentites.Utilisateur;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Aloui Omar
 */
public class AnnonceServices implements AnnonceServicesInterface{
    
    
    @Override
    public List<Annonce> afficherAnnonces() {
        List<Annonce> maliste = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM Annonce where active=1";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Annonce a = new Annonce();
            a.setIda(rs.getInt("ida"));
            a.setIdu(rs.getInt("idu"));
            a.setCategorie(rs.getString("categorie"));
            a.setTitre(rs.getString("titre"));
            a.setDescription(rs.getString("description"));
            a.setPrix(rs.getDouble("prix"));
            a.setPhoto(rs.getString("photo"));
            a.setDate(rs.getDate("date"));
            a.setActive(rs.getBoolean("active"));
            a.setType(rs.getString("type"));
            a.setTypevelo(rs.getString("typevelo"));
            a.setCouleur(rs.getString("couleur"));
            a.setGouvernorat(rs.getString("gouvernorat"));
            a.setDatep(rs.getDate("datep"));
            
            //d.setEnseigneId(e.AfficherEnseigne((Integer) rs.getInt("enseigne_id")));
            //d.getEnseigneId().setUserId(uu.AfficherUser(d.getEnseigneId().getUserId().getId()));
            
            
                maliste.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }
    @Override
    public List<Annonce> afficherAdminAnnonces() {
        List<Annonce> maliste = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM Annonce";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Annonce a = new Annonce();
            a.setIda(rs.getInt("ida"));
            a.setIdu(rs.getInt("idu"));
            a.setCategorie(rs.getString("categorie"));
            a.setTitre(rs.getString("titre"));
            a.setDescription(rs.getString("description"));
            a.setPrix(rs.getDouble("prix"));
            a.setPhoto(rs.getString("photo"));
            a.setDate(rs.getDate("date"));
            a.setActive(rs.getBoolean("active"));
            a.setType(rs.getString("type"));
            a.setTypevelo(rs.getString("typevelo"));
            a.setCouleur(rs.getString("couleur"));
            a.setGouvernorat(rs.getString("gouvernorat"));
            a.setDatep(rs.getDate("datep"));
            
            //d.setEnseigneId(e.AfficherEnseigne((Integer) rs.getInt("enseigne_id")));
            //d.getEnseigneId().setUserId(uu.AfficherUser(d.getEnseigneId().getUserId().getId()));
            
                maliste.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }
    @Override
    public void AjouterAnnonce(Annonce a, int idu) {
        
        try {
            
            
            String req="INSERT INTO `annonce` (`idu`, `categorie`, `titre`, `description`, `prix`, `photo`, `type`, `gouvernorat`,`date`,`active`,`datep`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,idu );
            ps.setString(2,a.getCategorie());
            ps.setString(3,a.getTitre());
            ps.setString(4,a.getDescription());
            ps.setDouble(5,a.getPrix());
            ps.setString(6,a.getPhoto());
            ps.setString(7,a.getType());
            ps.setString(8,a.getGouvernorat());
            ps.setDate(9,a.getDate());
            ps.setBoolean(10,a.isActive());
            ps.setDate(11,a.getDatep());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }}
    @Override
    public void AjouterVeloAnnonce(Annonce a, int idu) {
        
        try {
            
            
            String req="INSERT INTO `annonce` (`idu`, `categorie`, `titre`, `description`, `prix`, `photo`, `type`, `typevelo`, `couleur`, `gouvernorat`,`date`,`active`,`datep`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,idu );
            ps.setString(2,a.getCategorie());
            ps.setString(3,a.getTitre());
            ps.setString(4,a.getDescription());
            ps.setDouble(5,a.getPrix());
            ps.setString(6,a.getPhoto());
            ps.setString(7,a.getType());
            ps.setString(8,a.getTypevelo());
            ps.setString(9,a.getCouleur());
            ps.setString(10, a.getGouvernorat());
            ps.setDate(11,a.getDate());
            ps.setBoolean(12,a.isActive());
            ps.setDate(13,a.getDatep());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
    @Override
    public Annonce getAnnonce(int ida){
        Annonce a=new Annonce();
        try {
            String req="SELECT * FROM Annonce where ida='"+ida+"'";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
          
            a.setIda(rs.getInt("ida"));
            a.setIdu(rs.getInt("idu"));
            a.setCategorie(rs.getString("categorie"));
            a.setTitre(rs.getString("titre"));
            a.setDescription(rs.getString("description"));
            a.setPrix(rs.getDouble("prix"));
            a.setPhoto(rs.getString("photo"));
            a.setDate(rs.getDate("date"));
            a.setActive(rs.getBoolean("active"));
            a.setType(rs.getString("type"));
            a.setTypevelo(rs.getString("typevelo"));
            a.setCouleur(rs.getString("couleur"));
            a.setGouvernorat(rs.getString("gouvernorat"));
            a.setDatep(rs.getDate("datep"));

            
         
            
                
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    @Override
    public void signalerAnnnoce(int ida,String c){
        try {
            Annonce a;
            a=getAnnonce(ida);
            String req="INSERT INTO `signaler` (`ida`, `cause`) VALUES (?,?)";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,ida);
            ps.setString(2,c);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void signalerAnnnoce(Annonce a){
        try {
            
            String req="INSERT INTO `signaler` (`ida`, `cause`) VALUES (?,?)";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,a.getIda());
            ps.setString(2,"violence");
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void modifierAnnonce(Annonce a){
       try {

            String req="UPDATE `annonce` SET `categorie` = ?, `titre` = ?, `description` = ?, `prix` = ?, `photo` = ?, `date` = ?, `active` = ?, `type` = ?, `typevelo` = ?, `couleur` = ?, `gouvernorat` = ? WHERE `annonce`.`ida` = ?";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            
            ps.setString(1,a.getCategorie());
            ps.setString(2,a.getTitre());
            ps.setString(3,a.getDescription());
            ps.setDouble(4,a.getPrix());
            ps.setString(5,a.getPhoto());
            ps.setDate(6,(Date)a.getDate());
            ps.setBoolean(7,a.isActive());
            ps.setString(8,a.getType());
            ps.setString(9,a.getTypevelo());
            ps.setString(10,a.getCouleur());
            ps.setString(11, a.getGouvernorat());            
            ps.setInt(12,a.getIda());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void supprimerAnnonce(Annonce a){
        try {
            String req="DELETE FROM annonce WHERE ida=?";
            PreparedStatement ps=DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,a.getIda());
             String req1="INSERT INTO `archive` ( `categorie`, `titre`, `description`, `prix`, `photo`, `type`, `typevelo`, `couleur`, `date`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps1= DataSource.getInstance().getCnx().prepareStatement(req1);
            ps1.setString(1,a.getCategorie());
            ps1.setString(2,a.getTitre());
            ps1.setString(3,a.getDescription());
            ps1.setDouble(4,a.getPrix());
            ps1.setString(5,a.getPhoto());
            ps1.setString(6,a.getType());
            ps1.setString(7,a.getTypevelo());
            ps1.setString(8,a.getCouleur());
            ps1.setDate(9,a.getDate());
            ps1.executeUpdate();
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void supprimerAnnonce(int id){
        try {
            String req="DELETE FROM annonce WHERE ida=?";
            PreparedStatement ps=DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public List<Annonce> listerAnnonceSignler(){
        List<Annonce> maliste = new ArrayList<>();
        try {
            
            String req="SELECT * FROM `signaler` s join annonce a where s.ida=a.ida";
            PreparedStatement ps=DataSource.getInstance().getCnx().prepareStatement(req);
             ResultSet rs=ps.executeQuery(req);
            while(rs.next())
            {
                
            Annonce a = new Annonce();
            a.setIda(rs.getInt("ida"));
            a.setIdu(rs.getInt("idu"));
            a.setCategorie(rs.getString("categorie"));
            a.setTitre(rs.getString("titre"));
            a.setDescription(rs.getString("description"));
            a.setPrix(rs.getDouble("prix"));
            a.setPhoto(rs.getString("photo"));
            a.setDate(rs.getDate("date"));
            a.setActive(rs.getBoolean("active"));
            a.setType(rs.getString("type"));
            a.setTypevelo(rs.getString("typevelo"));
            a.setCouleur(rs.getString("couleur"));
            a.setGouvernorat(rs.getString("gouvernorat"));
            a.setDatep(rs.getDate("datep"));            
           
            maliste.add(a);
            
        }} catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }
    @Override
    public void DesactiverAnnonce(Annonce a){
        try {
            
            String req="Update annonce SET active=? WHERE ida=?";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setBoolean(1,false);
            ps.setInt(2,a.getIda());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void ActiverAnnonce(Annonce a){
        try {
            
            String req="Update annonce SET active=? WHERE ida=?";
            PreparedStatement ps= DataSource.getInstance().getCnx().prepareStatement(req);
            ps.setBoolean(1,true);
            ps.setInt(2,a.getIda());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public List<Annonce> RechercheCategorieAnnonces(String c) {
        List<Annonce> maliste = new ArrayList<>();
        try {
            // a.ville Like '%"+c+"%'"
            String req="SELECT * FROM Annonce where active=1 and categorie='"+c+"'";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Annonce a = new Annonce();
            a.setIda(rs.getInt("ida"));
            a.setIdu(rs.getInt("idu"));
            a.setCategorie(rs.getString("categorie"));
            a.setTitre(rs.getString("titre"));
            a.setDescription(rs.getString("description"));
            a.setPrix(rs.getDouble("prix"));
            a.setPhoto(rs.getString("photo"));
            a.setDate(rs.getDate("date"));
            a.setActive(rs.getBoolean("active"));
            a.setType(rs.getString("type"));
            a.setTypevelo(rs.getString("typevelo"));
            a.setCouleur(rs.getString("couleur"));
            a.setGouvernorat(rs.getString("gouvernorat"));
            a.setDatep(rs.getDate("datep"));            
           
            maliste.add(a);
            
        }} catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }
    
      public List<Annonce> RechercheAnnonces(String c) {
        List<Annonce> maliste = new ArrayList<>();
        try {
            // description Like '%"+c+"%'"
            String req="SELECT * FROM Annonce where active=1 and  description Like '%"+c+"%' or categorie LIKE '%"+c+"%' or titre LIKE '%"+c+"%' or type LIKE '%"+c+"%' or couleur LIKE '%"+c+"%' or typevelo LIKE '%"+c+"%' or date LIKE '%"+c+"%'or prix LIKE '%"+c+"%'";
            //String req="SELECT * FROM Annonce where description Like '%"+c+"%'";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Annonce a = new Annonce();
            a.setIda(rs.getInt("ida"));
            a.setIdu(rs.getInt("idu"));
            a.setCategorie(rs.getString("categorie"));
            a.setTitre(rs.getString("titre"));
            a.setDescription(rs.getString("description"));
            a.setPrix(rs.getDouble("prix"));
            a.setPhoto(rs.getString("photo"));
            a.setDate(rs.getDate("date"));
            a.setActive(rs.getBoolean("active"));
            a.setType(rs.getString("type"));
            a.setTypevelo(rs.getString("typevelo"));
            a.setCouleur(rs.getString("couleur"));
            a.setGouvernorat(rs.getString("gouvernorat"));
            a.setDatep(rs.getDate("datep"));            
           
            maliste.add(a);
            
        }} catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }
    
     public List<Annonce> getCategorie() {
        List<Annonce> maliste = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT DISTINCT categorie FROM `annonce`";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Annonce a = new Annonce();
            a.setCategorie(rs.getString("categorie"));
           
            
                maliste.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }
   
     public List<Annonce> afficherUserAnnonces(Utilisateur u) {
        List<Annonce> maliste = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM Annonce where idu='"+u.getId_user()+"'";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
            Annonce a = new Annonce();
            a.setIda(rs.getInt("ida"));
            a.setIdu(rs.getInt("idu"));
            a.setCategorie(rs.getString("categorie"));
            a.setTitre(rs.getString("titre"));
            a.setDescription(rs.getString("description"));
            a.setPrix(rs.getDouble("prix"));
            a.setPhoto(rs.getString("photo"));
            a.setDate(rs.getDate("date"));
            a.setActive(rs.getBoolean("active"));
            a.setType(rs.getString("type"));
            a.setTypevelo(rs.getString("typevelo"));
            a.setCouleur(rs.getString("couleur"));
            a.setGouvernorat(rs.getString("gouvernorat"));
            a.setDatep(rs.getDate("datep"));            
            //d.setEnseigneId(e.AfficherEnseigne((Integer) rs.getInt("enseigne_id")));
            //d.getEnseigneId().setUserId(uu.AfficherUser(d.getEnseigneId().getUserId().getId()));
            
            
                maliste.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }     
     
      public List<Annonce> listerAnnonceParDate(){
        List<Annonce> maliste = new ArrayList<>();
        try {
            
            String req="SELECT * FROM `annonce` where active=1 ORDER BY `date` DESC";
            PreparedStatement ps=DataSource.getInstance().getCnx().prepareStatement(req);
             ResultSet rs=ps.executeQuery(req);
            while(rs.next())
            {
                
            Annonce a = new Annonce();
            a.setIda(rs.getInt("ida"));
            a.setIdu(rs.getInt("idu"));
            a.setCategorie(rs.getString("categorie"));
            a.setTitre(rs.getString("titre"));
            a.setDescription(rs.getString("description"));
            a.setPrix(rs.getDouble("prix"));
            a.setPhoto(rs.getString("photo"));
            a.setDate(rs.getDate("date"));
            a.setActive(rs.getBoolean("active"));
            a.setType(rs.getString("type"));
            a.setTypevelo(rs.getString("typevelo"));
            a.setCouleur(rs.getString("couleur"));
            a.setGouvernorat(rs.getString("gouvernorat"));
            a.setDatep(rs.getDate("datep"));            
           
            maliste.add(a);
            
        }} catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }
      
       public List<Annonce> listerAnnonceParPrix(){
        List<Annonce> maliste = new ArrayList<>();
        try {
            
            String req="SELECT * FROM `annonce` where active=1 ORDER BY `prix` DESC";
            PreparedStatement ps=DataSource.getInstance().getCnx().prepareStatement(req);
             ResultSet rs=ps.executeQuery(req);
            while(rs.next())
            {
                
            Annonce a = new Annonce();
            a.setIda(rs.getInt("ida"));
            a.setIdu(rs.getInt("idu"));
            a.setCategorie(rs.getString("categorie"));
            a.setTitre(rs.getString("titre"));
            a.setDescription(rs.getString("description"));
            a.setPrix(rs.getDouble("prix"));
            a.setPhoto(rs.getString("photo"));
            a.setDate(rs.getDate("date"));
            a.setActive(rs.getBoolean("active"));
            a.setType(rs.getString("type"));
            a.setTypevelo(rs.getString("typevelo"));
            a.setCouleur(rs.getString("couleur"));
            a.setGouvernorat(rs.getString("gouvernorat"));
            a.setDatep(rs.getDate("datep"));            
           
            maliste.add(a);
            
        }} catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }

    public List<Stat> StatisticNre() {
    List<Stat> maliste = new ArrayList<Stat>();
        try {

            String req="SELECT COUNT(a.ida) AS nbr, a.gouvernorat AS ens FROM annonce a GROUP BY a.gouvernorat";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                //System.out.println("l'id : "+rs.getInt("id")+" le nom : "+rs.getString("nom")+" le prenom : "+rs.getString("prenom"));
            Stat sv = new Stat();
            sv.setNbr(rs.getInt("nbr"));
            sv.setNom(rs.getString("ens"));
            
            
                maliste.add(sv);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;    }

    public List<Stat> StatisticNrv() {
        List<Stat> maliste = new ArrayList<Stat>();
        try {
            String req="SELECT COUNT(a.ida) AS nbr, a.type AS ens FROM annonce a GROUP BY a.type";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                //System.out.println("l'id : "+rs.getInt("id")+" le nom : "+rs.getString("nom")+" le prenom : "+rs.getString("prenom"));
            Stat sv = new Stat();
            sv.setNbr(rs.getInt("nbr"));
            sv.setNom(rs.getString("ens"));
            
            
                maliste.add(sv);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;    }

      public List<Stat> StatisticNde(){
    List<Stat> maliste = new ArrayList<Stat>();
        try {
            String req="SELECT COUNT(s.ida) AS nbr, a.categorie AS ens FROM signaler s JOIN annonce a WHERE s.ida=a.ida GROUP BY a.categorie";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                //System.out.println("l'id : "+rs.getInt("id")+" le nom : "+rs.getString("nom")+" le prenom : "+rs.getString("prenom"));
            Stat sv = new Stat();
            sv.setNbr(rs.getInt("nbr"));
            sv.setNom(rs.getString("ens"));
            
            
                maliste.add(sv);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
        
    }    

    public List<Stat> StatisticNdv() {
    List<Stat> maliste = new ArrayList<Stat>();
        try {
            String req="SELECT COUNT(s.ida) AS nbr, s.cause AS ens FROM signaler s GROUP BY s.cause";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                //System.out.println("l'id : "+rs.getInt("id")+" le nom : "+rs.getString("nom")+" le prenom : "+rs.getString("prenom"));
            Stat sv = new Stat();
            sv.setNbr(rs.getInt("nbr"));
            sv.setNom(rs.getString("ens"));
            

                maliste.add(sv);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;    }
    
    public List<String> getCause(int ida){
        List<String> maliste = new ArrayList<String>();
        try {
            String req="SELECT DISTINCT cause FROM `signaler` WHERE ida='"+ida+"'";
            Statement s=DataSource.getInstance().getCnx().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
                
          
           maliste.add(rs.getString("cause"));
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnnonceServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maliste;
    }
     
}
