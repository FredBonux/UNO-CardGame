package Utility;

import java.io.ObjectInputStream;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class InputReadBuffered extends Thread{
	private ObjectInputStream ois;
	private Queue<Packet> buffer;
	private Semaphore semaforo = new Semaphore(0);
	
	public InputReadBuffered(ObjectInputStream in) {
		this.ois = in;
		this.buffer = new ArrayDeque<Packet>();
		this.start();
	}
	
	public void run() {
		boolean hasError = false;
		while(!hasError) {
			try {
				Packet p = (Packet) ois.readObject();
				System.out.println("ARRIVATO : " + p + " -> " + p.getEvento());
				buffer.add(p);
				semaforo.release();
			} catch (Exception e) {
				hasError = true;
				e.printStackTrace();
			}
		}
	}
	
	public Packet pop() throws Exception{
		semaforo.acquire();
		return buffer.poll();
	}
	
}
