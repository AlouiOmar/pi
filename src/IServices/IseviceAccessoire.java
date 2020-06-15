/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import entities.Accessoire;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Raef
 */
public interface IseviceAccessoire {

    public void addAccessoire(Accessoire p) throws SQLException;

    public List<Accessoire> getAccessoires() throws SQLException;

    public Accessoire getAccessoireById(int ID) throws SQLException;

    public void UpdateAccessoire(Accessoire r) throws SQLException;

    public void deleteAccessoire(Accessoire a) throws SQLException;

    public List<Accessoire> FiltrerAccessoireByCouleur(String color) throws SQLException;

    public List<Accessoire> FiltrerAccessoireByprix(float f1, float f2) throws SQLException;
}
