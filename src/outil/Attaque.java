package outil;

import java.util.List;

import ressources.Affichage;
import unite.*;

public class Attaque extends Etats {
	Unite uniteCourante;
	int curseurXMemo;
	int curseurYMemo;
	List<Case> lstCase;
	int IndCaseCourante;

	public Attaque(Case[][] grille, int x, int y, Unite u, Joueur joueur, boolean isOver, List<Case> lstCase) {
		this.isOver = isOver;
//		this.joueur = joueur;
		this.IndCaseCourante = 0;
		this.curseurXMemo = x;
		this.curseurYMemo = y;
		this.uniteCourante = u;
		this.grille = grille;
		this.lstCase = lstCase;
		this.curseurY = lstCase.get(IndCaseCourante).getY();
		this.curseurX = lstCase.get(IndCaseCourante).getX();;
		;
	}
	
	public Unite getUnite() {
		return uniteCourante;
	}
	
	public void changementCible() {
		if(lstCase.size() < IndCaseCourante) {
			IndCaseCourante += 1;
			curseurY = lstCase.get(IndCaseCourante).getY();
			curseurX = lstCase.get(IndCaseCourante).getX();
		}else {
			IndCaseCourante = 0;
			curseurY = lstCase.get(IndCaseCourante).getY();
			curseurX = lstCase.get(IndCaseCourante).getX();
		}
	}

	@Override
	public void actionGauche() {
//		if (curseurX > 0 && curseurX >= curseurXMemo && curseurY == curseurYMemo) {
//			curseurX--;
//		}
		changementCible();
	}

	@Override
	public void actionDroit() {
//		if (curseurX < grille[0].length - 1 && curseurX <= curseurXMemo && curseurY == curseurYMemo) {
//			curseurX++;
//		} 
		changementCible();
	}

	@Override
	public void actionBas() {
//		if (curseurY > 0 && curseurY >= curseurYMemo && curseurX == curseurXMemo) {
//			curseurY--;
//		}
		changementCible();
	}

	@Override
	public void actionHaut() {
//		if (curseurY < grille.length - 1 && curseurY <= curseurYMemo && curseurX == curseurXMemo) {
//			curseurY++;
//		}
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
			e = new DeplacementLibre(grille, curseurXMemo, curseurYMemo, joueur, false, false);
		}
		return e;
	}

	@Override
	public Etats actionEchap() {
		return new DeplacementLibre(grille, curseurX, curseurY, joueur, false, false);
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
