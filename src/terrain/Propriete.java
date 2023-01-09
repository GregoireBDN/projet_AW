package terrain;

import locomotion.Locomotion;
import outil.Joueur;

public abstract class Propriete extends Terrain {
	
	Joueur joueur;
	double resistance;
	

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
	
	public abstract String getImage();
	
}
