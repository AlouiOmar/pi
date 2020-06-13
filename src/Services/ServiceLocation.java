/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Location;
import Interfaces.IServiceLocation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author nahawnd
 */
public class ServiceLocation implements IServiceLocation {

    Connection cnx;

    public ServiceLocation() {
        cnx = MyConnection.getInstance().getConnection();
    }

    @Override
    public void addLocation(Location l) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "INSERT INTO `location` "
                + "(`id`,`titre`, `lieu`, `prix`,`photo`,`rating`,`dateCreation`)"
                + " VALUES (NULL, '"
                + l.getTitre() + "', '"
                + l.getLieu() + "', '"
                + l.getPrix() + "', '"
                + l.getPhoto() + "', '"
                + l.getRating() + "', '"
                + l.getDateCreation() + "' )";

        stm.executeUpdate(query);
        System.out.println("😃😈 element insert 😍 successfully 😈😃");
    }

    @Override
    public List<Location> getLocations() throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "select * from `location`";
        ResultSet rst = stm.executeQuery(query);
        List<Location> locations = new ArrayList<>();
        while (rst.next()) {
            Location l2 = new Location();
            l2.setId(rst.getInt("id"));
            l2.setTitre(rst.getString("titre"));
            l2.setLieu(rst.getString("lieu"));
            l2.setPrix(rst.getFloat("prix"));
            l2.setPhoto(rst.getString("photo"));
            l2.setRating(rst.getInt("rating"));
            l2.setDateCreation(rst.getDate("dateCreation"));
            locations.add(l2);
        }
        return locations;
    }

    @Override
    public Location getById(int id) throws SQLException {
//        Location l = new Location();

        String query = "select * FROM `location` WHERE id= " + id + "";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(query);
        Location l = new Location();
        if (rst.next()) {
            l.setId(rst.getInt("id"));
            l.setTitre(rst.getString("titre"));
            l.setLieu(rst.getString("lieu"));
            l.setPrix(rst.getFloat("prix"));
            l.setPhoto(rst.getString("photo"));
            l.setRating(rst.getInt("rating"));
            l.setDateCreation(rst.getDate("dateCreation"));

        }

        return l;
    }

    @Override
    public void deleteLocation(Location l) throws SQLException {
        try {
            Statement stm = cnx.createStatement();
            String query = "DELETE FROM `location` WHERE id=" + l.getId();
            stm.executeUpdate(query);
            System.out.println("Location deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLocation(int id) throws SQLException {
        try {
            Statement stm = cnx.createStatement();
            String query = "DELETE FROM `location` WHERE id='" + id + "'";
            // statement.executeUpdate(DeleteName("John"));
            stm.executeUpdate(query);
            System.out.println("Location deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLocation(Location l) throws SQLException {
        try {
            Statement stm = cnx.createStatement();
            String query = "UPDATE `location` SET `titre` ='" + l.getTitre() + "',"
                    + "`lieu` ='" + l.getLieu() + "' ,"
                    + "`prix` ='" + l.getPrix() + "' "
                    + "`photo` ='" + l.getPhoto()+ "' "
                    + "`rating` ='" + l.getRating()+ "' "
                    + "`dateCreation` ='" + l.getDateCreation()
                    + "' WHERE `location`.`id` =" + l.getId();
            stm.executeUpdate(query);
            System.out.println("😃😈 element updated 😍 successefully 😈😃");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Location> RechLocation(String titre) throws SQLException {
        List<Location> listrecherche = new ArrayList<>();
        

        try {
            String query = "SELECT * FROM `location` WHERE`titre` =" + titre+ "'";
            Statement stm = cnx.createStatement();
              ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Location l = new Location();
//                Location l = new Location();
                l.setId(rs.getInt("id"));
                l.setTitre(rs.getString("titre"));
                l.setLieu(rs.getString("lieu"));
                l.setPrix(rs.getFloat("prix"));
                l.setPhoto(rs.getString("photo"));
                l.setRating(rs.getInt("rating"));
                l.setDateCreation(rs.getDate("dateCreation"));
                listrecherche.add(l);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLocation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
    }

    public List<Location> getTrier() throws SQLException {
        List<Location> listTri = new ArrayList<>();
        try {
            String query = "select * from `location` ORDER BY prix ASC";
                  Statement stm = cnx.createStatement();
              ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                Location l = new Location();
                l.setId(rs.getInt("id"));
                l.setTitre(rs.getString("titre"));
                l.setLieu(rs.getString("lieu"));
                l.setPrix(rs.getFloat("prix"));
                l.setPhoto(rs.getString("photo"));
                l.setRating(rs.getInt("rating"));
                l.setDateCreation(rs.getDate("dateCreation"));
                listTri.add(l);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceLocation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTri;
    }

}
