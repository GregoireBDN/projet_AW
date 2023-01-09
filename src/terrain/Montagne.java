package terrain;

import locomotion.APied;
import locomotion.Aerien;
import locomotion.Locomotion;
import ressources.Chemins;

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
	
	
	@Override
	public String getImage() {
		return Chemins.FICHIER_MONTAGNE;
	}
	

}
