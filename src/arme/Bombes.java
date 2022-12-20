package arme;

import unite.Bazooka;
import unite.*;
import unite.Unite;

public class Bombes extends Arme {

	@Override
	public double efficacite(Unite u) {
		double efficacite = 0;
		if(u instanceof Infanterie || u instanceof Bazooka || u instanceof Tank){
			efficacite = 1;
		}else if(u instanceof Dca) {
			efficacite = 0.7;
		}
		return efficacite;
	}

}
