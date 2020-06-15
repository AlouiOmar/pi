/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Raef
 */
public class Rechange extends Produit{
     private String marque_P;
     private float prix_P;
     private String photo_P;
     private Date date;
     public Rechange(){}
    
    public Rechange(int id_P, int id_U,String nom_P, String type_P,String marque_P, float prix_P, String photo_P ,Date date ) {
        super(id_P,id_U,nom_P, type_P);
        this.marque_P = marque_P;
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
    public String toString() {
        return "Rechange{" + super.toString()+"marque_P=" + marque_P + ", prix_P=" + prix_P + ", photo_P=" + photo_P + ", date=" + date + '}';
    }

    
    
    
}
