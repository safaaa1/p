/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;


import pidev.controller.PanelParentController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pidev.entites.Utilisateur;
import pidev.services.UtilisateurService;
import pidev.utils.Password;
/**
 *
 * @author safa
 */
public class SeConnecterController implements Initializable {

    @FXML
    private TextField nomUtilisateur;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button seConnecter;

    public static int idUtilisateur;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("se connecter controller");
    }

    @FXML
    public void connecter(ActionEvent actionEvent) throws SQLException, IOException {
        System.out.println("se connecter action");

        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur utilisateur = new Utilisateur();

        utilisateur = utilisateurService.seConnecter(nomUtilisateur.getText());
        String responsable= "a:1:{i:0;s:16:\\\"ROLE_RESPONSABLE\\\";}";
        String testRole = utilisateur.getRoles();
        Password md = new Password();
        Boolean mdpCrypte = md.checkPassword(mdp.getText(), utilisateur.getPassword());

        if (mdpCrypte == true) {
            System.out.println("authentification reussite");
            
            idUtilisateur = utilisateur.getId();
            if (utilisateur.getRoles().equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/panelParent.fxml"));
                Parent root = loader.load();
                PanelParentController panelClientController = loader.getController();
                mdp.getScene().setRoot(root);
            }else if(testRole.equals(responsable)){
               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficheEvenement.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                Stage secondStage = new Stage();
                secondStage.setScene(new Scene(root));
               Stage stage = (Stage) mdp.getScene().getWindow();
                stage.hide();
                  secondStage.show();}  
            
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("veuillez verifier votre login ou mot de passe ..");
            alert.show();
            System.out.println("veuillez verifier votre login ou mot de passe ..");
        }

    }

    @FXML
    public void sinscrire(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/sinscrire.fxml"));
        Parent root = loader.load();
        SinscrireController sinscrireController = loader.getController();
        nomUtilisateur.getScene().setRoot(root);
    }

}
