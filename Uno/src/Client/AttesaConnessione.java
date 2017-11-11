package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AttesaConnessione extends JFrame {

	private JPanel contentPane;
	private JLabel lblInAttesaDellavversario;

	/**
	 * Create the frame.
	 */
	public AttesaConnessione() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 141);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		lblInAttesaDellavversario = new JLabel("IN ATTESA DELL'AVVERSARIO");
		lblInAttesaDellavversario.setHorizontalAlignment(SwingConstants.CENTER);
		lblInAttesaDellavversario.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		contentPane.add(lblInAttesaDellavversario, BorderLayout.CENTER);
		
		setVisible(true);
	}

}
