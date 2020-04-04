/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Evenement;
import pidev.entites.Utilisateur;
import pidev.utils.ConnectionBD;
import pidev.utils.Session;

/**
 *
 * @author safa
 */
public class EvenementService {
    
    Connection cnx = ConnectionBD.getInstance().getCnx();
         
    public void add(Evenement evenement){
        
        String req = "insert into evenement (nom, type, date, nbrPlace, dressCode,image) values(?, ?, ?, ?, ?, ?);";
        try {
            java.util.Date dateeve = new Date(evenement.getDate().getTime());
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setString(1, evenement.getNom());
                pst.setString(2, evenement.getType());
                pst.setDate(3, (Date)dateeve);
                pst.setInt(4, evenement.getNbrPlace());
                pst.setString(5, evenement.getDressCode());
                pst.setString(6, evenement.getImage());
                pst.executeUpdate();
                System.out.println("evenement crée!");
            } catch (SQLException ex) {
            }
    }
    
    public ObservableList<Evenement> getAllEvent()
   {
   
   
   
        ObservableList<Evenement> listEvenement = FXCollections.observableArrayList();
                    String requete = "select * from evenement ";

        try {
            PreparedStatement st = cnx.prepareStatement(requete);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Evenement evt = new Evenement();
                
               //evt.setIdEvent(rs.getInt("id"));
                evt.setNom(rs.getString("Nom"));
               evt.setType(rs.getString("type"));
               evt.setDate (rs.getDate("date"));
               evt.setNbrPlace(rs.getInt("NbrPlace"));
               evt.setDressCode(rs.getString("DressCode"));
               evt.setImage(rs.getString("image"));
               
                listEvenement.add(evt);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listEvenement;
    }
   
   
   
   
   
  public void remove(String nom){
        
        String req = "delete from evenement where nom = ?;";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1,nom);
            pst.executeUpdate();
            System.out.println("event supprimée!");
        } catch (SQLException ex) {
        }
    }
    public void supprimer(Evenement t) {
        try {
            String requete = "DELETE FROM evenement WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdEvent());
            pst.executeUpdate();
            System.out.println("Enfant supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }
   public ObservableList<Evenement> chercher(String mot){
        ObservableList<Evenement> list = FXCollections.observableArrayList();
        String req = "select * from evenement  where nom like '"+mot+"' or type like '"+mot+"' or nbrPlace like '"+mot+"' or dressCode like '"+mot+"';";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Evenement p = new Evenement(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                list.add(p);
            }
        } catch (SQLException ex) {
        }
        System.out.println(" recherche effectuee");
        return list;
    }
   public boolean modifierEvenement(String  nom,Evenement e)
    { 
    String requete="UPDATE evenement SET nom=?, type=?, date=?, nbrPlace=? , dressCode=? ,image=? where nom='"+nom+"'";
        try {
           java.util.Date date = new Date(e.getDate().getTime());
            PreparedStatement pst =cnx.prepareStatement(requete);
            pst.setString(1,e.getNom());
            pst.setString(1,e.getType());

            pst.setDate(3, (Date) date);
            pst.setInt(4,e.getNbrPlace());

            pst.setString(5,e.getDressCode());
            pst.setString(6,e.getImage());
             pst.executeUpdate();
            System.out.println("modifié avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
    public Evenement getByUser(String nom)
   {
       Utilisateur usr = Session.getLoggedInUser();
       
        Evenement ev = new Evenement();
        try {
            String requete = "select * from evenement where nom='"+nom+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                
               
               usr.setNomUser(nom);
               ev.setNom(rs.getString("Nom"));
               ev.setType(rs.getString("type"));
               ev.setDate (rs.getDate("date"));
               ev.setNbrPlace(rs.getInt("nbrPlace"));
               ev.setDressCode(rs.getString("dressCode"));
               ev.setImage(rs.getString("image"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return ev;
    }



 public void modifier(Evenement t) {
        try {
            String requete = "UPDATE evenement SET nom=?,type=?,date=?,nbrPlace=?,dressCode=?,image=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(7, t.getIdEvent());
            pst.setString(1, t.getNom());
            pst.setString(2, t.getType());
            pst.setDate(3, (Date) t.getDate());
            pst.setInt(4, t.getNbrPlace());

            pst.setString(5,t.getDressCode());
           pst.setString(6,t.getImage());

            pst.executeUpdate();
            System.out.println("Enfant modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }


 /*public void traiter(int idCommande){
        
        String req = "update commande set etat = ? where id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, "traite");
            pst.setInt(2, idCommande);
            pst.executeUpdate();
            System.out.println("commade traite!");
        } catch (SQLException ex) {
        }
    }*/






 
   public void reserver(int basketId){
        Evenement e=new Evenement();
            String req = "update evenement set nbrPlace = ? where id_Reser = ?;";
               try {
                System.out.println("0");
                PreparedStatement pst = cnx.prepareStatement(req);
                System.out.println("1");
                pst.setInt(1, getQtOfProduct2(basketId) - 1);
                pst.setInt(2, basketId);
                System.out.println("2");
                pst.executeUpdate();
                System.out.println("3");
                System.out.println("qt ++");
                System.out.println("4");
            } catch (SQLException ex) {
            }
        }
    public int getQtOfProduct2(int basketId) throws SQLException {
        int qt = 0;
        System.out.println("00");
        String req = "select nbrPlace from evenement where id_Reser = ?";
        System.out.println("11");
        PreparedStatement pst = cnx.prepareStatement(req);
        System.out.println("22");
        pst.setInt(1, basketId);
        System.out.println("33:" + basketId);
        ResultSet rs = pst.executeQuery();
        System.out.println("44");
        while (rs.next()) {
            System.out.println("55");
            qt = rs.getInt(1);
        }
        System.out.println("66");
        return qt;
    }  
     


}
