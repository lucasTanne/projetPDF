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
	 * Met les proprietees e leurs valeurs par defaut
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
	 * Methode get de la valeur du texte
	 * @return valeur
	 */
	public String getValeur() {
		return this.valeur;
	}
	
	/**
	 * Methode set de la valeur du texte
	 * @param texte
	 */
	public void setValeur(String texte) {
		this.valeur = texte;
	}
	
	/**
	 * Methode get de la police du texte
	 * @return police
	 */
	public String getPolice() {
		return this.police;
	}
	
	/**
	 * Methode set de la police du texte
	 * @param police
	 */
	public void setPolice(String police) {
		this.police = police;
	}
	
	/**
	 * Methode qui retourne la taille de la police
	 * @return
	 */
	public int getTaille() {
		return this.taille;
	}
	
	/**
	 * Methode qui definis la taille de la police
	 * @param taille
	 */
	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	/**
	 * Methode qui indique si le texte est en gras
	 * @return true/false
	 */
	public Boolean isGras() {
		return this.gras;
	}
	
	/**
	 * Methode qui definis si le texte est en gras
	 * @param true/false
	 */
	public void setGras(Boolean valeur) {
		this.gras = valeur;
	}
	
	/**
	 * Methode qui indique si le texte est en italique
	 * @return true/false
	 */
	public Boolean isItalique() {
		return this.italique;
	}
	
	/**
	 * Methode qui definis si le texte est en italique
	 * @param true/false
	 */
	public void setItalique(Boolean valeur) {
		this.italique = valeur;
	}
	
	/**
	 * Methode qui indique si le texte est souligne
	 * @return true/false
	 */
	public Boolean isSouligne() {
		return this.souligne;
	}
	
	/**
	 * Methode qui definis si le texte est souligne
	 * @param valeur
	 */
	public void setSouligne(Boolean valeur) {
		this.souligne = valeur;
	}
	
	/**
	 * Methode qui indique si le texte est barre
	 * @return true/false
	 */
	public Boolean isBarre() {
		return this.barre;
	}
	
	/**
	 * Methode qui definis si le texte est barre
	 * @param true/false
	 */
	public void setBarre(Boolean valeur) {
		this.barre = valeur;
	}
	
	/**
	 * Methode qui retorune le lien hypertexte
	 * @return lien hypertexte
	 */
	public String getHyperlien() {
		return this.hyperlien;
	}
	
	/**
	 * Methode qui definis le lien de l'hyperlien
	 * @param lien hypertexte
	 */
	public void setHyperlien(String lien) {
		this.hyperlien = lien;
	}
	
	/**
	 * Methode qui donne la couelur du texte
	 * @return un objet couleur
	 */
	public Color getCouleur() {
		return this.couleur;
	}
	
	/**
	 * Methode qui definis la couleur du texte
	 * @param un objet couleur
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	/**
	 * Methode toString de la classe texte
	 */
	@Override
	public String toString() {
		return "Texte [valeur=" + valeur + ", police=" + police + ", taille=" + taille + ", gras=" + gras
				+ ", italique=" + italique + ", souligne=" + souligne + ", barre=" + barre + ", hyperlien=" + hyperlien
				+ ", couleur=" + couleur + "]";
	}
	
	
}
