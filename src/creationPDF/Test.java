 package creationPDF;

import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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

import com.itextpdf.text.DocumentException;

import donnees.Page;
import gestionPDF.FichierPDF;

public class Test {
	private static String lien = "C:\\Users\\stobr\\Desktop\\RELOU.pdf";
	private static String save = "C:\\Users\\stobr\\Desktop\\test2.pdf";

	public static void main(String[] args) throws IOException, DocumentException, PrintException {
		// Extraction
		FichierPDF fichier = new FichierPDF(lien);
		fichier.ouvrir();
		int nbPages = fichier.nbPages();
		for(int i = 1; i <= nbPages; i++) {
			fichier.lire(i);
		}
		ArrayList<Page> lesPages = fichier.getPages();
		
		
		// Enregistrement
		CreationPDF  creation = new CreationPDF(save);
		creation.construction(lesPages);
		
		// Impression
		impression();
	}
	
	private static void impression() throws PrintException, IOException{
		// Encapsulation des donnees relative au format d impression
		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
		
		// Requette qui specifie un ensemble d attribut pour une demande d impression
		PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
		patts.add(Sides.DUPLEX);
		
		// Recuperation de l ensemble des impremantes correcpondantes au critere definis au dessus
		PrintService[] ps = PrinterJob.lookupPrintServices();
		if (ps.length == 0) {
		  throw new IllegalStateException("Aucune imprimante trouv�e");
		}
		for(PrintService p : ps) System.out.println(p.getName());
		System.out.println("Available printers: " + Arrays.asList(ps));
		
		PrintService myService = null;
		for (PrintService printService : ps) {
		  if (printService.getName().equals("Brother DCP-585CW Printer")) {
		    myService = printService;
		    break;
		  }
		}
		
		if (myService == null) {
		  throw new IllegalStateException("L'imprimante souhait�e est introuvable");
		}
		
		// Ouverture d un flux de lecture du fichier PDF
		FileInputStream fis = new FileInputStream(save);
		
		// Creation d une instance de Doc pour fournir les donnees d impression du flux 
		Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
		
		// Creation d une instance du DocPrintJob qui va lancer l impression
		DocPrintJob printJob = myService.createPrintJob();
		printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
		
		// Fermeture du flux de lecture du fichier PDF
		fis.close();
	}
}
