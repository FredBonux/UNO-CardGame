package Client;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import Carte.Carta;
import Carte.Colore;
import Carte.TipoCarta;
import Utility.Evento;
import Utility.Giocatore;
import Utility.Packet;

public class Controller {
	
	static Finestra fin;
	static Card carta_tavolo;
	static Giocatore giocatore;
	static Partita partita;
	static Semaphore playCheckSemaphore = new Semaphore(0);
	static boolean lastCheck = false;
	
	static void giocaCarta (Card c) {
		
		System.out.println("Tento di giocare: " + c.getCarta().getTipoCarta());
		
		if(c.getCarta().getTipoCarta()==TipoCarta.Piu4 || c.getCarta().getTipoCarta()==TipoCarta.CambioColore){ 			//carte speciali
			fin.setEnabled(false);
			FinestraColore fc = new FinestraColore();
			fin.setEnabled(true);
			c.getCarta().setColoreCarta(fc.coloreScelto);
			if(giocaSpeciale(c)) {
				rimuoviDaMano(c);
				setCartaTavolo(c.getCarta());
				partita.disableView();
			}else {
				JOptionPane.showMessageDialog(fin,"SERVER: Questa carta non puo' essere giocata!");
			}
		}else if(c.getCarta().getColoreCarta() == carta_tavolo.getCarta().getColoreCarta() || c.getCarta().getNumeroCarta() == carta_tavolo.getCarta().getNumeroCarta()){		//controllo colore e numero
			if(giocaNonSpeciale(c)) {
				System.out.println("Giocata Corretta!");
				cleanSfondoCarta();
				rimuoviDaMano(c);
				setCartaTavolo(c.getCarta());
				System.out.println("SETTATA");
				partita.disableView();
			}else {
				JOptionPane.showMessageDialog(fin,"SERVER: Questa carta non puo' essere giocata!");
			}
		}
		else {
			JOptionPane.showMessageDialog(fin,"Questa carta non puo' essere giocata!");
		}
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
	
	
}
