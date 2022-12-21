package unite;

import outil.Joueur;

public class Artillerie extends Unite {
	public Artillerie() {
	}
	public Artillerie(Joueur joueur) {
		this.joueur = joueur;
	}

	@Override
	public void resetDeplacement() {
	}

	@Override
	public String toString() {
		return "Artillerie";
	}
}
