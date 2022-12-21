package outil;

public abstract class Etats {
	int curseurX;
	int curseurY;
	Joueur joueur;
	Case[][] grille;

	public abstract void actionGauche();

	public abstract void actionDroit();

	public abstract void actionBas();

	public abstract void actionHaut();

	public abstract Etats actionEnter();
	
	public abstract Etats  actionEchap();

	public int getCurseurX() {
		return curseurX;
	}

	public void setCurseurX(int curseurX) {
		this.curseurX = curseurX;
	}

	public int getCurseurY() {
		return curseurY;
	}

	public void setCurseurY(int curseurY) {
		this.curseurY = curseurY;
	}
	
	
}
