package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class OthelloFrame extends JFrame {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OthelloFrame frame = new OthelloFrame();
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
	public OthelloFrame() {
		
		JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome to Mine Sweeper!"));
        Object[] dif = {"FreeClick Mode", "Regular Mode"};
		int response = JOptionPane.showOptionDialog(null, panel, "Mine Sweeper",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, dif, null);
		
		//FreeclickMMode
		if(response == 0)
		{
			OthelloPanel.freeClickMode = true;
		}
		//Regular Mode
		else if(response == 1)
		{
			OthelloPanel.freeClickMode = false;
		}
		else
		{
			System.exit(0);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Othello");
		ImageIcon icon = new ImageIcon("src/images/redball.png");
		this.setIconImage(icon.getImage());
		this.setLocation(300, 20);
		this.setResizable(false);
		setContentPane(new OthelloPanel());
		this.pack();
	}

}
