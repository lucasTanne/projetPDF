package donnees;

import java.awt.image.BufferedImage;

public class Images extends Bloc{
	private BufferedImage image;
	private String hyperlien;
	
	/**
	 * Constructeur d'un objet image
	 * @param BufferedImage image
	 */
	public Images(int x, int y) {
		super(x, y);
		
		this.image = null;
		this.hyperlien = null;
	}
	
	/**
	 * Methode qui retourne un BufferedImage
	 * @return BufferedImage
	 */
	public BufferedImage getImage() {
		return this.image;
	}
	
	/**
	 * Methode qui definis une image
	 * @param BufferedImage image
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * Methode qui retourne l'hyperlien de l'image
	 * @return String
	 */
	public String getHyperlien() {
		return this.hyperlien;
	}
	
	/**
	 * Methode qui definis l'hyperlien de l'image
	 * @param String lien
	 */
	public void setHyperlien(String lien) {
		this.hyperlien = lien;
	}
}
