package Client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Utility.MotionPanel;

public class WinDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JLabel gif;

	/**
	 * Create the dialog.
	 */
	public WinDialog() {
		ImageIcon gifImage = null;
		JLabel yourLabel = null;
		setResizable(false);
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setModal(true);
		try {
			
			gifImage = new ImageIcon(new File("img/win.gif").toURI().toURL());
			yourLabel = new JLabel(gifImage);
			gif = new JLabel(gifImage);
			gif.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
				}
			});
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		setBounds(100, 100, gifImage.getIconWidth(), gifImage.getIconHeight());
		contentPanel.add(gif, BorderLayout.CENTER);
		setVisible(true);	
		
		
	}

}
