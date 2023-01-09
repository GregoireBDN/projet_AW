package terrain;

import ressources.Chemins;

public class Ville extends Propriete {
	@Override
	public String getImage() {
		return Chemins.FICHIER_VILLE;
	}
	
}
