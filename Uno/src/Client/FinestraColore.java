package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Carte.Colore;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FinestraColore extends JFrame {

	private JPanel contentPane;
	private JButton btnRosso;
	private JButton btnGiallo;
	private JButton btnVerde;
	private JButton btnBlu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinestraColore frame = new FinestraColore();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FinestraColore() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnRosso = new JButton("");
		btnRosso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.setColore(Colore.ROSSO);
				dispose();
			}
		});
		btnRosso.setIcon(new ImageIcon("./img/red.jpg"));
		btnRosso.setBackground(Color.RED);
		btnRosso.setBounds(0, 0, 172, 152);
		contentPane.add(btnRosso);
		
		btnGiallo = new JButton("");
		btnGiallo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.setColore(Colore.GIALLO);
				dispose();
			}
		});
		btnGiallo.setIcon(new ImageIcon("./img/giallo.jpg"));
		btnGiallo.setBackground(Color.YELLOW);
		btnGiallo.setBounds(171, 0, 172, 152);
		contentPane.add(btnGiallo);
		
		btnVerde = new JButton("");
		btnVerde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.setColore(Colore.VERDE);
				dispose();
			}
		});
		btnVerde.setIcon(new ImageIcon("./img/green.png"));
		btnVerde.setBackground(Color.GREEN);
		btnVerde.setBounds(0, 152, 172, 157);
		contentPane.add(btnVerde);
		
		btnBlu = new JButton("");
		btnBlu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.setColore(Colore.AZZURRO);
				dispose();
			}
		});
		btnBlu.setIcon(new ImageIcon("./img/blue.png"));
		btnBlu.setBackground(Color.BLUE);
		btnBlu.setBounds(171, 152, 172, 157);
		contentPane.add(btnBlu);
		setUndecorated(true);
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
}
