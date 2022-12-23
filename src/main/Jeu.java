/** package principal */
package main;

import librairies.AssociationTouches;
import librairies.StdDraw;
import outil.*;
import ressources.Config;
import ressources.ParseurCartes;
import terrain.*;
import unite.*;
import ressources.Affichage;
import ressources.Chemins;

public class Jeu {
	Etats etat;
	Case[][] grille;
	Joueur joueur1;
	Joueur joueur2;

	public Jeu(String fileName) throws Exception {
		joueur1 = new Joueur(1);
		joueur2 = new Joueur(2);
		joueur1.setAdverse(joueur2);
		joueur2.setAdverse(joueur1);
		String[][] carteString = ParseurCartes.parseCarte(fileName);
		grille = new Case[carteString.length][carteString[0].length];
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				Case caseCourante = grille[i][j] = new Case();
				caseCourante.setX(j);
				caseCourante.setY(i);
				caseCourante.setFleche(new ElementFleche());
				associeCase(caseCourante, carteString[i][j]);
				System.out.println();
			}
		}

		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				System.out.print(grille[i][j]);
				if (j != grille[0].length) {
					System.out.print(" | ");
				} else {
					System.out.println();
				}
			}
			System.out.println();
		}

		etat = new DeplacementLibre(grille, 0, 0, joueur1, true, false);

		Config.setDimension(carteString[0].length, carteString.length);
	}

	public boolean isOver() {
		return etat.isOver();
	}

	public void afficheStatutJeu() {
		Affichage.videZoneTexte();
		Affichage.afficheTexteDescriptif("Status du jeu");
	}

	public Joueur stringToJoueur(String info) {
		Joueur joueur = new Joueur();
		if (info.equals("1")) {
			joueur = joueur1;
		} else if (info.equals("2")) {
			joueur = joueur2;
		}
		return joueur;
	}

	public void associeUnite(Case courante, String info, Joueur joueur) {
		switch (info) {
		case "Artillerie":
			break;
		case "Bazooka":
			courante.setUnite(new Artillerie());
			courante.getUnite().setJoueur(joueur);
			break;
		case "Bombardier":
			courante.setUnite(new Bombardier());
			courante.getUnite().setJoueur(joueur);
			break;
		case "DCA":
			courante.setUnite(new Dca());
			courante.getUnite().setJoueur(joueur);
			break;
		case "Helico":
			courante.setUnite(new Helicoptere());
			courante.getUnite().setJoueur(joueur);
			break;
		case "Infanterie":
			courante.setUnite(new Infanterie());
			courante.getUnite().setJoueur(joueur);
			break;
		case "Tank":
			courante.setUnite(new Tank());
			courante.getUnite().setJoueur(joueur);
			break;
		case "Convoit":
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + info);
		}
	}

	public void associeCase(Case courante, String info) {
		String terrain = info.split(":")[0].split(";")[0];
		String[] unite = info.split(":")[0].split(";");
		String[] joueur = info.split(":");
		Propriete propriete;

		switch (terrain) {

		case "Eau":
			courante.setTerrain(new Eau());
			if (aUneUnite(unite)) {
				associeUnite(courante, unite[1], stringToJoueur(joueur[1]));
			}
			break;
		case "Foret":
			courante.setTerrain(new Foret());
			if (aUneUnite(unite)) {
				associeUnite(courante, unite[1], stringToJoueur(joueur[1]));
			}
			break;
		case "Montagne":
			courante.setTerrain(new Montagne());
			if (aUneUnite(unite)) {
				associeUnite(courante, unite[1], stringToJoueur(joueur[1]));
			}
			break;
		case "Plaine":
			courante.setTerrain(new Plaine());
			if (aUneUnite(unite)) {
				associeUnite(courante, unite[1], stringToJoueur(joueur[1]));
			}
			break;
		case "QG":
			courante.setTerrain(new Qg());
			propriete = (Propriete) courante.getTerrain();
			propriete.setJoueur(stringToJoueur(joueur[1]));
			if (aUneUnite(unite)) {
				associeUnite(courante, unite[1], stringToJoueur(joueur[1]));
			}
			break;
		case "Usine":
			courante.setTerrain(new Usine());
			propriete = (Propriete) courante.getTerrain();
			propriete.setJoueur(stringToJoueur(joueur[1]));
			if (aUneUnite(unite)) {
				associeUnite(courante, unite[1], stringToJoueur(joueur[1]));
			}
			break;
		case "Ville":
			courante.setTerrain(new Ville());
			propriete = (Propriete) courante.getTerrain();
			propriete.setJoueur(stringToJoueur(joueur[1]));
			if (aUneUnite(unite)) {
				associeUnite(courante, unite[1], stringToJoueur(joueur[1]));
			}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + terrain);
		}
	}

	public boolean aUneUnite(String[] info) {
		return info.length == 2;
	}

	public void display() {
		StdDraw.clear();
		afficheStatutJeu();
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				Case caseCourante = grille[i][j];
				drowCases(caseCourante);
			}
		}

		Affichage.dessineGrille(); // affiche une grille, mais n'affiche rien dans les cases
		drawGameCursor();
		StdDraw.show(); // montre a l'ecran les changement demandes

	}

	public void drowFleche(Case courante) {
		if (courante.getFleche().getDepart() != null) {
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(),
					Chemins.getCheminFleche(courante.getFleche().getDepart(), courante.getFleche().getArriver()));
		}
	}

	public void drowUnite(Case courante) {
		if (courante.getUnite() instanceof Artillerie) {
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(), Chemins.getCheminUnite(
					courante.getUnite().getJoueur().getIndiceJoueur(), true, Chemins.FICHIER_ARTILLERIE));
		} else if (courante.getUnite() instanceof Bazooka) {
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(), Chemins
					.getCheminUnite(courante.getUnite().getJoueur().getIndiceJoueur(), true, Chemins.FICHIER_BAZOOKA));
		} else if (courante.getUnite() instanceof Bombardier) {
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(), Chemins.getCheminUnite(
					courante.getUnite().getJoueur().getIndiceJoueur(), true, Chemins.FICHIER_BOMBARDIER));
		} else if (courante.getUnite() instanceof Helicoptere) {
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(), Chemins.getCheminUnite(
					courante.getUnite().getJoueur().getIndiceJoueur(), true, Chemins.FICHIER_HELICOPTERE));
		} else if (courante.getUnite() instanceof Infanterie) {
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(), Chemins.getCheminUnite(
					courante.getUnite().getJoueur().getIndiceJoueur(), true, Chemins.FICHIER_INFANTERIE));
		} else if (courante.getUnite() instanceof Tank) {
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(), Chemins
					.getCheminUnite(courante.getUnite().getJoueur().getIndiceJoueur(), true, Chemins.FICHIER_TANK));
		} else if (courante.getUnite() instanceof Dca) {
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(), Chemins
					.getCheminUnite(courante.getUnite().getJoueur().getIndiceJoueur(), true, Chemins.FICHIER_ANTIAIR));
		}
	}

	public void drowCases(Case courante) {
		if (courante.getTerrain() instanceof Eau) {
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(),
					Chemins.getCheminTerrain(Chemins.FICHIER_EAU));
			drowUnite(courante);
			drowFleche(courante);
		} else if (courante.getTerrain() instanceof Foret) {
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(),
					Chemins.getCheminTerrain(Chemins.FICHIER_FORET));
			drowUnite(courante);
			drowFleche(courante);
		} else if (courante.getTerrain() instanceof Montagne) {
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(),
					Chemins.getCheminTerrain(Chemins.FICHIER_MONTAGNE));
			drowUnite(courante);
			drowFleche(courante);
		} else if (courante.getTerrain() instanceof Plaine) {
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(),
					Chemins.getCheminTerrain(Chemins.FICHIER_PLAINE));
			drowUnite(courante);
			drowFleche(courante);
		} else if (courante.getTerrain() instanceof Qg) {
			Propriete p = (Propriete) courante.getTerrain();
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(),
					Chemins.getCheminPropriete(Chemins.FICHIER_QG, p.getJoueur().getIndiceJoueur()));
			drowUnite(courante);
			drowFleche(courante);
		} else if (courante.getTerrain() instanceof Usine) {
			Propriete p = (Propriete) courante.getTerrain();
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(),
					Chemins.getCheminPropriete(Chemins.FICHIER_USINE, p.getJoueur().getIndiceJoueur()));
			drowUnite(courante);
			drowFleche(courante);
		} else if (courante.getTerrain() instanceof Ville) {
			Propriete p = (Propriete) courante.getTerrain();
			Affichage.dessineImageDansCase(courante.getX(), courante.getY(),
					Chemins.getCheminPropriete(Chemins.FICHIER_VILLE, p.getJoueur().getIndiceJoueur()));
			drowUnite(courante);
			drowFleche(courante);
		}

	}

	public void initialDisplay() {
		StdDraw.enableDoubleBuffering(); // rend l'affichage plus fluide: tout draw est mis en buffer et ne s'affiche
											// qu'au prochain StdDraw.show();
		display();
	}

	public void drawGameCursor() {
		Affichage.dessineCurseur(etat.getCurseurX(), etat.getCurseurY()); // affiche le curseur en (0,0), a modifier
	}

	public void update() {
		AssociationTouches toucheSuivante = AssociationTouches.trouveProchaineEntree(); // cette fonction boucle jusqu'a
																						// la prochaine entree de
																						// l'utilisateur
		if (toucheSuivante.isHaut()) {
			etat.actionHaut();
			display();
		}
		if (toucheSuivante.isBas()) {
			etat.actionBas();
			display();
		}
		if (toucheSuivante.isGauche()) {
			etat.actionGauche();
			display();
		}
		if (toucheSuivante.isDroite()) {
			etat.actionDroit();
			display();
		}

		if (toucheSuivante.isEntree()) {
			etat = etat.actionEnter();
			display();
		}
		if (toucheSuivante.isEchap()) {
			etat = etat.actionEchap();
			display();
		}

		// ATTENTION ! si vous voulez detecter d'autres touches que 't',
		// vous devez les ajouter au tableau Config.TOUCHES_PERTINENTES_CARACTERES
		if (toucheSuivante.isCaractere('t')) {
			etat = etat.actionT();
			display();
		}
	}
}
