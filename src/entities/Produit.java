package entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Objects;

/**
 *
 * @author Raef
 */
public class Produit {

    private int id_P;
    private int id_U;
    private String nom_P;
    private String type_P;
    
public Produit(){}
    public Produit(int id_P, int id_U,String nom_P, String type_P) {
        this.id_P = id_P;
        this.id_U = id_U;
        this.nom_P = nom_P;
        this.type_P = type_P;
    }

    public int getId_P() {
        return id_P;
    }

    public void setId_P(int id_P) {
        this.id_P = id_P;
    }
     public int getId_U() {
        return id_U;
    }

    public void setId_U(int id_U) {
        this.id_U = id_U;
    }

    public String getNom_P() {
        return nom_P;
    }

    public void setNom_P(String nom_P) {
        this.nom_P = nom_P;
    }

    public String getType_P() {
        return type_P;
    }

    public void setType_P(String type_P) {
        this.type_P = type_P;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id_P;
        hash = 79 * hash + this.id_U;
        hash = 79 * hash + Objects.hashCode(this.nom_P);
        hash = 79 * hash + Objects.hashCode(this.type_P);
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
        final Produit other = (Produit) obj;
        if (this.id_P != other.id_P) {
            return false;
        }
        if (this.id_U != other.id_U) {
            return false;
        }
        if (!Objects.equals(this.nom_P, other.nom_P)) {
            return false;
        }
        if (!Objects.equals(this.type_P, other.type_P)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_P=" + id_P + ", id_U=" + id_U + ", nom_P=" + nom_P + ", type_P=" + type_P + '}';
    }

   



}
