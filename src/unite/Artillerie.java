package unite;

import outil.Joueur;
import ressources.Chemins;

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
	
	@Override
	public String getImage() {
		return Chemins.FICHIER_ARTILLERIE;
	}
}
