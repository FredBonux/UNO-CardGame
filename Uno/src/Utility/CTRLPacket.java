package Utility;

import java.util.ArrayList;

import Carte.Carta;
import Carte.TipoCarta;

public abstract class CTRLPacket {
	private Packet pacchetto;
	
	public CTRLPacket(Packet p) {
		this.pacchetto = p;
		this.callToAction();
	}	
	
	public void readPacket(Packet p) {
		this.pacchetto = p;
		this.callToAction();
	}
	
	//Funzione che richiama la funzione astratta dell'evento
	public void callToAction() {
		switch (this.pacchetto.getEvento()) {
		case mano:
			this.manoIniziale(this.pacchetto.getCarte());
			break;
		case turno: //E' il turno del client che riceve il pacchetto
			this.myTurn(); 
			break;
		case aspetta: //E' il turno dell'avversario 
			this.otherTurn();
			break;
		case sconfitta: //Il client ha perso
			this.hoPerso();
			break;
		case subisci: //Il client avversario ha giocato una carta
			this.callSubisci(this.pacchetto.getCarte());
			break;
		case vittoria: //Il client ha vinto
			this.hoVinto();
			break;
		case pesco:
			this.avversarioPesca();
		
		default:
			System.out.println("EVENTO NON VALIDO!");
			break;
		}
	}
	
	public void callSubisci(ArrayList<Carta> carte) {
		//Gestisci gli eventi delle tipologie delle carte
		if(carte == null) return;
		TipoCarta tipoSubito = carte.get(0).getTipoCarta();
		if( tipoSubito == TipoCarta.Normale ) this.subisciNormale(carte.get(0));
		else if( tipoSubito == TipoCarta.Piu2 ) this.subisciPIU2(carte.get(0), carte.get(1), carte.get(2));
		else if( tipoSubito == TipoCarta.Piu4 ) this.subisciPIU4( carte.get(0), carte.get(1), carte.get(2), carte.get(3), carte.get(4) );
		else if( tipoSubito == TipoCarta.CambioColore ) this.subisciCambiaColore( carte.get(0) );
		else if( tipoSubito == TipoCarta.Stop ) this.subisciStop( carte.get(0) );
		else if( tipoSubito == TipoCarta.CambioGiro ) this.subisciCambioGiro( carte.get(0) );
		
		return;
	}
	
	public abstract void manoIniziale(ArrayList<Carta> carteIniziali);
	public abstract void myTurn();
	public abstract void otherTurn();
	public abstract void hoPerso();
	public abstract void hoVinto();
	public abstract void avversarioPesca();
	
	//Subisci
	public abstract void subisciNormale(Carta c);
	public abstract void subisciPIU2(Carta piu2, Carta c1, Carta c2);
	public abstract void subisciPIU4(Carta piu4, Carta c1, Carta c2, Carta c3, Carta c4);
	public abstract void subisciCambiaColore(Carta c);
	public abstract void subisciStop(Carta c);
	public abstract void subisciCambioGiro(Carta c);
	
}
