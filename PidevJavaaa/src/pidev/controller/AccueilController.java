
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pidev.entites.Evenement;
import pidev.services.EvenementService;


/**
 *
 * @author safa
 */
public class AccueilController {

    @FXML
    private AnchorPane anchorP;
    @FXML
    private Button evenement;
    @FXML
    private Button userName;
    @FXML
    private Pane pane;
    @FXML
    private Button btn11;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane anchorEvent;
    @FXML
    private TextField nomEvent;
    @FXML
    private DatePicker dateEvent;
    @FXML
    private TextField TypeEvent;
    @FXML
    private Button imageEvent;
    @FXML
    private TextField nbrPlace;
    @FXML
    private TextField dressCode;
    @FXML
    private Button ajouterEvent;
    @FXML
    private ImageView iv;

    
    
     public String handle(){
        FileChooser fileChooser = new FileChooser();//Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.getName();
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            iv.setImage(image);

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return filePath;
    }  
     
     

    @FXML
    private void hidePane(MouseEvent event) {
    }

    @FXML
    private void chooseAction(MouseEvent event) {
        imageEvent.setText(handle());
    }

    @FXML
    private void CreateEvent(ActionEvent event) throws IOException, SQLException {
       
        
        EvenementService se= new EvenementService();
           LocalDate d = dateEvent.getValue();
         Date dated = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String nbP =  nbrPlace.getText().toString();
        int i=0;
        
      
         Date date = new Date();
         if(dated.compareTo(date) <= 0 ||(dated.equals("")))  
       {
           i++;
       Alert alert = new Alert(AlertType.WARNING, "Date invalide: Un évenement doit être créer avant au moins d'un jour", ButtonType.OK);
        alert.showAndWait();}
        
      
       
        if (TypeEvent.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING, "Veuillez Saisir un type", ButtonType.OK);
        alert.showAndWait();
             i++;
        }
        if (dressCode.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING, "Veuillez Saisir un code vestimentaire", ButtonType.OK);
        alert.showAndWait();
             i++;
        }
        if (nomEvent.getText().isEmpty()) {
             Alert alert = new Alert(AlertType.WARNING, "Veuillez Saisir un nom", ButtonType.OK);
        alert.showAndWait();
             i++;
        }

      if(i==0)
      { 
       Evenement e=new Evenement(nomEvent.getText(),TypeEvent.getText(),dated,Integer.parseInt(nbrPlace.getText()),dressCode.getText(),imageEvent.getText());
           
           se.add(e);
         System.out.println("evenement ajouté");
         
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pidev/gui/AfficheEvenement.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                Stage secondStage = new Stage();
                secondStage.setScene(new Scene(root));
                  secondStage.show();}  
       }

    @FXML
    private void dashboard(ActionEvent event) throws IOException {
    
 System.out.println(" Accueil ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Dashboard.fxml"));
        Parent root = loader.load();
        anchorP.getChildren().setAll(root);
    }
    
    }
    
