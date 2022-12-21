package unite;

import arme.MitrailleuseLegere;
import locomoton.APied;
import outil.Joueur;

public class Infanterie extends Unite {
	public Infanterie() {
		this.a = new MitrailleuseLegere();
		this.deplacement = 3;
		this.locomotion = new APied();
		this.prix = EnumUnites.Infanterie.getPrix();
	}
	public Infanterie(Joueur joueur) {
		this.joueur = joueur;
		this.a = new MitrailleuseLegere();
		this.deplacement = 3;
		this.locomotion = new APied();
		this.prix = EnumUnites.Infanterie.getPrix();
	}
	

	@Override
	public void resetDeplacement() {
		this.deplacement = 3;
	}

	@Override
	public String toString() {
		return "Infanterie";
	}
}
