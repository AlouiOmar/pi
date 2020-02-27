/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entite.Location;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author nahawnd
 */
public interface IServiceLocation {
   public void addLocation(Location l) throws SQLException;

    public List<Location> getLocations() throws SQLException;

    public Location getById(int id) throws SQLException;

    public void deleteLocation(Location l) throws SQLException;

    public void deleteLocation(int id) throws SQLException;

    public void updateLocation(Location l) throws SQLException;
    
}
