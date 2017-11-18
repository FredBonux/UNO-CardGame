package Main;

import java.awt.EventQueue;

import Client.StartView;

public class Client {

	public static void main(String[] args) {
		reload();
	}
	
	public static void reload() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartView frame = new StartView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
