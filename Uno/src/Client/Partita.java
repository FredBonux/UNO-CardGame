package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Utility.Evento;
import Utility.Packet;

public class Partita extends Thread {
	
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos; 
	
	
	public Partita(Socket connection) throws IOException, ClassNotFoundException {
		this.socket = connection;
		System.out.println("Salvato socket");
		this.ois = new ObjectInputStream(socket.getInputStream());
		this.oos = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("Creato ois");
		Packet first = (Packet) this.ois.readObject();
		System.out.println("Primo: " + first.getEvento());
	}

	public void run(){
		AttesaConnessione attesa = new AttesaConnessione();
		try {
			Packet p = (Packet) this.ois.readObject();
			attesa.dispose();
			if(p.getEvento() != Evento.mano) throw new Exception("Evento non corretto!");
			Finestra f = new Finestra();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
