package Client;

import javax.swing.JOptionPane;

import Utility.Evento;
import Utility.InputReadBuffered;
import Utility.Packet;

public class GameReadThread extends Thread{
	private Partita partita;
	private InputReadBuffered irb;
	public boolean isGameOn = true;
	
	public GameReadThread(Partita p) {
		partita = p;
		irb = partita.getIrb();
		this.start();
	}
	
	public void run() {
		while(isGameOn) {
			try {
				Packet p = irb.pop();
				if(p != null) {
					switch (p.getEvento()) {
					case turno:
						if(Controller.hasPenality) {
							JOptionPane.showMessageDialog(null, "Hai subito una penalita'!", "AHI!", JOptionPane.ERROR_MESSAGE);
							Controller.hasPenality = false;
						}
						partita.myTurn();
						break;
					case aspetta:
						partita.otherTurn();
						break;
					case pesco:
						partita.otherPesca();
						break;
					case sconfitta:
						partita.defeat();
						break;
					case subisci:
						partita.subisci(p);
						break;
					case vittoria:
						partita.win();
						break;
					case okPlay:
						Controller.lastPacket = p;
						Controller.lastCheck = true;
						Controller.playCheckSemaphore.release();
						break;
					case badPlay: 
						Controller.lastPacket = p;
						Controller.lastCheck = false;
						Controller.playCheckSemaphore.release();
						break;
					case penalita:
						Controller.penalita(p);
						break;
					default: 
						System.out.println("ERRORE: " + p.getEvento());
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				isGameOn = false;
			}
		}
	}
}
