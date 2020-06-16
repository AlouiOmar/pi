/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Reservation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;
import Interfaces.IServiceReservation;
import java.sql.PreparedStatement;

/**
 *
 * @author nahawnd
 */
public class ServiceReservation implements IServiceReservation {

    Connection cnx = MyConnection.getInstance().getConnection();

    Statement stm;

    public ServiceReservation() throws SQLException {

        try {
            stm = cnx.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addLouer(Reservation r) throws SQLException {
        String query = "INSERT INTO `reservation` "
                + "(`id`,`titre`, `dateDeb`, `dateFin`)"
                + " VALUES (NULL, '"
                + r.getTitre() + "', '"
                + r.getDateDeb() + "', '"
                + r.getDateFin() + "')";
        stm.executeUpdate(query);
    }

    @Override
    public List<Reservation> getLouers() throws SQLException {
        String query = "select * from `reservation`";
        ResultSet rst = stm.executeQuery(query);
        List<Reservation> louers = new ArrayList<>();
        while (rst.next()) {
            Reservation r2 = new Reservation();
            r2.setId(rst.getInt("id"));
            r2.setTitre(rst.getString("titre"));
            r2.setDateDeb(rst.getDate("dateDeb"));
            r2.setDateFin(rst.getDate("dateFin"));
            louers.add(r2);
        }
        return louers;
    }

    @Override
    public Reservation getById(int id) throws SQLException {
        Reservation r = new Reservation();
        try {
            String query = "select * from `reservation` where id= " + id;
            ResultSet rst = stm.executeQuery(query);

            r.setId(rst.getInt("id"));
            r.setTitre(rst.getString("titre"));
            r.setDateDeb(rst.getDate("dateDeb"));
            r.setDateFin(rst.getDate("dateFin"));
        } catch (SQLException ex) {

        }
        return r;
    }

    @Override
    public void updateLouer(Reservation r) throws SQLException {
        try {
            String query = "UPDATE `reservation`  SET `titre` ='" + r.getTitre() + "',"
                    + "`dateDeb` ='" + r.getDateDeb() + "' ,"
                    + "`dateFin` ='" + r.getDateFin() + "' "
                    + "' WHERE `reservation`.`id` =" + r.getId();
            stm.executeUpdate(query);
            System.out.println("😃😈 element updated 😍 successefully 😈😃");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLouer(Reservation r) throws SQLException {
        try {
            String query = "DELETE FROM reservation WHERE id=" + r.getId();
            stm.executeUpdate(query);
            System.out.println("Reservation deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLouer(int id) throws SQLException {
        try {
            String query = "DELETE FROM reservation WHERE id=" + id;
            stm.executeUpdate(query);
            System.out.println("Reservation deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
