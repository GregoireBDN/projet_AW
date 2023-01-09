package outil;

import java.util.ArrayList;
import java.util.List;

import ressources.Affichage;
import terrain.Propriete;
import unite.Unite;

public abstract class Etats {
	int curseurX;
	int curseurY;
	Joueur joueur;
	Case[][] grille;
	boolean isOver;

	public abstract void actionGauche();

	public abstract void actionDroit();

	public abstract void actionBas();

	public abstract void actionHaut();

	public abstract Etats actionEnter();

	public abstract Etats actionEchap();

	public abstract Etats actionT();

	public abstract void actionN();

	public abstract Unite getUnite();

	public int getCurseurX() {
		return curseurX;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public abstract void drawGameCursorE();

	public void setCurseurX(int curseurX) {
		this.curseurX = curseurX;
	}

	public int getCurseurY() {
		return curseurY;
	}

	public boolean isOver() {
		return this.isOver;
	}

	public void setCurseurY(int curseurY) {
		this.curseurY = curseurY;
	}

	public int calculeCredit() {
		int creditSup = 0;
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				if (grille[i][j].terrain instanceof Propriete) {
					Propriete p = (Propriete) grille[i][j].terrain;
					if (p.getJoueur().equals(joueur)) {
						creditSup += 1000;
					}
				}
			}
		}
		return joueur.getCredit() + creditSup;
	}

	public void resetUnites() {
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				if (grille[i][j].unite != null) {
					grille[i][j].unite.resetUnite();
				}
			}
		}
	}

	public List<Case> lstUniteUtilisable() {
		List<Case> lst = new ArrayList<>();
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				if (grille[i][j].aUneUnite() && grille[i][j].getUnite().getJoueur().equals(joueur)
						&& !grille[i][j].getUnite().isUtiliser()) {
					lst.add(grille[i][j]);
				}
			}
		}
		return lst;
	}


}
