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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Parentt;
import pidev.utils.ConnectionBD;

/**
 *
 * @author safa
 */
public class ParentService {
    
      Connection cnx = ConnectionBD.getInstance().getCnx();
  public void add(Parentt parentt) {
        String requete = "insert into user (nom,prenom,email,mdp,tel) values (?,?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = ConnectionBD.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, parentt.getNom());//
            pst.setString(2, parentt.getPrenom());//
            pst.setString(3, parentt.getEmail());//
            pst.setString(4, parentt.getMdp());//
            pst.setInt(5, parentt.getTel());//
            
            

            pst.executeUpdate();
            System.out.println("Insertion effectué avec succés");
        } catch (SQLException ex) {
        }
    }
public boolean mailExiste(String mail) throws SQLException {
        String req = "select * from user where email =?";
        PreparedStatement preparedStatement;
        preparedStatement = ConnectionBD.getInstance().getCnx().prepareStatement(req);
        preparedStatement.setString(1, mail);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return false;
        return true;
    }

 public ObservableList<Parentt> afficher()
   {
        ObservableList<Parentt> listE = FXCollections.observableArrayList();
        try {
            String requete = "select * from user ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                Parentt evt = new Parentt();
                
               
                evt.setNom(rs.getString("Nom"));
               evt.setPrenom(rs.getString("type"));
               evt.setEmail (rs.getString("email"));
               evt.setMdp(rs.getString("mdp"));
               evt.setTel(rs.getInt("tel"));
               
                listE.add(evt);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listE;
    }
  



}
