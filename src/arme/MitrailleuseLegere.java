package arme;

import unite.*;

public class MitrailleuseLegere extends Arme {
	@Override
	public double efficacite(Unite u) {
		double efficacite = 0;
		if (u instanceof Infanterie) {
			efficacite = 0.6;
		} else if (u instanceof Dca) {
			efficacite = 0.1;
		} else if (u instanceof Tank) {
			efficacite = 0.15;
		} else if (u instanceof Helicoptere) {
			efficacite = 0.3;
		}else if(u instanceof Bazooka) {
			efficacite = 0.55;
		}
		return efficacite;
	}
}
