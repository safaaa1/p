/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entites.Utilisateur;
import pidev.utils.ConnectionBD;
/**
 *
 * @author safa
 */
public class GestionUtilisateur {
 public void ajouterClient(Utilisateur utilisateur) {
        String requete = "insert into Utilisateurs (username,username_canonical,email,email_canonical,password,nom,prenom,roles,enabled) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = ConnectionBD.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, utilisateur.getUsername());//
            pst.setString(2, utilisateur.getUsername());//
            pst.setString(3, utilisateur.getEmail());//
            pst.setString(4, utilisateur.getEmail());//
            pst.setString(5, utilisateur.getPassword());//
            pst.setString(6, utilisateur.getNomUser());//
            pst.setString(7, utilisateur.getPrenomUser());//
            pst.setString(8, "a:1:{i:0;s:11:\"ROLE_CLIENT\";}");//
            pst.setInt(9, 1);

            pst.executeUpdate();
            System.out.println("Insertion effectué avec succés");
        } catch (SQLException ex) {
        }
    }

    public boolean mailExiste(String mail) throws SQLException {
        String req = "select * from User where email =?";
        PreparedStatement preparedStatement;
        preparedStatement = ConnectionBD.getInstance().getCnx().prepareStatement(req);
        preparedStatement.setString(1, mail);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return false;
        return true;
    }   
}
