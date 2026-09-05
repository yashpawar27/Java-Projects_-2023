package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class MasterMindFrame extends JFrame{


	private MasterMindPanel panel;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterMindFrame frame = new MasterMindFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MasterMindFrame() {
		
		int response = JOptionPane.showConfirmDialog(null, "How to play: The MasterMind has set up code for you to crack. He has made a random code of colors.\n "
				+ "It is your job to figure out his code. Using the colors to the right of the screen, you will try different codes. But fret not! You\n"
				+ " still have more help avilable. Once you enter a code, the column to the right will indicate which and how many colors you got\n "
				+ "right or wrong. You will also have the delete button to remove any mistakes you may make. If your down on your luck or just a\n "
				+ "plain old gambler, you can you the gamble button located beneath the delete button to gain you a hint, but beware as it also might\n"
				+ " take away one of your attempts. You will only have a set a amount of attempts. Good Luck!",
                "", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(response == 1)
		{
			System.exit(0);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new MasterMindPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		setContentPane(panel);
		this.setTitle("MasterMind");
		this.setLocation(450, 70);
		this.setResizable(false);
		pack();
		


		
	}
}