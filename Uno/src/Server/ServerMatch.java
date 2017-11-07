package Server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import Carte.Carta;
import Carte.Mazzo;
import Utility.Evento;
import Utility.Giocatore;
import Utility.Packet;

public class ServerMatch extends Thread{
	
	private Giocatore giocatore1;
	private Giocatore giocatore2;
	private Mazzo mazzo = null;
	private Packet lastGiocata = null;
	
	private boolean isRunning = true; //E' true fino a quando la partita non è finita
	
	public ServerMatch(Socket home, Socket away) throws IOException {
		this.giocatore1 = new Giocatore(home);
		this.giocatore2 = new Giocatore(away);
		this.mazzo = new Mazzo();
	}
	
	public void run() {
		try{
			//Inizia la partita
			this.invioManiIniziali();
			while(this.isRunning) {
				//Invio il turno
				giocatore1.write(new Packet(Evento.turno));
				giocatore2.write(new Packet(Evento.aspetta));
				//Leggo la giocata
				while(!this.convalidaGiocata()) { //La partita non continua fino a quando il giocatore non fa la giocata corretta
					giocatore1.write(new Packet(Evento.badPlay));
				}
				if(this.lastGiocata.getEvento() == Evento.butto){
					this.applicaGiocata();
					giocatore1.write(new Packet(Evento.okPlay));
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
			this.giocatore1.getMano().aggiungiCarta(mazzo.pesca());
			this.giocatore2.getMano().aggiungiCarta(mazzo.pesca());
		}
		//Genero i due packet
		Packet pH = new Packet(Evento.mano, null, this.giocatore1.getMano().getMano());
		Packet pA = new Packet(Evento.mano, null, this.giocatore2.getMano().getMano());
		//Invio i due packet
		this.giocatore1.write(pH);
		this.giocatore2.write(pA);
		
	}

	private boolean convalidaGiocata() throws  Exception {
		this.lastGiocata = this.giocatore1.read();
		switch (this.lastGiocata.getEvento()) {
		case butto:
			Carta c = this.lastGiocata.getCarte().get(0);
			
			//TODO: manca il controllo sul tavolo
			if(!this.giocatore1.hasCarta(c)) return false;
			else return true;
		case pesco:
			return true;
		default:
			break;
		}
		return false;
	}
	
	private void applicaGiocata() throws Exception {
		Carta cartaGiocata = this.lastGiocata.getCarte().get(0);
		//rimuovo la carta dalla mano del giocatore
		this.giocatore1.getMano().removeByIndex(this.giocatore1.getMano().findCarta(cartaGiocata));
		//posiziono la carta in cima al tavolo
			//this.tavolo.push()
		
		//Invio la giocata all'avversario
		switch (cartaGiocata.getTipoCarta()) {
		case Normale: case CambioColore:
			this.giocatore2.write(new Packet(Evento.subisci, cartaGiocata));
			break;
		case CambioGiro: case Stop:
			this.giocatore2.write(new Packet(Evento.subisci, cartaGiocata));
			this.scambioGiocatori();
			break;
		case Piu2:
			this.giocatore2.write(new Packet(Evento.subisci, cartaGiocata, this.mazzo.pesca(2)));
			break;
		case Piu4:
			this.giocatore2.write(new Packet(Evento.subisci, cartaGiocata, this.mazzo.pesca(4)));
			break;
		default:
			break;
		}
	}
	
	private void giocatorePesca() throws Exception {
		Carta pescata = this.mazzo.pesca();
		this.giocatore1.write(new Packet(null, pescata));
		this.giocatore2.write(new Packet(Evento.pesco, pescata));
	}
	
	private void scambioGiocatori() {
		Giocatore app = this.giocatore1;
		this.giocatore1 = this.giocatore2;
		this.giocatore2 = app;
	}
	
	public void checkVittoria() throws Exception {
		if(this.giocatore1.haVinto()) {
			this.giocatore1.write(new Packet(Evento.vittoria));
			this.giocatore2.write(new Packet(Evento.sconfitta));
		}else if(this.giocatore2.haVinto()) {
			this.giocatore2.write(new Packet(Evento.vittoria));
			this.giocatore1.write(new Packet(Evento.sconfitta));
		}
	}
}
