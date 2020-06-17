/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_javafx.utile;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author USER
 */
public class Connection {
    final static String URL = "jdbc:mysql://127.0.0.1:3306/symfony";
    final static String LOGIN = "root";
    final static String PWD = "";
    static Connection instance = null;
    private java.sql.Connection cnx;
    private Connection() {
        
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PWD);
            System.out.println("cnx etablie  ");
        } catch (SQLException e) {
             System.out.println("erreur de cnx ");    
      

        }
    }

  public static Connection getInstance() {
        if (instance == null)
        {
            instance = new Connection();
        }
        return instance;
    }
    public  java.sql.Connection getConnection(){
        return cnx;
    }
}



