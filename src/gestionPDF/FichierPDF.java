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
		System.out.println(retour);
		
		
		
		// Déclaration d'un paragraphe
		Paragraphe paragraphe = null;
		
		String[] lesTextes = retour.split(";");
		for(String info : lesTextes) {
			String[] lesInfos = info.split(",");
			
			// Test si s'est un "\n"
//			System.out.print(lesInfos[0]);
			if(lesInfos[0].contentEquals(" ")) {
				// Ajout du paragraphe dans la liste de bloc
				if(paragraphe != null) this.ajouterBloc(paragraphe);
				paragraphe = null;
			}else {
				// Si il n'y a pas de paragraphe existant
				if(paragraphe == null) { 
					// Création d'un nouveau paragraphe
					paragraphe = new Paragraphe(Math.round(Float.parseFloat(lesInfos[3])), Math.round(Float.parseFloat(lesInfos[4])));
					
					// Création d'un nouveau texte
					Texte texte = new Texte(lesInfos[0]);
					
					// définition de la police du texte
					if(!lesInfos[1].contentEquals("null"))
						texte.setPolice(lesInfos[1]);
					
					// Définition du tyoe de police du texte
					if(!lesInfos[2].contentEquals("null")) {
						if(lesInfos[2].contains("Bold")) texte.setGras(true);
						if(lesInfos[2].contains("Italic")) texte.setItalique(true);
					}
					
					// Définition de la taille de la police
					texte.setTaille(Integer.parseInt(lesInfos[6]));
					
					// Ajout du texte au paragraphe
					paragraphe.ajouterTexte(texte);
				}else {
					Texte texte = paragraphe.getTexte().get(paragraphe.getTexte().size() - 1);
					texte.setValeur(texte.getValeur() + lesInfos[0]);
				}
			}
		}
		this.testPara();
	}
	
	/**
	 * Méthode qui test si le texte créer est identique au précédent
	 * @param Texte texteAvant
	 * @param Texte texte
	 * @return Boolean
	 */
//	private Boolean testTexte(Texte texteAvant, Texte texte, float yAvant, float y) {
//		if(yAvant != y) return false;
//		
//		if(texteAvant.getCouleur() != null)
//			if(!(texteAvant.getCouleur().equals(texte.getCouleur()))) return false;
//		
//		if(texteAvant.getPolice() != null)
//			if(!(texteAvant.getPolice().equals(texte.getPolice()))) return false;
//		
//		if(texteAvant.getTaille() != 0)
//			if(!(texteAvant.getTaille() == texte.getTaille())) return false;
//		
//		return true;
//	}
//	
	private void testPara() {
		System.out.println("nb : " + this.blocs.size());
		for(Bloc b : this.blocs) {
			if(b instanceof Paragraphe) {
				System.out.println("Ceci est un Paragraphe :");
				Paragraphe p = (Paragraphe)b;
				for(Texte t : p.getTexte()) {
					System.out.println("Valeur : " + t.getValeur());
				}
				System.out.println("\n");
			}
		}
//		Bloc b = this.blocs.get(0);
//		if(b instanceof Paragraphe) {
//			Paragraphe p = (Paragraphe)b;
//			
//			System.out.println(p.getTexte().get(0).getValeur());
//		}
	}
}

