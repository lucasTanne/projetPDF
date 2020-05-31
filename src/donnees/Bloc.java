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
	 * Methode get de la proprietee x
	 * @return x
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Methode get de la proprietee y
	 * @return y
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Methode set de la proprietee x
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Methode set de la proprietee Y
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	
}
