package unite;

import arme.Bombes;
import arme.MitrailleuseLourde;
import locomoton.Aerien;
import locomoton.SurChenilles;

public class Dca extends Unite {
	public Dca() {
		this.a = new MitrailleuseLourde();
		this.deplacement = 6;
		this.locomotion = new SurChenilles();
	}

	@Override
	public void resetDeplacement() {
		this.deplacement = 6;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Dca";
	}
}
