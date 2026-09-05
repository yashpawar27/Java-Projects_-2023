package game;

//imports everything in the thing (bad, don't do since it takes up extra space in storage)
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;



public class MatchPanel extends JPanel implements MouseListener{
	
	
private int width = 700, height = 700, index1, index2, turn = 1;
private Dimension size = new Dimension(width, height);
private JLabel[] labels;
private ImageIcon[] icons;
private ImageIcon grey = new ImageIcon("src/images/grey.png");
private Random rand = new Random();
private Timer myTimer;
private Border border;
boolean addBack = false;
int y = 0;
int a = 0;
int c = 2;






	public MatchPanel() 
	{
		
		
		this.setPreferredSize(size);
		this.setLayout(new GridLayout(6, 6, 5, 2));
		
		myTimer = new Timer(1100, new TimeListener());
		setArrays();
		GameOver();
		
	}


	private void GameOver() 
	{
		int x = 0;
		for(int b = 0; b < 36; b++)
		{
			if(labels[b].getIcon() != grey)
			{
				x +=1;	
			}
		}
		if(x == 36)
			{
				resetGame();
			}
		x = 0;
	}


	private void resetGame()
	{
		int answer = JOptionPane.showConfirmDialog(null, 
				"Play again?","Game over", JOptionPane.YES_NO_OPTION);
		
		if(answer == 0)
		{
			ImageIcon temp = new ImageIcon();
		
			for(int i = 0; i < 36; i++)
			{
				labels[i] = new JLabel(grey);
			
				temp = icons[i];
				int num = rand.nextInt(36);
				icons[i] = icons[num];
				icons[num] = temp;
			}
		}
		else
		{
			//Shut down completely
			System.exit(0);
		}

		
		

	}


	private void setArrays()
	{
		//for 6 by 6 array
		// only use 18 of the 25 images
		labels = new JLabel[36];
		icons = new ImageIcon[36];
		
		//temporary variable
		ImageIcon temp = new ImageIcon();
		
		
		for(int x = 0; x < 18; x++)
		{
			//Handles 0 - 17
			temp = new ImageIcon("src/images/" + x + ".png");
			icons[x] = temp;
			
			//Handles 18 - 36
			icons[x + 18] = temp;
			
			labels[x] = new JLabel(grey);
			labels[x + 18] = new JLabel(grey);
			//adding MouseListeners to the grey labels to flip over the card
			
			labels[x].addMouseListener(this);
			labels[x + 18].addMouseListener(this);
			//adding to the GUI
			this.add(labels[x]);
			this.add(labels[x + 18]);
			
			
		}
		
		for(int x = 0; x < 36; x++)
		{
			temp = icons[x];
			int num = rand.nextInt(36);
			icons[x] = icons[num];
			icons[num] = temp;
		}
	}


	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e)
	{

		a += 1;
		
		if(a <= c)
		{
		
		//gets the object the mouse is on
		Object src = e.getSource();
		
		//finds the proper label
		for(int i = 0; i < labels.length; i++)
		{
			if(src == labels[i])
			{
				if(turn%2 == 1)
				{	
					//change the index of the first guess
					index1 = i;
				}
				else
				{
					//change the index of the second guess
					index2 = i;	
					
				}


				//swap icons
				labels[i].setIcon(icons[i]);
				//turn off the border
				labels[i].setBorder(null);
				//turn off the mouse listener
				labels[i].removeMouseListener(this);
			
				
			}
		}
		


		turn++;
		
		if(turn%2 == 0)
		{
				//flips the image back to grey
			if(!(icons[index1].equals(icons[index2])))
				{
				labels[index1].addMouseListener(this);
				labels[index2].addMouseListener(this);
				myTimer.start();
			}
			else if(icons[index1].equals(icons[index2]))
			{
				
				
				for(int b = 0; b < 36; b++)
				{
					if(labels[index1].getIcon()== labels[index2].getIcon())
					{
						labels[index1].setIcon(icons[index1]);
						labels[index2].setIcon(icons[index2]);
					}
					
					
				}
			}
			
				
		}
		
		

		}
		
			System.out.println(a);
	}
	


	
	public void mouseReleased(MouseEvent e) {
		
		
	}


	@Override
	public void mouseEntered(MouseEvent e) 
	{

		Object src = e.getSource();
		
		for(int x = 0; x < 36; x++)
		{
			if(src == labels[x])
			{
				Random colorRand = new Random();
				float r = colorRand.nextFloat();
				float g = colorRand.nextFloat();
				float b =  colorRand.nextFloat();
				Color randColor = new Color(r, g, b);
				border = BorderFactory.createLineBorder(randColor, 5);
				
				labels[x].setBorder(border);
			}

		}
		
	}


	@Override
	public void mouseExited(MouseEvent e) 
	{
		Object src = e.getSource();
		
		for(int x = 0; x < 36; x++)
		{
			if(src == labels[x])
			{
				labels[x].setBorder(null);
			}
		}
		
	}

	//You need to create a subclass in order to stop the timer
	public class TimeListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			labels[index1].setIcon(grey);
			labels[index2].setIcon(grey);
			a = 0;
			myTimer.stop();

		}
		
	}
	
}

