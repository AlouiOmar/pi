/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.velo.userentites;

/**
 *
 * @author USER
 */
public class Utilisateur {
    private static int id_user;
    private String nom;
    private String prenom;
    private String email;
    private int age;
    private int telephone;
    private String role ;
    private String password;
    private String photo;

    public Utilisateur( String nom, String prenom, String email, int age, int telephone, String role, String password,String photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.telephone = telephone;
        this.role = role;
        this.password = password;
        this.photo=photo;
    }

    public Utilisateur() {
       
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

   

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", age=" + age + ", telephone=" + telephone + ", role=" + role + ", password=" + password + ", photo=" + photo + '}';
    }

  
    
    
    
}
