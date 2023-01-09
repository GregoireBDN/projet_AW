package terrain;

import locomotion.Aerien;
import locomotion.Locomotion;
import ressources.Chemins;

public class Eau extends Terrain{

	@Override
	public int CoupDeDeplacement(Locomotion l) {
		int cout = -1;
		if(l instanceof Aerien) {
			cout = 1;
		}return cout;
	}

	@Override
	public String getImage() {
		return Chemins.FICHIER_EAU;
	}
	
	
}
