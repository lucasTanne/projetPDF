package gestionPDF;

import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

public class Strategie implements TextExtractionStrategy{
	private String res = "";
    private String police = "";
    private String typePolice = "";
    private Boolean debut = false;
    private String coordonnees = "";
    private int taillePolice = 0;

    @Override
    public void beginTextBlock()
    {
    	this.debut = true;
    }

    @Override
    public void endTextBlock()
    {
    	
    	this.res += "," + this.police + "," + this.typePolice + "," + this.coordonnees + "," + this.taillePolice + ";";
    }

    @Override
    public void renderImage(ImageRenderInfo arg0)
    {
        
    }

    @Override
    public void renderText(TextRenderInfo arg0)
    {
		res += arg0.getText();
		
	    String fontName = arg0.getFont().getPostscriptFontName();
	    this.police = (fontName.indexOf("+") == -1 ? "null" : fontName.substring(fontName.indexOf("+") + 1));
	    this.typePolice = (fontName.indexOf("-") == -1 ? "null" : fontName.substring(fontName.indexOf("-") + 1));

	    if(this.debut == true) {
	    	this.coordonnees = arg0.getAscentLine().getStartPoint().toString();
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
}
