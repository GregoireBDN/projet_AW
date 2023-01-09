package outil;

import java.util.List;

import ressources.Affichage;
import unite.*;

public class Attaque extends Etats {
	Unite uniteCourante;
	int curseurXMemo;
	int curseurYMemo;
	List<Case> lstCase;
	int idxLstCase;

	public Attaque(Case[][] grille, int x, int y, Unite u, Joueur joueur, boolean isOver, List<Case> lstCase) {
		this.isOver = isOver;
		this.joueur = joueur;
		this.idxLstCase = 0;
		this.curseurXMemo = x;
		this.curseurYMemo = y;
		this.uniteCourante = u;
		this.grille = grille;
		this.lstCase = lstCase;
		this.curseurY = lstCase.get(idxLstCase).getY();
		this.curseurX = lstCase.get(idxLstCase).getX();
	}
	
	public Unite getUnite() {
		return uniteCourante;
	}
	
	public void changementCible() {
		if(idxLstCase < lstCase.size() - 1) {
			idxLstCase += 1;
			curseurY = lstCase.get(idxLstCase).getY();
			curseurX = lstCase.get(idxLstCase).getX();
		}else {
			idxLstCase = 0;
			curseurY = lstCase.get(idxLstCase).getY();
			curseurX = lstCase.get(idxLstCase).getX();
		}
	}

	@Override
	public void actionGauche() {
		changementCible();
	}

	@Override
	public void actionDroit() {
		changementCible();
	}

	@Override
	public void actionBas() {
		changementCible();
	}

	@Override
	public void actionHaut() {
		changementCible();
	}

	public Etats actionEnter() {
		Etats e = this;
		if (grille[curseurY][curseurX].aUneUniteeEnemie(uniteCourante)) {
			System.out.println("Touche ENTER");
			grille[curseurY][curseurX].unite.setPv(uniteCourante.infligeDegat(grille[curseurY][curseurX].unite));
			if (!estMort(grille[curseurY][curseurX].unite)) {
				uniteCourante.setPv(grille[curseurY][curseurX].unite.infligeDegat(uniteCourante));
				estMort(uniteCourante);
			}
			if(lstUniteUtilisable().isEmpty()) {
				e = new DeplacementLibre(grille, 0, 0, joueur.getAdverse(), true, isOver);
			}else {
				e = new DeplacementLibre(grille, curseurXMemo, curseurYMemo, joueur, false, isOver);
			}
		}
		return e;
	}

	@Override
	public Etats actionEchap() {
		return new DeplacementLibre(grille, curseurX, curseurY, joueur, false, isOver);
	}

	public boolean estMort(Unite uniteCoutante) {
		if (grille[curseurY][curseurX].unite.getPv() <= 0) {
			System.out.println(grille[curseurY][curseurX].unite.toString() + "(Joueur : "
					+ grille[curseurY][curseurX].unite.getJoueur() + ")" + " : " + "Detruit");
			grille[curseurY][curseurX].setUnite(null);
			return true;
		}
		return false;
	}

	@Override
	public Etats actionT() {
		return this;
	}

	public void drawGameCursorE() {
		Affichage.dessineCurseurAttaque(getCurseurX(), getCurseurY()); // affiche le curseur en (0,0), a modifier
	}

	@Override
	public void actionN() {
	}

}
