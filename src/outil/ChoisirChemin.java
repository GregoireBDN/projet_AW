package outil;

import locomoton.*;
import ressources.*;
import terrain.Propriete;
import unite.*;

public class ChoisirChemin extends Etats {
	Unite uniteCourante;
	int curseurXMemo;
	int curseurYMemo;

	public ChoisirChemin(Case[][] grille, int x, int y, Unite u) {
		this.uniteCourante = u;
		this.curseurX = x;
		this.curseurY = y;
		this.curseurXMemo = x;
		this.curseurYMemo = y;
		this.grille = grille;
		grille[curseurY][curseurX].setFleche(new Fleche("begin", "end"));
		System.out.println("Choisie le Chemin : " + uniteCourante.toString() + "(Joueur : " + uniteCourante.getJoueur()
				+ ")" + "\n PV : " + uniteCourante.getPv() + "\n Deplacement : " + uniteCourante.getDeplacement());
	}

	@Override
	public void actionGauche() {
		if (curseurX > 0 && grille[curseurY][curseurX - 1].estAccecible(uniteCourante)) {
			if (!grille[curseurY][curseurX].fleche.getDepart().equals("left")) {
				grille[curseurY][curseurX].fleche.setArriver("left");
			}
			curseurX--;
			grille[curseurY][curseurX].incrDeplacement(uniteCourante);
			grille[curseurY][curseurX].fleche.setDepart("right");
			grille[curseurY][curseurX].fleche.setArriver("end");
			System.out.println("Deplacement restant : " + uniteCourante.getDeplacement());
		}
	}

	@Override
	public void actionDroit() {
		if (curseurX < grille[0].length - 1 && grille[curseurY][curseurX + 1].estAccecible(uniteCourante)) {
			if (!grille[curseurY][curseurX].fleche.getDepart().equals("right")) {
				grille[curseurY][curseurX].fleche.setArriver("right");
			}
			curseurX++;
			grille[curseurY][curseurX].incrDeplacement(uniteCourante);
			grille[curseurY][curseurX].fleche.setArriver("end");
			grille[curseurY][curseurX].fleche.setDepart("left");
			System.out.println("Deplacement restant : " + uniteCourante.getDeplacement());
		}
	}

	@Override
	public void actionBas() {
		if (curseurY > 0 && grille[curseurY - 1][curseurX].estAccecible(uniteCourante)) {
			if (!grille[curseurY][curseurX].fleche.getDepart().equals("down")) {
				grille[curseurY][curseurX].fleche.setArriver("down");
			}
			;
			curseurY--;
			grille[curseurY][curseurX].incrDeplacement(uniteCourante);
			grille[curseurY][curseurX].fleche.setDepart("up");
			grille[curseurY][curseurX].fleche.setArriver("end");
			System.out.println("Deplacement restant : " + uniteCourante.getDeplacement());
		}
	}

	@Override
	public void actionHaut() {
		if (curseurY < grille.length - 1 && grille[curseurY + 1][curseurX].estAccecible(uniteCourante)) {
			System.out.println("Touche HAUT");
			if (!grille[curseurY][curseurX].fleche.getDepart().equals("up")) {
				grille[curseurY][curseurX].fleche.setArriver("up");
			}
			curseurY++;
			grille[curseurY][curseurX].incrDeplacement(uniteCourante);
			grille[curseurY][curseurX].fleche.setDepart("down");
			grille[curseurY][curseurX].fleche.setArriver("end");
			System.out.println("Deplacement restant : " + uniteCourante.getDeplacement());
		}
	}

	public void supprimeFlecheComplete() {
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				grille[i][j].suprimeFleche();
			}
		}
	}

	@Override
	public Etats actionEchap() {
		supprimeFlecheComplete();
		uniteCourante.resetDeplacement();
		return new DeplacementLibre(grille, curseurXMemo, curseurYMemo);
	}

	@Override
	public Etats actionEnter() {
		Etats e = null;
		if (!grille[curseurY][curseurX].aUneUnite()
				|| grille[curseurY][curseurX].equals(grille[curseurYMemo][curseurXMemo])) {
			if (ennemisAPorter()) {
				uniteCourante.ajoutAction(ActionsUnites.attaque.name());
			}if(actionCapture()) {
				uniteCourante.ajoutAction(ActionsUnites.capture.name());
			}
			grille[curseurYMemo][curseurXMemo].setUnite(null);
			grille[curseurY][curseurX].setUnite(uniteCourante);
			supprimeFlecheComplete();
			int action = Affichage.popup("Choisire une action", grille[curseurY][curseurX].unite.getActions(), true, 0);
			String[] actionsCourante = uniteCourante.getActions();
			
			if (action == -1) {
				grille[curseurYMemo][curseurXMemo].setUnite(uniteCourante);
				grille[curseurY][curseurX].setUnite(null);
				e = new DeplacementLibre(grille, curseurXMemo, curseurYMemo);
			} else if (action > 0) {
				if(actionsCourante[action].equals(ActionsUnites.attaque.name())) {
					e = new Attaque(grille, curseurX, curseurY, uniteCourante);
				}else {
					//ToDo gérer action capture
				}
				
			} else {
				e = new DeplacementLibre(grille, curseurX, curseurY);
			}
			uniteCourante.rstAction();
			uniteCourante.resetDeplacement();
		} else {
			e = this;
		}
		return e;
	}
	
	public boolean actionCapture() {
		Propriete propriete= grille[curseurY][curseurX].getTerrain() instanceof Propriete? (Propriete)grille[curseurY][curseurX].getTerrain():null ;
		return uniteCourante.getLocomotion() instanceof APied && propriete != null
				&& propriete.getJoueur() != uniteCourante.getJoueur();
	}

	public boolean ennemisAPorter() {
		return curseurY > 0 && grille[curseurY - 1][curseurX].aUneUniteeEnemie(uniteCourante)
				|| curseurY < grille.length - 1 && grille[curseurY + 1][curseurX].aUneUniteeEnemie(uniteCourante)
				|| curseurX > 0 && grille[curseurY][curseurX - 1].aUneUniteeEnemie(uniteCourante)
				|| curseurX < grille[curseurY].length - 1
						&& grille[curseurY][curseurX + 1].aUneUniteeEnemie(uniteCourante);
	}

}
