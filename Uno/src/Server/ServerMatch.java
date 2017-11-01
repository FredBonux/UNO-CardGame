package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerMatch extends Thread{
	
	private Socket home = null; //Giocatore 1
	private Socket away = null; //Giocatore 2
	private ObjectOutputStream homeOutStream;
	private ObjectOutputStream awayOutStream;
	/*private Mazzo mazzo = null;
	private Mano manoHome = null;
	private Mano manoAway = null;*/
	
	private boolean isRunning = true;
	
	public ServerMatch(Socket home, Socket away) throws IOException {
		this.home = home;
		this.away = away;

		this.homeOutStream = new ObjectOutputStream(this.home.getOutputStream());
		this.awayOutStream = new ObjectOutputStream(this.away.getOutputStream());
	}
	
	public void run() {
		//Inizia la partita
		/*for(int i = 0; i < 7; i++) { //Consegno 7 carte
			Carta cHome = mazzo.pesca();
			Carta cAway = mazzo.pesca();
			
		}*/
		while(this.isRunning) {
			
		}
	}
	
	public void kill() {
		this.isRunning = false;
	}

}
