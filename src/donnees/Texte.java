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
	 * Met les propri�t�es � leurs valeurs par d�faut
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
	 * M�thode get de la valeur du texte
	 * @return valeur
	 */
	public String getValeur() {
		return this.valeur;
	}
	
	/**
	 * M�thode set de la valeur du texte
	 * @param texte
	 */
	public void setValeur(String texte) {
		this.valeur = texte;
	}
	
	/**
	 * M�thode get de la police du texte
	 * @return police
	 */
	public String getPolice() {
		return this.police;
	}
	
	/**
	 * M�thode set de la police du texte
	 * @param police
	 */
	public void setPolice(String police) {
		this.police = police;
	}
	
	/**
	 * M�thode qui retourne la taille de la police
	 * @return
	 */
	public int getTaille() {
		return this.taille;
	}
	
	/**
	 * M�thode qui d�finis la taille de la police
	 * @param taille
	 */
	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	/**
	 * M�thode qui indique si le texte est en gras
	 * @return true/false
	 */
	public Boolean isGras() {
		return this.gras;
	}
	
	/**
	 * M�thode qui d�finis si le texte est en gras
	 * @param true/false
	 */
	public void setGras(Boolean valeur) {
		this.gras = valeur;
	}
	
	/**
	 * M�thode qui indique si le texte est en italique
	 * @return true/false
	 */
	public Boolean isItalique() {
		return this.italique;
	}
	
	/**
	 * M�thode qui d�finis si le texte est en italique
	 * @param true/false
	 */
	public void setItalique(Boolean valeur) {
		this.italique = valeur;
	}
	
	/**
	 * M�thode qui indique si le texte est soulign�
	 * @return true/false
	 */
	public Boolean isSouligne() {
		return this.souligne;
	}
	
	/**
	 * M�thode qui d�finis si le texte est soulign�
	 * @param valeur
	 */
	public void setSouligne(Boolean valeur) {
		this.souligne = valeur;
	}
	
	/**
	 * M�thode qui indique si le texte est barr�
	 * @return true/false
	 */
	public Boolean isBarre() {
		return this.barre;
	}
	
	/**
	 * M�thode qui d�finis si le texte est barr�
	 * @param true/false
	 */
	public void setBarre(Boolean valeur) {
		this.barre = valeur;
	}
	
	/**
	 * M�thode qui retorune le lien hypertexte
	 * @return lien hypertexte
	 */
	public String getHyperlien() {
		return this.hyperlien;
	}
	
	/**
	 * M�thode qui d�finis le lien de l'hyperlien
	 * @param lien hypertexte
	 */
	public void setHyperlien(String lien) {
		this.hyperlien = lien;
	}
	
	/**
	 * M�thode qui donne la couelur du texte
	 * @return un objet couleur
	 */
	public Color getCouleur() {
		return this.couleur;
	}
	
	/**
	 * M�thode qui d�finis la couleur du texte
	 * @param un objet couleur
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
}
