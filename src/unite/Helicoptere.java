package unite;

import arme.Missile;
import arme.MitrailleuseLourde;
import locomoton.Aerien;
import locomoton.SurChenilles;
import outil.Joueur;

public class Helicoptere extends Unite {
	public Helicoptere() {
		this.a = new Missile();
		this.deplacement = 6;
		this.locomotion = new Aerien();
		this.prix = EnumUnites.Helicoptere.getPrix();
	}
	public Helicoptere(Joueur joueur) {
		this.joueur = joueur;
		this.a = new Missile();
		this.deplacement = 6;
		this.locomotion = new Aerien();
		this.prix = EnumUnites.Helicoptere.getPrix();
	}
	

	@Override
	public void resetDeplacement() {
		this.deplacement = 6;
	}

	@Override
	public String toString() {
		return 	"Helicoptere";
	}
}
