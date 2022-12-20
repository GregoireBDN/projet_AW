package arme;

import unite.*;

public class MitrailleuseLourde extends Arme{
	@Override
	public double efficacite(Unite u) {
		double efficacite = 0;
		if (u instanceof Infanterie) {
			efficacite = 1;
		} else if (u instanceof Dca || u instanceof Tank) {
			efficacite = 0.3;
		} else if (u instanceof Bombardier) {
			efficacite = 0.7;
		} else if (u instanceof Helicoptere) {
			efficacite = 1.1;
		}else if(u instanceof Bazooka) {
			efficacite = 0.8;
		}
		return efficacite;
	}
}
