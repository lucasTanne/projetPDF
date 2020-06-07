package creationPDF;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import donnees.Paragraphe;
import donnees.Texte;

public class CreationTexte {
	private Paragraphe paragraphe;
	
	/**
	 * Constructeur
	 * @param Paragraphe paragraphe
	 */
	public CreationTexte(Paragraphe paragraphe){
		this.paragraphe = paragraphe;
	}
	
	/**
	 * Fonction de creation d un paragraphe
	 * @return Paragraph
	 */
	public Paragraph creationParagraphe() {
		// Creation d'un paragraphe iText
		Paragraph paragraph = new Paragraph();
		
		// Parcourt des textes du paragraphe
		for(Texte texte : this.paragraphe.getTexte()) {
			// Creation de la font
			Font font = this.creerFont(texte);
			
			// Creation d'un objet phrase
			Phrase phrase = new Phrase((float)texte.getTaille(), texte.getValeur(), font);
			paragraph.add(phrase);
		}
		paragraph.setIndentationLeft((float)paragraphe.getX());
		paragraph.setSpacingAfter(10);
		
		return paragraph;
	}
	
	/**
	 * Fonction qui creer une font
	 * @param Texte texte
	 * @return Font
	 */
	private Font creerFont(Texte texte) {
		Font font = new Font();
		
		if(texte.isGras()) font.setStyle("bold"); 
		if(texte.isItalique()) font.setStyle("italic");
		
		return font;
	}
	
	private void position(Phrase phrase) {
		
	}
}