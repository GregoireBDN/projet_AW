package arme;

import unite.Bazooka;
import unite.Bombardier;
import unite.Dca;
import unite.Helicoptere;
import unite.Infanterie;
import unite.Tank;
import unite.Unite;

public class Missile extends Arme {
	@Override
	public double efficacite(Unite u) {
		double efficacite = 0;
		if (u instanceof Infanterie || u instanceof Bazooka) {
			efficacite = 0.5;
		} else if (u instanceof Dca) {
			efficacite = 0.4;
		} else if (u instanceof Tank || u instanceof Helicoptere || u instanceof Bombardier) {
			efficacite = 0.7;
		}
		return efficacite;
	}
}
