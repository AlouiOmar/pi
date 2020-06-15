/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entite.Reservation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author nahawnd
 */
public interface IServiceReservation {
     public void addLouer(Reservation r) throws SQLException;

    public List<Reservation> getLouers() throws SQLException;

    public Reservation getById(int id) throws SQLException;

    public void deleteLouer(Reservation r)throws SQLException;

    public void deleteLouer(int id) throws SQLException;

    public void updateLouer(Reservation r) throws SQLException;
    
}
