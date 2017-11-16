package Utility;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Carte.Carta;
import Carte.Mano;
import Carte.TipoCarta;

public class Giocatore {
	private Socket socket;
	private ObjectOutputStream outStream;
	private ObjectInputStream inStream;
	private Mano mano;
	private InputReadBuffered irb;
	
	public Giocatore(Socket s) {
		this.socket = s;
		try {
			this.outStream = new ObjectOutputStream(socket.getOutputStream());
			this.inStream = new ObjectInputStream(socket.getInputStream());
			this.irb = new InputReadBuffered(inStream);
			this.mano = new Mano();
			this.write(new Packet(Evento.connessione)); //Invio Connessione
			this.read(); //Connessione avviata
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return this.socket.toString();
	}

	public Mano getMano() {
		return mano;
	}

	public void setMano(Mano mano) {
		this.mano = mano;
	}
	
	public Packet read() throws Exception {
		Packet p = this.irb.pop();
		System.out.println(p);
		return p;
	}
	
	public void write(Packet p) throws Exception {
		this.outStream.reset();
		this.outStream.writeObject(p);
	}
	
	public boolean hasCarta(Carta c) {
		return (this.mano.findCarta(c) != -1) ? true : false;
	}
	
	public boolean haVinto() {
		return (this.mano.getMano().size() <= 0) ? true : false;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ObjectOutputStream getOutStream() {
		return outStream;
	}

	public void setOutStream(ObjectOutputStream outStream) {
		this.outStream = outStream;
	}

	public ObjectInputStream getInStream() {
		return inStream;
	}

	public void setInStream(ObjectInputStream inStream) {
		this.inStream = inStream;
	}
	
	public InputReadBuffered getIrb() {
		return this.irb;
	}
	
	public boolean controllaLastCarta(Carta c) {
		if(this.mano.getMano().size() > 1) return true;
		if(c.getTipoCarta() != TipoCarta.Normale) return false;
		return true;
	}
	
	public boolean checkUno(Packet p) {
		if(mano.getMano().size() != 2) return true;
		if(p.isUnoCalled()) return true;
		return false;
	}
	
	
}
