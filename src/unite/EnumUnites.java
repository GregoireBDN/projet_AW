package unite;

import java.util.stream.Stream;

public enum EnumUnites {
	Artillerie(6000), Bazouka(3500), Bombardier(20000), Dca(6000), Helicoptere(12000), Infanterie(1500), Tank(7000);
	
	int prix;
	
	EnumUnites(int prix) {
		this.prix = prix;
	}

	public static String[] getUnites() {
		return Stream.of(EnumUnites.values()).map(EnumUnites::name).toArray(String[]::new); 
	}
	public int getPrix() {
		return prix;
	}
}
