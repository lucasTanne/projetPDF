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
	 * M�thode get de la propri�t�e X
	 * @return X
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * M�thode get de la propri�t�e Y
	 * @return Y
	 */
	public int getY() {
		return this.y;
	}
}
