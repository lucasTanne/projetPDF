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
	 * Methode get de la proprietee X
	 * @return X
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Methode get de la proprietee Y
	 * @return Y
	 */
	public int getY() {
		return this.y;
	}
}
