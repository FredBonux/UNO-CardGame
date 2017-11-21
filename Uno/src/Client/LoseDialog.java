package Client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoseDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JLabel gif;

	/**
	 * Create the dialog.
	 */
	public LoseDialog(Component c) {
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
			
			gifImage = new ImageIcon(new File("img/lose.gif").toURI().toURL());
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
		int x = c.getX() + c.getWidth()/2 - (gifImage.getIconWidth()/2);
		int y = c.getY() + c.getHeight()/2 - (gifImage.getIconHeight()/2);
		setBounds(x, y, gifImage.getIconWidth(), gifImage.getIconHeight());
		contentPanel.add(gif, BorderLayout.CENTER);
		setVisible(true);	
		
		
	}

}