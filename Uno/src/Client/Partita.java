package Client;

import java.net.Socket;

public class Partita extends Thread {
	
	private Socket socket;
	
	
	public Partita(Socket connection) {
		this.socket = connection;
	}

	public void run(){
		System.out.println("Ciao Mondo");
	}
	
	
}
