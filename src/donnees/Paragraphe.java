package donnees;

import java.util.ArrayList;

public class Paragraphe extends Bloc{
	private ArrayList<Texte> texte;
	
	public Paragraphe(double x, double y) {
		super(x, y);
		this.texte = new ArrayList<Texte>();
	}
	
	/**
	 * Methode qui retourne une ArrayList de Texte
	 * @return ArrayList<Texte>
	 */
	public ArrayList<Texte> getTexte(){
		return this.texte;
	}
	
	
	
	/**
	 * Methode modifiant la liste de texte
	 * @param texte le texte a modifie
	 */
	public void setTexte(ArrayList<Texte> texte) {
		this.texte = texte;
	}

	/**
	 * Methode qui ajoute un objet texte dans l'ArrayList<Texte>
	 * @param objet Texte
	 */
	public void ajouterTexte(Texte texte) {
		this.texte.add(texte);
	}
	
	/**
	 * Methode qui supprimer un objet Texte dans l'ArrayList<Texte>
	 * @param int index
	 */
	public void supprimerTexte(int index) {
		this.texte.remove(index);
	}
}
