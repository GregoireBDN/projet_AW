package unite;

import arme.Bombes;
import locomoton.Aerien;
import outil.Joueur;

public class Bombardier extends Unite {
	public Bombardier() {
		this.a = new Bombes();
		this.deplacement = 7;
		this.locomotion = new Aerien();
		this.prix = EnumUnites.Bombardier.getPrix();
	}
	public Bombardier(Joueur joueur) {
		this.joueur = joueur;
		this.a = new Bombes();
		this.deplacement = 7;
		this.locomotion = new Aerien();
		this.prix = EnumUnites.Bombardier.getPrix();
	}

	@Override
	public void resetDeplacement() {
		this.deplacement = 7;
	}

	@Override
	public String toString() {
		return "Bombardier";
	}
}
