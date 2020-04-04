/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author safa
 */
public class ConnectionBD {
    
 static ConnectionBD instance;
    Connection connection;

    private ConnectionBD() {
        try {
            String url = "jdbc:mysql://localhost:3306/junior";
            String login = "root";
            String pwd = "";

            connection = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion Etablie ! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public Connection getCnx() {
        return connection;
    }

    public static ConnectionBD getInstance() {
        if (instance == null) {
            instance = new ConnectionBD();

        }
        return instance;
    }

}
