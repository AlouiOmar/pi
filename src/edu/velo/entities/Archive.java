/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.entities;

import java.sql.Date;

/**
 *
 * @author Aloui Omar
 */
public class Archive {
    private int ida;
    private String categorie;
    private String titre;
    private String description;
    private double prix;
    private String photo;
    private Date date;
    private String type;
    private String typevelo;
    private String couleur;

    public Archive() {
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypevelo() {
        return typevelo;
    }

    public void setTypevelo(String typevelo) {
        this.typevelo = typevelo;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "Archive{" + "ida=" + ida + ", categorie=" + categorie + ", titre=" + titre + ", description=" + description + ", prix=" + prix + ", photo=" + photo + ", date=" + date + ", type=" + type + ", typevelo=" + typevelo + ", couleur=" + couleur + '}';
    }

}
