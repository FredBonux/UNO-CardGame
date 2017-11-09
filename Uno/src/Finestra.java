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
import Client.Card;

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

public class Finestra extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JPanel panel;
	private JLabel label_1;
	private JPanel panel_1;
	private JLabel label_2;
	private JLabel label_3;
	private Card label_4;

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
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(137, 287, 514, 135);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		
		label_4 = new Card();
		label_4.setBounds(564, 169, 87, 106);
		contentPane.add(label_4);
		contentPane.add(scrollPane);
		
		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(null);
		scrollPane.setViewportView(panel_1);
		
		label_2 = new JLabel("");
		panel_1.add(label_2);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
		scrollPane_1.setBounds(137, 26, 514, 135);
		contentPane.add(scrollPane_1);
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setBorder(null);
		panel.setOpaque(false);

		scrollPane_1.setViewportView(panel);
		for(int i = 0; i < 9; i++) {
			Card app = new Card();
			panel.add(app);

			Card app2 = new Card(new Carta(TipoCarta.Normale, Colore.GIALLO, i));
			panel_1.add(app2);
		}
		
		label = new JLabel("");
		try {
			BufferedImage bg = ImageIO.read(new File("./Bunker_-_The_Underground_Game_Background_Red_Dawn.jpg"));
			BufferedImage bgResized = resize(bg, 788, 475);
			label.setIcon(new ImageIcon(bgResized));
			contentPane.add(label);
			label.setBounds(0, 0, 788, 475);
		} catch (IOException e) {
			
		}
		
		
		/*image= MarvinImageIO.loadImage("./cards.png");
		crop(image.clone(), image, 0, 440, 74, 110);
		MarvinImageIO.saveImage(image, "./c.png");
		File app = new File("./c.png");
		ImageIcon an = new ImageIcon(app.getAbsolutePath());
		label_3.setIcon(an);
		
		JLabel label_4 = new JLabel("");
		label_4.setOpaque(false);
		label_4.setBounds(555, 164, 96, 112);
		panel.add(label_4);
		
		image= MarvinImageIO.loadImage("./cards.png");
		crop(image.clone(), image, 10, 300, 74, 110);
		MarvinImageIO.saveImage(image, "./c.png");
		ImageIcon an2 = new ImageIcon("./c.png");
		label_4.setIcon(an2);*/
		//JLabel lab=new JLabel("");
		//panel.add(lab);
		//lab.setIcon(an);
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
