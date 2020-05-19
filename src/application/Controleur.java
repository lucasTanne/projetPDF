package application;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Controleur implements Initializable{

    @FXML
    private MenuBar menu;

    @FXML
    private Menu fichier;

    @FXML
    private MenuItem menuOuvrir;

    @FXML
    private MenuItem menuFermer;

    @FXML
    private Menu edition;

    @FXML
    private Menu aide;

    @FXML
    private Button btnOuvrir;
    
    @FXML
    private AnchorPane panel;
    
    @FXML
    private Rectangle page;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Test image dans le bouton (icone => 31 x 31 px)
		InputStream input = getClass().getResourceAsStream("/img/31.png");
		ImageView image = new ImageView(new Image(input));
		this.btnOuvrir.setGraphic(image);
		
		// Test rectangle dimention (A4)
		this.page.setWidth(595);
		this.page.setHeight(842);
		
		// Position du rectangle dans le panel
		this.page.setLayoutX((this.panel.getPrefWidth() / 2) - (this.page.getWidth() / 2));
		this.page.setLayoutY((this.panel.getPrefHeight() - this.page.getHeight()) / 2);
	}
	
	/*
	 * Méthode qui permet l'ouverture d'un fichier
	 */
	private void ouvrirFichier() {
		
	}

}
