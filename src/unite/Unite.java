package unite;

import arme.*;
import locomoton.*;
import outil.ActionsUnites;

public abstract class Unite {
	double pv;
	Locomotion locomotion;
	int prix;
	int joueur;
	Arme a;
	String[] actions;
	int deplacement;
	
	public Unite() {
		this.actions = new String[1];
		actions[0] = ActionsUnites.attendre.name();
		this.setPv(10);
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
	public int getJoueur() {
		return joueur;
	}
	public void setJoueur(int joueur) {
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
		for(int i = 0; i<actions.length;i++) {
			newAction[i] = actions[i];
		}
		newAction[actions.length] = a;
		this.actions = newAction;
	}
	
	public void rstAction() {
		this.actions = new String[1];
		actions[0] = ActionsUnites.attendre.name();
	}

	public int getDeplacement() {
		return deplacement;
	}

	public void setDeplacement(int deplacement) {
		this.deplacement = deplacement;
	}
	
	public double infligeDegat (Unite cible) {
		return this.a.efficacite(cible)*Math.ceil(this.pv);
	}
	
	public abstract void resetDeplacement();
	
	public abstract String toString();
}
