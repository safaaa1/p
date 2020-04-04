/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import pidev.entites.Parentt;
import pidev.services.EvenementService;
import pidev.services.ParentService;
import pidev.utils.Password;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 *
 * @author safa
 */
public class ParentController implements Initializable{

    @FXML
    private Button btn_goBack;
    @FXML
    private Label lb;
    @FXML
    private ImageView iv;
    @FXML
    private TextField picturepath;
    @FXML
    private TextField input_nom;
    @FXML
    private TextField input_prenom;
    @FXML
    private TextField input_email;
    @FXML
    private TextField input_mdp;
    @FXML
    private Label lbnom;
    @FXML
    private Label lbrace;
    @FXML
    private Label lbtype;
    @FXML
    private Label lbage;
    @FXML
    private Label lbetat;
    @FXML
    private Label lbphoto;
    @FXML
    private TextField input_tel;
    @FXML
    private Label logOut;
    @FXML
    private TableView<Parentt> table_list_animal;
    @FXML
    private TableColumn<Parentt, Integer> column_id;
    @FXML
    private TableColumn<Parentt, String> column_nom;
    @FXML
    private TableColumn<Parentt,String > column_prenom;
    @FXML
    private TableColumn<Parentt, String> column_email;
    @FXML
    private TableColumn<Parentt, String> column_mdp;
    @FXML
    private TableColumn<Parentt, Integer> column_tel;
    Boolean verificationNom = true;
    Boolean verificationPrenom = true;
    Boolean verificationEmail = true;
    Boolean verificationNumTel = true;
    Boolean verificationMdp = true;
    private Label numTelTest;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("S'inscrire");

    }
    
    @FXML
    private void GoBack(ActionEvent event) {
    }


    @FXML
    private void logOut(MouseEvent event) {
    }

    @FXML
    private void supprimerAnimal(ActionEvent event) {
    }
   
   

    @FXML
    public void CreateParent(ActionEvent actionEvent) throws IOException {


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
            String mdpCrypte1 = md.hashPassword(input_mdp.getText());

            Parentt parentt = new Parentt();
            parentt.setNom(input_nom.getText());
            parentt.setPrenom(input_prenom.getText());
            parentt.setEmail(input_email.getText());
            parentt.setMdp(mdpCrypte1);


            ParentService gestionParent = new ParentService();
            gestionParent.add(parentt);
            
            TrayNotification tray = new TrayNotification("Successfully",
                    "ajout Effectué avec Succés", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/seConnecter.fxml"));
            Parent root = loader.load();
            
    }
    }
    public void action(KeyEvent keyEvent) {
        System.out.println("action");
    }

    private void controlNom(KeyEvent event) {

        if (input_nom.getText().trim().equals("")) {
            verificationNom = false;
        } else {
            verificationNom = true;
        }

    }

    private void controlPrenom(KeyEvent event) {
        if (input_prenom.getText().trim().equals("")) {
            verificationPrenom = false;
        } else {
            verificationPrenom = true;
        }
    }

    private void controlNumero(KeyEvent event) {
        verificationNumTel = false;
        if (input_tel.getText().trim().length() == 7) {
            boolean test = true;
            for (int i = 1; i < input_tel.getText().trim().length() && test; i++) {
                char ch = input_tel.getText().charAt(i);
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
    
    private void controlEmail(KeyEvent event) throws SQLException {
        verificationEmail = false;
        ParentService gestionParent = new ParentService();
        if(gestionParent.mailExiste(input_email.getText())){
            System.out.println("mail est unique");
            String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(email_pattern);
            Matcher matcher = pattern.matcher(input_email.getText());

            if (matcher.matches())
                verificationEmail = true;
        }
        else
            System.out.println("mail non valide");
    }
    
    private void controlMDP(KeyEvent event) {

        if (input_mdp.getText().trim().equals("")) {

            verificationMdp = false;

        } else {

            verificationMdp = true;

        }
    }

   
  public void afficherParents(){
       
       ParentService se = new ParentService();
       
         column_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         column_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         column_email.setCellValueFactory(new PropertyValueFactory<>("email")); 
         column_mdp.setCellValueFactory(new PropertyValueFactory<>("mdp")); 
         column_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));

         table_list_animal.setItems(se.afficher());
         

    }
    
}
