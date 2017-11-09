package Carte;

import java.io.Serializable;

public class Carta implements Serializable, Comparable<Carta>{
	
	private final TipoCarta tipoCarta;
	private Colore coloreCarta;
	private int numeroCarta;
	private int x;
	private int y;
	
	public Carta(TipoCarta tipoCarta, Colore coloreCarta, int numeroCarta) {
		this.tipoCarta = tipoCarta;
		this.coloreCarta = coloreCarta;
		this.numeroCarta = numeroCarta;
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
	
	public int trovaY(Carta c){
		int y=0;
		if(c.tipoCarta==TipoCarta.Normale){
		if(c.getColoreCarta()==Colore.ROSSO)
			y=0;
		if(c.getColoreCarta()==Colore.GIALLO)
			y=110;
		if(c.getColoreCarta()==Colore.VERDE)
			y=220;
		if(c.getColoreCarta()==Colore.AZZURRO)
			y=330;
		}else{
			if(c.tipoCarta==TipoCarta.Piu4)
			y=439;
			if(c.tipoCarta==TipoCarta.CambioColore)
				y=0;
		}	
		return y;
	}
	
	public int trovaX(Carta c){
		int x=0;
		if(c.tipoCarta == tipoCarta.Normale){
			switch (c.getNumeroCarta()){
				case 0: x=0; break;
				case 1: x=73; break;
				case 2: x=146; break;
				case 3: x=219; break;
				case 4: x=292; break;
				case 5: x=365; break;
				case 6: x=438; break;
				case 7: x=511; break;
				case 8: x=584; break;
				case 9: x=658; break;
			}
		}else{
			if(c.tipoCarta==TipoCarta.Stop)
				x=730;
			if(c.tipoCarta==TipoCarta.CambioGiro)
				x=804;
			if(c.tipoCarta==TipoCarta.Piu2)
				x=876;
			if(c.tipoCarta==TipoCarta.CambioColore)
				x=951;
			if(c.tipoCarta==TipoCarta.Piu4)
				x=951;
		}
		return x;
	}

	public int getX() {
		return this.trovaX(this);
	}

	public int getY() {
		return this.trovaY(this);
	}
	
	
}
