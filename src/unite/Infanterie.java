package unite;

import arme.Missile;
import arme.MitrailleuseLegere;
import locomoton.APied;
import locomoton.Aerien;

public class Infanterie extends Unite {
	public Infanterie() {
		this.a = new MitrailleuseLegere();
		this.deplacement = 3;
		this.locomotion = new APied();
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
