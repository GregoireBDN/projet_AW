package unite;

import arme.Canon;
import arme.Mortier;
import locomotion.APied;
import locomotion.SurChenilles;
import outil.Joueur;
import ressources.Chemins;

public class Artillerie extends Unite {
	public Artillerie() {
		this.a = new Mortier();
		this.deplacement = 5;
		this.locomotion = new SurChenilles();
		this.prix = EnumUnites.Artillerie.getPrix();
	}
	public Artillerie(Joueur joueur) {
		this.joueur = joueur;
		this.a = new Mortier();
		this.deplacement = 5;
		this.locomotion = new SurChenilles();
		this.prix = EnumUnites.Artillerie.getPrix();
	}

	@Override
	public void resetDeplacement() {
		deplacement = 5;
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
