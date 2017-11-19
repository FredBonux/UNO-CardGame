package Client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Carte.Mano;
import Main.Client;
import Utility.Evento;
import Utility.Giocatore;
import Utility.InputReadBuffered;
import Utility.Packet;

public class Partita extends Thread {
	private Giocatore giocatore;
	public static boolean isGame = true;
	private Finestra f;
	private GameReadThread grt;
	private Clip mainClip;
	private boolean audio = true;
	public boolean isMyTurn = false;
	
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
			
			playSong();
			
			
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
		audio = false;
		mainClip.stop();
		Client.reload();
	}

	public void subisci(Packet p) {
		this.cartaAudio();
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
		
		f.revalidate();
		f.repaint();
	}
	
	public void defeat() {
		//Stoppo i Thread di lettura e esecuzione
		isGame = false;
		grt.isGameOn = false; 
		giocatore.getIrb().hasError = true;
		//Mostro la vittoria
		new LoseDialog();
		f.dispose(); 
		audio = false;
		mainClip.stop();
		Client.reload();
	}
	
	public void otherPesca() {
		this.cartaAudio();
		Controller.avversarioPesca();
	}
	
	public void otherTurn() {
		this.isMyTurn = false;
		f.getInAttesaLbl().setVisible(true);
		f.getPanel_mano().setEnabled(false);
		//f.setEnabled(false);
	}
	
	public void myTurn() {
		this.isMyTurn = true;
		f.getInAttesaLbl().setVisible(false);
		f.setEnabled(true);
	}
	
	
	public void disableView() {
		f.getInAttesaLbl().setVisible(true);
		f.getPanel_mano().setEnabled(false);
		//f.setEnabled(false);
	}
	
	public InputReadBuffered getIrb() {
		return this.giocatore.getIrb();
	}
	
	private void playSong() {
		try {
	         // Open an audio input stream.           
	          File soundFile = new File("./snd/canzone.wav"); //you could also get the sound file with an URL
	          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
	         // Get a sound clip resource.
	         mainClip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         mainClip.open(audioIn);
	         mainClip.loop(Clip.LOOP_CONTINUOUSLY);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }	
	}
	
	public void toggleAudio() {
		if(this.audio) {
			mainClip.stop();
			this.audio = false;
			this.f.getBtnMute().setIcon(new ImageIcon(Finestra.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaUnmute.png")));
		}else {
	        mainClip.loop(Clip.LOOP_CONTINUOUSLY);
			this.audio = true;
			this.f.getBtnMute().setIcon(new ImageIcon(Finestra.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaMute.png")));
		}
	}
	public void cartaAudio() {
		if(this.audio == false) return;
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
	}
	public void playUnoSound() {
		if(!this.audio) return;
		try {
	         // Open an audio input stream.           
	          File soundFile = new File("./snd/Uno.wav"); //you could also get the sound file with an URL
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
	}
}


