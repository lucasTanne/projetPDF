package donnees;

import javafx.scene.paint.Color;

public class Texte {
	private String valeur;
	private String police;
	private int taille;
	private Boolean gras;
	private Boolean italique;
	private Boolean souligne;
	private Boolean barre;
	private String hyperlien;
	private Color couleur;
	
	/**
	 * Constructeur d'un texte
	 * @param valeur
	 * Met les propriétées à leurs valeurs par défaut
	 */
	public Texte(String valeur) {
		this.valeur = valeur;
		
		this.police = "";
		this.taille = 0;
		this.gras = false;
		this.italique = false;
		this.souligne = false;
		this.barre = false;
		this.hyperlien = "";
		this.couleur = null;
	}
	
	/**
	 * Méthode get de la valeur du texte
	 * @return valeur
	 */
	public String getValeur() {
		return this.valeur;
	}
	
	/**
	 * Méthode set de la valeur du texte
	 * @param texte
	 */
	public void setValeur(String texte) {
		this.valeur = texte;
	}
	
	/**
	 * Méthode get de la police du texte
	 * @return police
	 */
	public String getPolice() {
		return this.police;
	}
	
	/**
	 * Méthode set de la police du texte
	 * @param police
	 */
	public void setPolice(String police) {
		this.police = police;
	}
	
	/**
	 * Méthode qui retourne la taille de la police
	 * @return
	 */
	public int getTaille() {
		return this.taille;
	}
	
	/**
	 * Méthode qui définis la taille de la police
	 * @param taille
	 */
	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	/**
	 * Méthode qui indique si le texte est en gras
	 * @return true/false
	 */
	public Boolean isGras() {
		return this.gras;
	}
	
	/**
	 * Méthode qui définis si le texte est en gras
	 * @param true/false
	 */
	public void setGras(Boolean valeur) {
		this.gras = valeur;
	}
	
	/**
	 * Méthode qui indique si le texte est en italique
	 * @return true/false
	 */
	public Boolean isItalique() {
		return this.italique;
	}
	
	/**
	 * Méthode qui définis si le texte est en italique
	 * @param true/false
	 */
	public void setItalique(Boolean valeur) {
		this.italique = valeur;
	}
	
	/**
	 * Méthode qui indique si le texte est souligné
	 * @return true/false
	 */
	public Boolean isSouligne() {
		return this.souligne;
	}
	
	/**
	 * Méthode qui définis si le texte est souligné
	 * @param valeur
	 */
	public void setSouligne(Boolean valeur) {
		this.souligne = valeur;
	}
	
	/**
	 * Méthode qui indique si le texte est barré
	 * @return true/false
	 */
	public Boolean isBarre() {
		return this.barre;
	}
	
	/**
	 * Méthode qui définis si le texte est barré
	 * @param true/false
	 */
	public void setBarre(Boolean valeur) {
		this.barre = valeur;
	}
	
	/**
	 * Méthode qui retorune le lien hypertexte
	 * @return lien hypertexte
	 */
	public String getHyperlien() {
		return this.hyperlien;
	}
	
	/**
	 * Méthode qui définis le lien de l'hyperlien
	 * @param lien hypertexte
	 */
	public void setHyperlien(String lien) {
		this.hyperlien = lien;
	}
	
	/**
	 * Méthode qui donne la couelur du texte
	 * @return un objet couleur
	 */
	public Color getCouleur() {
		return this.couleur;
	}
	
	/**
	 * Méthode qui définis la couleur du texte
	 * @param un objet couleur
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
}
