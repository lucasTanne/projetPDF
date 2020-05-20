package gestionPDF;

import java.io.IOException;

import com.itextpdf.text.pdf.PdfReader;

public class OuverturePDF {
	private String cheminFichier;	
	private PdfReader fichier;
	
	/**
	 * Constructeur
	 * @param chemin
	 */
	public OuverturePDF(String chemin){
		this.cheminFichier = chemin;
	}
	
	/**
	 * M�thode de chargement du fichier PDF.
	 * Propage l'erreur pour la g�rer dans le package de l'IHM
	 * et affiche une popup en cas d'�chec. 
	 * @throws IOException
	 */
	public void chargement() throws IOException {
		// ouverture du fichier
		this.fichier = new PdfReader(this.cheminFichier);
	}
}
