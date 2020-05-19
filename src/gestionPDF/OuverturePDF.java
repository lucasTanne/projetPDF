package gestionPDF;

import java.io.IOException;

import com.itextpdf.text.pdf.PdfReader;

public class OuverturePDF {
	private String cheminFichier;	
	private PdfReader fichier;
	
	public OuverturePDF(String chemin){
		this.cheminFichier = chemin;
	}
	
	public void chargement() throws IOException {
		// ouverture du fichier
		this.fichier = new PdfReader(this.cheminFichier);
	}
}
