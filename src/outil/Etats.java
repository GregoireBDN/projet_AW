package outil;

import terrain.Propriete;

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
	
	public abstract Etats  actionEchap();
	
	public abstract Etats actionT();

	public int getCurseurX() {
		return curseurX;
	}

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
	
	
}
