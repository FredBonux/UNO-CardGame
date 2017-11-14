package Client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import Carte.Mano;
import Utility.Evento;
import Utility.Giocatore;
import Utility.InputReadBuffered;
import Utility.Packet;

public class Partita extends Thread {
	private Giocatore giocatore;
	public static boolean isGame = true;
	private Finestra f;
	private GameReadThread grt;
	
	public Partita(Socket connection) throws IOException, ClassNotFoundException {
		System.out.println("Carico le risorse");
		Card.loadSources();
		System.out.println("Risorse Caricate... In attesa della connessione");
		this.giocatore = new Giocatore(connection);
	}

	public void run(){
		AttesaConnessione attesa = new AttesaConnessione();
		try {
			Packet p = this.giocatore.read(); //Leggi mano iniziale
			attesa.dispose();
			if(p.getEvento() != Evento.mano) throw new Exception("Evento non corretto!");
			this.giocatore.setMano(new Mano(p.getCarte()));
			Controller.setPartita(this);
			Controller.setGiocatore(this.giocatore);
			f = new Finestra();
			Controller.setFinestra(f);
			Controller.setCartaTavolo(p.getCartaSubita());
			grt = new GameReadThread(this);
			try {
		         // Open an audio input stream.           
		          File soundFile = new File("./snd/inizio.wav"); //you could also get the sound file with an URL
		          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
		         // Get a sound clip resource.
		         Clip clip = AudioSystem.getClip();
		         // Open audio clip and load samples from the audio input stream.
		         clip.open(audioIn);
		         clip.start();
		      } catch (UnsupportedAudioFileException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      } catch (LineUnavailableException e) {
		         e.printStackTrace();
		      }
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void callToAction(Packet p) {
		switch (p.getEvento()) {
		case turno:
			myTurn();
			break;
		case aspetta:
			otherTurn();
			break;
		case pesco:
			otherPesca();
			break;
		case sconfitta:
			defeat();
			break;
		case subisci:
			subisci(p);
			break;
		case vittoria:
			win();
			break;	
		default: 
			System.out.println("ERRORE: " + p.getEvento());
			break;
		}
	}
	
	public void win() {
		//Stoppo i Thread di lettura e esecuzione
		isGame = false;
		grt.isGameOn = false; 
		giocatore.getIrb().hasError = true;
		//Mostro la vittoria
		new WinDialog();
		f.dispose();
		System.exit(0);
	}

	public void subisci(Packet p) {
		try {
	         // Open an audio input stream.           
	          File soundFile = new File("./snd/carta.wav"); //you could also get the sound file with an URL
	          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
	         // Get a sound clip resource.
	         Clip clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioIn);
	         clip.start();
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
		Controller.setCartaTavolo(p.getCartaSubita());
		Controller.avversarioGioca();
		switch (p.getCartaSubita().getTipoCarta()) {
		case Piu2:
			giocatore.getMano().aggiungiCarta(p.getCarte().get(0));
			f.getPanel_mano().add(new Card(p.getCarte().get(0)));
			giocatore.getMano().aggiungiCarta(p.getCarte().get(1));
			f.getPanel_mano().add(new Card(p.getCarte().get(1)));
			break;
		case Piu4:
			giocatore.getMano().aggiungiCarta(p.getCarte().get(0));
			f.getPanel_mano().add(new Card(p.getCarte().get(0)));
			giocatore.getMano().aggiungiCarta(p.getCarte().get(1));
			f.getPanel_mano().add(new Card(p.getCarte().get(1)));
			giocatore.getMano().aggiungiCarta(p.getCarte().get(2));
			f.getPanel_mano().add(new Card(p.getCarte().get(2)));
			giocatore.getMano().aggiungiCarta(p.getCarte().get(3));
			f.getPanel_mano().add(new Card(p.getCarte().get(3)));
			break;
		}
	}
	
	public void defeat() {
		//Stoppo i Thread di lettura e esecuzione
		isGame = false;
		grt.isGameOn = false; 
		giocatore.getIrb().hasError = true;
		//Mostro la vittoria
		JOptionPane.showMessageDialog(f, "HAI PERSO!", "La partita e' finita!", JOptionPane.WARNING_MESSAGE);
		f.dispose();
		System.exit(0);
	}
	
	public void otherPesca() {
		Controller.avversarioPesca();
	}
	
	public void otherTurn() {
		f.getInAttesaLbl().setVisible(true);
		f.setEnabled(false);
	}
	
	public void myTurn() {
		f.getInAttesaLbl().setVisible(false);
		f.setEnabled(true);
	}
	
	
	public void disableView() {
		f.getInAttesaLbl().setVisible(true);
		f.setEnabled(false);
	}
	
	public InputReadBuffered getIrb() {
		return this.giocatore.getIrb();
	}
	
}
