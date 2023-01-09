package outil;

import java.util.ArrayList;
import java.util.List;

import locomotion.*;
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
		for (Case c : deplacementPossible()) {
			Affichage.dessineRectangDansCase(c.x, y);
		}
	}

	public Unite getUnite() {
		return uniteCourante;
	}

	public void action(Case courant, Case courantSuivant, directionFleche d) {
		if (courantSuivant.estAccecible(uniteCourante)) {
			if (!courant.fleche.getDepart().equals(d.name())) {
				courant.fleche.setArriver(d.name());
			}
			courantSuivant.getFleche().setPrecedent(courant.getFleche());
			courant.setX(courant.getX() - 1);
			;
			courant.decreDeplacement(uniteCourante);
			courant.fleche.setDepart(d.oppose);
			courant.fleche.setArriver("end");
		} else if (courant.getFleche().getPrecedent().equals(courantSuivant.getFleche())) {
			courant.setFleche(new ElementFleche());
			courant.setX(courant.getX() - 1);
			;
			;
			courant.inecDeplacement(uniteCourante);
			courant.getFleche().setArriver("end");
		}
	}

	@Override
	public void actionGauche() {
		if (controlPosCursMinMaxX()) {
			if (grille[curseurY][curseurX - 1].estAccecible(uniteCourante)) {
				if (!grille[curseurY][curseurX].fleche.getDepart().equals("left")) {
					grille[curseurY][curseurX].fleche.setArriver("left");
				}
				grille[curseurY][curseurX - 1].getFleche().setPrecedent(grille[curseurY][curseurX].getFleche());
				curseurX--;
				grille[curseurY][curseurX].decreDeplacement(uniteCourante);
				grille[curseurY][curseurX].fleche.setDepart("right");
				grille[curseurY][curseurX].fleche.setArriver("end");
			} else if (grille[curseurY][curseurX].getFleche().getPrecedent()
					.equals(grille[curseurY][curseurX - 1].getFleche())) {
				grille[curseurY][curseurX].setFleche(new ElementFleche());
				curseurX--;
				grille[curseurY][curseurX].inecDeplacement(uniteCourante);
				grille[curseurY][curseurX].getFleche().setArriver("end");
			}
		}
	}

	@Override
	public void actionDroit() {
		if (controlPosCursMinMaxX()) {
			if (controlPosCursMinMaxX() && grille[curseurY][curseurX + 1].estAccecible(uniteCourante)) {
				if (!grille[curseurY][curseurX].fleche.getDepart().equals("right")) {
					grille[curseurY][curseurX].fleche.setArriver("right");
				}
				grille[curseurY][curseurX + 1].getFleche().setPrecedent(grille[curseurY][curseurX].getFleche());
				curseurX++;
				grille[curseurY][curseurX].decreDeplacement(uniteCourante);
				grille[curseurY][curseurX].fleche.setArriver("end");
				grille[curseurY][curseurX].fleche.setDepart("left");
			} else if (grille[curseurY][curseurX].getFleche().getPrecedent()
					.equals(grille[curseurY][curseurX + 1].getFleche())) {
				grille[curseurY][curseurX].setFleche(new ElementFleche());
				curseurX++;
				grille[curseurY][curseurX].inecDeplacement(uniteCourante);
				grille[curseurY][curseurX].getFleche().setArriver("end");
			}
		}
	}

	@Override
	public void actionBas() {
		if (controlPosCursMinMaxY()) {
			if (grille[curseurY - 1][curseurX].estAccecible(uniteCourante)) {
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
	}

	@Override
	public void actionHaut() {
		if (controlPosCursMinMaxY()) {
			if (grille[curseurY + 1][curseurX].estAccecible(uniteCourante)) {
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
				System.out.println("Deplacement restant : " + uniteCourante.getDeplacement());
			}
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
			List<Case> lstEnmisAPorter = uniteCourante.getA().enmisAPorter(grille[curseurY][curseurX], grille, joueur);
			if (!lstEnmisAPorter.isEmpty()) {
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
				if (lstUniteUtilisable().isEmpty()) {
					e = new DeplacementLibre(grille, 0, 0, joueur.getAdverse(), true, isOver);
				} else {
					e = new DeplacementLibre(grille, curseurXMemo, curseurYMemo, joueur, false, isOver);
				}
			} else if (action > 0) {
				if (actionsCourante[action].equals(ActionsUnites.attaque.name())) {
					e = new Attaque(grille, curseurX, curseurY, uniteCourante, joueur, isOver, lstEnmisAPorter);
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
					if (lstUniteUtilisable().isEmpty()) {
						e = new DeplacementLibre(grille, 0, 0, joueur.getAdverse(), true, isOver);
					} else {
						e = new DeplacementLibre(grille, curseurXMemo, curseurYMemo, joueur, false, isOver);
					}
				}

			} else {
				if (lstUniteUtilisable().isEmpty()) {
					e = new DeplacementLibre(grille, 0, 0, joueur.getAdverse(), true, isOver);
				} else {
					e = new DeplacementLibre(grille, curseurXMemo, curseurYMemo, joueur, false, isOver);
				}
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

	public List<Case> deplacementPossible() {
		List<Case> lst = new ArrayList<>();
		deplacementPossibleRec(lst, grille[curseurY][curseurX], uniteCourante.getDeplacement());
		return lst;
	}

	public void deplacementPossibleRec(List<Case> lst, Case courante, int deplacement) {
		if (!lst.contains(courante)) {
			lst.add(courante);
		}
		if (deplacement != 0) {
			if (controlPosCursMinMaxY()) {
				deplacementPossibleRec(lst, grille[curseurY + 1][curseurX], deplacement - 1);
				deplacementPossibleRec(lst, grille[curseurY - 1][curseurX], deplacement - 1);
			}
			if (controlPosCursMinMaxX()) {
				deplacementPossibleRec(lst, grille[curseurY][curseurX + 1], deplacement - 1);
				deplacementPossibleRec(lst, grille[curseurY][curseurX - 1], deplacement - 1);
			}
		}

	}

	private boolean controlPosCursMinMaxX() {
		return curseurX < grille[0].length - 1 || curseurX > 0;
	}

	private boolean controlPosCursMinMaxY() {
		return curseurY < grille.length - 1 || curseurY > 0;
	}

	@Override
	public Etats actionT() {
		return this;
	}

	public void drawGameCursorE() {
		Affichage.dessineCurseurChoisirChemin(getCurseurX(), getCurseurY()); // affiche le curseur en (0,0), a modifier
	}

	@Override
	public void actionN() {
	}

}
