package outil;

import terrain.Propriete;
import terrain.Terrain;
import unite.Unite;

public class Case {

	Terrain terrain;
	int x;
	int y;
	Unite unite;
	Fleche fleche;

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

	public Fleche getFleche() {
		return fleche;
	}

	public boolean aUneFleche() {
		return fleche.depart != null;
	}

	public void setFleche(Fleche fleche) {
		this.fleche = fleche;
	}

	public void suprimeFleche() {
		this.getFleche().setArriver(null);
		this.getFleche().setDepart(null);
	}

	public boolean estAccecible(Unite u) {
		return !this.aUneUniteeEnemie(u) && !this.aUneFleche() && this.terrain.CoupDeDeplacement(u.getLocomotion()) != -1
				&& this.terrain.CoupDeDeplacement(u.getLocomotion()) <= u.getDeplacement();
	}
	
	public void incrDeplacement(Unite u) {
		u.setDeplacement(
				u.getDeplacement() - terrain.CoupDeDeplacement(u.getLocomotion()));
	}

}
