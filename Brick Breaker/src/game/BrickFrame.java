package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class BrickFrame extends JFrame {


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrickFrame frame = new BrickFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public BrickFrame() {
		
		
		int response = JOptionPane.showConfirmDialog(null, "Rules: Bounce the ball off the paddle to get the ball to"
				+ "hit the bricks, and do not let the ball touch the bottom. \nYou only have 3 lives. Every 10 bricks"
				+ "you hit, you recieve an extra ball, and every 12 bricks the speed is increased. \nBreak all the bricks"
				+ " to win!",
                "", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(response == 1)
		{
			System.exit(0);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Brick Breaker Game");
		ImageIcon icon = new ImageIcon("src/images/redball.png");
		this.setIconImage(icon.getImage());
		this.setLocation(300, 0);
		this.setResizable(false);
		setContentPane(new BrickPanel());
		this.pack();
	}

}
