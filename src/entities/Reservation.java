/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author nahawnd
 */
public class Reservation {

    private String titre;
    private int id;
    private Date dateDeb, dateFin;

    public Reservation() {
    }

    public Reservation(String titre, int id, Date dateDeb, Date dateFin) {
        this.titre = titre;
        this.id = id;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }

    public Reservation(String text, java.sql.Date valueOf, java.sql.Date valueOf0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Reservation{" + "titre=" + titre + ", id=" + id + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + '}';
    }

}
