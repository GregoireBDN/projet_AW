package arme;

import unite.*;

public class Canon extends Arme{

	public double efficacite(Unite u) {
		double efficacite = 0;
		if(u instanceof Infanterie || u instanceof Bazooka){
			efficacite = 0.45;
		}else if(u instanceof Dca) {
			efficacite = 0.6;
		}else if(u instanceof Tank) {
			efficacite = 0.55;
		}else if(u instanceof Helicoptere) {
			efficacite = 0.3;
		}
		return efficacite;
	}
	
}
