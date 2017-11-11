package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Utility.Evento;
import Utility.Giocatore;
import Utility.Packet;

public class Server extends Thread{
	
	private ServerSocket serverSocket;
	private boolean isRunning = true;
	private ArrayList<ServerMatch> matches;
	public Server(int port) throws IOException {
		this.serverSocket = new ServerSocket(((port > 1000) ? port : 9999));
		this.matches = new ArrayList<ServerMatch>();
	}
	
	public void run() {
		while(isRunning) {
			try {
				Socket home = this.serverSocket.accept(); //Si connette il primo giocatore di una partita
					System.out.println("SERVER: socket attivo -> " + home.toString());
					Giocatore g1 = new Giocatore(home);
				Socket away = this.serverSocket.accept(); //Si connette il secondo
					System.out.println("SERVER: socket attivo -> " + away.toString());
					Giocatore g2 = new Giocatore(away);
				ServerMatch sm = (new ServerMatch(g1,g2)); //Creo la partita
				this.matches.add(sm); //Aggiungo la partita alla lista di partite attive
				sm.start(); //Inizia la partita
				
			} catch (IOException e) {
				e.printStackTrace();
				this.isRunning = false;
			}
		}
	}
	
	public void kill() {
		this.isRunning = false;
	}

}
