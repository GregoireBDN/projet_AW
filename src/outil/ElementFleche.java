package outil;

public class ElementFleche {
	String depart;
	String arriver;
	ElementFleche precedent;

	public ElementFleche(String depart, String arriver, ElementFleche precedent) {
		this.precedent = precedent;
		this.depart = depart;
		this.arriver = arriver;
	}

	public ElementFleche() {
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

	public ElementFleche getPrecedent() {
		return precedent;
	}

	public void setPrecedent(ElementFleche precedent) {
		this.precedent = precedent;
	}
}
