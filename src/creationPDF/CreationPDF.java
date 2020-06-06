package creationPDF;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import donnees.Bloc;
import donnees.Images;
import donnees.Page;
import donnees.Paragraphe;
import javafx.print.PrinterJob;

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
	
	/**
	 * Methode qui construit un document PDF
	 * @param ArrayList<Page> lesPages
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public void construction(ArrayList<Page> lesPages) throws FileNotFoundException, DocumentException {
		// Creation d un document PDF
		this.document = new Document();
		
		// Instanciation d une instance PdfWriter du document
		PdfWriter.getInstance(this.document, new FileOutputStream(this.cheminDest));
		
		// Ouverture du document
		this.document.open();
		
		// Pour chaques pages venant de l ArrayList<Page>
		for(Page page : lesPages) {
			// Creation d une nouvelle page dans le document
			this.document.newPage();
			
			// Pour chaques blocs dans une page
			for(Bloc bloc : page.getLesBlocs()) {
				// Test si le bloc est une instance de Paragraphe
				if(bloc instanceof Paragraphe) {
					// Cast de l objet en Paragraphe
					Paragraphe paragraphe = (Paragraphe)bloc;
					
					// Ajout d un paragraphe creer dans la classe CreationTexte
					this.document.add(new CreationTexte(paragraphe).creationParagraphe());
				// Test si le bloc est une instance de Images
				}else if(bloc instanceof Images) {
					// Cast de l objet en Images
					Images monImage = (Images)bloc;
					
					// Ajout de l instance d image iText creer dans la classe CreationImage
					this.document.add(new CreationImage(monImage).creerImage());
				}
			}
		}
		// Fermeture du document
		this.document.close();
	}
}