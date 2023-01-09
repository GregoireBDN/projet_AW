package arme;

import unite.Bazooka;

import java.util.ArrayList;
import java.util.List;

import outil.Case;
import outil.Joueur;
import unite.*;
import unite.Unite;

public class Bombes extends Arme {

	@Override
	public double efficacite(Unite u) {
		double efficacite = 0;
		if (u instanceof Infanterie || u instanceof Bazooka || u instanceof Tank) {
			efficacite = 1;
		} else if (u instanceof Dca) {
			efficacite = 0.7;
		}
		return efficacite;
	}
	@Override
	public List<Case> enmisAPorter(Case caseCourante, Case[][] grille, Joueur joueur) {
		List<Case> casesAPorte = new ArrayList<>();
		if (caseCourante.getX() > 0) {
			ajouteLstAPorter(grille[caseCourante.getY()][caseCourante.getX() - 1], joueur, casesAPorte);
		}
		if (caseCourante.getX() < grille[0].length - 1) {
			ajouteLstAPorter(grille[caseCourante.getY()][caseCourante.getX() + 1], joueur, casesAPorte);
		}
		if (caseCourante.getY() < grille.length - 1) {
			ajouteLstAPorter(grille[caseCourante.getY() + 1][caseCourante.getX()], joueur, casesAPorte);
		}
		if (caseCourante.getY() > 0) {
			ajouteLstAPorter(grille[caseCourante.getY() - 1][caseCourante.getX()], joueur, casesAPorte);
		}
		return casesAPorte;
	}
}
