package outil;

import locomoton.*;
import ressources.*;
import terrain.Propriete;
import terrain.Qg;
import unite.*;

public class ChoisirChemin extends Etats {
	Unite uniteCourante;
	int curseurXMemo;
	int curseurYMemo;

	public ChoisirChemin(Case[][] grille, int x, int y, Unite u, Joueur joueur, boolean isOver) {
		this.isOver = isOver;
		this.joueur = joueur;
		this.uniteCourante = u;
		this.curseurX = x;
		this.curseurY = y;
		this.curseurXMemo = x;
		this.curseurYMemo = y;
		this.grille = grille;
		uniteCourante.setUtiliser(true);
		grille[curseurY][curseurX].setFleche(new ElementFleche("begin", "end", null));
		System.out.println("Choisie le Chemin : " + uniteCourante.toString() + "(Joueur : " + uniteCourante.getJoueur()
				+ ")" + "\n PV : " + uniteCourante.getPv() + "\n Deplacement : " + uniteCourante.getDeplacement());
	}

	@Override
	public void actionGauche() {
		if (curseurX > 0 && grille[curseurY][curseurX - 1].estAccecible(uniteCourante)) {
			if (!grille[curseurY][curseurX].fleche.getDepart().equals("left")) {
				grille[curseurY][curseurX].fleche.setArriver("left");
			}
			grille[curseurY][curseurX - 1].getFleche().setPrecedent(grille[curseurY][curseurX].getFleche());
			curseurX--;
			grille[curseurY][curseurX].decreDeplacement(uniteCourante);
			grille[curseurY][curseurX].fleche.setDepart("right");
			grille[curseurY][curseurX].fleche.setArriver("end");
			System.out.println("Deplacement restant : " + uniteCourante.getDeplacement());
		} else if (grille[curseurY][curseurX].getFleche().getPrecedent()
				.equals(grille[curseurY][curseurX - 1].getFleche())) {
			grille[curseurY][curseurX].setFleche(new ElementFleche());
			curseurX--;
			grille[curseurY][curseurX].inecDeplacement(uniteCourante);
			grille[curseurY][curseurX].getFleche().setArriver("end");
		}
	}

	@Override
	public void actionDroit() {
		if (curseurX < grille[0].length - 1 && grille[curseurY][curseurX + 1].estAccecible(uniteCourante)) {
			if (!grille[curseurY][curseurX].fleche.getDepart().equals("right")) {
				grille[curseurY][curseurX].fleche.setArriver("right");
			}
			grille[curseurY][curseurX + 1].getFleche().setPrecedent(grille[curseurY][curseurX].getFleche());
			curseurX++;
			grille[curseurY][curseurX].decreDeplacement(uniteCourante);
			grille[curseurY][curseurX].fleche.setArriver("end");
			grille[curseurY][curseurX].fleche.setDepart("left");
			System.out.println("Deplacement restant : " + uniteCourante.getDeplacement());
		} else if (grille[curseurY][curseurX].getFleche().getPrecedent()
				.equals(grille[curseurY][curseurX + 1].getFleche())) {
			grille[curseurY][curseurX].setFleche(new ElementFleche());
			curseurX++;
			grille[curseurY][curseurX].inecDeplacement(uniteCourante);
			grille[curseurY][curseurX].getFleche().setArriver("end");
		}
	}

	@Override
	public void actionBas() {
		if (curseurY > 0 && grille[curseurY - 1][curseurX].estAccecible(uniteCourante)) {
			if (!grille[curseurY][curseurX].fleche.getDepart().equals("down")) {
				grille[curseurY][curseurX].fleche.setArriver("down");
			}
			grille[curseurY - 1][curseurX].getFleche().setPrecedent(grille[curseurY][curseurX].getFleche());
			curseurY--;
			grille[curseurY][curseurX].decreDeplacement(uniteCourante);
			grille[curseurY][curseurX].fleche.setDepart("up");
			grille[curseurY][curseurX].fleche.setArriver("end");
			System.out.println("Deplacement restant : " + uniteCourante.getDeplacement());
		} else if (grille[curseurY][curseurX].getFleche().getPrecedent()
				.equals(grille[curseurY - 1][curseurX].getFleche())) {
			grille[curseurY][curseurX].setFleche(new ElementFleche());
			curseurY--;
			grille[curseurY][curseurX].inecDeplacement(uniteCourante);
			grille[curseurY][curseurX].getFleche().setArriver("end");
		}
	}

	@Override
	public void actionHaut() {
		if (curseurY < grille.length - 1 && grille[curseurY + 1][curseurX].estAccecible(uniteCourante)) {
			System.out.println("Touche HAUT");
			if (!grille[curseurY][curseurX].fleche.getDepart().equals("up")) {
				grille[curseurY][curseurX].fleche.setArriver("up");
			}
			grille[curseurY + 1][curseurX].getFleche().setPrecedent(grille[curseurY][curseurX].getFleche());
			curseurY++;
			grille[curseurY][curseurX].decreDeplacement(uniteCourante);
			grille[curseurY][curseurX].fleche.setDepart("down");
			grille[curseurY][curseurX].fleche.setArriver("end");
			System.out.println("Deplacement restant : " + uniteCourante.getDeplacement());
		} else if (grille[curseurY][curseurX].getFleche().getPrecedent()
				.equals(grille[curseurY + 1][curseurX].getFleche())) {
			grille[curseurY][curseurX].setFleche(new ElementFleche());
			curseurY++;
			grille[curseurY][curseurX].inecDeplacement(uniteCourante);
			grille[curseurY][curseurX].getFleche().setArriver("end");
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
		uniteCourante.resetUnite();
		return new DeplacementLibre(grille, curseurXMemo, curseurYMemo, joueur, false, isOver);
	}

	@Override
	public Etats actionEnter() {
		Etats e = null;
		if (!grille[curseurY][curseurX].aUneUnite()
				|| grille[curseurY][curseurX].equals(grille[curseurYMemo][curseurXMemo])) {
			if (ennemisAPorter()) {
				uniteCourante.ajoutAction(ActionsUnites.attaque.name());
			}
			if (actionCapture()) {
				uniteCourante.ajoutAction(ActionsUnites.capture.name());
			}
			grille[curseurYMemo][curseurXMemo].setUnite(null);
			grille[curseurY][curseurX].setUnite(uniteCourante);
			supprimeFlecheComplete();
			int action = Affichage.popup("Choisire une action", grille[curseurY][curseurX].unite.getActions(), true, 0);
			String[] actionsCourante = uniteCourante.getActions();

			if (action == -1) {
				uniteCourante.resetUnite();
				grille[curseurYMemo][curseurXMemo].setUnite(uniteCourante);
				grille[curseurY][curseurX].setUnite(null);
				e = new DeplacementLibre(grille, curseurXMemo, curseurYMemo, joueur, false, isOver);
			} else if (action > 0) {
				if (actionsCourante[action].equals(ActionsUnites.attaque.name())) {
					e = new Attaque(grille, curseurX, curseurY, uniteCourante, joueur, isOver);
				} else {
					Propriete propriete = grille[curseurY][curseurX].getTerrain() instanceof Propriete
							? (Propriete) grille[curseurY][curseurX].getTerrain()
							: null;
					propriete.setResistance(uniteCourante.captureProp(propriete));
					if (propriete.getResistance() <= 0) {
						propriete.setJoueur(uniteCourante.getJoueur());
						if (propriete instanceof Qg) {
							isOver = true;
							System.out.println("Partie terminé! Le Joueur " + joueur.getIndiceJoueur() + " à gagné!");
						}
					}
					e = new DeplacementLibre(grille, curseurX, curseurY, joueur, false, isOver);
				}

			} else {
				e = new DeplacementLibre(grille, curseurX, curseurY, joueur, false, isOver);
			}
		} else {
			e = this;
		}
		return e;
	}

	public boolean actionCapture() {
		Propriete propriete = grille[curseurY][curseurX].getTerrain() instanceof Propriete
				? (Propriete) grille[curseurY][curseurX].getTerrain()
				: null;
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

	@Override
	public Etats actionT() {
		return this;
	}

}
