/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;

/**
 *
 * @author nahawnd
 */
public class Reservation {
     private int id_R;
     private String titre;
     private Date dateDeb, dateFin;

    public Reservation() {
    }

    public Reservation(int id_R, String titre, Date dateDeb, Date dateFin) {
        this.id_R = id_R;
        this.titre = titre;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }



    public int getId_R() {
        return id_R;
    }

    public String getTitre() {
        return titre;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setId_R(int id_R) {
        this.id_R= id_R;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Louer{" + "id_R=" + id_R + ", titre=" + titre + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + '}';
    }

    

    
}
