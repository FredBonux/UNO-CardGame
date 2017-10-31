package Tipologia;

import Carta.Carta;
import Carta.Colore;

public class Normal extends Carta {

	Colore col;
	int numero;
	
	public Normal(Carta c, Colore col, int numero) {
		super(c, col);
		this.numero = numero;
	}

	public Colore getCol() {
		return col;
	}

	public int getNumero() {
		return numero;
	}

}
