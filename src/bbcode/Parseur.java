package bbcode;

import java.util.ArrayList;

import donnees.Texte;

public class Parseur
{
	/**
	 * La listes des balises a parser
	 */
	private ArrayList<Balise> balises;
	
	/**
	 * Constructeur de la classe Parseur
	 * @param balises les balises a inserer dans la liste
	 */
	public Parseur(Balise... balises)
	{
		// Instancie la liste
		this.balises = new ArrayList<Balise>();
		
		// Ajoute les balises a la liste
		for(Balise balise : balises)
		{
			this.balises.add(balise);
		}
	}
	
	/**
	 * Ajoute une ou plusieurs balises a la liste
	 * @param balises
	 */
	public void AjouterBalise(Balise... balises)
	{
		// Ajoute les balises a la liste
		for(Balise balise : balises)
		{
			this.balises.add(balise);			
		}
	}
	
	/**
	 * Supprime une balise de la liste
	 * @param balise
	 */
	public void supprimerBalise(Balise balise)
	{
		// Supprime la balise
		this.balises.remove(balise);
	}
	
	/**
	 * Supprime une balise de la liste a partir de son index
	 * @param balise
	 */
	public void supprimerBalise(int index)
	{
		// Supprime la balise a partir de son index
		this.balises.remove(index);
	}
	
	/**
	 * Parse un texte en parcourant la liste de ses balises
	 * @param chaine
	 * @return
	 */
	public ArrayList<Texte> parser(String chaine)
	{
		// On parse le texte de base avec la premiere balise de la liste
		ArrayList<Texte> paragraphe = this.balises.get(0).parser(chaine);
		
		// Si la liste retournee n'est pas nulle
		if(paragraphe != null)
		{
			// On parse la liste de texte avec chaque balise de la liste
			for(int i = 1; i < this.balises.size(); i++)
			{
				paragraphe = this.balises.get(i).parserParagraphe(paragraphe);
			}
		}
		
		// Retourne la liste de texte parse
		return paragraphe;
	}
}
