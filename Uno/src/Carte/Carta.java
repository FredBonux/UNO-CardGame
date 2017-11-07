package Carte;

import java.io.Serializable;

public class Carta implements Serializable, Comparable<Carta>{
	private final TipoCarta tipoCarta;
	private Colore coloreCarta;
	private int numeroCarta;
	
	public Carta(TipoCarta tipo, Colore colore, int numero){
		this.tipoCarta = tipo;
		this.coloreCarta = colore;
		this.numeroCarta = numero;
	}
	
	public Carta(TipoCarta tipo, Colore colore) {
		this.tipoCarta = tipo;
		this.coloreCarta = colore;
	}
	
	public Carta(TipoCarta tipo) {
		this.tipoCarta = tipo;
	}

	public Colore getColoreCarta() {
		return coloreCarta;
	}

	public void setColoreCarta(Colore coloreCarta) {
		this.coloreCarta = coloreCarta;
	}

	public int getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(int numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public TipoCarta getTipoCarta() {
		return tipoCarta;
	}

	@Override
	public String toString() {
		return "Carta [tipoCarta=" + tipoCarta + ", coloreCarta=" + coloreCarta + ", numeroCarta=" + numeroCarta + "]";
	}

	@Override
	public int compareTo(Carta o) {
		if(tipoCarta != o.tipoCarta) return 1;
		switch (tipoCarta) {
		case Normale:
			return (coloreCarta == o.coloreCarta && numeroCarta == o.numeroCarta) ? 0 : 1;
		case CambioColore: case Piu4:
			return 0;
		case Piu2: case Stop: case CambioGiro:
			return (coloreCarta == o.coloreCarta) ? 0 : 1;
			
		}
		return 1;
	}
	
	

}
