package Client;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Carte.Carta;
import marvin.image.MarvinImage; 
import marvin.io.MarvinImageIO;
import marvin.plugin.MarvinImagePlugin;
import marvin.util.MarvinPluginLoader;

import static marvin.MarvinPluginCollection.*;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class Card extends JLabel {
	private Carta carta;
	private static final int DIMX = 74;
	private static final int DIMY = 110;

	private static final int BACKX = 0;
	private static final int BACKY = 439;
	private ImageIcon icon;
	private BufferedImage img;
	
	public Card(Carta c) {
		super("");
		try {
			this.carta = c;
			MarvinImage image= MarvinImageIO.loadImage("./cards.png");
			crop(image.clone(), image, c.getX(), c.getY(), DIMX, DIMY);
			MarvinImageIO.saveImage(image, "c.png");
			this.img = ImageIO.read(new File("c.png"));
			this.img = this.makeRoundedCorner(this.img, 25);
			this.icon = new ImageIcon(this.img);
			this.setIcon(icon);
		} catch(Exception e) {
		}
	}
	
	public Card() {
		try {
			MarvinImage image= MarvinImageIO.loadImage("./cards.png");
			crop(image.clone(), image, BACKX, BACKY,DIMX, DIMY);
			MarvinImageIO.saveImage(image, "c.png");
			this.img = ImageIO.read(new File("c.png"));
			this.img = this.makeRoundedCorner(this.img, 25);
			this.icon = new ImageIcon(this.img);
			this.setIcon(icon);
		} catch(Exception e) {
			
		}
	}
	
	private BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
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
}
