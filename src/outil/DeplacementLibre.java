package outil;

import ressources.*;

public class DeplacementLibre extends Etats {

	public DeplacementLibre(Case[][] grille, int x, int y) {
		this.curseurX = x;
		this.curseurY = y;
		this.grille = grille;
		System.out.println("Deplacement libre");
	}

	@Override
	public void actionGauche() {
		if (curseurX > 0) {
			System.out.println("Touche GAUCHE");
			curseurX--;
		}
	}

	@Override
	public void actionDroit() {
		if (curseurX < grille[0].length - 1) {
			System.out.println("Touche DROITE");
			curseurX++;
		}
	}

	@Override
	public void actionBas() {
		if (curseurY > 0) {
			System.out.println("Touche BAS");
			curseurY--;
		}

	}

	@Override
	public void actionHaut() {
		if (curseurY < grille.length - 1) {
			System.out.println("Touche HAUT");
			curseurY++;
		}
	}

	public Etats actionEnter() {
		Etats e = this;
		if (grille[curseurY][curseurX].unite != null) {
			System.out.println("Touche ENTER");
			e = new ChoisirChemin(grille,curseurX, curseurY, grille[curseurY][curseurX].unite);
		}
		return e;
	}

	@Override
	public Etats actionEchap() {
		return new DeplacementLibre(grille, curseurX, curseurY);
	}

	

}
