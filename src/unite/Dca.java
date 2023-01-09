package unite;

import arme.Bombes;
import arme.MitrailleuseLourde;
import locomotion.Aerien;
import locomotion.SurChenilles;
import outil.Joueur;
import ressources.Chemins;

public class Dca extends Unite {
	public Dca() {
		this.a = new MitrailleuseLourde();
		this.deplacement = 6;
		this.locomotion = new SurChenilles();
		this.prix = EnumUnites.Dca.getPrix();
	}
	public Dca(Joueur joueur) {
		this.joueur = joueur;
		this.a = new MitrailleuseLourde();
		this.deplacement = 6;
		this.locomotion = new SurChenilles();
		this.prix = EnumUnites.Dca.getPrix();
	}
	

	@Override
	public void resetDeplacement() {
		this.deplacement = 6;
	}

	@Override
	public String toString() {
		return "Dca";
	}
	
	@Override
	public String getImage() {
		return Chemins.FICHIER_ANTIAIR;
	}
}
