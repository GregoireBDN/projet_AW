package terrain;

import locomotion.*;
import ressources.Chemins;


public abstract class Terrain {
	public abstract int CoupDeDeplacement(Locomotion l);
	public abstract String getImage();
}
