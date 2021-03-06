package bbcode;

import java.util.ArrayList;

import donnees.Texte;
import javafx.scene.paint.Color;

public class Italique implements Balise
{
	/**
	 * La balise de debut de mise en forme
	 */
	static final String debut = "[i]";

	/**
	 * La balise de fin de mise en forme
	 */
	static final String fin = "[\\i]";

	public ArrayList<Texte> parser(String texte)
	{
		// La chaine ne doit pas être nulle ou vide
		if(texte == null || texte.isEmpty())
		{
			return null;
		}
		
		// On initialise le paragraphe
		ArrayList<Texte> paragraphe = new ArrayList<Texte>();

		// On initialise les indexs de debut et de fin de la premiere balise
		int indiceDebut = texte.indexOf(debut);
		int indiceFin = texte.indexOf(fin);
		
		/*
		 *  Si le texte contiens la balise de debut
		 *  mais pas la balise de fin ou si la derniere balise
		 *  de debut est apres la derniere balise de fin
		 */
		if(texte.contains(debut) && !texte.contains(fin) ||
				(texte.contains(debut) && texte.contains(fin) && texte.lastIndexOf(debut) > texte.lastIndexOf(fin)))
		{
			// On ajoute la balise de fin a la fin du texte
			texte = texte + fin;
		}
		
		/*
		 *  Si le texte contiens la balise de fin
		 *  mais pas la balise de debut ou si la premiere balise
		 *  de debut est apres la premiere balise de fin
		 */
		else if((texte.contains(fin) && !texte.contains(debut)) ||
				(texte.contains(debut) && texte.contains(fin) && indiceDebut > indiceFin))
		{
			// On ajoute la balise de debut au debut du texte
			texte = debut + texte;
		}
		
		indiceDebut = texte.indexOf(debut);
		indiceFin = texte.indexOf(fin);
		
		// On verifie que le texte contiens les balises bien positionnees
		if(texte.contains(debut) && texte.contains(fin) && indiceDebut < indiceFin)
		{
			/* 
			 * Si la balise de debut ne se trouve pas au debut
			 * On ajoute le debut du texte au paragraphe
			*/ 
			if(indiceDebut > 0)
			{
				paragraphe.add(new Texte(texte.substring(0, indiceDebut)));
			}
			
			/*
			 * Tant qu'on trouve des balises bien positionnees
			 */
			while(indiceDebut > -1 && indiceFin > -1 && indiceDebut < indiceFin)
			{
				// On recupere le texte a mettre en italique
				String valeur = texte.substring(indiceDebut + debut.length(), indiceFin);
				
				/*
				 *  Si le texte est une chaine vide
				 *  On ne le recupere pas
				 */
				if(!valeur.equals(""))
				{
					Texte t = new Texte(valeur);
					t.setItalique(true);
					paragraphe.add(t);
				}
				
				// On recupere la prochaine balise de debut
				indiceDebut = texte.indexOf(debut, indiceFin + fin.length());
				
				/*
				 * S'il y a du texte entre la prochaine balise de debut
				 * et la balise de fin precedente, on l'ajoute au paragraphe
				 */
				if(indiceDebut > indiceFin + fin.length())
				{
					paragraphe.add(new Texte(texte.substring(indiceFin + fin.length(), indiceDebut)));
				}
				
				// On met a jour la balise de fin
				indiceFin = texte.indexOf(fin, indiceDebut);
			}
			
			/*
			 * S'il reste du texte apres la derniere balise de fin
			 */
			if(texte.lastIndexOf(fin) < texte.length() - fin.length())
			{
				// On ajoute ce texte au paragraphe
				paragraphe.add(new Texte(texte.substring(texte.lastIndexOf(fin) + fin.length())));
			}
		}
		
		/*
		 *  Si le texte ne contient pas de balises bien positionnees
		 */
		
		else
		{
			// On ajoute le texte tel quel dans le paragraphe
			paragraphe.add(new Texte(texte));
		}
		
		// On retourne le paragraphe
		return paragraphe;
	}
	
	
	@Override
	public ArrayList<Texte> parserParagraphe(ArrayList<Texte> textes)
	{
		// On initialise le paragraphe
		ArrayList<Texte> paragraphe = new ArrayList<Texte>();
		
		// On parcours le paragraphe d'origine
		for(int i = 0; i < textes.size(); i++)
		{
			Texte texte = textes.get(i);
			
			/*
			 * S'il la derniere balise de debut se trouve apres la derniere balise de fin
			 * et que le prochain texte ne commence pas par la balise de debut, on ajoute
			 * une balise de debut au prochain texte
			 */
			if(texte.getValeur().lastIndexOf(debut) > texte.getValeur().indexOf(fin) && i < textes.size() - 1)
			{
				Texte suivant = textes.get(i+1);
				
				if(!suivant.getValeur().startsWith(debut))
				{
					suivant.setValeur(debut + suivant.getValeur());
				}
			}
			
			// On recupere les parametre du texte en cours de traitement
			boolean gras = texte.isGras();
			boolean souligne = texte.isSouligne();
			boolean barre = texte.isBarre();
			
			Color couleur = texte.getCouleur();
			
			String hyperlien = texte.getHyperlien();
			
			String police = texte.getPolice();
			int taille = texte.getTaille();
			
			// On parse le texte en cours
			ArrayList<Texte> res = parser(texte.getValeur());
			
			if(res != null)
			{
				// On parcours le paragraphe qui a ete parser
				for(Texte t : res)
				{
					// On remet ses attributs de bases
					t.setGras(gras);
					t.setSouligne(souligne);
					t.setBarre(barre);
					t.setCouleur(couleur);
					t.setHyperlien(hyperlien);
					t.setPolice(police);
					t.setTaille(taille);
					
					// On ajoute le texte au paragraphe
					paragraphe.add(t);
				}
			}
		}
		
		// On retourne le paragraphe
		return paragraphe;
	}
}
