	package application;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import gestionPDF.FichierPDF;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controleur implements Initializable{

    @FXML
    private Menu fichier;

    @FXML
    private Menu aide;

    @FXML
    private MenuItem menuAPropos;

    @FXML
    private Button btnOuvrir;

    @FXML
    private Menu edition;

    @FXML
    private MenuItem menuQuitter;
    
    @FXML
    private VBox pdfContainer;

    @FXML
    private MenuBar menu;

    @FXML
    private MenuItem menuOuvrir;
    
    private FichierPDF pdf;
    
    @FXML
    private VBox mainPane;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// Test image dans le bouton (icone => 31 x 31 px)
		InputStream input = getClass().getResourceAsStream("/img/31.png");
		ImageView image = new ImageView(new Image(input));
		this.btnOuvrir.setGraphic(image);
		
		stageSizeChangeListener((Stage) mainPane.getScene().getWindow());
	}
	
	@FXML
	/**
	 * Méthode qui ferme l'application
	 * @param event
	 */
    void quitterApp(ActionEvent event)
	{
		// Fermeture du fichier si il est ouvert
		this.pdf.fermer();
		
		// Quitter l'application
		Platform.exit();
    }
	
	 @FXML
	 /**
	  * Méthode appelée quand on clique sur l'outil "ouvrir un fichier"
	  * @param event
	  * @throws IOException
	  */
    void btnOuvrirFichier(MouseEvent event) throws IOException
	 {
		 // Appel de la méthode ouvrir()
		 ouvrir();
	 }
	 
	 @FXML
	 /**
	  * Méthode appelée quand on sélectionne le menu "Fichier/Ouvrir"
	  * @param event
	  */
    void menuOuvrirFichier(ActionEvent event)
	 {
		// Appel de la méthode ouvrir()
		ouvrir();
    }
	 
	 /**
	  * Méthode qui permet d'ouvrir un fichier PDF
	  */
	 private void ouvrir()
	 {
		 // Ouverture d'un explorateur windows fournit par JavaFX
		 FileChooser fileChooser = new FileChooser();
		 
		 // Définition du filtre de recherche sur les fichiers PDF
		 fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
		 
		 // Récupère le fichier choisi
		 File fichier = fileChooser.showOpenDialog(new Stage());
		 
		 // Si un fichier a été sélectionner
		 if(fichier != null)
		 {
			 // Récupère le chemin absolut du fichier
			 String cheminFichier = fichier.getAbsolutePath();
			 
			 // Chargement du fichier PDF
			 //System.out.println(cheminFichier);
			 chargementPDF(cheminFichier);
			 
		 }
	 }
	
	/**
	 * Méthode qui permet le chargement d'un fichier PDF
	 * @param cheminFichier
	 */
	void chargementPDF(String cheminFichier)
	{
		this.pdf = new FichierPDF(cheminFichier);
		
		try
		{
			this.pdf.chargement();
			
			AnchorPane pagePane = new AnchorPane();
			
			Rectangle page = new Rectangle();
			
			page.setWidth(595);
			
			page.setHeight(842);
			
			pagePane.getChildren().add(page);
			
			pdfContainer.getChildren().add(pagePane);
			
			page.setLayoutX(pdfContainer.getWidth() / 2 - page.getWidth() / 2);
			
			
		}
		
		catch (IOException e)
		{
			// Création d'une popup d'erreur
			String entete = "Erreur dans l'ouverture du fichier";
			String message = "Il y a eu une erreur dans l'ouverture du fichier PDF";
			erreurDialog(entete, message);
		}
	}
	
	   private void stageSizeChangeListener(Stage stage)
	   {
	        stage.widthProperty().addListener(new ChangeListener<Number>()
	        {
	            @Override
	            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
	            {
	                 System.out.println("Width changed!!");
	            	
	            }
	        });

	        /*stage.heightProperty().addListener(new ChangeListener<Number>() {
	            @Override
	            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	                System.out.println("Height changed!!");
	            }
	        });*/
	   }
	
	/**
	 * Méthode d'afffichage d'une popup d'erreur
	 * @param entete
	 * @param message
	 */
	void erreurDialog(String entete, String message)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur");
		alert.setHeaderText(entete);
		alert.setContentText(message);

		alert.showAndWait();
	}
}
