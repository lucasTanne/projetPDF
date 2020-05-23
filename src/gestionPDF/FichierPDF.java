package gestionPDF;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import donnees.Bloc;
import donnees.Paragraphe;
import donnees.Texte;

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
	
	/**
	 * Méthode qui va lire le contenu du fichier
	 * @throws IOException 
	 */
	public void lire() throws IOException {
		String retour = PdfTextExtractor.getTextFromPage(this.fichier, 1, new Strategie());
//		System.out.println(retour);
		
		// Données de sauvegarde
		Paragraphe paragrapheAvant = null;
		Texte texteAvant = null;
		float xAvant = -1;
		float yAvant = -1;
		
		/* Test création objet Texte */
		String[] lesTextes = retour.split(";");
//		System.out.println(lesTextes[0]);
		for(String info : lesTextes) {
			// Création du paragraphe
			String[] lesInfos = info.split(",");
			// Création du nouveau paragraphe;
			float x = Float.parseFloat(lesInfos[3]);
			float y = Float.parseFloat(lesInfos[4]);
			
			Paragraphe paragraphe = null;
			if(xAvant == -1 && yAvant == -1) {
				paragraphe = new Paragraphe(Math.round(x), Math.round(y));
			}else {
				if(yAvant != y) {
					paragraphe = new Paragraphe(Math.round(x), Math.round(y));
				}else {
					paragraphe = paragrapheAvant;
				}
			}
			// Mise à jour des coordonnées de sauvegarde
			xAvant = x;
			yAvant = y;
			
			//Création d'un nouveau Texte
			Texte texte = new Texte(lesInfos[0]);
			
			// Définition de la police
			texte.setPolice(lesInfos[1]);
			
			// Définition du type de police
			String typePolice = lesInfos[2];
			if(typePolice != null) {
				switch(typePolice) {
					case "Bold" :
						texte.setGras(true);
						break;
					case "Italic" :
						texte.setItalique(true);
						break;
				}
			}
			
			// Définition de la taille de la police
			texte.setTaille(Integer.parseInt(lesInfos[6]));
			
			// Test si c'est un texte de la même ligne que le précédent texte
			// Pour faire la mise à jour ou non
			if(texteAvant == null) {
				texteAvant = texte;
				paragraphe.ajouterTexte(texte);
			}else {
				boolean test = this.testTexte(texteAvant, texte, yAvant, y);
				if(test && paragraphe.getTexte().size() == 0) {
					paragraphe.ajouterTexte(texte);
				}else if(test) {
					int taille = paragraphe.getTexte().size();
					
					// Mise a jour du texte dans le paragraphe
					String valeurAvant = paragraphe.getTexte().get(taille - 1).getValeur();
					paragraphe.getTexte().get(taille - 1).setValeur(valeurAvant += texte.getValeur());
					
					// Mise à jour du texteAvant par le nouveau
					texteAvant = paragraphe.getTexte().get(taille - 1);
				}else {
					// Ajout du texte dans le paragraphe
					paragraphe.ajouterTexte(texte);
					
					// Mise à jour du texteAvant par le nouveau
					texteAvant = texte;
				}
			}
			
			// Ajout du paragraphe dans l'ArrayList<Bloc>
			// Rapel : Paragraphe hérite de Bloc
			this.ajouterBloc(paragraphe);
		}
		
		this.testPara();
	}
	
	/**
	 * Méthode qui test si le texte créer est identique au précédent
	 * @param Texte texteAvant
	 * @param Texte texte
	 * @return Boolean
	 */
	private Boolean testTexte(Texte texteAvant, Texte texte, float yAvant, float y) {
		if(yAvant != y) return false;
		
		if(texteAvant.getCouleur() != null)
			if(!(texteAvant.getCouleur().equals(texte.getCouleur()))) return false;
		
		if(texteAvant.getPolice() != null)
			if(!(texteAvant.getPolice().equals(texte.getPolice()))) return false;
		
		if(texteAvant.getTaille() != 0)
			if(!(texteAvant.getTaille() == texte.getTaille())) return false;
		
		return true;
	}
	
	private void testPara() {
		Bloc b = this.blocs.get(0);
		if(b instanceof Paragraphe) {
			Paragraphe p = (Paragraphe)b;
			
			System.out.println(p.getTexte().get(0).getValeur());
		}
	}
}

