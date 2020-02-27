/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import entities.Rechange;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Raef
 */
public interface IserviceRechange {

    public void addRechange(Rechange p) throws SQLException;

    public List<Rechange> getRechanges() throws SQLException;

    public Rechange getRechangeById(int ID) throws SQLException;

    public void UpdateRechange(Rechange r) throws SQLException;

    public void deleteRechange(Rechange r) throws SQLException;

    public List<Rechange> FiltrerRechangeByMarque(String marque) throws SQLException;
    
    public List<Rechange> FiltrerRechangeByprix(float f1, float f2) throws SQLException;
}
