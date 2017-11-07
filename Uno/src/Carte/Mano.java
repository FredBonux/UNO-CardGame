package Carte;

import java.util.ArrayList;

public class Mano {
	private ArrayList<Carta> carte;
	
	public Mano(ArrayList<Carta> carte) {
		this.carte = carte;		
	}
	
	public Mano() {
		this.carte = new ArrayList<Carta>();		
	}
	
	public Carta removeByIndex(int index) {
		
		Carta app = carte.get(index);
		
		this.carte.remove(index);
		
		return app;
	}
	
	public void aggiungiCarta(Carta c) {
		this.carte.add(c);
	}
	
	public ArrayList<Carta> getMano() {
		return this.carte;
	}
	
	public int findCarta(Carta c) {
		for(int i = 0; i < this.carte.size(); i++) {
			if(this.carte.get(i).compareTo(c) == 0) return i;
		}
		return -1;
	}
	
	
}
