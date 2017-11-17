package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Regole extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblTutorial;
	private JTextPane txtpn;
	private JLabel lblCarte;
	private JLabel lblProcedimento;
	private JLabel lblCarteSpeciali;
	private JLabel lblPenalita;
	private JButton btnGioca;


	public Regole(StartView s) {
		setTitle("Tutorial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 583, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 567, 39);
		contentPane.add(panel);
		
		lblTutorial = new JLabel("TUTORIAL");
		lblTutorial.setFont(new Font("Swis721 Lt BT", Font.PLAIN, 19));
		panel.add(lblTutorial);
		
		txtpn = new JTextPane();
		txtpn.setBackground(SystemColor.control);
		txtpn.setEditable(false);
		txtpn.setBounds(10, 116, 547, 286);
		contentPane.add(txtpn);
		
		lblCarte = new JLabel("CARTE");
		lblCarte.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				txtpn.setText("19 carte di colore Blu che vanno dall' 1 al 9 (2 serie) pi� uno 0 \n19 carte di colore Rosso che vanno dall 1 al 9 (2 serie) pi� uno 0 \n19 carte di colore Verde che vanno dall 1 al 9 (2 serie) pi� uno 0 \n19 carte di colore Giallo che vanno dall 1 al 9 (2 serie) pi� uno 0\n8 carte Pesca Due dei quattro colori sopracitati \n8 carte Inverti o Cambio giro dei quattro colori sopracitati \n8 carte Salta o Stop dei quattro colori sopracitati \n4 carte Jolly o Cambio colore\n4 carte Jolly Pesca Quattro o Cambio colore - Pesca quattro\n4 carte bianche (utilizzabili in caso di smarrimento di una o pi� carte del mazzo");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtpn.setText("");
			}
		});
		lblCarte.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarte.setBounds(10, 46, 85, 59);
		contentPane.add(lblCarte);
		
		lblProcedimento = new JLabel("PROCEDIMENTO");
		lblProcedimento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				txtpn.setText("Il giocatore comincia il gioco  questi deve scartare una carta dalle proprie a disposizione, compatibile con quella in cima al mazzo scarti per colore o per numero. Ci� significa che la carta deve avere colore o numero uguale alla prima carta sul banco. Alternativamente, il giocatore pu� usare una carta Azione . Non � possibile scartare pi� di una carta per turno.\n\n\n Se il giocatore non ha a disposizione carte da scartare, ha l'obbligo di pescarne una dal mazzo pesca .Il giocatore ha l'obbligo di tirare una carta in suo possesso, se compatibile con quella che si trova sul mazzo scarti. \n\n\nQuando un giocatore scarta una delle sue due carte rimaste cos� rimanendo con una sola carta in mano, deve pronunciare \"UNO\".");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				txtpn.setText("");
			}
		});
		lblProcedimento.setHorizontalAlignment(SwingConstants.CENTER);
		lblProcedimento.setBounds(105, 46, 106, 59);
		contentPane.add(lblProcedimento);
		
		lblCarteSpeciali = new JLabel("CARTE SPECIALI");
		lblCarteSpeciali.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtpn.setText("Carta Inverti o Cambio giro: La carta Inverti ha lo stesso valore della carta Salta.\n\nCarta Salta: fa saltare un turno al giocatore successivo nel senso del gioco.\n\nCarta Pesca due carte: il giocatore successivo nel senso del gioco pesca due carte e salta il turno.\n\nCarta Jolly o Cambio colore: permette al giocatore di scegliere uno tra i quattro colori disponibili . Pu� essere giocata in qualsiasi momento e su qualsiasi carta, \n\n Carta Jolly: Costringe il giocatore successivo nel senso del gioco a pescare quattro carte ed a saltare il turno. La carta in questione consente, inoltre, a chi l'ha giocata di cambiare il colore del gioco. ");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtpn.setText("");
			}
		});
		
		lblCarteSpeciali.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarteSpeciali.setBounds(220, 46, 96, 59);
		contentPane.add(lblCarteSpeciali);
		
		lblPenalita = new JLabel("PENALITA'");
		lblPenalita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtpn.setText("Se ci si dimentica di esclamare \"UNO!\" quando si scarta la penultima carta e la cosa viene notificata da un altro giocatore, si devono pescare 2 carte di penalit� dal mazzo pesca.");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				txtpn.setText("");
			}
		});
		lblPenalita.setHorizontalAlignment(SwingConstants.CENTER);
		lblPenalita.setBounds(326, 46, 106, 59);
		contentPane.add(lblPenalita);
		
		btnGioca = new JButton("GIOCA");
		btnGioca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				s.setVisible(true);
			}
		});
		btnGioca.setBounds(442, 56, 106, 39);
		contentPane.add(btnGioca);
		setVisible(true);
	}

}
