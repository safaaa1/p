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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entites.Evenement;
import pidev.entites.Parentt;
import pidev.entites.Reservation;
import pidev.entites.Utilisateur;
import pidev.utils.ConnectionBD;
import pidev.utils.Session;

/**
 *
 * @author safa
 */
public class ReservationService {
     Connection cnx = ConnectionBD.getInstance().getCnx();
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
   
   /* public void reserver(Evenement t) {
        
        String req = "update event set nbrPlace = ? where nom = ?";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, nom);
            pst.setInt(1,t.getNbrPlace()-);
            pst.executeUpdate();
            System.out.println("commade traite!");
        } catch (SQLException ex) {
        }
    }
    
   */
   /*  public void reserver(int basketId) throws SQLException {

        
            String req = "update evenement set nbrPlace = ? where id = ?;";
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
     
   
    
    
    
    
    
    
    
    
    
    
    
    
    
         public Statement ste;

    public ReservationService()throws SQLException  {
        ste=cnx.createStatement();
    }

    
    
   public boolean exist(int i)
    { 
         Utilisateur usr = Session.getLoggedInUser();
         Integer idUser = usr.getId();
         ResultSet rs =null;
         boolean v = false;
        try {  
            String requete = "SELECT * FROM reservation WHERE `idEvent`="+i+" and `id` ='"+idUser+"'";

           
             rs = ste.executeQuery(requete);

             while(rs.next()){
                v=true;              
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    
    }
      
         
     public boolean reserver2(Reservation p)
    {
       String requete = "INSERT INTO reservation (etat,dateReservation) VALUES (?,?) ";
        Utilisateur usr = Session.getLoggedInUser();
       
       try {
          
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, (Date) p.getDateReservation());
            pst.setString(2,p.getEtat());
          
           
            pst.executeUpdate();
            System.out.println("ajouté avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
         
    
    
    
    
    
    
    
    
    
}
