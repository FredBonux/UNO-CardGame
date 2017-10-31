package Carta;

public abstract class Carta {
	
	Carta c;
	Colore col;
	
	public Carta(Carta c, Colore col) {
		this.c = c;
		this.col = col; 
	}

	
	public Carta getC() {
		return c;
	}

	public void setC(Carta c) {
		this.c = c;
	}


	public Colore getCol() {
		return col;
	}
	
	
	

}
