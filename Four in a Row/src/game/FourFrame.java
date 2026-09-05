package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FourFrame extends JFrame {


	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					FourFrame frame = new FourFrame();
					frame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FourFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//You can just create the Jpanel in the setContentPane
		setContentPane(new FourPanel());
		this.setLocation(300, 0);
		this.setTitle("Match Four");
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/letter-y.png"));
		pack();




	}

}
