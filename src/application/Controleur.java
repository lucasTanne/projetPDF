package application;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Test image dans le bouton
		InputStream input = getClass().getResourceAsStream("/img/31.png");
		ImageView image = new ImageView(new Image(input));
		btnOuvrir.setGraphic(image);
		
		
	}

}
