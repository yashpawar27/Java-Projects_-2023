import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import game.OthelloPanel;

public class PokerFrame extends JFrame {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokerFrame frame = new PokerFrame();
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
	public PokerFrame() {
		
		JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome to Mine Sweeper!"));
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Poker");
		ImageIcon icon = new ImageIcon("src/images/13c.gif");
		this.setIconImage(icon.getImage());
		this.setLocation(300, 20);
		this.setResizable(true);
		setContentPane(new PokerPanel());
		this.pack();
	}

}
