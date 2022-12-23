package outil;

import ressources.*;
import terrain.*;
import unite.*;

public class DeplacementLibre extends Etats {

	public DeplacementLibre(Case[][] grille, int x, int y, Joueur joueur, boolean newTours, boolean isOver) {
		this.joueur = joueur;
		this.curseurX = x;
		this.curseurY = y;
		this.grille = grille;
		this.isOver = isOver;
		if (!isOver) {
			if (newTours) {
				joueur.setCredit(calculeCredit());
				resetUnites();
				System.out.println(
						"\n" + "Tour :" + "\n" + joueur.toString() + " " + " \n" + "Credit : " + joueur.credit + "$");
			} else {
				System.out.println("Deplacement libre");
			}
		}

	}

	@Override
	public void actionGauche() {
		if (curseurX > 0 && !isOver) {
			curseurX--;
		}
	}

	@Override
	public void actionDroit() {
		if (curseurX < grille[0].length - 1 && !isOver) {
			curseurX++;
		}
	}

	@Override
	public void actionBas() {
		if (curseurY > 0 && !isOver) {
			curseurY--;
		}

	}

	@Override
	public void actionHaut() {
		if (curseurY < grille.length - 1 && !isOver) {
			curseurY++;
		}
	}

	public Etats actionEnter() {
		Etats e = this;
		if (!isOver) {
			if (grille[curseurY][curseurX].unite != null && grille[curseurY][curseurX].unite.getJoueur().equals(joueur)
					&& !grille[curseurY][curseurX].unite.isUtiliser()) {
				e = new ChoisirChemin(grille, curseurX, curseurY, grille[curseurY][curseurX].unite, joueur, isOver);
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
						System.out.println("Unite non fonctionelle");
						break;
					}
					case 1: {
						if (joueur.getCredit() >= new Bazooka(joueur).getPrix()) {
							unite = new Bazooka(joueur);
							joueur.setCredit(joueur.getCredit() - unite.getPrix());
						} else {
							System.out.println("Credit insufisant");
						}
						break;
					}
					case 2: {
						if (joueur.getCredit() >= new Bombardier(joueur).getPrix()) {
							unite = new Bombardier(joueur);
							joueur.setCredit(joueur.getCredit() - unite.getPrix());
						} else {
							System.out.println("Credit insufisant");
						}
						break;
					}
					case 3: {
						if (joueur.getCredit() >= new Dca(joueur).getPrix()) {
							unite = new Dca(joueur);
							joueur.setCredit(joueur.getCredit() - unite.getPrix());
						} else {
							System.out.println("Credit insufisant");
						}
						break;
					}
					case 4: {
						if (joueur.getCredit() >= new Helicoptere(joueur).getPrix()) {
							unite = new Helicoptere(joueur);
							joueur.setCredit(joueur.getCredit() - unite.getPrix());
						} else {
							System.out.println("Credit insufisant");
						}
						break;
					}
					case 5: {
						if (joueur.getCredit() >= new Infanterie(joueur).getPrix()) {
							unite = new Infanterie(joueur);
							joueur.setCredit(joueur.getCredit() - unite.getPrix());
						} else {
							System.out.println("Credit insufisant");
						}
						break;
					}
					case 6: {
						if (joueur.getCredit() >= new Infanterie(joueur).getPrix()) {
							unite = new Tank(joueur);
							joueur.setCredit(joueur.getCredit() - unite.getPrix());
						} else {
							System.out.println("Credit insufisant");
						}
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + unite);
					}
					grille[curseurY][curseurX].setUnite(unite);

				}
			}
		}
		return e;
	}

	@Override
	public Etats actionEchap() {
		return new DeplacementLibre(grille, curseurX, curseurY, joueur, false, isOver);
	}

	@Override
	public Etats actionT() {
		Etats e = this;
		if (!isOver) {
			String[] options = { "Oui", "Non" };
			if (Affichage.popup("Finir le tour de Joueur" + joueur.indiceJoueur + "?", options, true, 1) == 0) {
				e = new DeplacementLibre(grille, 0, 0, joueur.getAdverse(), true, isOver);
				System.out.println("FIN DE TOUR");
			}
		}
		return e;
	}

}
