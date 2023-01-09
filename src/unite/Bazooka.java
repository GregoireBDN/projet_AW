package unite;
import arme.*;
import locomotion.APied;
import outil.Joueur;
import ressources.Chemins;
public class Bazooka extends Unite {
	public Bazooka() {
		this.a = new Canon();
		this.deplacement = 2;
		this.locomotion = new APied();
		this.prix = EnumUnites.Bazouka.getPrix();
	}
	
	public Bazooka(Joueur joueur) {
		this.joueur = joueur;
		this.a = new Canon();
		this.deplacement = 2;
		this.locomotion = new APied();
		this.prix = EnumUnites.Bazouka.getPrix();
	}

	@Override
	public void resetDeplacement() {
		this.deplacement = 2;
	}

	@Override
	public String toString() {
		return "Bazooka";
	}
	
	@Override
	public String getImage() {
		return Chemins.FICHIER_BAZOOKA;
	}
	
	
}
