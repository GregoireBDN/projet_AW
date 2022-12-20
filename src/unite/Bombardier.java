package unite;

import arme.Bombes;
import locomoton.Aerien;

public class Bombardier extends Unite {
	public Bombardier() {
		this.a = new Bombes();
		this.deplacement = 7;
		this.locomotion = new Aerien();
	}

	@Override
	public void resetDeplacement() {
		this.deplacement = 7;
	}

	@Override
	public String toString() {
		return "Bombardier";
	}
}
