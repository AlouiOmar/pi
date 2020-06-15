/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Circuit;
import interfaces.IServiceCircuit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import utile.MaConnection;

/**
 *
 * @author ezzedine
 */
public class ServiceCircuit implements IServiceCircuit {

    Connection cnx;

    public ServiceCircuit() {
        cnx = MaConnection.getInstance().getConnection();

    }

    @Override
    public void addCircuit(Circuit c) throws SQLException {

        Statement stm = cnx.createStatement();
        String query = "INSERT INTO `circuit` (`id`, `name`, `begin`,`end`,`pause`,`distance`,`duration`,`difficulty`) VALUES (NULL, '"
                + c.getName() + "','"
                + c.getBegin() + "','"
                + c.getEnd() + "','"
                + c.getPause() + "','"
                + c.getDistance() + "','"
                + c.getDuration() + "','"
                + c.getDifficulty() + "')";
        stm.executeUpdate(query);
        System.out.println("add success");
    }

    @Override
    public void deleteCircuit(Circuit c) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "DELETE FROM `circuit` WHERE `id`=" + c.getId();
        stm.executeUpdate(query);
        System.out.println("iii");
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Circuit> getCircuits() throws SQLException {
        String querry = "SELECT * FROM `circuit` ";
        com.mysql.jdbc.Statement stm = (com.mysql.jdbc.Statement) cnx.createStatement();

        ResultSet rs = stm.executeQuery(querry);

        List<Circuit> circuits = new ArrayList<>();
        while (rs.next()) {
            Circuit c = new Circuit();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setBegin(rs.getString("begin"));
            c.setEnd(rs.getString("end"));
            c.setPause(rs.getString("pause"));
            c.setDistance(rs.getFloat("distance"));
            c.setDuration(rs.getTime("duration"));
            c.setDifficulty(rs.getString("difficulty"));
            //System.out.println(rs);
            circuits.add(c);
            
        }
      //  circuits.stream().forEach(System.out::println);
        return circuits;
    }

    @Override
    public void updateCircuit(Circuit c) throws SQLException {

        Statement stm = cnx.createStatement();
        String query = "UPDATE `circuit` SET `name` = '"+ c.getName() 
                + "', `begin` = '"+ c.getBegin() 
                + "', `end` = '"+ c.getEnd() 
                + "', `pause` = '"+ c.getPause()
                +"', `distance` = '"+ c.getDistance()
                +"', `duration` = '"+ c.getDuration()
                +"', `difficulty`= '"+ c.getDifficulty()
                + "' WHERE `circuit`.`id` = "+ c.getId();
                
        stm.executeUpdate(query);
        System.out.println("iii");
    }

    @Override
    public List<Circuit> getCircuitsSortedByDistanceIncreasing() throws SQLException {
        List<Circuit>ls= getCircuits();
        /*String querry = "SELECT * FROM circuit";
        Statement stm = (Statement) cnx.createStatement();
        ResultSet rs = stm.executeQuery(querry);
        List<Circuit> circuits = new ArrayList<>();
        while (rs.next()) {
        Circuit c = new Circuit();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setBegin(rs.getInt("begin"));
        c.setEnd(rs.getInt("end"));
        c.setPause(rs.getInt("pause"));
        c.setDistance(rs.getFloat("distance"));
        c.setDuration(rs.getTime("duration"));
        c.setDifficulty(rs.getString("difficulty"));
        circuits.add(c);
        }
        */
        List<Circuit> sortedList = new ArrayList<>();
        sortedList = ls.stream().sorted(Comparator.comparing(Circuit::getDistance))
                .collect(Collectors.toList());
        return sortedList;
    }
    @Override
    public List<Circuit> getCircuitsSortedByDurationIncreasing() throws SQLException {
        List<Circuit>ls= getCircuits();
          List<Circuit> sortedList = new ArrayList<>();
        sortedList = ls.stream().sorted(Comparator.comparing(Circuit::getDuration))
                .collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public List<Circuit> getHardCircuits() throws SQLException {
        List<Circuit>ls= getCircuits();
          List<Circuit> filtredList = new ArrayList<>();
        filtredList = ls.stream().filter(c->c.getDifficulty().equals("hard"))
                .collect(Collectors.toList());
        return filtredList;
    }

    @Override
    public List<Circuit> getEasyCircuits() throws SQLException {
    
        List<Circuit>ls= getCircuits();
          List<Circuit> filtredList = new ArrayList<>();
        filtredList = ls.stream().filter(c->c.getDifficulty().equals("easy"))
                .collect(Collectors.toList());
        return filtredList;
    
    }
    
}
