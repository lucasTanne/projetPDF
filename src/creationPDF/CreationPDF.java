package creationPDF;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import donnees.Bloc;
import donnees.Images;
import donnees.Page;
import donnees.Paragraphe;
import donnees.Texte;

public class CreationPDF {
	private String cheminDest;
	private Document document;
	
	/**
	 * Constructeur
	 * @param String cheminDest
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 */
	public CreationPDF(String cheminDest) throws FileNotFoundException, DocumentException {
		this.cheminDest = cheminDest;
	}
	
	public void construction(ArrayList<Page> lesPages) throws FileNotFoundException, DocumentException {
		this.document = new Document();
		PdfWriter writer = PdfWriter.getInstance(this.document, new FileOutputStream(this.cheminDest));
		this.document.open();
		
		for(Page page : lesPages) {
			
			this.document.newPage();
			
			for(Bloc bloc : page.getLesBlocs()) {
				if(bloc instanceof Paragraphe) {
					Paragraphe paragraphe = (Paragraphe)bloc;
					
					// Cr�ation d'un paragraphe iText
					Paragraph paragraph = new Paragraph();
					for(Texte texte : paragraphe.getTexte()) {
						Font font = new Font(); 
						if(texte.isGras()) font.setStyle("bold"); 
						if(texte.isItalique()) font.setStyle("italic");
						
						Phrase phrase = new Phrase(texte.getTaille(), texte.getValeur(), font);
						paragraph.add(phrase);
					}
					paragraph.setIndentationLeft((float)paragraphe.getX());
					paragraph.setSpacingAfter(10);
					
					this.document.add(paragraph);
				}else if(bloc instanceof Images) {
					Images image = (Images)bloc;
				}
			}
		}
		this.document.close();
	}
}
