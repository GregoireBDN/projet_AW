package outil;

import java.awt.Color;

import ressources.Affichage;
import ressources.Chemins;
import ressources.Config;
import terrain.Propriete;
import terrain.Terrain;
import unite.Artillerie;
import unite.Unite;

public class Case {

	Terrain terrain;
	int x;
	int y;
	Unite unite;
	ElementFleche fleche;

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Unite getUnite() {
		return unite;
	}

	public void setUnite(Unite unite) {
		this.unite = unite;
	}

	public boolean aUneUnite() {
		return unite != null;
	}

	public boolean aUneUniteeEnemie(Unite u) {
		return unite != null && unite.getJoueur() != u.getJoueur();
	}

	public ElementFleche getFleche() {
		return fleche;
	}

	public boolean aUneFleche() {
		return fleche.depart != null;
	}

	public void setFleche(ElementFleche fleche) {
		this.fleche = fleche;
	}

	public void suprimeFleche() {
		this.getFleche().setArriver(null);
		this.getFleche().setDepart(null);
	}

	public boolean estAccecible(Unite u) {
		return !this.aUneUniteeEnemie(u) && !this.aUneFleche()
				&& this.terrain.CoupDeDeplacement(u.getLocomotion()) != -1
				&& this.terrain.CoupDeDeplacement(u.getLocomotion()) <= u.getDeplacement();
	}

	public void decreDeplacement(Unite u) {
		u.setDeplacement(u.getDeplacement() - terrain.CoupDeDeplacement(u.getLocomotion()));
	}

	public void inecDeplacement(Unite u) {
		u.setDeplacement(u.getDeplacement() + terrain.CoupDeDeplacement(u.getLocomotion()));
	}

	public void drowCase() {
		if (terrain instanceof Propriete p) {
			Affichage.dessineImageDansCase(x, y, Chemins.getCheminPropriete(p.getImage(), p.getJoueur().indiceJoueur));
		} else {
			Affichage.dessineImageDansCase(x, y, Chemins.getCheminTerrain(terrain.getImage()));
		}
		if (fleche.getDepart() != null) {
			Affichage.dessineImageDansCase(x, y, Chemins.getCheminFleche(fleche.getDepart(), fleche.getArriver()));
		}
		if (unite != null) {
			Affichage.dessineImageDansCase(x, y,
					Chemins.getCheminUnite(unite.getJoueur().getIndiceJoueur(), true, unite.getImage()));
			if (unite.getPv() < 10) {
				Affichage.afficheTexteDansCase(x, y, "" + (int) unite.getPv(), Color.WHITE, Color.DARK_GRAY, 0.1, 0.1,
						Config.POLICE_PV);
			}
		}
	}

}
