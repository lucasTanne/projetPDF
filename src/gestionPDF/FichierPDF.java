package gestionPDF;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfReader;

import donnees.Bloc;

public class FichierPDF {
	private String chemin;	
	private PdfReader fichier;
	private ArrayList<Bloc> blocs;
	
	/**
	 * Constructeur d'un fichier PDF
	 * @param String chemin
	 */
	public FichierPDF(String chemin){
		this.chemin = chemin;
		
		this.fichier = null;
		this.blocs = new ArrayList<Bloc>();
	}
	
	/**
	 * Méthode de chargement du fichier PDF.
	 * Propage l'erreur pour la gérer dans le package de l'IHM.
	 * @throws IOException
	 */
	public void ouvrir() throws IOException {
		this.fichier = new PdfReader(this.chemin);
	}
	
	/**
	 * Méthode qui referme un fichier PDF
	 */
	public void fermer() {
		this.fichier.close();
	}
	
	/**
	 * Méthode qui test si un fichier est ouvert
	 * @return Boolean
	 */
	public Boolean estOuvert() {
		if(this.fichier != null) return true;
		return false;
	}
	
	/**
	 * Méthode qui va lire le contenu du fichier
	 * @throws IOException 
	 */
	public void lire() throws IOException {
		
	}
	
	/**
	 * Méthode qui ajoute un bloc dans l'ArrayList<Bloc>
	 * @param Bloc bloc
	 */
	public void ajouterBloc(Bloc bloc) {
		this.blocs.add(bloc);
	}
	
	/**
	 * Méthode qui supprime un bloc de l'ArrayList<Bloc>
	 * @param int index
	 */
	public void supprimerBloc(int index) {
		this.blocs.remove(index);
	}
}

