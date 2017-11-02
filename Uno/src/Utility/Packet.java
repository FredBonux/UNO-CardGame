package Utility;

import java.io.Serializable;

import Carte.Carta;

public class Packet implements Serializable{
	private final Evento evento; //tipo di evento inviato via socket
	private final Carta[] carte; //Alcuni eventi inviano una o piu carte
	
	//Costruttore per gli eventi base (quelli che non hanno a che fare con le carte)
	public Packet(Evento tipoEvento) {
		this.evento = tipoEvento;
		this.carte = null;
	}
	
	//Costruttore per gli eventi con carta
	public Packet(Evento tipoEvento, Carta[] carte) {
		this.evento = tipoEvento;
		this.carte = carte;
	}
	
	public Evento getEvento() {
		return this.evento;
	}
	
	public Carta[] getCarte() {
		return this.carte;
	}
	
	
}
