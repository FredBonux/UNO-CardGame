package Client;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Carte.Carta;
import Carte.Colore;
import Carte.TipoCarta;
import Utility.Evento;
import Utility.Giocatore;
import Utility.Packet;
import sun.audio.*;
import  java.io.*;

public class Controller{
	
	static Finestra fin;
	static Card carta_tavolo;
	static Giocatore giocatore;
	static Partita partita;
	static Semaphore playCheckSemaphore = new Semaphore(0);
	static boolean lastCheck = false;
	static Packet lastPacket;
	static boolean hasPenality = false;
	static boolean unoCalled = false;
	
	static void giocaCarta (Card c) {
		
		System.out.println("Tento di giocare: " + c.getCarta().getTipoCarta());
		
		if(giocataCartaControl(c.getCarta()) == false) {
			JOptionPane.showMessageDialog(fin,"Questa carta non puo' essere giocata!");
			return;
		}
		
		if(c.getCarta().getTipoCarta()==TipoCarta.Piu4 || c.getCarta().getTipoCarta()==TipoCarta.CambioColore){ 			//carte speciali
			fin.setEnabled(false);
			FinestraColore fc = new FinestraColore();
			fin.setEnabled(true);
			c.getCarta().setColoreCarta(fc.coloreScelto);
			if(giocaSpeciale(c)) {
				if(TipoCarta.Piu4 == c.getCarta().getTipoCarta()) 
					for(int i = 0; i < 4; i++) avversarioPesca();
				rimuoviDaMano(c);
				setCartaTavolo(c.getCarta());
				//partita.disableView();
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
			}else {
				JOptionPane.showMessageDialog(fin,"SERVER: Questa carta non puo' essere giocata!");
			}
		}else {		//controllo colore e numero
			if(giocaNonSpeciale(c)) {
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
				if(TipoCarta.Piu2 == c.getCarta().getTipoCarta()) 
					for(int i = 0; i < 2; i++) avversarioPesca();
				cleanSfondoCarta();
				rimuoviDaMano(c);
				setCartaTavolo(c.getCarta());
				System.out.println("SETTATA");
				//partita.disableView();
			}else {
				JOptionPane.showMessageDialog(fin,"SERVER: Questa carta non puo' essere giocata!");
			}
		}
	}
	
	private static boolean giocataCartaControl(Carta c) {
		
		Carta pop = carta_tavolo.getCarta();	//leggo ultima carta del tavolo 
		
		if(c.getTipoCarta()==TipoCarta.Piu4 || c.getTipoCarta()==TipoCarta.CambioColore){ 					//carte speciali
			return true;
		}
		
		if(c.getTipoCarta()==TipoCarta.Piu2 &&(pop.getColoreCarta()==c.getColoreCarta() || pop.getTipoCarta()==c.getTipoCarta())){	//carte speciali
			return true;
		}
		
		
		if(c.getTipoCarta()==TipoCarta.CambioGiro &&(pop.getColoreCarta()==c.getColoreCarta() || pop.getTipoCarta()==c.getTipoCarta())){	//carte speciali
			return true;
		}
		
		if(c.getTipoCarta()==TipoCarta.Stop &&(pop.getColoreCarta()==c.getColoreCarta() || pop.getTipoCarta()==c.getTipoCarta())){	//carte speciali
			return true;
		}
		
		if(c.getColoreCarta() == pop.getColoreCarta() || c.getNumeroCarta() == pop.getNumeroCarta()){		//controllo colore e numero
			return true; 
		}
		
		return false;
	}
	
	private static void rimuoviDaMano(Card c) {
		Container parent = c.getParent();
		parent.remove(c);
		parent.validate();
		parent.repaint();
	}
	
	private static void cleanSfondoCarta() {
		fin.getSfondoCarta().setVisible(false);
	}
	
	private static boolean giocaSpeciale(Card c) {
		Colore col = c.getCarta().getColoreCarta();
		if (col==Colore.ROSSO) fin.getSfondoCarta().setBackground(new Color(255,51,51,150));
		if (col==Colore.GIALLO) fin.getSfondoCarta().setBackground(new Color(255,168,4,150));
		if (col==Colore.VERDE) fin.getSfondoCarta().setBackground(new Color(52,175,52,150));
		if (col==Colore.AZZURRO) fin.getSfondoCarta().setBackground(new Color(47,47,255,150));
		fin.getSfondoCarta().setVisible(true);
		return giocaNonSpeciale(c);
	}
	
	private static boolean giocaNonSpeciale(Card c) {
		try {
			if(giocatore.getMano().getMano().size() < 3) {
				giocatore.write(new Packet(Evento.butto, c.getCarta(), unoCalled));
				unoCalled = false;
			}else
				giocatore.write(new Packet(Evento.butto, c.getCarta()));
			playCheckSemaphore.acquire();
			return (lastCheck) ? true : false;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	} 
		
	
	
	static void setCartaTavolo (Carta c) {
		carta_tavolo=new Card(c);
		fin.setCard(carta_tavolo);
		if(c.getTipoCarta() == TipoCarta.Piu4 || c.getTipoCarta() == TipoCarta.CambioColore) {
			Colore col = c.getColoreCarta();
			if (col==Colore.ROSSO) fin.getSfondoCarta().setBackground(new Color(255,51,51,150));
			if (col==Colore.GIALLO) fin.getSfondoCarta().setBackground(new Color(255,168,4,150));
			if (col==Colore.VERDE) fin.getSfondoCarta().setBackground(new Color(52,175,52,150));
			if (col==Colore.AZZURRO) fin.getSfondoCarta().setBackground(new Color(47,47,255,150));
			fin.getSfondoCarta().setVisible(true);
		}else {
			cleanSfondoCarta();
		}
	}

	
	static void setGiocatore(Giocatore g) {
		giocatore = g;
	}
	
	static void setFinestra(Finestra f) {
		fin = f;
	}
	
	static void setPartita(Partita p) {
		partita = p;
	}
	
	static void avversarioPesca() {
		fin.getPanel_mano_avv().add(new Card());
	}
	
	static void avversarioGioca() {
		fin.getPanel_mano_avv().remove(0);
	}
	
	static void penalita(Packet p) {
		for(int i = 0; i < p.getCarte().size(); i++) {
			Carta c = p.getCarte().get(i);
			giocatore.getMano().aggiungiCarta(c);
			fin.getPanel_mano().add(new Card(c));
		}
		Controller.hasPenality = true;
	}
	
	static void pesca() {
		try {
			giocatore.write(new Packet(Evento.pesco));
			playCheckSemaphore.acquire();
			Carta pescata = lastPacket.getCartaSubita();
			giocatore.getMano().aggiungiCarta(pescata);
			fin.getPanel_mano().add(new Card(pescata));
			if(lastCheck) {
				
			}
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
