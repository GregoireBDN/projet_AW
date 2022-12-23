package outil;

public class Joueur {
int indiceJoueur;
int credit;
Joueur adverse;

public Joueur() {
	this.indiceJoueur = 0;
}

public Joueur(int indice) {
	this.indiceJoueur = indice;
	this.credit = 0;
}

public int getIndiceJoueur() {
	return indiceJoueur;
}

public void setIndiceJoueur(int indiceJoueur) {
	this.indiceJoueur = indiceJoueur;
}

public int getCredit() {
	return credit;
}

public void setCredit(int credit) {
	this.credit = credit;
}

public Joueur getAdverse() {
	return adverse;
}

public void setAdverse(Joueur adverse) {
	this.adverse = adverse;
}

public String toString () {
	return "Joueur : " + getIndiceJoueur();
}
}
