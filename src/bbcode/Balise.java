package bbcode;

import java.util.ArrayList;

import donnees.Texte;

public interface Balise
{
	/**
	 * Parse le texte en liste d'objet Texte
	 * en lui attribuant le formatage de la balise
	 * @param texte Le texte a parser
	 * @return La liste de texte parse
	 */
	public abstract ArrayList<Texte> parser(String texte);
	
	/**
	 * Parse la liste de texte en un autre liste de
	 * texte a l'aide de la balise, tout en conservant
	 * ses attributs precedents
	 * @param textes La liste de texte a parser
	 * @return La liste de texte parse
	 */
	public abstract ArrayList<Texte> parserParagraphe(ArrayList<Texte> textes);
}
