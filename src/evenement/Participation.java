/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement;
import evenement.*;

/**
 *
 * @author Administrateur
 */
public class Participation {
    int id_E;
    int id_U;
   

    public Participation(int id_E, int id_U) {
        this.id_E = id_E;
        this.id_U = id_U;
       
    }

    public Participation() {
    }

    @Override
    public String toString() {
        return "Participation{" + "id_E=" + id_E + ", id_U=" + id_U +'}';
    }

    public void setId_E(int id_E) {
        this.id_E = id_E;
    }

    public void setId_U(int id_U) {
        this.id_U = id_U;
    }


    public int getId_E() {
        return id_E;
    }

    public int getId_U() {
        return id_U;
    }

   

    
    
}
