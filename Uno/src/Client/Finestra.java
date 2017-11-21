package Client;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Carte.Carta;
import Carte.Colore;
import Carte.TipoCarta;
import Utility.Giocatore;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import marvin.image.MarvinImage; 
import marvin.io.MarvinImageIO;
import marvin.plugin.MarvinImagePlugin;
import marvin.util.MarvinPluginLoader;

import static marvin.MarvinPluginCollection.*;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class Finestra extends JFrame {

	private JPanel contentPane;
	private JLabel sfondo;
	private JScrollPane scrollPane_mano;
	private JScrollPane scrollPane_mano_avv;
	private JPanel panel_mano_avv;
	private JPanel panel_mano;
	private Card mazzo;
	private JButton btnUno;
	private Card card;
	private JPanel sfondoCarta;
	
	private Giocatore giocatore;
	private JLabel lblInAttesaDella;
	private JButton btnMute;

	/**
	 * Create the frame.
	 */
	public Finestra() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/UnoLogo.png"));
		setBackground(new Color(153, 0, 0));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 475);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane_mano = new JScrollPane();
		scrollPane_mano.setBorder(null);
		scrollPane_mano.setBounds(6, 287, 776, 150);
		scrollPane_mano.setOpaque(false);
		scrollPane_mano.getViewport().setOpaque(false);
		
		lblInAttesaDella = new JLabel("In attesa della mossa ...");
		lblInAttesaDella.setBackground(new Color(0, 0, 0, 150));
		lblInAttesaDella.setOpaque(true);
		lblInAttesaDella.setForeground(Color.WHITE);
		lblInAttesaDella.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 22));
		lblInAttesaDella.setHorizontalAlignment(SwingConstants.CENTER);
		lblInAttesaDella.setBounds(0, 0, 788, 453);
		lblInAttesaDella.setVisible(false);
		
		btnMute = new JButton("");
		btnMute.setForeground(Color.GRAY);
		btnMute.setBackground(new Color(0,0,0,100));
		btnMute.setBorderPainted(false);
		btnMute.setOpaque(true);
		btnMute.setIcon(new ImageIcon(Finestra.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaMute.png")));
		btnMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.toggleAudio();
			}
		});
		btnMute.setBounds(22, 206, 41, 41);
		contentPane.add(btnMute);
		contentPane.add(lblInAttesaDella);
		
		mazzo = new Card();
		mazzo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Controller.isMyTurn())
					Controller.pesca();
			}
		});
		mazzo.setBounds(564, 169, 87, 106);
		contentPane.add(mazzo);
		contentPane.add(scrollPane_mano);
		
		panel_mano = new JPanel();
		panel_mano.setOpaque(false);
		panel_mano.setBorder(null);
		for(int i = 0; i < Controller.giocatore.getMano().getMano().size(); i++) {
			System.out.println("Carta: " + Controller.giocatore.getMano().getMano().get(i).getTipoCarta() + " " + Controller.giocatore.getMano().getMano().get(i).getNumeroCarta() + " " + Controller.giocatore.getMano().getMano().get(i).getColoreCarta());
			panel_mano.add(new Card(Controller.giocatore.getMano().getMano().get(i)));
		}
		
		scrollPane_mano.setViewportView(panel_mano);
		
		scrollPane_mano_avv = new JScrollPane();
		scrollPane_mano_avv.setBorder(null);
		scrollPane_mano_avv.setOpaque(false);
		scrollPane_mano_avv.getViewport().setOpaque(false);
		scrollPane_mano_avv.setBounds(6, 5, 776, 150);
		contentPane.add(scrollPane_mano_avv);
		
		panel_mano_avv = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_mano_avv.getLayout();
		panel_mano_avv.setBorder(null);
		panel_mano_avv.setOpaque(false);
		scrollPane_mano_avv.setViewportView(panel_mano_avv);

		//Popolo lo scrollPane dell'avversario
		for(int i = 0; i < 7; i++) {
			panel_mano_avv.add(new Card());
		}
		
		sfondo = new JLabel("");
		
		sfondoCarta = new JPanel();
		sfondoCarta.setBackground(new Color(0, 0, 0, 0));
		sfondoCarta.setBounds(354, 169, 73, 107);
		contentPane.add(sfondoCarta);
		
		card = new Card();
		card.setBounds(354, 169, 73, 106);
		contentPane.add(card);
		
		try {
			
			BufferedImage bg = ImageIO.read(new File("./img/bg.jpg"));
			BufferedImage bgResized = resize(bg, 788, 475);
			
			btnUno = new JButton("UNO!");
			btnUno.setEnabled(false);
			btnUno.setForeground(Color.BLACK);
			btnUno.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 38));
			btnUno.setContentAreaFilled(false); 
		    btnUno.setFocusPainted(false);
			//setBackground(new Color(0, 0, 0, 0));
			btnUno.setBounds(137, 174, 157, 101);
			contentPane.add(btnUno);
			sfondo.setIcon(new ImageIcon(bgResized));
			contentPane.add(sfondo);
			sfondo.setBounds(0, 0, 788, 475);
			
			this.setVisible(true);
			
		} catch (IOException e) {
			
		}	
	}
	
	public JPanel getSfondoCarta() {
		return sfondoCarta;
	}

	public void setCard(Card c) {
		this.card.updateCarta(c.getCarta());
	}

	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	
	public JLabel getInAttesaLbl() {
		System.out.println("LBL");
		return this.lblInAttesaDella;
	}

	public JPanel getPanel_mano_avv() {
		return panel_mano_avv;
	}

	public JPanel getPanel_mano() {
		return panel_mano;
	}

	public JButton getBtnUno() {
		return btnUno;
	}
	
	public JButton getBtnMute() {
		return this.btnMute;
	}
}
