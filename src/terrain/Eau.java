package terrain;

import locomoton.Aerien;
import locomoton.Locomotion;

public class Eau extends Terrain{

	@Override
	public int CoupDeDeplacement(Locomotion l) {
		int cout = -1;
		if(l instanceof Aerien) {
			cout = 1;
		}return cout;
	}
}
