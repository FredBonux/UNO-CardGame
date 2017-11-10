package Client;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Carte.Carta;
import Carte.Colore;
import Carte.TipoCarta;

public class Controller {
	
	static Finestra  fin;
	static FinestraColore fin2;
	static Card carta_tavolo;
	static Colore col;
	
	static void giocaCarta (Card c) {
		
		if(c.getCarta().getTipoCarta()==TipoCarta.Piu4 || c.getCarta().getTipoCarta()==TipoCarta.CambioColore){ 			//carte speciali
			if (c.getCarta().getTipoCarta()==TipoCarta.CambioColore) {
				fin2= new FinestraColore();
				carta_tavolo.getCarta().setColoreCarta(col);
				if (col==Colore.ROSSO) fin.getSfondoCarta().setBackground(new Color(255,51,51,150));
				if (col==Colore.GIALLO) fin.getSfondoCarta().setBackground(new Color(255,168,4,150));
				if (col==Colore.VERDE) fin.getSfondoCarta().setBackground(new Color(52,175,52,150));
				if (col==Colore.AZZURRO) fin.getSfondoCarta().setBackground(new Color(47,47,255,150));
			}
		}
		if(c.getCarta().getColoreCarta() == carta_tavolo.getCarta().getColoreCarta() || c.getCarta().getNumeroCarta() == carta_tavolo.getCarta().getNumeroCarta()){		//controllo colore e numero
			Container parent = c.getParent();
			parent.remove(c);
			parent.validate();
			parent.repaint();
			carta_tavolo=c;
		}
		else {
			JOptionPane.showMessageDialog(fin,"Questa carta non può essere giocata!");
		}
	}
		
	
	
	static void setCartaTavolo (Carta c) {
		carta_tavolo=new Card(c);
		fin.setCard(carta_tavolo);
	}

	static void setColore (Colore color) {
		col=color;
	}
	
	
}
