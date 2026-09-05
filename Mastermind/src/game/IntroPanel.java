package game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class IntroPanel extends JPanel implements ActionListener
{
	private int HEIGHT = 750, WIDTH = 600;
	private Dimension size = new Dimension(WIDTH, HEIGHT);

	private JButton startButtonLBL = new JButton("Start");
	public static boolean remove = false;
	
	public IntroPanel() 
	{
		this.setPreferredSize(size);
		this.setBackground(Color.GRAY);
		setLayout(null);
		
		JLabel IntroTitleLBL = new JLabel("MasterMind");
		IntroTitleLBL.setForeground(Color.WHITE);
		IntroTitleLBL.setHorizontalAlignment(SwingConstants.CENTER);
		IntroTitleLBL.setFont(new Font("Monaco", Font.BOLD | Font.ITALIC, 28));
		IntroTitleLBL.setBounds(170, 18, 272, 32);
		add(IntroTitleLBL);
		
		JLabel IntroSubtitleLBL = new JLabel("Can you outwit the MasterMind?!");
		IntroSubtitleLBL.setHorizontalAlignment(SwingConstants.CENTER);
		IntroSubtitleLBL.setForeground(Color.WHITE);
		IntroSubtitleLBL.setFont(new Font("Marker Felt", Font.BOLD | Font.ITALIC, 24));
		IntroSubtitleLBL.setBounds(102, 62, 424, 32);
		add(IntroSubtitleLBL);
		
		
		startButtonLBL.setFont(new Font("Marker Felt", Font.BOLD | Font.ITALIC, 14));
		startButtonLBL.setBounds(275, 138, 61, 16);
		add(startButtonLBL);
		
		JLabel instructionsLBL = new JLabel("Instructions");
		instructionsLBL.setBounds(275, 201, 83, 16);
		add(instructionsLBL);
		
		JLabel instructionsWordsLBL = new JLabel("How to play: The MasterMind has set up code for you to crack. He has made a random code of colors. "
				+ "It is your job to figure out his code. Using the colors to the right of the screen, you will try different codes. But fret not! You"
				+ " still have more help avilable. Once you enter a code, the column to the right will indicate which and how many colors you got "
				+ "right or wrong. You will also have the delete button to remove any mistakes you may make. If your down on your luck or just a "
				+ "plain old gambler, you can you the gamble button located beneath the delete button to gain you a hint, but beware as it also might"
				+ " take away one of your attempts. You will only have a set a amount of attempts. Good Luck!");
		instructionsWordsLBL.setBounds(52, 229, 519, 381);
		add(instructionsWordsLBL);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource() == startButtonLBL)
		{
			remove = true;
		}
	}
		
		
}
