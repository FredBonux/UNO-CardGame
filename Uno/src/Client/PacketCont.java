package Client;

import java.util.ArrayList;

import Carte.Carta;
import Carte.Mano;
import Carte.Mazzo;
import Carte.Tavolo;
import Utility.CTRLPacket;
import Utility.Packet;

public class PacketCont extends CTRLPacket{

	private boolean isMyTurn;
	private Mano mano;
	private Mazzo mazzo;
	private Tavolo tavolo;
	
	public PacketCont(Packet p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void manoIniziale(ArrayList<Carta> carteIniziali) {
		
		mano = new Mano(carteIniziali);
		
	}

	@Override
	public void myTurn() {
		// TODO Auto-generated method stub
		isMyTurn=true;
		while(isMyTurn){
			
		}
	}

	@Override
	public void otherTurn() {
		
		
	}

	@Override
	public void hoPerso() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hoVinto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subisciNormale(Carta c) {
		// TODO Auto-generated method stub
		
		tavolo.pushToTavolo(c);
}

	@Override
	public void subisciPIU2(Carta piu2, Carta c1, Carta c2) {
		// TODO Auto-generated method stub
		mano.aggiungiCarta(c1);
		mano.aggiungiCarta(c2);
		
		tavolo.pushToTavolo(piu2);
	}

	@Override
	public void subisciPIU4(Carta piu4, Carta c1, Carta c2, Carta c3, Carta c4) {
		// TODO Auto-generated method stub
		mano.aggiungiCarta(c1);
		mano.aggiungiCarta(c2);
		mano.aggiungiCarta(c3);
		mano.aggiungiCarta(c4);
		
		tavolo.pushToTavolo(piu4);
	}

	@Override
	public void subisciCambiaColore(Carta c) {
		// TODO Auto-generated method stub
		
		tavolo.pushToTavolo(c);
	}

	@Override
	public void subisciStop(Carta c) {
		// TODO Auto-generated method stub
		tavolo.pushToTavolo(c);
	}

	@Override
	public void subisciCambioGiro(Carta c) {
		// TODO Auto-generated method stub
		tavolo.pushToTavolo(c);
	}

	@Override
	public void avversarioPesca() {
		// TODO Auto-generated method stub
		
	}

}
