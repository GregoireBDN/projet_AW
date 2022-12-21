package terrain;

import locomoton.Locomotion;
import outil.Joueur;

public class Propriete extends Terrain {
	
	Joueur joueur;
	double resistance;
	
	public Propriete() {
		this.resistance = 20;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	@Override
	public int CoupDeDeplacement(Locomotion l) {
		return 1;
	}

	public double getResistance() {
		return resistance;
	}

	public void setResistance(double resistance) {
		this.resistance = resistance;
	}
	
}
