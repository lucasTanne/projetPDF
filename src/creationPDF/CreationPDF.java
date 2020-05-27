package creationPDF;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import donnees.Bloc;
import donnees.Images;
import donnees.Page;
import donnees.Paragraphe;
import donnees.Texte;

public class CreationPDF {
	private String cheminDest;
	private PdfWriter document;
	
	/**
	 * Constructeur
	 * @param String cheminDest
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 */
	public CreationPDF(String cheminDest) throws FileNotFoundException, DocumentException {
		this.cheminDest = cheminDest;
		
		Document doc = new Document();
		this.document.getInstance(doc, new FileOutputStream(this.cheminDest));
		this.document.open();
	}
	
	public void construction(ArrayList<Page> lesPages) {
		for(Page page : lesPages) {
			for(Bloc bloc : page.getLesBlocs()) {
				if(bloc instanceof Paragraphe) {
					Paragraphe paragraphe = (Paragraphe)bloc;
					
					for(Texte texte : paragraphe.getTexte()) {
						Font font = new Font();
						if(texte.isGras()) font.setStyle("bold");
						if(texte.isItalique()) font.setStyle("italic");
						Phrase phrase = new Phrase(texte.getTaille(), new Chunk(texte.getValeur(), font));
						try {
							this.document.add(phrase);
						} catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else if(bloc instanceof Images) {
					Images image = (Images)bloc;
				}
			}
		}
	}
}
