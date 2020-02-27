/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author nahawnd
 */
public class Location {

    private int id_L;
    private String titre; 
    private String lieu;
    private float prix;
    private String photo;

    public Location() {
    }

    public Location(int id_L, String titre, String lieu, float prix, String photo) {
        this.id_L = id_L;
        this.titre = titre;
        this.lieu = lieu;
        this.prix = prix;
        this.photo = photo;
    }

    public int getId_L() {
        return id_L;
    }

    public String getTitre() {
        return titre;
    }

    public String getLieu() {
        return lieu;
    }

    public float getPrix() {
        return prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setId_L(int id_L) {
        this.id_L = id_L;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

   
   
   
}