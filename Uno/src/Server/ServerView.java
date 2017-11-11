package Server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerView extends JFrame {

	private JPanel contentPane;
	private JLabel lblUnoServer;
	private JPanel panel;
	private JLabel lblPorta;
	private JTextField txtPortaserver;
	private JButton btnAvvia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerView frame = new ServerView();
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
	public ServerView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		lblUnoServer = new JLabel("UNO SERVER");
		lblUnoServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnoServer.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		contentPane.add(lblUnoServer, BorderLayout.NORTH);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblPorta = new JLabel("PORTA");
		lblPorta.setBounds(126, 11, 61, 16);
		panel.add(lblPorta);
		
		txtPortaserver = new JTextField();
		txtPortaserver.setBounds(199, 6, 130, 26);
		panel.add(txtPortaserver);
		txtPortaserver.setColumns(10);
		setResizable(false);
		btnAvvia = new JButton("AVVIA");
		btnAvvia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtPortaserver.getText().length() <= 0) return;
				try {
					int porta = Integer.parseInt(txtPortaserver.getText());
					(new Server(porta)).start();
					contentPane.removeAll();
					contentPane.repaint();
					lblUnoServer.setText("SERVER ATTIVO, PORTA: " + porta);
					contentPane.add(lblUnoServer);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(btnAvvia, BorderLayout.SOUTH);
	}
}
