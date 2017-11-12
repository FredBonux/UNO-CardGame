package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Carte.Carta;
import Carte.Mano;
import Carte.Mazzo;
import Carte.Tavolo;
import Carte.TipoCarta;
import Utility.Evento;
import Utility.Giocatore;
import Utility.Packet;

public class ServerMatch extends Thread{
	
	private Giocatore[] giocatori = new Giocatore[2];
	private Mazzo mazzo = null;
	private Packet lastGiocata = null;
	private Tavolo tavolo = null;
	private boolean turn = true;
	
	private boolean isRunning = true; //E' true fino a quando la partita non è finita
	
	public ServerMatch(Giocatore home, Giocatore away) throws IOException {
		this.giocatori[0] = home;
		this.giocatori[1] = away;
		this.tavolo = new Tavolo();
		this.mazzo = new Mazzo(this.tavolo);
	}
	
	public void run() {
		try{
			//Inizia la partita
			this.invioManiIniziali();
			while(this.isRunning) {
				System.out.println("GIOCATORE 1: " + getGiocatore1());
				System.out.println("GIOCATORE 2: " + getGiocatore2());
				
				//Invio il turno
				getGiocatore1().write((new Packet(Evento.turno)));
				getGiocatore2().write((new Packet(Evento.aspetta)));
				//Leggo la giocata
				while(!this.convalidaGiocata()) { //La partita non continua fino a quando il giocatore non fa la giocata corretta
					System.out.println("BAD PLAY");
					getGiocatore1().write((new Packet(Evento.badPlay)));
				}
				if(this.lastGiocata.getEvento() == Evento.butto){
					Packet p = new Packet(Evento.okPlay);
					System.out.println("Ok: " + p);
					getGiocatore1().write(p);
					this.applicaGiocata();
				}
				else {
					this.giocatorePesca();
				}
				
				//Scambio il turno
				this.scambioGiocatori();
				this.checkVittoria();
			}
		}catch (Exception e) {
			this.isRunning = false;
			e.printStackTrace();
		}
	}

	public void kill() {
		this.isRunning = false;
	}
	
	private void invioManiIniziali() throws Exception {
		//Genero le due mani iniziali
		for(int i = 0; i < 7; i++) {
			this.getGiocatore1().getMano().aggiungiCarta(mazzo.pesca());
			this.getGiocatore2().getMano().aggiungiCarta(mazzo.pesca());
		}
		Carta first;
		while((first = mazzo.pesca()).getTipoCarta() != TipoCarta.Normale) {
			mazzo.add(first);
		}
		this.tavolo.pushToTavolo(first); //Carta iniziale
		//Genero i due packet
		Packet pH = new Packet(Evento.mano, first, this.getGiocatore1().getMano().getMano());
		Packet pA = new Packet(Evento.mano, first, this.getGiocatore2().getMano().getMano());
		//Invio i due packet
		this.getGiocatore1().write(pH);
		this.getGiocatore2().write(pA);
		
	}

	private boolean convalidaGiocata() throws Exception {
		this.lastGiocata = this.getGiocatore1().read();
		switch (this.lastGiocata.getEvento()) {
		case butto:
			Carta c = this.lastGiocata.getCartaSubita();
			
			if(!this.tavolo.pushToTavoloControl(c)) return false; //Controllo sul tavolo
			if(!this.getGiocatore1().hasCarta(c)) return false; //Controllo sulla mano del giocatore 1
			else return true;
		case pesco:
			return true;
		default:
			break;
		}
		return false;
	}
	
	private void applicaGiocata() throws Exception {
		Carta cartaGiocata = this.lastGiocata.getCartaSubita();
		//rimuovo la carta dalla mano del giocatore
		this.getGiocatore1().getMano().removeByIndex(this.getGiocatore1().getMano().findCarta(cartaGiocata));
		//posiziono la carta in cima al tavolo
		this.tavolo.pushToTavolo(cartaGiocata);
		
		//Invio la giocata all'avversario
		switch (cartaGiocata.getTipoCarta()) {
		case Normale: case CambioColore:
			this.getGiocatore2().write((new Packet(Evento.subisci, cartaGiocata)));
			break;
		case CambioGiro: case Stop:
			this.getGiocatore2().write((new Packet(Evento.subisci, cartaGiocata)));
			this.scambioGiocatori();
			break;
		case Piu2:
			//TODO: manca l'update della mano
			this.getGiocatore2().write((new Packet(Evento.subisci, cartaGiocata, this.mazzo.pesca(2))));
			break;
		case Piu4:
			//TODO: manca l'update della mano
			this.getGiocatore2().write((new Packet(Evento.subisci, cartaGiocata, this.mazzo.pesca(4))));
			break;
		default:
			break;
		}
	}
	
	private void giocatorePesca() throws Exception {
		Carta pescata = this.mazzo.pesca();
		this.getGiocatore1().write((new Packet(Evento.okPlay, pescata)));
		this.getGiocatore2().write((new Packet(Evento.pesco)));
	}
	
	private void scambioGiocatori() {
		turn = !turn;
	}
	
	public void checkVittoria() throws Exception {
		if(this.getGiocatore1().haVinto()) {
			this.getGiocatore1().write((new Packet(Evento.vittoria)));
			this.getGiocatore2().write((new Packet(Evento.sconfitta)));
		}else if(this.getGiocatore2().haVinto()) {
			this.getGiocatore2().write((new Packet(Evento.vittoria)));
			this.getGiocatore1().write((new Packet(Evento.sconfitta)));
		}
	}
	
	public Giocatore getGiocatore1() {
		if(turn) return giocatori[0];
		else return giocatori[1];
	}
	public Giocatore getGiocatore2() {
		if(turn) return giocatori[1];
		else return giocatori[0];
	}
	
}
