package outil;

public class Fleche {
	String depart;
	String arriver;
	public Fleche(String depart, String arriver) {
		this.depart = depart;
		this.arriver = arriver;
	}
	public Fleche() {
		this.depart = null;
		this.arriver = null;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getArriver() {
		return arriver;
	}
	public void setArriver(String arriver) {
		this.arriver = arriver;
	}
}
