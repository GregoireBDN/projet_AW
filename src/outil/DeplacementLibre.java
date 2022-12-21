package outil;

import java.util.List;

import ressources.*;
import terrain.Propriete;
import terrain.Usine;
import unite.Artillerie;
import unite.Bazooka;
import unite.Bombardier;
import unite.Dca;
import unite.EnumUnites;
import unite.Helicoptere;
import unite.Infanterie;
import unite.Tank;
import unite.Unite;

public class DeplacementLibre extends Etats {

	public DeplacementLibre(Case[][] grille, int x, int y, Joueur joueur) {
		this.joueur = joueur;
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

		if (grille[curseurY][curseurX].unite != null && grille[curseurY][curseurX].unite.getJoueur() == joueur) {
			System.out.println("Touche ENTER");
			e = new ChoisirChemin(grille, curseurX, curseurY, grille[curseurY][curseurX].unite, joueur);
		} else if (grille[curseurY][curseurX].terrain instanceof Usine) {
			Propriete propriete = (Propriete) grille[curseurY][curseurX].terrain;
			if (propriete.getJoueur().equals(joueur)) {
				String[] option = new String[EnumUnites.getUnites().length];
				int i = 0;
				for (EnumUnites unite : EnumUnites.values()) {
					option[i] = unite.name().concat(" ").concat(String.valueOf(unite.getPrix())).concat("$");
					i++;
				}
				int IndexUnite = Affichage.popup("Acheter une unite", option, true, 0);
				Unite unite = null;
				switch (IndexUnite) {
				case -1: {
					break;
				}
				case 0: {
					unite = new Artillerie(joueur);
					break;
				}
				case 1: {
					unite = new Bazooka(joueur);
					break;
				}
				case 2: {
					unite = new Bombardier(joueur);
					break;
				}
				case 3: {
					unite = new Dca(joueur);
					break;
				}
				case 4: {
					unite = new Helicoptere(joueur);
					break;
				}
				case 5: {
					unite = new Infanterie(joueur);
					break;
				}
				case 6: {
					unite = new Tank(joueur);
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + unite);
				}
				grille[curseurY][curseurX].setUnite(unite);
				
			}
		}
		return e;
	}

	@Override
	public Etats actionEchap() {
		return new DeplacementLibre(grille, curseurX, curseurY, joueur);
	}

}
