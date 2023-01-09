package terrain;

import locomotion.Locomotion;
import locomotion.SurChenilles;
import ressources.Chemins;

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
	
	@Override
	public String getImage() {
		return Chemins.FICHIER_FORET;
	}
	

}
