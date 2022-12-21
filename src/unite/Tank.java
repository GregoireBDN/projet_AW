package unite;

import arme.Canon;
import arme.MitrailleuseLourde;
import locomoton.SurChenilles;
import outil.Joueur;

public class Tank extends Unite {
	public Tank() {
		this.a = new Canon();
		this.deplacement = 6;
		this.locomotion = new SurChenilles();
	}
	public Tank(Joueur joueur) {
		this.joueur = joueur;
		this.a = new Canon();
		this.deplacement = 6;
		this.locomotion = new SurChenilles();
	}
	

	@Override
	public void resetDeplacement() {
		this.deplacement = 6;
	}

	@Override
	public String toString() {
		return "Tank";
	}
}
