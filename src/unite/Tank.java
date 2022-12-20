package unite;

import arme.Canon;
import arme.MitrailleuseLourde;
import locomoton.SurChenilles;

public class Tank extends Unite {
	public Tank() {
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
