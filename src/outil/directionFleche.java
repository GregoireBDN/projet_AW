package outil;

public enum directionFleche {
	right("left"), left("right"), up("down"), down("up");
	
	String oppose;
	
	private directionFleche(String opposer) {
		this.oppose=opposer;
	}
}
