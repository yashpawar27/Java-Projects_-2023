package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MineFrame extends JFrame 
{

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MineFrame frame = new MineFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MineFrame() 
	{
		
		JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome to Mine Sweeper!"));
        Object[] dif = {"Hard", "Medium", "Easy"};
		int response = JOptionPane.showOptionDialog(null, panel, "Mine Sweeper",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, dif, null);
		
		//Hard
		if(response == 0)
		{
			MinePanel.maxMines = 100;
			MinePanel.chance = 25;
		}
		//Medium
		else if(response == 1)
		{
			MinePanel.maxMines = 50;
			MinePanel.chance = 13;
		}
		//Easy
		else if(response == 2)
		{
			MinePanel.maxMines = 25;
			MinePanel.chance = 7;
		}
		else
		{
			System.exit(0);
		}
		this.add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Mine Sweeper");
		ImageIcon icon = new ImageIcon("src/images/redball.png");
		this.setIconImage(icon.getImage());
		this.setLocation(300, 20);
		this.setResizable(false);
		setContentPane(new MinePanel());
		this.pack();
	}
}
