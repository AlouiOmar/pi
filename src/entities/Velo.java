/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.Produit;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Raef
 */
public class Velo extends Produit{
    private String marque_P;
    private String categorie_P;
    private String couleur_P;
    private float prix_P;
    private String photo_P;
    private Date date;
    
    public Velo(){}

    public Velo(int id_P,int id_U, String nom_P, String type_P,String marque_P, String categorie_P, String couleur_P, float prix_P, String photo_P, Date date) {
        super(id_P,id_U, nom_P, type_P);
        this.marque_P = marque_P;
        this.categorie_P = categorie_P;
        this.couleur_P = couleur_P;
        this.prix_P = prix_P;
        this.photo_P = photo_P;
        this.date=date;
    }

    public String getMarque_P() {
        return marque_P;
    }

    public void setMarque_P(String marque_P) {
        this.marque_P = marque_P;
    }

    public String getCategorie_P() {
        return categorie_P;
    }

    public void setCategorie_P(String categorie_P) {
        this.categorie_P = categorie_P;
    }

    public String getCouleur_P() {
        return couleur_P;
    }

    public void setCouleur_P(String couleur_P) {
        this.couleur_P = couleur_P;
    }

    public float getPrix_P() {
        return prix_P;
    }

    public void setPrix_P(float prix_P) {
        this.prix_P = prix_P;
    }

    public String getPhoto_P() {
        return photo_P;
    }

    public void setPhoto_P(String photo_P) {
        this.photo_P = photo_P;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Velo other = (Velo) obj;
        if (Float.floatToIntBits(this.prix_P) != Float.floatToIntBits(other.prix_P)) {
            return false;
        }
        if (!Objects.equals(this.marque_P, other.marque_P)) {
            return false;
        }
        if (!Objects.equals(this.categorie_P, other.categorie_P)) {
            return false;
        }
        if (!Objects.equals(this.couleur_P, other.couleur_P)) {
            return false;
        }
        if (!Objects.equals(this.photo_P, other.photo_P)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Velo{"+super.toString()+ "marque_P=" + marque_P + ", categorie_P=" + categorie_P + ", couleur_P=" + couleur_P + ", prix_P=" + prix_P + ", photo_P=" + photo_P + ", date=" + date + '}';
    }

   
    
    

    
}
