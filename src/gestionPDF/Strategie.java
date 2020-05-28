package gestionPDF;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

import donnees.Images;

public class Strategie implements TextExtractionStrategy{
	private String res = "";
    private String police = "";
    private String typePolice = "";
    private Boolean debut = false;
    private String coordonnees = "";
    private int taillePolice = 0;
    
    // Buffer d'image si il y en a une dans l'extraction de la page
    private ArrayList<Images> images = null;

    @Override
    public void beginTextBlock()
    {
    	this.debut = true;
    }

    @Override
    public void endTextBlock()
    {
    	
    	this.res += ",," + this.police + ",," + this.typePolice + ",," + this.coordonnees + ",," + this.taillePolice + ";;";
    }

    @Override
    public void renderImage(ImageRenderInfo arg0)
    {
    	if(this.images == null) this.images = new ArrayList<Images>();
    	
    	try {
    		String[] infos = arg0.getStartPoint().toString().split(",");
    		String x = infos[0];
    		String y = infos[1];
			Images image = new Images(Math.round(Float.parseFloat(x)), Math.round(Float.parseFloat(y)));
			
			BufferedImage bufImg = arg0.getImage().getBufferedImage();
		    BufferedImage convertedImg = new BufferedImage(bufImg.getWidth(), bufImg.getHeight(), BufferedImage.TRANSLUCENT);
		    convertedImg.getGraphics().drawImage(bufImg, 0, 0, null);
			
			image.setImage(convertedImg);
			
			this.images.add(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @Override
    public void renderText(TextRenderInfo arg0)
    {
		res += arg0.getText();
		
	    String fontName = arg0.getFont().getPostscriptFontName();
	    
	    this.police = (fontName.indexOf("+") == -1 ? "null" : fontName.substring(fontName.indexOf("+") + 1));
	    if(this.police.indexOf("-") != -1) this.police = this.police.split("-")[0];
	   
	    this.typePolice = (fontName.indexOf("-") == -1 ? "null" : fontName.substring(fontName.indexOf("-") + 1));

	    if(this.debut == true) {
	    	this.coordonnees = arg0.getAscentLine().getStartPoint().toString().replace(",", ",,");
	    	this.debut = false;
	    }
	    
	    // taille de la police en float convertie en int
	    this.taillePolice = Math.round((arg0.getAscentLine().getStartPoint().get(1) - arg0.getDescentLine().getStartPoint().get(1)));
    }

    @Override
    public String getResultantText()
    {
        return this.res;
    }
    
    /**
     * Methode qui retourne les images
     * @return
     */
    public ArrayList<Images> getLesImages(){
    	return this.images;
    }
}
