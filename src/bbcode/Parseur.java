package bbcode;

import java.util.ArrayList;

import donnees.Texte;

public class Parseur
{
	ArrayList<Balise> balises;
	
	public Parseur(Balise... balises)
	{
		this.balises = new ArrayList<Balise>();
		
		for(Balise balise : balises)
		{
			this.balises.add(balise);
		}
	}
	
	public void AjouterBalise(Balise... balises)
	{
		for(Balise balise : balises)
		{
			this.balises.add(balise);			
		}
	}
	
	public void supprimerBalise(Balise balise)
	{
		this.balises.remove(balise);
	}
	
	public void supprimerBalise(int index)
	{
		this.balises.remove(index);
	}
	
	public ArrayList<Texte> parser(String chaine)
	{
		ArrayList<Texte> paragraphe = this.balises.get(0).parser(chaine);
		
		if(paragraphe != null)
		{
			for(int i = 1; i < this.balises.size(); i++)
			{
				paragraphe = this.balises.get(i).parserParagraphe(paragraphe);
			}
		}
		
		return paragraphe;
	}
}
