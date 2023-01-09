package unite;

import arme.*;
import locomotion.*;
import outil.ActionsUnites;
import outil.Joueur;
import terrain.Propriete;

public abstract class Unite {
	double pv;
	Locomotion locomotion;
	int prix;
	Joueur joueur;
	Arme a;
	String[] actions;
	int deplacement;
	boolean utiliser;

	public Unite() {
		this.actions = new String[1];
		actions[0] = ActionsUnites.attendre.name();
		this.setPv(10);
		this.utiliser = false;
	}

	public double getPv() {
		return pv;
	}

	public void setPv(double pv) {
		this.pv = pv;
	}

	public Locomotion getLocomotion() {
		return locomotion;
	}

	public void setLocomotion(Locomotion locomotion) {
		this.locomotion = locomotion;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public Arme getA() {
		return a;
	}

	public void setA(Arme a) {
		this.a = a;
	}

	public String[] getActions() {
		return actions;
	}

	public void setAction(String[] action) {
		this.actions = action;
	}

	public void ajoutAction(String a) {
		String[] newAction = new String[actions.length + 1];
		for (int i = 0; i < actions.length; i++) {
			newAction[i] = actions[i];
		}
		newAction[actions.length] = a;
		this.actions = newAction;
	}

	public void resetAction() {
		this.actions = new String[1];
		actions[0] = ActionsUnites.attendre.name();
	}

	public int getDeplacement() {
		return deplacement;
	}

	public void setDeplacement(int deplacement) {
		this.deplacement = deplacement;
	}

	public double infligeDegat(Unite cible) {
		return cible.getPv() - this.a.efficacite(cible) * Math.ceil(this.pv);
	}

	public double captureProp (Propriete p) {
		return p.getResistance() -Math.ceil(this.pv);
		}

	public abstract void resetDeplacement();

	public abstract String toString();

	public boolean isUtiliser() {
		return utiliser;
	}

	public void setUtiliser(boolean utilise) {
		this.utiliser = utilise;
	}
	
	public void resetUnite() {
		resetAction();
		resetDeplacement();
		utiliser = false;
	}
	
	public abstract String getImage();
}
