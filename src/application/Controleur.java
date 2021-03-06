package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.print.PrintException;

import com.itextpdf.text.DocumentException;

import bbcode.Barre;
import bbcode.Gras;
import bbcode.Italique;
import bbcode.Parseur;
import bbcode.Souligne;
import creationPDF.CreationPDF;
import creationPDF.Impression;
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
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
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
    private Button btnTexte;
    
    @FXML
    private Button btnGras;
    
    @FXML
    private Button btnItalique;
    
    @FXML
    private Button btnSouligne;
    
    @FXML
    private Button btnCouleur;
    
    @FXML
    private TextArea editeurTexte;
    
    @FXML
    private Button btnImage;
    
    @FXML
    private ScrollPane scrollPdfPane;
    
    @FXML
    private Button btnBarre;
    
    @FXML
    private Button btnImprimer;
    
    @FXML
    private Button btnHyperlien;
    
    private Paragraphe edite;
    
    private Page pageEdite;
    
    boolean insertionImage;
    
    boolean insertionTexte;
    
    private final double width = 595;
    
    private final double height = 842;

    /**
     * Initialise les parametres du controleur
     * @param location
     * @param resources
     */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// Aucun paragraphe en cours d'edition
		this.editeurTexte = null;
		this.edite = null;
		
		// Pas de mode insertion d'image ou de texte
		this.insertionImage = false;
		this.insertionTexte = false;
		
		// Desactivation des boutons
		this.btnCouleur.setDisable(true);
		this.btnGras.setDisable(true);
		this.btnItalique.setDisable(true);
		this.btnSouligne.setDisable(true);
		this.btnBarre.setDisable(true);
		this.btnTexte.setDisable(true);
		this.btnImage.setDisable(true);
		this.btnImprimer.setDisable(true);
		this.btnSauvegarde.setDisable(true);
		this.btnHyperlien.setDisable(true);
		
		/*
		 *  Lors d'un clic sur l'espace d'edition
		 *  on lance la validation de l'edition
		 */
		eventValiderEdition(this.pdfContainer);
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
				
				insererBloc(pagePane);
				
				pagePane.setId("" + (numPage - 1));
				
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
								label.setStyle(label.getStyle() + "-fx-font-style : italic;\n");
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
						
						/*
						 * On permet l'edition du paragraphe lors d'un
						 * double-clique sur le TextFlow
						 */
						editerTexte(flow, paragraphe, this.pdf.getPage(numPage - 1));
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
						
						changerImage(im, this.pdf.getPage(numPage - 1));
					}
				}
				
				// Dimensionnement automatique du conteneur
				pagePane.prefHeightProperty();
				
				pagePane.prefWidthProperty();
				
				// Ajout du conteneur de la page au conteneur du fichier PDF
				pdfContainer.getChildren().add(pagePane);
				
				// Mise en place d'une marge entre chaque page et centrage de ces dernieres
				VBox.setMargin(pagePane, new Insets(0, pagePane.getWidth() / 2 - page.getWidth() / 2, 10, pdfContainer.getWidth() / 2 - page.getWidth() / 2));
				
				// On active les boutons
				this.btnImprimer.setDisable(false);
				this.btnTexte.setDisable(false);
				this.btnImage.setDisable(false);
				
				// On desactive le bouton et le menu d'ouverture d'un fichier
				this.btnOuvrir.setDisable(true);
				this.menuOuvrir.setDisable(true);
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
	 * Lors d'un clic sur l'espace d'edition en pleine
	 * edition de texte, lance la validation de cette derniere
	 * @param pdfContainer
	 */
	private void eventValiderEdition(VBox pdfContainer)
	{
		// Detection d'un clic
		pdfContainer.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
		    @Override
		    public void handle(MouseEvent mouseEvent)
		    {
		    	// Si c'est un clic gauche et qu'on n'est pas en mode edition de texte
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY) &&
		        		mouseEvent.getClickCount() == 1 &&
		        		!insertionTexte)
		        {
		        	// On valide l'edition
		        	validerEdition();
		        }
		    }
		});
	}
	
	
	/**
	 * permet l'edition de texte lors d'un double-clic sur un paragraphe
	 * @param flow
	 * @param paragraphe
	 */
	private void editerTexte(TextFlow flow, Paragraphe paragraphe, Page page)
	{
		// Detection d'un clic
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
		            	// On desactive le bouton de sauvegarde
		    			btnSauvegarde.setDisable(true);
		            	
		            	// On cree une zone de texte modifiable
		                editeurTexte = new TextArea();
		                edite = paragraphe;
		                pageEdite = page;
		                
		                // On active les boutons de formattage de texte
		                btnGras.setDisable(false);
		                btnItalique.setDisable(false);
		                btnSouligne.setDisable(false);
		                btnBarre.setDisable(false);
		                
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
		                	
	                		editeurTexte.setPrefHeight(flow.getPrefHeight());
	                		editeurTexte.setPrefWidth(flow.getPrefWidth());
	                		
	                		
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
	 * Ferme l'editeur et place le paragraohe edite a l'emplacement
	 * de l'editeur
	 */
    @FXML
    void validerEdition()
    {
    	// L'editeur de texte doit etre ouvert
    	if(this.editeurTexte != null)
    	{
    		// On active le bouton de sauvegarde
			this.btnSauvegarde.setDisable(false);
			
			// On desactive les boutons de formattage de texte
            btnGras.setDisable(true);
            btnItalique.setDisable(true);
            btnSouligne.setDisable(true);
            btnBarre.setDisable(true);
    		
			// On recupere le conteneur de la page
    		Parent parent = this.editeurTexte.getParent();
    		
    		if(parent instanceof AnchorPane)
        	{
        		AnchorPane parentPane = (AnchorPane) parent;
        		
        		// On cree un nouveau TextFlow permettant de placer le texte modifie
        		TextFlow flow = new TextFlow();
        		
        		// On place le texte au meme endroit que l'editeur
        		/*this.edite.setX(this.editeurTexte.getLayoutX());
        		this.edite.setY(this.editeurTexte.getLayoutY());*/
        		
        		// On parse le texte
        		ArrayList<Texte> textes = new Parseur(new Gras(), new Italique(), new Souligne(), new Barre()).parser(editeurTexte.getText());
        		
        		// Si la liste de texte n'est pas vide
        		if(textes != null && textes.size() > 0)
        		{
        			// On parcours la liste de texte obtenue
	        		for(Texte texte : textes)
	        		{
	        			/*
	        			 *  On met la meme taille et police que le premier texte
	        			 *  du paragraphe precedemment edite
	        			 */
	        			texte.setTaille(edite.getTexte().get(0).getTaille());
	        			texte.setPolice(edite.getTexte().get(0).getPolice());
	        			
	        			// On recupere le texte
						Label label = new Label(texte.getValeur());
						
						// On met le texte a la bonne police
						label.setFont(new Font(texte.getPolice(), texte.getTaille()));
						
						// On met la couleur au texte
						//label.setTextFill(texte.getCouleur());
						
						
						// On applique les differentes mises en forme
						if(texte.isGras())
						{
							label.setStyle(label.getStyle() + "-fx-font-weight : bold;\n");
						}
						
						if(texte.isItalique())
						{
							label.setStyle(label.getStyle() + "-fx-font-style : italic;\n");
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
	        		
	        		// On modifie la liste de texte du paragraphe
	        		this.edite.setTexte(textes);
	        		
	        		/*
					 * On permet l'edition du paragraphe lors d'un
					 * double-clique sur le TextFlow
					 */
	        		editerTexte(flow, edite, this.pageEdite);
	        		
	        		// On place le TextFlow au meme endroit que l'editeur
	        		flow.setLayoutX(editeurTexte.getLayoutX());
	        		flow.setLayoutY(editeurTexte.getLayoutY());
	        		
	        		// On remplace l'editeur par le TextFlow dans la page
	        		parentPane.getChildren().add(flow);
	        		parentPane.getChildren().remove(editeurTexte);
	        		
	        		// On supprime le paragraphe en cours d'edition
	        		editeurTexte = null;
	        		this.edite = null;
	        	}
        		
        		// Si l'editeur est vide
        		else
        		{
        			// On supprime le paragraphe de la page
        			pageEdite.supprimerBloc(edite);
        			
        			// On supprime l'editeur de la page
        			parentPane.getChildren().remove(editeurTexte);
	        		
	        		// On supprime le paragraphe en cours d'edition
	        		editeurTexte = null;
	        		this.edite = null;
        		}
        	}
    	}
    }
	
	/**
	 * permet de sauvegarder le PDF modifie
	 * @param event
	 */
    @FXML
    void sauvegarder(ActionEvent event)
    {
    	try
    	{
	    	// On initialise un FileChooser
    		FileChooser fileChooser = new FileChooser();
    		 
            //N'autorise que le format PDF
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF", "*.pdf");
            fileChooser.getExtensionFilters().add(extFilter);
 
            // Affiche la fenetre de sauvegarde
            File file = fileChooser.showSaveDialog(new Stage());

            // Si un fichier a ete selectionne
            if(file != null)
            {
            	// On cree le fichier PDF
            	new CreationPDF(file.getPath()).construction(pdf.getPages());
            }
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
     * Met le texte selectionne en gras
     * @param event
     */
    @FXML
    void gras(MouseEvent event)
    {
    	if(!editeurTexte.getSelectedText().isEmpty())
    	{
    		// On recupere l'interval du texte selectionne
        	IndexRange range = this.editeurTexte.getSelection();
    	
	    	// On recupere le texte de l'editeur
	    	String base = this.editeurTexte.getText();
	    	
	    	/*
	    	 * On place d'abord le texte avant la selection, puis la balise ddebut,
	    	 * puis le texte selectionne, puis la balise de fin, puis le texte qui suis la
	    	 * selection
	    	 */
	    	this.editeurTexte.setText(base.substring(0, range.getStart()) + "[b]" +
	    	base.substring(range.getStart(), range.getEnd()) + "[\\b]" + base.substring(range.getEnd()));
    	}
    }
    
    /**
     * Met le texte selectionne en italique
     * @param event
     */
    @FXML
    void italique(MouseEvent event)
    {
    	if(!editeurTexte.getSelectedText().isEmpty())
    	{
	    	// On recupere l'interval du texte selectionne
	    	IndexRange range = this.editeurTexte.getSelection();
	    	
	    	// On recupere le texte de l'editeur
	    	String base = this.editeurTexte.getText();
	    	
	    	/*
	    	 * On place d'abord le texte avant la selection, puis la balise ddebut,
	    	 * puis le texte selectionne, puis la balise de fin, puis le texte qui suis la
	    	 * selection
	    	 */
	    	this.editeurTexte.setText(base.substring(0, range.getStart()) + "[i]" +
	    	base.substring(range.getStart(), range.getEnd()) + "[\\i]" + base.substring(range.getEnd()));
    	}
    }
    
    /**
     * Met le texte selectionne en souligne
     * @param event
     */
    @FXML
    void souligne(MouseEvent event)
    {
    	if(!editeurTexte.getSelectedText().isEmpty())
    	{
	    	// On recupere l'interval du texte selectionne
	    	IndexRange range = this.editeurTexte.getSelection();
	    	
	    	// On recupere le texte de l'editeur
	    	String base = this.editeurTexte.getText();
	    	
	    	/*
	    	 * On place d'abord le texte avant la selection, puis la balise ddebut,
	    	 * puis le texte selectionne, puis la balise de fin, puis le texte qui suis la
	    	 * selection
	    	 */
	    	this.editeurTexte.setText(base.substring(0, range.getStart()) + "[u]" +
	    	base.substring(range.getStart(), range.getEnd()) + "[\\u]" + base.substring(range.getEnd()));
    	}
    }
    
    /**
     * Met le texte selectionne en barre
     * @param event
     */
    @FXML
    void barre(MouseEvent event)
    {
    	if(!editeurTexte.getSelectedText().isEmpty())
    	{
	    	// On recupere l'interval du texte selectionne
	    	IndexRange range = this.editeurTexte.getSelection();
	    	
	    	// On recupere le texte de l'editeur
	    	String base = this.editeurTexte.getText();
	    	
	    	/*
	    	 * On place d'abord le texte avant la selection, puis la balise ddebut,
	    	 * puis le texte selectionne, puis la balise de fin, puis le texte qui suis la
	    	 * selection
	    	 */
	    	this.editeurTexte.setText(base.substring(0, range.getStart()) + "[s]" +
	    	base.substring(range.getStart(), range.getEnd()) + "[\\s]" + base.substring(range.getEnd()));
    	}
    }
    
    /**
	 * permet d'imprimer le PDF modifie
	 * @param event
	 */
    @FXML
    void imprimer(MouseEvent event)
    {
    	// On initialise un FileChooser
		FileChooser fileChooser = new FileChooser();
		 
		//N'autorise que le format PDF
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF", "*.pdf");
			fileChooser.getExtensionFilters().add(extFilter);
 
			// Affiche la fenetre de sauvegarde
		File file = fileChooser.showSaveDialog(new Stage());

		// Si un fichier a ete selectionne
		if(file != null)
		{
			try
			{
				// On lance l'impression
				new Impression(file.getPath()).impression();
			}
			
			catch (PrintException e)
			{
				e.printStackTrace();
			}
			
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
    }
	
	/**
	 * Methode d'afffichage d'une popup d'erreur
	 * @param entete
	 * @param message
	 */
	private void erreurDialog(String entete, String message)
	{
		// On instancie la boite de dialogue
		Alert alert = new Alert(AlertType.ERROR);
		
		// Initialise le titre
		alert.setTitle("Erreur");
		
		// On modifie l'entete
		alert.setHeaderText(entete);
		
		// On modifie le message
		alert.setContentText(message);

		/*
		 *  Affiche la boite de dialogue tant que
		 *  l'utilisateur ne la ferme pas
		 */
		alert.showAndWait();
	}
	
	/**
	 * Change l'image lors d'un double-clique dessus
	 * @param image
	 * @param page 
	 */
	void changerImage(ImageView image, Page page)
	{
		// Detection d'un clic
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
		        		 
		        		// Si un fichier a ete selectionne
		        		if(fichier != null)
		        		{
		        			// On active le bouton de sauvegarde
		        			btnSauvegarde.setDisable(false);
		        			
		        			// On charge l'image
		        			ImageView newImage = new ImageView(new Image("file:///"+ fichier.getPath()));
		        			
		        			// On recupere le conteneur de la page
		        			Parent parent = image.getParent();
		        			
		        			if(parent instanceof AnchorPane)
		        			{
		        				AnchorPane pane = (AnchorPane) parent;
		        				
		        				// On ajoute l'image a la page
		        				pane.getChildren().add(newImage);
		        				
		        				// On place l'image a l'endroit de l'ancienne image
		        				newImage.setLayoutX(image.getLayoutX());
		        				newImage.setLayoutY(image.getLayoutY());
		        				
		        				// On supprime l'ancienne image
		        				pane.getChildren().remove(image);
		        				
		        				// Autorise la modification de l'image lors d'un double-clique
		        				changerImage(newImage, pageEdite);
		        			}
		        		}
		        	}
		        }
		    }
		});
	}
	
	/**
	 * Change le curseur dans l'espace d'edition
	 * lors d'un appui sur le bouton image
	 * @param event
	 */
    @FXML
    void curseurImage(MouseEvent event)
    {
    	/*
    	 * Si le bouton n'est pas actif et qu'un pdf est ouvert
    	 */
    	if(!insertionImage)
    	{
    		// On passe en mode insertion d'image
    		this.insertionImage = true;
    		
    		// On desactive le mode insertion de texte
    		this.insertionTexte = false;
    		
    		// On charge l'image du curseur
        	InputStream input = getClass().getResourceAsStream("/img/cursorImage.png");
    		ImageView image = new ImageView(new Image(input));
    		
    		// On modifie le curseur
    		this.scrollPdfPane.setCursor(new ImageCursor(image.getImage()));
    	}
    	
    	/*
    	 * Si le bouton est actif
    	 */
    	else if(insertionImage)
    	{
    		// On desactive le mode insertion d'image
    		this.insertionImage = false;
    		
    		// On remet le curseur par defaut
    		this.scrollPdfPane.setCursor(Cursor.DEFAULT);
    	}
    }
    
	/**
	 * Change le curseur dans l'espace d'edition
	 * lors d'un appui sur le bouton texte
	 * @param event
	 */
    @FXML
    void curseurTexte(MouseEvent event)
    {
    	/*
    	 * Si le bouton n'est pas actif et qu'un pdf est ouvert
    	 */
    	if(!insertionTexte)
    	{
    		// On passe en mode insertion de texte
    		this.insertionTexte = true;
    		
    		// On desactive le mode insertion d'image
    		this.insertionImage = false;
    		
    		// On charge l'image du curseur
        	InputStream input = getClass().getResourceAsStream("/img/textCursor.png");
    		ImageView image = new ImageView(new Image(input));
    		
    		// On modifie le curseur
    		this.scrollPdfPane.setCursor(new ImageCursor(image.getImage()));
    	}
    	
    	/*
    	 * Si le bouton est actif
    	 */
    	else if(insertionTexte)
    	{
    		// On desactive le mode insertion de texte
    		this.insertionTexte = false;
    		
    		// On remet le curseur par defaut
    		this.scrollPdfPane.setCursor(Cursor.DEFAULT);
    	}
    }
    
    /**
     * Insere un bloc a l'endroit on l'on clique
     * @param parent
     */
    void insererBloc(AnchorPane parent)
    {
    	// Detection d'un clic
    	parent.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
		    @Override
		    public void handle(MouseEvent mouseEvent)
		    {
		    	pageEdite = pdf.getPage(Integer.parseInt(parent.getId()));
		    	
		    	// Si l'insertion d'image est active
		    	if(insertionImage)
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
						// Active le bouton de sauvegarde
						btnSauvegarde.setDisable(false);
						
						// On charge l'image
						Image im = new Image("file:///"+ fichier.getPath());
						ImageView newImage = new ImageView((im));
						
						// On ajoute l'image a la page
						parent.getChildren().add(newImage);
						
						// On place l'image aux coordonnees du curseur
						newImage.setLayoutX(mouseEvent.getX());
						newImage.setLayoutY(mouseEvent.getY());
						
						// Autorise la modification de l'image lors d'un double-clique
						changerImage(newImage, pageEdite);
						
						// On ajoute le bloc a la page
						Images blocImage = new Images(newImage.getFitWidth(), newImage.getFitHeight());
						blocImage.setImage(SwingFXUtils.fromFXImage(im, null));
						
						// On ajoute l'image aux blocs de la page
						pageEdite.ajouterBloc(blocImage);
						
						pageEdite = null;
					}
			    }
		    	
		    	// Si l'insertion de texte est active
		    	else if(insertionTexte)
		    	{
		    		// On desactive le bouton de sauvegarde
	    			btnSauvegarde.setDisable(true);
	            	
	    			// Si un texte etait en cours d'edition, on valide l'edition
	            	if(editeurTexte != null)
	            	{
	            		validerEdition();
	            	}
	            	
	            	// On active les boutons de formattage de texte
	                btnGras.setDisable(false);
	                btnItalique.setDisable(false);
	                btnSouligne.setDisable(false);
	                btnBarre.setDisable(false);
	            	
	            	// On cree une zone de texte modifiable
	                editeurTexte = new TextArea();
	                
	                /*
	                 *  On cree un nouveau paragraphe en cours d'edition afin d'avoir
	                 *  des parametre de bases pour la validation
	                 */
	                edite = new Paragraphe(mouseEvent.getX(), mouseEvent.getY() - height);
	                
	                Texte defaut = new Texte(null);
	                
	                // On met les attribus du texte par defaut
	                defaut.setTaille((int) Font.getDefault().getSize());
	                defaut.setPolice(Font.getDefault().toString());
	                
	                // On ajoute le texte par defaut dans le paragraphe
	                edite.ajouterTexte(defaut);
	                
	                // On ajoute la paragraphe a la page
	                pageEdite.ajouterBloc(edite);
	                
	                // On met l'editeur a l'endroit on a eu lieu le clic
	                editeurTexte.setLayoutX(mouseEvent.getX());
	                editeurTexte.setLayoutY(mouseEvent.getY());
	                
	                // On ajoute l'editeur a la page
	                parent.getChildren().add(editeurTexte);
		    	}
		    }
		});
    }
    
    
}