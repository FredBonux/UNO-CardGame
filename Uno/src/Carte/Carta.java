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
		Carta c = new Carta(tipoCarta,coloreCarta,numeroCarta);
		this.x = trovaX(c);
		this.y= trovaY(c);
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
			y=440;
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
				case 1: x= 86;break;
				case 2: x=172;break;
				case 3: x=258;break;
				case 4: x=344;break;
				case 5: x=430;break;
				case 6: x=516;break;
				case 7: x=602;break;
				case 8: x=688;break;
				case 9: x=774;break;
			}
		}else{
			if(c.tipoCarta==TipoCarta.Stop)
				x=860;
			if(c.tipoCarta==TipoCarta.CambioGiro)
				x=946;
			if(c.tipoCarta==TipoCarta.Piu2)
				x=1032;
			if(c.tipoCarta==TipoCarta.CambioColore)
				x=1114;
			if(c.tipoCarta==TipoCarta.Piu4)
				x=1114;
		}
		return x;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
