package arme;

import java.util.List;

import outil.Case;
import outil.Joueur;
import unite.Unite;

public abstract class Arme {
	public abstract double efficacite(Unite u);
	public abstract List<Case> enmisAPorter(Case c, Case[][] grille, Joueur joueur);
	
	public void ajouteLstAPorter(Case c, Joueur j, List<Case> lstAporter) {
		if(c.aUneUnite() && c.getUnite().getJoueur() != j) {
			lstAporter.add(c);
		}
	}
}
