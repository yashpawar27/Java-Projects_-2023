package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BirdFrame extends JFrame {

	private JPanel BirdPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BirdFrame frame = new BirdFrame();
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
	public BirdFrame() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Flappy Bird");
		ImageIcon icon = new ImageIcon("src/images/redball.png");
		this.setIconImage(icon.getImage());
		this.setLocation(300, 20);
		this.setResizable(false);
		setContentPane(new BirdPanel());
		this.pack();
	}

}
