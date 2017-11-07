package Utility;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Carte.Carta;
import Carte.Mano;

public class Giocatore {
	private Socket socket;
	private ObjectOutputStream outStream;
	private ObjectInputStream inStream;
	private Mano mano;
	
	public Giocatore(Socket s) {
		this.socket = s;
		try {
			this.outStream = new ObjectOutputStream(socket.getOutputStream());
			this.inStream = new ObjectInputStream(socket.getInputStream());
			this.mano = new Mano();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Mano getMano() {
		return mano;
	}

	public void setMano(Mano mano) {
		this.mano = mano;
	}
	
	public Packet read() throws Exception {
		return (Packet) this.inStream.readObject();
	}
	
	public void write(Packet p) throws Exception {
		this.outStream.writeObject(p);
	}
	
	public boolean hasCarta(Carta c) {
		return (this.mano.findCarta(c) != -1) ? true : false;
	}
}
