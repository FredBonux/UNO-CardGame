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
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;

public class Finestra extends JFrame {

	private JPanel contentPane;
	private JLabel sfondo;
	private JScrollPane scrollPane_mano;
	private JScrollPane scrollPane_mano_avv;
	private JPanel panel_mano_avv;
	private JPanel panel_mano;
	private JLabel label_2;
	private Card mazzo;
	private JButton btnUno;
	private Card card;
	private JPanel sfondoCarta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Finestra frame = new Finestra();
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
	public Finestra() {
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
		scrollPane_mano.setBounds(137, 287, 514, 137);
		scrollPane_mano.setOpaque(false);
		scrollPane_mano.getViewport().setOpaque(false);
		
		mazzo = new Card();
		mazzo.setBounds(564, 169, 87, 106);
		contentPane.add(mazzo);
		contentPane.add(scrollPane_mano);
		
		panel_mano = new JPanel();
		panel_mano.setOpaque(false);
		panel_mano.setBorder(null);
		scrollPane_mano.setViewportView(panel_mano);
		
		label_2 = new JLabel("");
		panel_mano.add(label_2);
		
		scrollPane_mano_avv = new JScrollPane();
		scrollPane_mano_avv.setBorder(null);
		scrollPane_mano_avv.setOpaque(false);
		scrollPane_mano_avv.getViewport().setOpaque(false);
		scrollPane_mano_avv.setBounds(137, 26, 514, 137);
		contentPane.add(scrollPane_mano_avv);
		
		panel_mano_avv = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_mano_avv.getLayout();
		panel_mano_avv.setBorder(null);
		panel_mano_avv.setOpaque(false);

		scrollPane_mano_avv.setViewportView(panel_mano_avv);
		
		sfondo = new JLabel("");
		
		sfondoCarta = new JPanel();
		sfondoCarta.setBackground(new Color(0, 0, 0, 0));
		sfondoCarta.setBounds(354, 169, 73, 107);
		contentPane.add(sfondoCarta);
		
		card = new Card();
		card.setBounds(354, 169, 87, 106);
		contentPane.add(card);
		
		try {
			
			BufferedImage bg = ImageIO.read(new File("./img/bg.jpg"));
			BufferedImage bgResized = resize(bg, 788, 475);
			
			btnUno = new JButton("UNO!");
			btnUno.setEnabled(false);
			btnUno.setForeground(Color.BLACK);
			btnUno.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 38));
			btnUno.setBounds(137, 174, 157, 101);
			contentPane.add(btnUno);
			sfondo.setIcon(new ImageIcon(bgResized));
			contentPane.add(sfondo);
			sfondo.setBounds(0, 0, 788, 475);
			
			
		} catch (IOException e) {
			
		}
		
	}
	
	public JPanel getSfondoCarta() {
		return sfondoCarta;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
}
