package terrain;

import locomoton.APied;
import locomoton.Aerien;
import locomoton.Locomotion;

public class Montagne extends Terrain {

	@Override
	public int CoupDeDeplacement(Locomotion l) {
		int cout = -1;
		if(l instanceof APied) {
			cout = 2;
		}else if(l instanceof Aerien) {
			cout = 1;
		}
		return cout;
	}

}
