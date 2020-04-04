/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import pidev.controller.SeConnecterController;
import pidev.entites.Utilisateur;
import pidev.services.GestionUtilisateur;
import pidev.utils.Password;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author safa
 */
public class SinscrireController implements Initializable {
    ObservableList<String> rolesList=FXCollections.observableArrayList("admin","parent","medecin","enseignant");
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField numTel;
    @FXML
    private TextField mdp;
       @FXML
private ChoiceBox rolesBox;
               
       
    Boolean verificationNom = false;
    Boolean verificationPrenom = false;
    Boolean verificationEmail = false;
    Boolean verificationNumTel = false;
    Boolean verificationMdp = false;
    @FXML
    private Label numTelTest;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("S'inscrire");
        numTelTest.setVisible(false);
        rolesBox.setItems(rolesList);
    }

    @FXML
    public void sinscrire(ActionEvent actionEvent) throws IOException {
        if (verificationNom == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir le nom");
            alert.show();

        } else if (verificationPrenom == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veillez remplir le prenom");
            alert.show();

            } else if (verificationEmail == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir l'email");
            alert.show();
        } else if (verificationNumTel == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir le telephone");
            alert.show();

        } else if (verificationEmail == false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez remplir l'email");
            alert.show();
        }else if ((verificationMdp == false)) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Veuillez resaissir votre Mot de Passe Correctement");
            alert.show();

        }else{
            Password md = new Password();
            String mdpCrypte1 = md.hashPassword(mdp.getText());

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setUsername(nom.getText());
            utilisateur.setEmail(email.getText());
            utilisateur.setEmailCanonical(email.getText());
            utilisateur.setPassword(mdpCrypte1);
            utilisateur.setNomUser(nom.getText());
            utilisateur.setPrenomUser(prenom.getText());
            utilisateur.setRoles((String) rolesBox.getValue());

            GestionUtilisateur gestionUtilisateur = new GestionUtilisateur();
            gestionUtilisateur.ajouterClient(utilisateur);
            
            TrayNotification tray = new TrayNotification("Successfully",
                    "Inscription Effectuée avec Succés", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
            Parent root = loader.load();
            SeConnecterController seConnecterController = loader.getController();
            prenom.getScene().setRoot(root);
        }
    }

    public void action(KeyEvent keyEvent) {
        System.out.println("action");
    }

    @FXML
    private void controlNom(KeyEvent event) {

        if (nom.getText().trim().equals("")) {
            verificationNom = false;
        } else {
            verificationNom = true;
        }

    }

    @FXML
    private void controlPrenom(KeyEvent event) {
        if (prenom.getText().trim().equals("")) {
            verificationPrenom = false;
        } else {
            verificationPrenom = true;
        }
    }

    @FXML
    private void controlNumero(KeyEvent event) {
        verificationNumTel = false;
        if (numTel.getText().trim().length() == 7) {
            boolean test = true;
            for (int i = 1; i < numTel.getText().trim().length() && test; i++) {
                char ch = numTel.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    test = false;
                }
            }
            if (test) {
                System.out.println("taille num est valide");
                numTelTest.setVisible(false);
                verificationNumTel = true;
            }
        } else {
            System.out.println("taille num non valide");
            numTelTest.setVisible(true);
            numTelTest.setText("Il faut 8 chiffres");
            verificationNumTel = false;
        }
    }
    
    @FXML
    private void controlEmail(KeyEvent event) throws SQLException {
        verificationEmail = false;
        GestionUtilisateur gestionUtilisateur = new GestionUtilisateur();
        if(gestionUtilisateur.mailExiste(email.getText())){
            System.out.println("mail est unique");
            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(email.getText());

            if (matcher.matches())
                verificationEmail = true;
        }
        else
            System.out.println("mail non valide");
    }
    
    @FXML
    private void controlMDP(KeyEvent event) {

        if (mdp.getText().trim().equals("")) {

            verificationMdp = false;

        } else {

            verificationMdp = true;

        }
    }

}

