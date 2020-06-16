/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author nahawnd
 */
public class Location {

   private int id;
    private String titre;
    private String lieu;
    private float prix;
    private String photo;
//    private int rating;
    private Date dateCreation;

    public Location() {
    }

    public Location(int id, String titre, String lieu, float prix, String photo, Date dateCreation) {
        this.id = id;
        this.titre = titre;
        this.lieu = lieu;
        this.prix = prix;
        this.photo = photo;
        this.dateCreation = dateCreation;
    }

    public Location(String titre, String lieu, float prix, String photo, Date dateCreation) {
        this.titre = titre;
        this.lieu = lieu;
        this.prix = prix;
        this.photo = photo;
        this.dateCreation = dateCreation;
    }

    public Location(String text, String text0, Float valueOf, String text1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

   
    public int getId() {
        return id;
    }
    
      public void setId(int id) {
        this.id = id;
    }
      
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLieu() {
        return lieu;
    }

      public void setLieu(String lieu) {
        this.lieu = lieu;
    }
      
    public float getPrix() {
        return prix;
    }
    
  public void setPrix(float prix) {
        this.prix = prix;
    }
  
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

   

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", titre=" + titre + ", lieu=" + lieu + ", prix=" + prix + ", photo=" + photo + ", dateCreation=" + dateCreation + '}';
    }

   
}