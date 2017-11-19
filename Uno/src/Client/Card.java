package Client;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import Carte.Carta;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class Card extends JButton implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4707778825339408131L;
	private Carta carta;
	private static final int DIMX = 73;
	private static final int DIMY = 110;

	private static final int BACKX = 0;
	private static final int BACKY = 439;
	private ImageIcon icon;
	private static BufferedImage source;
	
	
	public Card(Carta c) {
		super("");
	    setContentAreaFilled(false); 
	    setFocusPainted(false); 
	    setOpaque(false);
		setMargin(new Insets(-4,-4,-4,-4));
		setBorder(null);
		setBorderPainted(false);
		setBackground(new Color(0, 0, 0, 0));
		try {
			this.carta = c;
			this.updateImage();
			this.addActionListener(this);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Card() {
		setContentAreaFilled(false); 
		setFocusPainted(false); 
	    setOpaque(false);
		setMargin(new Insets(-4,-4,-4,-4));
		setBorder(null);
		setBorderPainted(false);
		setBackground(new Color(0, 0, 0, 0));
		try {
			this.updateRetro();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateCarta(Carta c) {
		this.carta = c;
		this.updateImage();
	}
	
	private void updateRetro() {
		this.icon = new ImageIcon(makeRoundedCorner(source.getSubimage(BACKX, BACKY, DIMX, DIMY), 25));
		this.setIcon(icon);
	}
	
	private synchronized void updateImage() {
		this.icon = new ImageIcon(makeRoundedCorner(source.getSubimage(this.carta.getX(), this.carta.getY(), DIMX, DIMY), 25));
		this.setIcon(icon);
	}
	
	private static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
	    int w = image.getWidth();
	    int h = image.getHeight();
	    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2 = output.createGraphics();

	    // This is what we want, but it only does hard-clipping, i.e. aliasing
	    // g2.setClip(new RoundRectangle2D ...)

	    // so instead fake soft-clipping by first drawing the desired clip shape
	    // in fully opaque white with antialiasing enabled...
	    g2.setComposite(AlphaComposite.Src);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(Color.WHITE);
	    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

	    // ... then compositing the image on top,
	    // using the white shape from above as alpha source
	    g2.setComposite(AlphaComposite.SrcAtop);
	    g2.drawImage(image, 0, 0, null);

	    g2.dispose();

	    return output;
	}
	public Carta getCarta () {
		return carta;
	}
	
	private static BufferedImage loadSource() {
		try {
			return ImageIO.read(new File("./img/cards.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void loadSources() {
		source = loadSource();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(Controller.isMyTurn() == false) return;
 		Controller.giocaCarta(this);
		
	}
}
