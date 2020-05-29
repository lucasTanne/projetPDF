package donnees;

public abstract class Bloc {
	private double x;
	private double y;
	
	/**
	 * Constructeur d'un bloc
	 * @param x
	 * @param y
	 */
	public Bloc(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Methode get de la proprietee X
	 * @return X
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Methode get de la proprietee Y
	 * @return Y
	 */
	public double getY() {
		return this.y;
	}
}
