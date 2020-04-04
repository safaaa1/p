/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import pidev.entites.Evenement;
import pidev.entites.Parentt;
import pidev.entites.Reservation;
import pidev.entites.Utilisateur;
import pidev.services.EvenementService;
import pidev.utils.ConnectionBD;
import pidev.services.ReservationService;
import pidev.utils.Session;

/**
 * FXML Controller class
 *
 * @author safa
 */
public class EventFrontController implements Initializable {

    @FXML
    private Button parc;
    @FXML
    private Button btn_goBack;
    @FXML
    private Button userName;
    @FXML
    private AnchorPane containerEvent;
    @FXML
    private TableView<Evenement> tableSafa;
    @FXML
    private TableColumn<Evenement, Integer> Id;
    @FXML
    private TableColumn<Evenement, String> nom;
    @FXML
    private TableColumn<Evenement, String> type;
    @FXML
    private TableColumn<Evenement, Date> date;
    @FXML
    private TableColumn<Evenement, Integer> nbrPlace;
    @FXML
    private TableColumn<Evenement, String> dressCode;
    @FXML
    private TableColumn<Evenement, String> image;
    @FXML
    private TextField mot;
    int a;
    @FXML
    private TableColumn reserverEvent;
public ObservableList<Evenement> data=FXCollections.observableArrayList();
    private ObservableList<Evenement> list;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                afficherEvenements();

        // TODO
    }    
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

        } catch (IOException ex) {
            System.err.println(ex);
        }

        return filePath;
    }  
   @FXML
    private void afficherAccueil(ActionEvent event) throws IOException{
        System.out.println(" Accueil ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/Dashboard.fxml"));
        Parent root = loader.load();
        containerEvent.getChildren().setAll(root);
    }
  
    @FXML
    private void AfficherParc(ActionEvent event) {
    }

    @FXML
    private void GoBack(ActionEvent event) {
    }

    @FXML
    private void showPaneProfil(MouseEvent event) {
    }

    @FXML
    private void chercher(KeyEvent event) {
    }

    @FXML
    private void hidePane(MouseEvent event) {
    }
           
    
    public void afficherEvenements(){

        EvenementService se = new EvenementService();
          list = se.getAllEvent();
           // tableSafa.setItems((ObservableList<Evenement>) se.getAllEvent());
         
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         type.setCellValueFactory(new PropertyValueFactory<>("type"));
         date.setCellValueFactory(new PropertyValueFactory<>("date")); 
         nbrPlace.setCellValueFactory(new PropertyValueFactory<>("nbrPlace")); 
         dressCode.setCellValueFactory(new PropertyValueFactory<>("dressCode"));
                  image.setCellValueFactory(new PropertyValueFactory<>("image"));
 
         Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFactoryDec = (param) -> {

            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {

                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button test = new Button("dec");
                        test.setOnAction(event -> {

                           Evenement p = getTableView().getItems().get(getIndex());
                            System.out.println("+++:" + p);
                            EvenementService panierService = new EvenementService();

                            panierService.reserver(p.getIdEvent());
                            try {
                                refresh();
                            } catch (IOException ex) {
                                Logger.getLogger(EventFrontController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //panierService.addProduct(SingninController.userIden, p.getId());
                            System.out.println(p);
                        });

                        setGraphic(test);
                        setText(null);

                    }
                }

            };
            return cell;
        };
        
        
               reserverEvent.setCellFactory(cellFactoryDec);
        //modifierEvent.setCellFactory(cellFactoryTraiter);
         tableSafa.setItems(list);
               
               
               
               
               
               
           }
           
         public void refresh() throws IOException {
        System.out.println("afficher mes events");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pidev/gui/EventFront.fxml"));
        Parent root = loader.load();
        containerEvent.getChildren().setAll(root);
    }
      
           
           
           
           
           
           
}
