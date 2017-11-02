package Carte;

public class Carta {
	private TipoCarta tipoCarta;
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
	
	

}
