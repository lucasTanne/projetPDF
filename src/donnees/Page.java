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
	 * M�thode qui retourne une liste de blocs
	 * @return ArrayList<Bloc>
	 */
	public ArrayList<Bloc> getLesBlocs() {
		return this.lesBlocs;
	}
	
	/**
	 * M�thode qui ajoute un bloc dans la liste de bloc
	 * @param Bloc bloc
	 */
	public void ajouterBloc(Bloc bloc) {
		this.lesBlocs.add(bloc);
	}
	
	/**
	 * M�thode qui supprime un bloc dans la liste de bloc
	 * @param int index
	 */
	public void supprimerBloc(int index) {
		this.lesBlocs.remove(index);
	}
}
