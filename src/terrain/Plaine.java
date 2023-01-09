package terrain;

import locomotion.Locomotion;
import ressources.Chemins;

public class Plaine extends Terrain{

	@Override
	public int CoupDeDeplacement(Locomotion l) {
		return 1;
	}
	
	@Override
	public String getImage() {
		return Chemins.FICHIER_PLAINE;
	}
	

}
