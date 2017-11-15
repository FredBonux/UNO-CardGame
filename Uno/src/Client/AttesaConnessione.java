package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.SwingConstants;
import java.awt.Color;

public class AttesaConnessione extends JFrame {

	private JPanel contentPane;
	private JLabel lblInAttesaDellavversario;
	private JLabel gif;

	/**
	 * Create the frame.
	 */
	public AttesaConnessione() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		lblInAttesaDellavversario = new JLabel("IN ATTESA DELL'AVVERSARIO");
		lblInAttesaDellavversario.setForeground(Color.WHITE);
		lblInAttesaDellavversario.setHorizontalAlignment(SwingConstants.CENTER);
		lblInAttesaDellavversario.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		contentPane.add(lblInAttesaDellavversario, BorderLayout.NORTH);
		
		ImageIcon gifImage = null;
		
		try {
			gifImage = new ImageIcon(new File("img/waiting.gif").toURI().toURL());
			gif = new JLabel(gifImage);
			gif.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
				}
			});
			contentPane.add(gif, BorderLayout.CENTER);
		}catch(Exception ex) {
			
		}
		setBounds(100, 100, gifImage.getIconWidth(), 22 + gifImage.getIconHeight());
		setVisible(true);
	}

}
