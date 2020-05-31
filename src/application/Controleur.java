package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.itextpdf.text.DocumentException;
import creationPDF.CreationPDF;
import donnees.Bloc;
import donnees.Images;
import donnees.Page;
import donnees.Paragraphe;
import donnees.Texte;
import gestionPDF.FichierPDF;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
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
    
    @FXML
    private Button btnSauvegarde;
    
    @FXML
    private TextArea editeurTexte;
    
    private Paragraphe edite;
    
    private Page pageEdit;
    
    private final double width = 595;
    
    private final double height = 842;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.editeurTexte = null;
		this.edite = null;
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
			// On supprime les elements du pdf precedemment ouvert
			this.pdfContainer.getChildren().clear();
			
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
			for(int numPage = 1; numPage <= this.pdf.nbPages(); numPage++)
			{
				// On lis le contenu de la page
				this.pdf.lire(numPage);
				
				// Creation du conteneur pour la page en cours
				AnchorPane pagePane = new AnchorPane();
				
				// Creation de la page
				Rectangle page = new Rectangle();
				
				// Coloration en blanc de la page
				page.setFill(Color.WHITE);
				
				// Dimensionnement de la page
				page.setWidth(this.width);
				
				page.setHeight(this.height);
				
				pagePane.prefHeight(page.getHeight());
				
				pagePane.prefWidth(page.getWidth());
				
				// Ajout d'un contour noir aux pages
				page.setStroke(Color.BLACK);
				
				// Ajustement de la largeur du contour a 1 pixel
				page.setStrokeWidth(1);
				
				// Obtention des blocs a afficher
				ArrayList<Bloc> blocs = this.pdf.getPage(numPage - 1).getLesBlocs();
				
				// Ajout de la page dans son conteneur
				pagePane.getChildren().add(page);
				
				// Parcours des blocs
				for(int numBloc = 0; numBloc < blocs.size(); numBloc++)
				{
					Bloc bloc = blocs.get(numBloc);
					
					// Si il s'agit d'un paragraphe
					if(bloc instanceof Paragraphe)
					{
						// On caste le bloc en paragraphe
						Paragraphe paragraphe = (Paragraphe) bloc;
						
						// On cree la conteneur du paragraphe
						TextFlow flow = new TextFlow();
						
						ArrayList<Texte> paragrapheTexte = paragraphe.getTexte();
						
						// On remplis le paragraphe
						for(int numTexte = 0; numTexte < paragrapheTexte.size(); numTexte++)
						{
							
							Texte texte = paragrapheTexte.get(numTexte);
							
							// On recupere le texte
							Label label = new Label(texte.getValeur());
							
							// On met le texte a la bonne police
							label.setFont(new Font(texte.getPolice(), texte.getTaille()));
							
							label.setId(numPage + "-" + numBloc + "-" + numTexte);
							
							// On met la couleur au texte
							//label.setTextFill(texte.getCouleur());
							
							
							// On applique les differentes mises en forme
							if(texte.isGras())
							{
								label.setStyle(label.getStyle() + "-fx-font-weight : bold;\n");
							}
							
							if(texte.isItalique())
							{
								label.setStyle(label.getStyle() + "-fx-font-style : italic\n");
							}
							
							if(texte.isSouligne())
							{
								label.setStyle(label.getStyle() + "-fx-underline : true;\n");
							}
							
							if(texte.isBarre())
							{
								label.setStyle(label.getStyle() + "-fx-strikethrough: true;\n");
							}
							
							// On ajoute le label au TextFlow
							flow.getChildren().add(label);
						}
						
						// On ajoute le paragraphe dans la page
						pagePane.getChildren().add(flow);
						
						// On determine les coordonnees du paragraphe
						flow.setLayoutX(page.getLayoutX() + bloc.getX());
						
						flow.setLayoutY(page.getHeight() - bloc.getY());
						
						editText(flow, paragraphe);
					}
					
					// Si il s'agit d'une image
					else if(bloc instanceof Images)
					{
						BufferedImage imageBuffer = ((Images)bloc).getImage();
						
						// On transforme l'image afin de l'afficher dans l'interface javaFX
						ImageView im = new ImageView(SwingFXUtils.toFXImage(imageBuffer, null));
						
						// On ajoute l'image dans la page
						pagePane.getChildren().add(im);
						
						// On determine les coordonnees de l'image
						im.setLayoutX(page.getLayoutX() + bloc.getX());
						
						// Les coordonnees des images dans le fichier PDF prennent en compte le point bas gauche
						// on remonte donc les images de leur hauteur
						im.setLayoutY(page.getHeight() - (bloc.getY() + imageBuffer.getHeight()));
						
						changerImage(im);
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
	 * premet l'edition de texte lors d'un double-clic sur un paragraphe
	 * @param flow
	 * @param paragraphe
	 */
	private void editText(TextFlow flow, Paragraphe paragraphe)
	{
		flow.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
		    @Override
		    public void handle(MouseEvent mouseEvent)
		    {
		    	// Si c'est un clic gauche
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
		        {
		        	// Si il y a eu deux clics
		            if(mouseEvent.getClickCount() == 2)
		            {
		            	// On cree une zone de texte modifiable
		                editeurTexte = new TextArea();
		                edite = paragraphe;
		                
		                // On recupere le texte du paragraphe contenu dans le TextFlow
		                for(Texte texte : paragraphe.getTexte())
		                {
		                	// On recupere la valeur du texte
	                		String text = texte.getValeur();
	                		
	                		// On ajoute des balises BBCode pour symboliser la mise en forme du texte
	                		if(texte.isGras())
	                		{
	                			text = "[b]" + text + "[\\b]";
	                		}
	                		
	                		if(texte.isItalique())
	                		{
	                			text = "[i]" + text + "[\\i]";
	                		}
	                		
	                		if(texte.isSouligne())
	                		{
	                			text = "[u]" + text + "[\\u]";
	                		}
	                		
	                		if(texte.isBarre())
	                		{
	                			text = "[s]" + text + "[\\s]";
	                		}
	                		
	                		// On ajoute le texte dans la zone de texte
	                		editeurTexte.setText(editeurTexte.getText() + text);
		                	
	                		// On recupere le conteneur de la page
		                	Parent parent = flow.getParent();
		                	
		                	if(parent instanceof AnchorPane)
		                	{
		                		AnchorPane parentPane = (AnchorPane) parent;
		                		
		                		// On remplace le TextFlow contenant le par la zone de texte modifiable
		                		parentPane.getChildren().remove(flow);
		                		parentPane.getChildren().add(editeurTexte);
		                		
		                		//area.setBackground(Background.EMPTY);
		                		
		                		// On place la zone de texte au meme endroit que le TextFlow precedent
		                		editeurTexte.setLayoutX(flow.getLayoutX());
		                		editeurTexte.setLayoutY(flow.getLayoutY());
		                	}
		                }
		            }
		        }
		    }
		});
	}
	
	/**
	 * Lorsqu'un editeur de texte est ouvert et que l'on clique autre-part
	 * On ferme l'editeur
	 * @param event
	 */
    @FXML
    void validerEdition(MouseEvent event)
    {
    	if(this.editeurTexte != null)
    	{
    		/*Parent parent = this.editeurTexte.getParent();
    		
    		if(parent instanceof AnchorPane)
        	{
        		AnchorPane parentPane = (AnchorPane) parent;
        		TextFlow flow = new TextFlow();
        		
        		this.edite.setX(this.editeurTexte.getLayoutX());
        		this.edite.setY(this.editeurTexte.getLayoutY() - this.height);
        	}*/
    		System.out.println("click");
    		bbcode(this.editeurTexte.getText());
    	}
    }
	
	
	/**
	 * permet de sauvegarder le PDF modifie
	 * @param event
	 */
    @FXML
    void sauvegarder(ActionEvent event)
    {
    	CreationPDF creation;
    	try
    	{
    		creation = new CreationPDF("C:\\Users\\lulu\\Desktop\\test.pdf");
    		creation.construction(pdf.getPages());
    	}
    	
    	catch (FileNotFoundException e)
    	{
    		e.printStackTrace();
    	}
    	
    	catch (DocumentException e)
    	{
    		e.printStackTrace();
    	}
    }
	
	/**
	 * Methode d'afffichage d'une popup d'erreur
	 * @param entete
	 * @param message
	 */
	private void erreurDialog(String entete, String message)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur");
		alert.setHeaderText(entete);
		alert.setContentText(message);

		alert.showAndWait();
	}
	
	public static String bbcode(String text)
	{
		ArrayList<Texte> paragraphe = new ArrayList<Texte>();
        Map<String, String> bbMap = new HashMap<String , String>();
        
        bbMap.put("[b]", "[\\b]");
        bbMap.put("[i]", "[\\i]");
        bbMap.put("[u]", "[\\u]");
        bbMap.put("[s]", "[\\s]");
        
        boolean[] bool = new boolean[bbMap.size()];
        
        int i = 0;
        
        for(i = 0; i < bool.length; i++)
        {
        	bool[i] = false;
        }
        
        i = 0;
        
        for(Map.Entry<String, String> entry: bbMap.entrySet())
        {
        	if(text.indexOf(entry.getKey()) != -1)
        	{
	        	String[] debut = text.split(entry.getKey());
	        	
	        	for(String s : debut)
	        	{
	        		
	        	}
        	}
        	
        	i++;
        }

        return text;
    }
	
	void changerImage(ImageView image)
	{
		image.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
		    @Override
		    public void handle(MouseEvent mouseEvent)
		    {
		    	// Si c'est un clic gauche
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
		        {
		        	// Si il y a eu deux clics
		            if(mouseEvent.getClickCount() == 2)
		            {
		        		// Ouverture d'un explorateur windows fournit par JavaFX
		        		FileChooser fileChooser = new FileChooser();
		        		 
		        		// Definition du filtre de recherche sur les fichiers image
		        		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Toutes le images", "*.*"),
		                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
		                        new FileChooser.ExtensionFilter("PNG", "*.png")
		                    );
		        		
		        		// Recupere le fichier choisis
		        		File fichier = fileChooser.showOpenDialog(new Stage());
		        		 
		        		// Si un fichier a ete selectionner
		        		if(fichier != null)
		        		{
		        			ImageView newImage = new ImageView(new Image("file:///"+ fichier.getPath()));
		        			Parent parent = image.getParent();
		        			
		        			if(parent instanceof AnchorPane)
		        			{
		        				AnchorPane pane = (AnchorPane) parent;
		        				pane.getChildren().add(newImage);
		        				newImage.setLayoutX(image.getLayoutX());
		        				newImage.setLayoutY(image.getLayoutY());
		        				pane.getChildren().remove(image);
		        				changerImage(newImage);
		        			}
		        		}
		        	}
		        }
		    }
		});
	}
}
