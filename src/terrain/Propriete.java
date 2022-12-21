package terrain;

import locomoton.Locomotion;

public class Propriete extends Terrain {
	
	int joueur;
	double resistance;
	
	public Propriete() {
		this.resistance = 20;
	}

	public int getJoueur() {
		return joueur;
	}

	public void setJoueur(int joueur) {
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
