package donnees;

import java.util.ArrayList;

public class Page {
	private ArrayList<Bloc> lesBlocs;
	
	/**
	 * Constructeur d'une page
	 */
	public Page() {
		this.lesBlocs = new ArrayList<Bloc>();
	}
	
	/**
	 * Méthode qui retourne une liste de blocs
	 * @return ArrayList<Bloc>
	 */
	public ArrayList<Bloc> getLesBlocs() {
		return this.lesBlocs;
	}
	
	/**
	 * Méthode qui ajoute un bloc dans la liste de bloc
	 * @param Bloc bloc
	 */
	public void ajouterBloc(Bloc bloc) {
		this.lesBlocs.add(bloc);
	}
	
	/**
	 * Méthode qui supprime un bloc dans la liste de bloc
	 * @param int index
	 */
	public void supprimerBloc(int index) {
		this.lesBlocs.remove(index);
	}
}
