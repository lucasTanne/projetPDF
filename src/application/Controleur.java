	package application;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import donnees.Bloc;
import donnees.Images;
import donnees.Paragraphe;
import donnees.Texte;
import gestionPDF.FichierPDF;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;
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
		/*InputStream input = getClass().getResourceAsStream("/img/31.png");
		ImageView image = new ImageView(new Image(input));
		this.btnOuvrir.setGraphic(image);*/
	}
	
	@FXML
	/**
	 * Methode qui ferme l'application
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
	  * Methode appelee quand on clique sur l'outil "ouvrir un fichier"
	  * @param event
	  * @throws IOException
	  */
    void btnOuvrirFichier(MouseEvent event) throws IOException
	 {
		 // Appel de la methode ouvrir()
		 ouvrir();
	 }
	 
	 @FXML
	 /**
	  * Methode appelee quand on selectionne le menu "Fichier/Ouvrir"
	  * @param event
	  */
    void menuOuvrirFichier(ActionEvent event)
	 {
		// Appel de la methode ouvrir()
		ouvrir();
    }
	 
	 /**
	  * Methode qui permet d'ouvrir un fichier PDF
	  */
	private void ouvrir()
	{
			// Ouverture d'un explorateur windows fournit par JavaFX
		FileChooser fileChooser = new FileChooser();
		 
		// Definition du filtre de recherche sur les fichiers PDF
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
		
		// Recupere le fichier choisi
		File fichier = fileChooser.showOpenDialog(new Stage());
		 
		// Si un fichier a ete selectionner
		if(fichier != null)
		{
			// Recupere le chemin absolut du fichier
			String cheminFichier = fichier.getAbsolutePath();
		 
			// Chargement du fichier PDF
			//System.out.println(cheminFichier);
			chargementPDF(cheminFichier);
			
			stageSizeChangeListener((Stage) mainPane.getScene().getWindow());
		}
	}
	
	/**
	 * Methode qui permet le chargement d'un fichier PDF
	 * @param cheminFichier
	 */
	void chargementPDF(String cheminFichier)
	{
		// Creation du fichier PDF
		this.pdf = new FichierPDF(cheminFichier);
		
		try
		{
			// Ouverture du fichier PDF
			this.pdf.ouvrir();
			
			// Pour chaque page
			for(int i = 1; i <= this.pdf.nbPages(); i++)
			{
				// On lis le contenu de la page
				this.pdf.lire(i);
				
				// Creation du conteneur pour la page en cours
				AnchorPane pagePane = new AnchorPane();
				
				// Creation de la page
				Rectangle page = new Rectangle();
				
				// Coloration en blanc de la page
				page.setFill(Color.WHITE);
				
				// Dimensionnement de la page
				page.setWidth(595);
				
				page.setHeight(842);
				
				pagePane.prefHeight(page.getHeight());
				
				pagePane.prefWidth(page.getWidth());
				
				// Ajout d'un contour noir aux pages
				page.setStroke(Color.BLACK);
				
				// Ajustement de la largeur du contour a 1 pixel
				page.setStrokeWidth(1);
				
				// Obtention des blocs a afficher
				ArrayList<Bloc> blocs = this.pdf.getPage(i - 1).getLesBlocs();
				
				// Ajout de la page dans son conteneur
				pagePane.getChildren().add(page);
				
				// Parcours des blocs
				for(Bloc bloc : blocs)
				{
					// Si il s'agit d'un paragraphe
					if(bloc instanceof Paragraphe)
					{
						// On caste le bloc en paragraphe
						Paragraphe paragraphe = (Paragraphe) bloc;
						
						// On cree la conteneur du paragraphe
						TextFlow flow = new TextFlow();
						
						// On remplis le paragraphe
						for(Texte texte : paragraphe.getTexte())
						{
							Label label = new Label(texte.getValeur());
							
							flow.getChildren().add(label);
						}
						
						// On ajoute le paragraphe dans la page
						pagePane.getChildren().add(flow);
						
						// On determine les coordonnees du paragraphe
						flow.setLayoutX(page.getLayoutX() + bloc.getX());
						
						flow.setLayoutY(page.getHeight() - bloc.getY());
					}
					
					// Si il s'agit d'une image
					else if(bloc instanceof Images)
					{
						// On transforme l'image afin de l'afficher dans l'interface javaFX
						ImageView im = new ImageView(SwingFXUtils.toFXImage(((Images) bloc).getImage(), null));
						
						// On ajoute l'image dans la page
						pagePane.getChildren().add(im);
						
						// On determine les coordonnees de l'image
						im.setLayoutX(page.getLayoutX() + bloc.getX());
						
						im.setLayoutY(page.getHeight() - bloc.getY());
					}
				}
				
				// Dimensionnement automatique du conteneur
				pagePane.prefHeightProperty();
				
				pagePane.prefWidthProperty();
				
				// Ajout du conteneur de la page au conteneur du fichier PDF
				pdfContainer.getChildren().add(pagePane);
				
				// Mise en place d'une marge entre chaque page et centrage de ces dernieres
				VBox.setMargin(pagePane, new Insets(0, pagePane.getWidth() / 2 - page.getWidth() / 2, 10, pdfContainer.getWidth() / 2 - page.getWidth() / 2));
			}
		}
		
		catch (IOException e)
		{
			// Creation d'une popup d'erreur
			String entete = "Erreur dans l'ouverture du fichier";
			String message = "Il y a eu une erreur dans l'ouverture du fichier PDF";
			erreurDialog(entete, message);
		}
	}
	
	/**
	 * Ajout d'evenements detectant le redimensionnement de la fenetre
	 * @param stage
	 */
	private void stageSizeChangeListener(Stage stage)
	{
		// Detection du changement de largeur de la fenetre
		stage.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
			{
				//System.out.println("Width changed!!");
				
				// On recupere les differents conteneurs de page
				ObservableList<Node> pagesContainers = pdfContainer.getChildren();

				// On les parcourt
				for(Node pageContainer : pagesContainers)
				{
					if(pageContainer instanceof AnchorPane)
					{
						// On recupere les differents elements de la page en cours
						ObservableList<Node> elements = ((AnchorPane) pageContainer).getChildren();
						
						// On les parcourt
						for(Node element : elements)
						{
							// Si il s'agit de la page elle-meme
							if(element instanceof Rectangle)
							{
								Rectangle page = (Rectangle) element;
								
								// On les centres tout en maintenant la mage entre elles
								VBox.setMargin(pageContainer, new Insets(0, stage.getWidth() / 2 - page.getWidth() / 2, 10, stage.getWidth() / 2 - page.getWidth() / 2));
							}
						}
					}
				}
			}
		});
	}
	
	/**
	 * Methode d'afffichage d'une popup d'erreur
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
