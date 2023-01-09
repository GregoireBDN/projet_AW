package arme;

import java.util.ArrayList;
import java.util.List;

import outil.Case;
import outil.Joueur;
import unite.*;

public class MitrailleuseLourde extends Arme{
	@Override
	public double efficacite(Unite u) {
		double efficacite = 0;
		if (u instanceof Infanterie) {
			efficacite = 1;
		} else if (u instanceof Dca || u instanceof Tank) {
			efficacite = 0.3;
		} else if (u instanceof Bombardier) {
			efficacite = 0.7;
		} else if (u instanceof Helicoptere) {
			efficacite = 1.1;
		}else if(u instanceof Bazooka) {
			efficacite = 0.8;
		}
		return efficacite;
	}
	
	public List<Case> enmisAPorter(Case c, Case[][] grille, Joueur joueur) {
		List<Case> casesAPorte = new ArrayList<>();
		if (c.getX() > 0) {
			ajouteLstAPorter(grille[c.getY()][c.getX() - 1], joueur, casesAPorte);
		}
		if (c.getX() < grille[0].length - 1) {
			ajouteLstAPorter(grille[c.getY()][c.getX() + 1], joueur, casesAPorte);
		}
		if (c.getY() > grille.length - 1) {
			ajouteLstAPorter(grille[c.getY() + 1][c.getX()], joueur, casesAPorte);
		}
		if (c.getY() < 0) {
			ajouteLstAPorter(grille[c.getY() - 1][c.getX()], joueur, casesAPorte);
		}
		return casesAPorte;
	}
}
