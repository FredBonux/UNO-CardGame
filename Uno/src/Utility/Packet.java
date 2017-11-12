package Utility;

import java.io.Serializable;
import java.util.ArrayList;

import Carte.Carta;

public class Packet implements Serializable{
	private static final long serialVersionUID = 7375970102973712717L;
	private final Evento evento; //tipo di evento inviato via socket
	private final Carta cartaSubita;
	private final ArrayList<Carta> carte; //Alcuni eventi inviano una o piu carte
	
	//Costruttore per gli eventi base (quelli che non hanno a che fare con le carte)
	public Packet(Evento tipoEvento) {
		this.evento = tipoEvento;
		this.cartaSubita = null;
		this.carte = null;
	}
	
	//Costruttore per gli eventi con carta
	public Packet(Evento tipoEvento, Carta cartaSubita, ArrayList<Carta> carte) {
		this.evento = tipoEvento;
		this.carte = carte;
		this.cartaSubita = cartaSubita;
	}
	
	//Costruttore per eventi con una carta solo
	public Packet(Evento tipoEvento, Carta c) {
		this.evento = tipoEvento;
		this.carte = null;
		this.cartaSubita = c;
	}
	
	public Evento getEvento() {
		return this.evento;
	}
	
	public ArrayList<Carta> getCarte() {
		return this.carte;
	}
	
	public Carta getCartaSubita() {
		return this.cartaSubita;
	};
	
	
}
