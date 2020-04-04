/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Parentt;
import pidev.utils.ConnectionBD;

/**
 *
 * @author safa
 */
public class ParenttService {
      Connection cnx = ConnectionBD.getInstance().getCnx();


    
    public void ajouter(Parentt t) {
   
        String requete = "insert into user (nom,prenom,email,mdp,tel) values (?,?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = ConnectionBD.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, t.getNom());//
            pst.setString(2, t.getPrenom());//
            pst.setString(3, t.getEmail());//
            pst.setString(4, t.getMdp());//
            pst.setInt(5, t.getTel());//
            
            

            pst.executeUpdate();
            System.out.println("Insertion effectué avec succés");
        } catch (SQLException ex) {
        }
    }

   public void supprimer(String id)
         {
                
             String requete="DELETE FROM user WHERE id='"+id+"' ";     
             Statement st;
             try {
              st = cnx.createStatement(); 
              st.executeUpdate(requete);
              System.out.println("Enfant supprimé");

            } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}

    
    public void modifier(Parentt t) {
        try {
            String requete = "UPDATE user SET nom=?,prenom=?,email=?,mdp=?,tel=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(6, t.getId());
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMdp());

            pst.setInt(5,t.getTel());
            pst.executeUpdate();
            System.out.println("Enfant modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    public List<Parentt> afficher() {
ObservableList <Parentt> list =FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM user";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Parentt(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }    
}
