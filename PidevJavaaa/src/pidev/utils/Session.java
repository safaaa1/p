/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.utils;

import pidev.entites.Utilisateur;




public class Session {
    
    private static Utilisateur loggedInUser = null;
    private static String ip = "127.0.0.1";
    private static int IdThisSujet=0;
    public static String addImage = "file:///C:/Users/safa/Desktop/";
   

    public static Utilisateur getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(Utilisateur loggedInUser) {
        Session.loggedInUser = loggedInUser;
    }

    public static String getIp() {
        return ip;
    }

   

    
    
    

   
    
    
    
    
    
}
