package terrain;

import locomoton.Locomotion;

public class Plaine extends Terrain{

	@Override
	public int CoupDeDeplacement(Locomotion l) {
		return 1;
	}

}
