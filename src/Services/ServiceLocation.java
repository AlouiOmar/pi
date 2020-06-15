/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Location;
import Interfaces.IServiceLocation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
                + "(`id_L`,`titre`, `lieu`, `prix`,`photo`)"
                + " VALUES (NULL, '"
                + l.getTitre() + "', '"
                //                + l.getDateDeb() + "', '"
                //                + l.getDateFin() + "','"
                //                + l.getDuree() + "', '"
                + l.getLieu() + "', '"
                + l.getPrix() + "', '"
                + l.getPhoto() + "' )";

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
            l2.setId_L(rst.getInt("id_L"));
            l2.setTitre(rst.getString("titre"));
//            l2.setDateDeb(rst.getDate("dateDeb"));
//            l2.setDateFin(rst.getDate("dateFin"));
//            l2.setDuree(rst.getInt("duree"));
            l2.setLieu(rst.getString("lieu"));
            l2.setPrix(rst.getFloat("prix"));
            l2.setPhoto(rst.getString("photo"));
            locations.add(l2);
        }
        return locations;
    }

    @Override
    public Location getById(int id) throws SQLException {
//        Location l = new Location();

        String query = "select * FROM `location` WHERE id_L= " + id + "";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(query);
        Location l = new Location();
        if (rst.next()) {
            l.setId_L(rst.getInt("id_L"));
            l.setTitre(rst.getString("titre"));
            l.setLieu(rst.getString("lieu"));
            l.setPrix(rst.getFloat("prix"));
            l.setPhoto(rst.getString("photo"));

        }

        return l;
    }

    @Override
    public void deleteLocation(Location l) throws SQLException {
        try {
            Statement stm = cnx.createStatement();
            String query = "DELETE FROM location WHERE id_L=" + l.getId_L();
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
            String query = "DELETE FROM `location` WHERE id_L='" + id + "'";
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
                    + "`lieu` ='" + l.getLieu() + "' ,`prix` ='" + l.getPrix() + "' , " + "`photo` ='" + l.getPhoto()
                    + "' WHERE `location`.`id_L` =" + l.getId_L();
            stm.executeUpdate(query);
            System.out.println("😃😈 element updated 😍 successefully 😈😃");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
