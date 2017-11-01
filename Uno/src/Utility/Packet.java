package Utility;

public class Packet {
	private final Evento evento;
	//final Carta carta;
	
	//Costruttore per gli eventi base (quelli che non hanno a che fare con le carte)
	public Packet(Evento tipoEvento) {
		this.evento = tipoEvento;
	}
	
	//Costruttore per gli eventi con carta
	/*public Packet(Evento tipoEvento, Carta carta) {
		this.tipoEvento = tipoEvento;
		this.carta = carta;
	} */
	
	public Evento getEvento() {
		return this.evento;
	}
	
	/*public Carta getCarta() {
		return this.carta;
	}*/
	
	
}
