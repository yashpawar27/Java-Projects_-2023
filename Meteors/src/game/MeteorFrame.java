package game;


import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MeteorFrame extends JFrame {


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeteorFrame frame = new MeteorFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MeteorFrame() {
		//"this" does nothing except refer the command at the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Meteors");
		ImageIcon icon = new ImageIcon("src/images/asteroid0.png");
		this.setIconImage(icon.getImage());
		this.setResizable(false);
		this.setLocation(200, 0);
		setContentPane(new MeteorPanel());
		this.pack();
	}

}
