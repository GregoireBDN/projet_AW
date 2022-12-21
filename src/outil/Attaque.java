package outil;

import unite.*;

public class Attaque extends Etats {
	Unite uniteCourante;
	int curseurXMemo;
	int curseurYMemo;

	public Attaque(Case[][] grille, int x, int y, Unite u) {
		this.curseurX = x;
		this.curseurY = y;
		this.curseurXMemo = x;
		this.curseurYMemo = y;
		this.uniteCourante = u;
		this.grille = grille;
		System.out.println("Attaque");
	}

	@Override
	public void actionGauche() {
		if (curseurX > 0 && curseurX >= curseurXMemo) {
			System.out.println("Touche GAUCHE");
			curseurX--;
		}
	}

	@Override
	public void actionDroit() {
		if (curseurX < grille[0].length - 1 && curseurX <= curseurXMemo) {
			System.out.println("Touche DROITE");
			curseurX++;
		} else {
			System.out.println("Case inaccesible");
		}
	}

	@Override
	public void actionBas() {
		if (curseurY > 0 && curseurY >= curseurYMemo) {
			System.out.println("Touche BAS");
			curseurY--;
		}

	}

	@Override
	public void actionHaut() {
		if (curseurY < grille.length - 1 && curseurY <= curseurYMemo) {
			System.out.println("Touche HAUT");
			curseurY++;
		}
	}

	public Etats actionEnter() {
		Etats e = this;
		if (grille[curseurY][curseurX].aUneUniteeEnemie(uniteCourante)) {
			System.out.println("Touche ENTER");
			grille[curseurY][curseurX].unite.setPv(uniteCourante.infligeDegat(grille[curseurY][curseurX].unite));
			if (!estMort(grille[curseurY][curseurX].unite)) {
				System.out.println(grille[curseurY][curseurX].unite.toString() + "(Joueur : "
						+ grille[curseurY][curseurX].unite.getJoueur() + ")" + " : "
						+ grille[curseurY][curseurX].unite.getPv());
				uniteCourante.setPv(grille[curseurY][curseurX].unite.infligeDegat(uniteCourante));
				estMort(uniteCourante);
			}
			e = new DeplacementLibre(grille, curseurXMemo, curseurYMemo);
		}
		return e;
	}

	@Override
	public Etats actionEchap() {
		return new DeplacementLibre(grille, curseurX, curseurY);
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
}
