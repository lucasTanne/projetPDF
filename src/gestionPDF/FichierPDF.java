package gestionPDF;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import donnees.Bloc;
import donnees.Images;
import donnees.Page;
import donnees.Paragraphe;
import donnees.Texte;

public class FichierPDF {
	private String chemin;	
	private PdfReader fichier;
	private ArrayList<Page> pages;
	
	/**
	 * Constructeur d'un fichier PDF
	 * @param String chemin
	 */
	public FichierPDF(String chemin){
		this.chemin = chemin;
		
		this.fichier = null;
		this.pages = new ArrayList<Page>();
	}
	
	/**
	 * Methode de chargement du fichier PDF.
	 * Propage l'erreur pour la gerer dans le package de l'IHM.
	 * @throws IOException
	 */
	public void ouvrir() throws IOException {
		this.fichier = new PdfReader(this.chemin);
	}
	
	/**
	 * Methode qui referme un fichier PDF
	 */
	public void fermer() {
		this.fichier.close();
	}
	
	/**
	 * Methode qui test si un fichier est ouvert
	 * @return Boolean
	 */
	public Boolean estOuvert() {
		if(this.fichier != null) return true;
		return false;
	}
	
	/**
	 * Methode qui retourne le nombre de pages du document
	 * @return int nbPages
	 */
	public int nbPages() {
		return this.fichier.getNumberOfPages();
	}
	
	/**
	 * Methode qui ajoute une page dans l'ArrayList<Page>
	 * @param Page page
	 */
	public void ajouterPage(Page page) {
		this.pages.add(page);
	}
	
	/**
	 * Methode qui supprime un bloc de l'ArrayList<Bloc>
	 * @param int index
	 */
	public void supprimerPage(int index) {
		this.pages.remove(index);
	}
	
	/**
	 * Methode qui retourne une liste de pages
	 * @return ArrayList<Page>
	 */
	public ArrayList<Page> getPages(){
		return this.pages;
	}
	
	/**
	 * Methode qui retourne une page de la liste de pages
	 * @param int index
	 * @return Page page
	 */
	public Page getPage(int index) {
		return this.pages.get(index);
	}
	
	/**
	 * Methode qui va lire le contenu du fichier
	 * @throws IOException 
	 */
	public void lire(int indexPage) throws IOException {
		// Creation d'une instance de Strategie pour l'extraction
		Strategie strat = new Strategie();
		
		// Extraction des donnees de la page
		String retour = PdfTextExtractor.getTextFromPage(this.fichier, indexPage, strat);
		System.out.println(retour);
		
		// Creation d'une nouvelle page
		Page page = new Page();
		
		// Declaration d'un paragraphe
		Paragraphe paragraphe = null;
		
		String[] lesTextes = retour.split(";;");
		for(String info : lesTextes) {
			System.out.println(info + "\n\n");
			String[] lesInfos = info.split(",,");
//			System.out.println(lesInfos.length);
			if(lesInfos.length == 7) {
			
				// Test si s'est un "\n"
	//			System.out.print(lesInfos[0]);
				if(lesInfos[0].contentEquals(" ")) {
					// Ajout du paragraphe dans la liste de bloc
					if(paragraphe != null) page.ajouterBloc(paragraphe);
					paragraphe = null;
				}else {
					// Si il n'y a pas de paragraphe existant
					if(paragraphe == null) { 
						// Creation d'un nouveau paragraphe
						paragraphe = new Paragraphe(Math.round(Float.parseFloat(lesInfos[3])), Math.round(Float.parseFloat(lesInfos[4])));
					
						// Creation d'un nouveau texte
						Texte texte = new Texte(lesInfos[0]);
						
						// definition de la police du texte
						if(!lesInfos[1].contentEquals("null"))
							texte.setPolice(lesInfos[1]);
						
						// Definition du type de police du texte
						if(!lesInfos[2].contentEquals("null")) {
							if(lesInfos[2].contains("Bold")) texte.setGras(true);
							if(lesInfos[2].contains("Italic")) texte.setItalique(true);
						}
						
						// Definition de la taille de la police
						texte.setTaille(Integer.parseInt(lesInfos[6]));
						
						// Ajout du texte au paragraphe
						paragraphe.ajouterTexte(texte);
					}else {
						// Recuperation du dernier texte de la liste de Texte du paragraphe
						Texte texteDernier = paragraphe.getTexte().get(paragraphe.getTexte().size() - 1);
						
						// Test si les informations concernent le texte sont identiques
						if(this.testTexte(lesInfos, texteDernier)) {
							// Recupere la valeur du texte
							String valeur = texteDernier.getValeur();
							paragraphe.getTexte().get(paragraphe.getTexte().size() - 1).setValeur(valeur + lesInfos[0]);
						}else {
							// Creation d'un nouveau texte
							Texte texte = new Texte(lesInfos[0]);
							
							// definition de la police du texte
							if(!lesInfos[1].contentEquals("null"))
								texte.setPolice(lesInfos[1]);
							
							// Definition du type de police du texte
							if(!lesInfos[2].contentEquals("null")) {
								if(lesInfos[2].contains("Bold")) texte.setGras(true);
								if(lesInfos[2].contains("Italic")) texte.setItalique(true);
							}
							
							// Definition de la taille de la police
							texte.setTaille(Integer.parseInt(lesInfos[6]));
							
							// Ajout du texte au paragraphe
							paragraphe.ajouterTexte(texte);
						}
					}
				}
			}
		}
		// Recuperation des images de la page
		ArrayList<Images> lesImages = strat.getLesImages();
		if(lesImages != null) {
			for(Images image : lesImages) {
				page.ajouterBloc(image);
			}
		}
		this.ajouterPage(page);
		
		//this.testPara();
		//this.testImage();
	}
	
	/**
	 * Methode qui test si le texte creer est identique au precedent
	 * @param Texte texteAvant
	 * @param Texte texte
	 * @return Boolean
	 */
	private Boolean testTexte(String[] lesInfos, Texte texte) {		
		// Test sur la police
		if(!(lesInfos[1].contentEquals(texte.getPolice())))	return false;
		
		// Test sur la taille de la police
		if(Integer.parseInt(lesInfos[6]) != texte.getTaille()) return false;
		
		// Test sur les types de police
		if(lesInfos[2].contains("Bold") && texte.isGras()) return false;
		if(lesInfos[2].contains("Italic") && texte.isItalique()) return false;
		
		return true;
	}
	
	private void testPara() {
		System.out.println("nb : " + this.pages.size());
		for(Page pa : this.pages) {
			for(Bloc b : pa.getLesBlocs()) {
				if(b instanceof Paragraphe) {
					System.out.println("Ceci est un Paragraphe :");
					Paragraphe p = (Paragraphe)b;
					for(Texte t : p.getTexte()) {
						System.out.println("Valeur : " + t.getValeur());
					}
					System.out.println("\n");
				}
			}
		}
	}
	
	private void testImage() {
		int a = 0;
		for(Page p : this.pages) {
			for(Bloc b : p.getLesBlocs()) {
				if(b instanceof Images) {
					Images i = (Images)b;
					
					File outputfile = new File("C:\\Users\\lulu\\Documents\\UBO_2019-2020\\projet\\testImage\\" + a + ".png");
					try {
						ImageIO.write(i.getImage(), "png", outputfile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					a++;
				}
			}
		}
	}
}

