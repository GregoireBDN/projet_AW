package unite;
import arme.*;
import locomoton.APied;
public class Bazooka extends Unite {
	public Bazooka() {
		this.a = new Canon();
		this.deplacement = 2;
		this.locomotion = new APied();
	}

	@Override
	public void resetDeplacement() {
		this.deplacement = 2;
	}

	@Override
	public String toString() {
		return "Bazooka";
	}
	
	
}
