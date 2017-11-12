package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Carte.Colore;
import Server.ServerView;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import java.awt.event.ActionEvent;

public class FinestraColore extends JDialog implements ActionListener{

	private JPanel contentPane;
	private JButton btnRosso;
	private JButton btnGiallo;
	private JButton btnVerde;
	private JButton btnBlu;
	public Colore coloreScelto;
	private FinestraColore that;
	
	/**
	 * Create the frame.
	 */
	public FinestraColore() {
		that = this;
		setResizable(false);
		Point location = MouseInfo.getPointerInfo().getLocation(); 
		int x = (int) location.getX();
		int y = (int) location.getY();
		setBounds(x, y, 80, 80);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnRosso = new JButton("");
		btnRosso.setBorder(null);
		btnRosso.addActionListener(this);
		btnRosso.setIcon(new ImageIcon("./img/red.jpg"));
		btnRosso.setBackground(Color.RED);
		btnRosso.setBounds(0, 0, 40, 40);
		contentPane.add(btnRosso);
		
		btnGiallo = new JButton("");
		btnGiallo.setBorder(null);
		btnGiallo.addActionListener(this);
		btnGiallo.setIcon(new ImageIcon("./img/giallo.jpg"));
		btnGiallo.setBackground(Color.YELLOW);
		btnGiallo.setBounds(40, 0, 40, 40);
		contentPane.add(btnGiallo);
		
		btnVerde = new JButton("");
		btnVerde.setBorder(null);
		btnVerde.addActionListener(this);
		btnVerde.setIcon(new ImageIcon("./img/green.png"));
		btnVerde.setBackground(Color.GREEN);
		btnVerde.setBounds(0, 40, 40, 40);
		contentPane.add(btnVerde);
		
		btnBlu = new JButton("");
		btnBlu.setBorder(null);
		btnBlu.addActionListener(this);
		btnBlu.setIcon(new ImageIcon("./img/blue.png"));
		btnBlu.setActionCommand("BLU");
		btnBlu.setBackground(Color.BLUE);
		btnBlu.setBounds(40, 40, 40, 40);
		contentPane.add(btnBlu);
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setVisible(true);
	}

	public JButton getBtnRosso() {
		return btnRosso;
	}

	public JButton getBtnGiallo() {
		return btnGiallo;
	}

	public JButton getBtnVerde() {
		return btnVerde;
	}

	public JButton getBtnBlu() {
		return btnBlu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBlu) {
			coloreScelto = Colore.AZZURRO;
			dispose();
		}else if(e.getSource() == btnRosso) {
			coloreScelto = Colore.ROSSO;
			dispose();
		}else if(e.getSource() == btnGiallo) {
			coloreScelto = Colore.GIALLO;
			dispose();
		}else if(e.getSource() == btnVerde) {
			coloreScelto = Colore.VERDE;
			dispose();
		}
		
	}
}
