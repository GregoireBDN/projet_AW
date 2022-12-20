package terrain;

import locomoton.Locomotion;
import locomoton.SurChenilles;

public class Foret extends Terrain {

	@Override
	public int CoupDeDeplacement(Locomotion l) {
		int cout = -1;
		if(l instanceof SurChenilles) {
			cout = 2;
		}else {
			cout = 1;
		}
		return cout;
	}

}
