package Client;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Utility.MotionPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartView extends JFrame {

	private JPanel contentPane;
	private JLabel logoBg;
	private JLabel lblIpServer;
	private JTextField ipServer;
	private JPanel panel;
	private JLabel label;
	private JTextField portaServer;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnX;
	private JFrame that = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		StartView frame = new StartView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public StartView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 277);
		contentPane = new MotionPanel(this);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			BufferedImage img = ImageIO.read(new File("img/UnoLogo.png"));
			ImageIcon icon = new ImageIcon(img);
			Color color2 = new Color(0,0,0,100);
			
			lblIpServer = new JLabel("IP SERVER:");
			lblIpServer.setHorizontalAlignment(SwingConstants.CENTER);
			lblIpServer.setForeground(Color.WHITE);
			lblIpServer.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lblIpServer.setBounds(3, 18, 444, 16);
			contentPane.add(lblIpServer);
			
			ipServer = new JTextField();
			ipServer.setText("localhost");
			ipServer.setHorizontalAlignment(SwingConstants.CENTER);
			ipServer.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			ipServer.setBounds(115, 38, 219, 41);
			contentPane.add(ipServer);
			ipServer.setColumns(10);
			Color c = new Color(0, 0, 0, 150);
			
			label = new JLabel("PORTA SERVER:");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setForeground(Color.WHITE);
			label.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			label.setBounds(3, 91, 444, 16);
			contentPane.add(label);
			
			portaServer = new JTextField();
			portaServer.setText("1234");
			portaServer.setHorizontalAlignment(SwingConstants.CENTER);
			portaServer.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			portaServer.setColumns(10);
			portaServer.setBounds(115, 111, 219, 41);
			contentPane.add(portaServer);
			
			btnNewButton_1 = new JButton("GAME ON");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String ip = ipServer.getText();
					if(ip.length() <= 0) return;
					String port = portaServer.getText();
					if(port.length() <= 0) return;
					try {
						Socket socket = new Socket(ip, Integer.parseInt(port));
						Partita p = new Partita(socket);
						p.start();
						dispose();
						return;
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(that, "Non è stato possibile connettersi al server!", "ERRORE!", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				}
			});
			btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			btnNewButton_1.setBounds(130, 164, 190, 96);
			btnNewButton_1.setBackground(new Color(255, 255, 255));
			contentPane.add(btnNewButton_1);
			
			btnX = new JButton("X");
			btnX.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					System.exit(0);
				}
			});
			btnX.setBounds(418, 0, 32, 34);
			contentPane.add(btnX);
			panel = new JPanel();
			panel.setBackground(c);
			panel.setBounds(0, 0, 450, 278);
			contentPane.add(panel);
			panel.setLayout(null);
			
			logoBg = new JLabel("");
			logoBg.setHorizontalAlignment(SwingConstants.CENTER);
			logoBg.setBounds(0, 0, 450, 278);
			contentPane.add(logoBg);
			logoBg.setBackground(color2);
			logoBg.setIcon(icon);
			
			setUndecorated(true);
		} catch (Exception e) {
		}
		
		//this.setUndecorated(true);
	}
}
