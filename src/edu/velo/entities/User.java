/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.entities;

/**
 *
 * @author Aloui Omar
 */
public class User {
    private int idu;
    private String nom;
    private String prenom;
    private String email;
    private String role;

    public User(int idu, String nom, String prenom, String email, String role) {
        this.idu = idu;
        this.nom = nom;
        this.prenom = prenom;
        this.email=email;
        this.role = role;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "idu=" + idu + ", nom=" + nom + ", prenom=" + prenom + ", role=" + role + '}';
    }
    
}
