package arme;

import java.util.ArrayList;
import java.util.List;

import outil.Case;
import outil.Joueur;
import unite.Artillerie;
import unite.Bazooka;
import unite.Dca;
import unite.Infanterie;
import unite.Tank;
import unite.Unite;

public class Mortier extends Arme {
	
	@Override
	public double efficacite(Unite u) {
		double efficacite = 0;
		if (u instanceof Bazooka) {
			efficacite = 0.5;
		} else if (u instanceof Dca || u instanceof Tank || u instanceof Artillerie) {
			efficacite = 0.7;
		}else if (u instanceof Infanterie){
			efficacite = 0.4;
		}
		return efficacite;
	}
	@Override
	public List<Case> enmisAPorter(Case c, Case[][] grille, Joueur joueur) {
		List<Case> casesAPorte = new ArrayList<>();
		
		if (c.getX() - 1 > 0) {
			ajouteLstAPorter(grille[c.getY()][c.getX() - 2], joueur, casesAPorte);
			if (c.getY()< grille.length - 1) {
				ajouteLstAPorter(grille[c.getY() + 1][c.getX()  - 2], joueur, casesAPorte);
			}
		}
		if (c.getX() - 2 > 0) {
			ajouteLstAPorter(grille[c.getY()][c.getX() - 3], joueur, casesAPorte);
		}
		if (c.getX() + 1 < grille[0].length - 1) {
			ajouteLstAPorter(grille[c.getY()][c.getX() + 2], joueur, casesAPorte);
		}
		if (c.getX() + 2 < grille[0].length - 1) {
			ajouteLstAPorter(grille[c.getY()][c.getX() + 3], joueur, casesAPorte);
		}
		if (c.getY() + 1 < grille.length - 1) {
			ajouteLstAPorter(grille[c.getY() + 2][c.getX()], joueur, casesAPorte);
		}
		
		if (c.getY() + 2 < grille.length - 1) {
			ajouteLstAPorter(grille[c.getY() + 3][c.getX()], joueur, casesAPorte);
		}
		if (c.getY() - 1 > 0) {
			ajouteLstAPorter(grille[c.getY() - 2][c.getX()], joueur, casesAPorte);
		}
		if (c.getY() - 2 > 0) {
			ajouteLstAPorter(grille[c.getY() - 3][c.getX()], joueur, casesAPorte);
		}
		return casesAPorte;
	}
}
