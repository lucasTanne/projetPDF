package creationPDF;

import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;

public class Impression {
	private String lien;
	
	/**
	 * Constructeur
	 * @param String lien
	 */
	public Impression(String lien) {
		this.lien = lien;
	}
	
	public void impression() throws PrintException, IOException{
		// Requette qui specifie un ensemble d attribut pour une demande d impression
		PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
		patts.add(Sides.DUPLEX);
		
		// Recuperation de l ensemble des impremantes correcpondantes au critere definis au dessus
		PrintService[] ps = PrinterJob.lookupPrintServices();
		if (ps.length == 0) {
		  throw new IllegalStateException("Aucune imprimante trouvée");
		}
		System.out.println("Available printers: " + Arrays.asList(ps));
		
		// Choix de l'imprimante
		PrintService myService = null;
		for (PrintService printService : ps) {
		  if (printService.getName().equals("Microsoft Print to PDF")) {
		    myService = printService;
		    break;
		  }
		}
		
		if (myService == null) {
		  throw new IllegalStateException("L'imprimante souhaitée est introuvable");
		}
		
		// Ouverture d un flux de lecture du fichier PDF
		FileInputStream fis = new FileInputStream(this.lien);
		
		// Creation d une instance de Doc pour fournir les donnees d impression du flux 
		Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
		
		// Creation d une instance du DocPrintJob qui va lancer l impression
		DocPrintJob printJob = myService.createPrintJob();
		printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
		
		// Fermeture du flux de lecture du fichier PDF
		fis.close();
		
		System.out.println("Imprimer !");
	}
}
