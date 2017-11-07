import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Server.Server;
import Utility.Packet;

public class Prova {

	public static void main(String[] args) {
		System.out.println("Ciao");
		try {
			Server s = new Server(9999);
			s.start();
			
			new Thread(){
				public void run() {
					Socket c1 = null;
					try {
						c1 = new Socket("localhost", 9999);
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					ObjectInputStream ois1 = null;
					try {
						ois1 = new ObjectInputStream(c1.getInputStream());
						while(true) {
							System.out.println("C2) Evento; " + ((Packet) ois1.readObject()).getEvento());
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
			
			new Thread(){
				public void run() {
					Socket c1 = null;
					try {
						c1 = new Socket("localhost", 9999);
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					ObjectInputStream ois1 = null;
					try {
						ois1 = new ObjectInputStream(c1.getInputStream());
						while(true) {
							System.out.println("C1) Evento; " + ((Packet) ois1.readObject()).getEvento());
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
