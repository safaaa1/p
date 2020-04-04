/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author safa
 */
public class DashboardController implements Initializable {

    @FXML
    private Button GestEvent;
    @FXML
    private Button GestReser;
    @FXML
    private Button GestPar;
    @FXML
    private Button avis;
    
    @FXML
    private AnchorPane container_client;
    @FXML
    private Button btn_goBack;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GestEvent(ActionEvent event) throws IOException {
         System.out.println(" Ajouter un evenement ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/accueil.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }
   

    @FXML
    private void GestDispo(ActionEvent event) throws IOException {
        System.out.println(" Lister les evenements ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficheEvenement.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root); 
    }


    @FXML
    private void VoirCoteClient(ActionEvent event) {
    }

    @FXML
    private void Deconnexion(MouseEvent event) {
    }

    @FXML
    private void GestPar(ActionEvent event) throws IOException {
      System.out.println(" Ajouter un evenement ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/GestionParent.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);   
    }

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        System.out.println(" Retour ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
        Parent root = loader.load();
        container_client.getChildren().setAll(root);
    }
 
}
