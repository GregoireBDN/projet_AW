package unite;

import arme.Missile;
import arme.MitrailleuseLourde;
import locomoton.Aerien;
import locomoton.SurChenilles;

public class Helicoptere extends Unite {
	public Helicoptere() {
		this.a = new Missile();
		this.deplacement = 6;
		this.locomotion = new Aerien();
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
