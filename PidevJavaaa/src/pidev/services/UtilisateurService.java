/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pidev.entites.Utilisateur;
import pidev.utils.ConnectionBD;

/**
 *
 * @author safa
 */
public class UtilisateurService {
   public Utilisateur seConnecter(String login) throws SQLException {

        Utilisateur utilisateur = new Utilisateur();

        String req = "Select * from Utilisateurs WHERE username='" + login + "'or email='" + login + "'";
        Statement statement = ConnectionBD.getInstance().getCnx().createStatement();
        ResultSet resultSet = statement.executeQuery(req);

        while (resultSet.next()) {
            utilisateur.setId(resultSet.getInt("id"));
            utilisateur.setUsername(resultSet.getString("username"));
            utilisateur.setPassword(resultSet.getString("password"));
            utilisateur.setRoles(resultSet.getString("roles"));
        }
        
        return utilisateur;
    }  
}
