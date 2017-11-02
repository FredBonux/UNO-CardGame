package Carte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Mazzo {
	private ArrayList<Carta> mazzo;
	private int carteRimaste = 108; //Su wikipedia sono 112 perchè conta 4 carte bianche
	
	public Mazzo() {
		mazzo = new ArrayList<Carta>();
		//Crea il mazzo
		mazzo.add(new Carta(TipoCarta.Normale, Colore.ROSSO, 0));
		mazzo.add(new Carta(TipoCarta.Normale, Colore.VERDE, 0));
		mazzo.add(new Carta(TipoCarta.Normale, Colore.AZZURRO, 0));
		mazzo.add(new Carta(TipoCarta.Normale, Colore.GIALLO, 0));
		mazzo.add(new Carta(TipoCarta.CambioColore));
		mazzo.add(new Carta(TipoCarta.CambioColore));
		mazzo.add(new Carta(TipoCarta.CambioColore));
		mazzo.add(new Carta(TipoCarta.CambioColore));
		mazzo.add(new Carta(TipoCarta.Piu4));
		mazzo.add(new Carta(TipoCarta.Piu4));
		mazzo.add(new Carta(TipoCarta.Piu4));
		mazzo.add(new Carta(TipoCarta.Piu4));
		for(int i = 0; i < 2; i++) {
			for(int j = 1; j < 10; j++) {
				//Carta normali
				mazzo.add(new Carta(TipoCarta.Normale, Colore.ROSSO, j));
				mazzo.add(new Carta(TipoCarta.Normale, Colore.VERDE, j));
				mazzo.add(new Carta(TipoCarta.Normale, Colore.AZZURRO, j));
				mazzo.add(new Carta(TipoCarta.Normale, Colore.GIALLO, j));
			}
			//Stop
			mazzo.add(new Carta(TipoCarta.Stop, Colore.ROSSO));
			mazzo.add(new Carta(TipoCarta.Stop, Colore.VERDE));
			mazzo.add(new Carta(TipoCarta.Stop, Colore.AZZURRO));
			mazzo.add(new Carta(TipoCarta.Stop, Colore.GIALLO));
			//Cambio Giro
			mazzo.add(new Carta(TipoCarta.CambioGiro, Colore.ROSSO));
			mazzo.add(new Carta(TipoCarta.CambioGiro, Colore.VERDE));
			mazzo.add(new Carta(TipoCarta.CambioGiro, Colore.AZZURRO));
			mazzo.add(new Carta(TipoCarta.CambioGiro, Colore.GIALLO));
			//Piu2
			mazzo.add(new Carta(TipoCarta.Piu2, Colore.ROSSO));
			mazzo.add(new Carta(TipoCarta.Piu2, Colore.VERDE));
			mazzo.add(new Carta(TipoCarta.Piu2, Colore.AZZURRO));
			mazzo.add(new Carta(TipoCarta.Piu2, Colore.GIALLO));
		}
		Collections.shuffle(this.mazzo);
	}
	
	public Carta pesca() {
		this.carteRimaste --;
		Carta ret = this.mazzo.get(0);
		this.mazzo.remove(0);
		return ret;
	}
	
	public void stampa() {
		System.out.println("CARTE : " + mazzo.size());
		for(int i = 0; i < mazzo.size(); i++) {
			System.out.println(mazzo.get(i));
		}
	}
}
