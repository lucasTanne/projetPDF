package donnees;

public abstract class Bloc {
	private int x;
	private int y;
	
	/**
	 * Constructeur d'un bloc
	 * @param x
	 * @param y
	 */
	public Bloc(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Méthode get de la propriétée X
	 * @return X
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Méthode get de la propriétée Y
	 * @return Y
	 */
	public int getY() {
		return this.y;
	}
}
