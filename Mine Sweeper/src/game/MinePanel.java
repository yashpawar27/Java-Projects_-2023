package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
//import java.io.File;
//import java.io.IOException;
//import java.time.Duration;
//import java.time.Instant;
import java.util.Random;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class MinePanel extends JPanel implements MouseListener, ActionListener
{
	private int WIDTH = 500, HEIGHT = 525;
	private int unit_size = 25;
	public static int maxMines= 400;
	public static int chance = 20;
	
	public boolean playing = true;
	public boolean resTime;
	public int hr;
	public int min;
	public int sec;
	Timer timer1;


	
	//wants first row to be blank
	int numRows = (HEIGHT-unit_size)/unit_size;
	int numCols = WIDTH/unit_size;
	private JLabel top;
	private JLabel[][] labels;
	
	private ImageIcon[][] icons;
//	Instant begin;
//	Instant end;
//	Duration timer;
//private Time timer;
	
	private JPanel centerPanel;
	private Random rand = new Random();
	private int numMines;
	private long startTime;
	
	//Sound variable is needed
	private Sound sound;
	private String win = "You Win!";
	private ImageIcon tile = new ImageIcon("src/images/tile.png");
	private ImageIcon mine = new ImageIcon("src/images/mine.png");
	private ImageIcon flag = new ImageIcon("src/images/flag.png");
	private ImageIcon blank = new ImageIcon("src/images/0.png");
	
	
	public MinePanel() 
	{
		
//		begin = Instant.now();
		
		sound = new Sound();
		resTime = true;
		startTime = new Date().getTime();
		timer1 = new Timer(1000, this);
		timer1.start();
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLayout(new BorderLayout());
		//Shows working with centerPanel
		centerPanel = new JPanel();
		//Inserts a GridLayout panel inside the main BorderLayout panel
		centerPanel.setLayout(new GridLayout(numRows, numCols));
		//Shows working with the orginal panel
		this.top = new JLabel("Game");
		this.add(top);
		//adds top label to the north side
		this.add(top, BorderLayout.NORTH);
		//adds top label to the center
		this.add(centerPanel, BorderLayout.CENTER);
	
		
		//this.thread = new Thread(this);
		//thread.start();
		
		setBoard();
		addMines();
		addCount();
		
		timer1 = new Timer(1000, this);
		timer1.start();
	}
	


	private void setBoard() 
	{
		labels = new JLabel[numRows][numCols];
		for(int row = 0; row < numRows; row++)
		{
			for(int col = 0; col < numCols; col++)
			{
				//New feature won't work if they don't have the newer versions of Java installed
				labels[row][col] = new JLabel();
				labels[row][col].setIcon(tile);
				labels[row][col].addMouseListener(this);
				centerPanel.add(labels[row][col]);
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(resTime)
		{
			
		long currentTime = new Date().getTime();
		long elapsed = currentTime - startTime;
			
		sec = ((int)(elapsed/1000))%60;
		min = ((int) (elapsed/60000))%60;
		hr = (int)(elapsed/3600000);

			this.top.setText("Mines Left: " + numMines +"                                  " + 
		"                                               "+"Time: "+hr+"."+min+"."+sec);
		
		}
		
		if(!resTime)
		{
			sec = 0;
			min = 0;
			hr = 0;
				
			startTime = new Date().getTime();
		}
	
	}

	private void addMines() 
	{
		//Put mines in icon array
		icons = new ImageIcon[numRows][numCols];
		numMines = 0;
		
		//Randomly placing mines
		for(int row = 0; row < numRows; row++)
		{
			for(int col = 0; col < numCols; col++)
			{
				icons[row][col] = new ImageIcon();
				
				//% chance there is a mine placed
				if(rand.nextInt(100) < chance)
				{
					if(numMines < maxMines)
					{
						icons[row][col] = mine;
						numMines++;
					}
				}
			}
		}
		
		while(numMines < maxMines)
		{
			int tempRow = rand.nextInt(20);
			int tempCol = rand.nextInt(20);
			if(!(icons[tempRow][tempCol].equals(mine))) 
			{
				icons[tempRow][tempCol] = mine;
				numMines++;
			}
		}
		this.top.setText("Mines Left: " + numMines);
	}

	//Adds the number of mines around one element(tile)
	private void addCount() 
	{
		for(int row = 0; row < numRows; row++)
		{
			for(int col = 0; col < numCols; col++)
			{
				if(icons[row][col].equals(mine))
				{
					//continue makes code go back to the top of the loop
					continue;
				}
				//If not a mine, it counts ow many mines are around it.
				int cntr = 0;
				
				//Checks top left
				if(col - 1 >= 0 && row - 1 >= 0)
				{
					if(icons[row-1][col-1].equals(mine))
					{
						cntr++;
					}
				}
				//Check top
				if(row - 1 >= 0)
				{
					if(icons[row-1][col].equals(mine))
					{
						cntr++;
					}
				}
				//Check top right
				if(col + 1 < numCols && row - 1 >= 0)
				{
					if(icons[row-1][col+1].equals(mine))
					{
						cntr++;
					}
				}
				//Check bottom left
				if(col - 1 >= 0 && row + 1 < numRows)
				{
					if(icons[row+1][col-1].equals(mine))
					{
						cntr++;
					}
				}
				//Check bottom
				if(row + 1 < numRows)
				{
					if(icons[row+1][col].equals(mine))
					{
						cntr++;
					}
				}
				//Check bottom right
				if(col + 1 < numCols && row + 1 < numRows)
				{
					if(icons[row+1][col+1].equals(mine))
					{
						cntr++;
					}
				}
				//Check right
				if(col + 1 < numCols)
				{
					if(icons[row][col+1].equals(mine))
					{
						cntr++;
					}
				}
				//Check left
				if(col - 1 >= 0)
				{
					if(icons[row][col-1].equals(mine))
					{
						cntr++;
					}
				}
				//Add images
				if(cntr > 0)
				{
					icons[row][col] = new ImageIcon("src/images/" + cntr + ".png");
				}
				else
				{
					icons[row][col] = blank;
				}
			}
		}
	}

	private void gameOver() 
	{
//		end = Instant.now();
		
		resTime = false;
		Sound sound = new Sound();
        sound.start();
        
        for(int row = 0; row < numRows; row++)
        {
            for(int col = 0; col < numCols; col++)
            {
                labels[row][col].removeMouseListener(this);
                
                if(icons[row][col].equals(mine))
                {
                	labels[row][col].setIcon(mine);
                }
            }
        }
        
//        Time();
        
		int answer = JOptionPane.showConfirmDialog(null, 
				win + " Play again?","Game over", JOptionPane.YES_NO_OPTION);
		
//		win + " Play again?"+"\n Time: "+timer.toHours()+"."+
//		timer.toMinutes()+"."+timer.toSeconds(),"Game over", JOptionPane.YES_NO_OPTION);
		
		
		if(answer == 0)
		{
			restart();
			Sound.clip.close();
			
		}
		else
		{
			System.exit(0);
		}
	}
	
	private void restart()
	{
		resTime = true;
		
		 for(int row = 0; row < numRows; row++)
	        {
	            for(int col = 0; col < numCols; col++)
	            {
	                labels[row][col].addMouseListener(this);;
	                labels[row][col].setIcon(tile);
	            }
	        }
		
		addMines();
		addCount();
		
		win = "You Win!";
		playing = true;
//		begin = Instant.now();
	}
	
//	public void Time() {
//		
//			timer = Duration.between(begin, end);
//			System.out.println("Time in seconds: " + timer.toSeconds());
//	        System.out.println("Time in minutes: " + timer.toMinutes());
//	        System.out.println("Time in hours: " + timer.toHours());
//		
//	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		//Gets what the user clicked
		Object src = e.getSource();
		
		// captures left click
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			//Nested loop to go through the array
			for(int row = 0; row < numRows; row++)
			{
				for(int col = 0; col < numCols; col++)
				{
					if(src == labels[row][col] && labels[row][col].getIcon().equals(tile))
					{
						//sets the icon to what it acually is, which is stored in the icon array
						labels[row][col].setIcon(icons[row][col]);
						labels[row][col].removeMouseListener(this);
						
						if(icons[row][col].equals(mine))
						{
							//Sound.start();
							win = "You Lost.";
							playing = true;
							gameOver();
							//Just to be sure that it doesn't go to the next line
							break;
						}
						else if(icons[row][col].equals(blank))
						{
							showBlanks(row, col);
						}
					}
				}
			}
		}
		else if(e.getButton() == MouseEvent.BUTTON2)
		{
			System.out.println("Middle button clicked");
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			for(int row = 0; row < numRows; row++) 
			{
				for(int col = 0; col < numCols; col++) 
				{
					if(src == labels[row][col])
					{
						if(labels[row][col].getIcon().equals(flag))
						{
							labels[row][col].setIcon(tile);
							numMines++;
							top.setText("Mines Left: " + numMines);
						}
						else if(labels[row][col].getIcon().equals(tile))
						{
							labels[row][col].setIcon(flag);
							numMines--;
						}
					}
				}
			}
		}	
		
		int check = 0;
		for(int row = 0; row < numRows; row++)
		{
			for(int col = 0; col < numCols; col++)
			{
				if(!icons[row][col].equals(mine) || labels[row][col].getIcon().equals(flag))
				{
					check++;
				}
			}
		}
		
		
		
		
		if(check == 400)
		{
			int answer = JOptionPane.showConfirmDialog(null, 
					win + " Play again?","Game over", JOptionPane.YES_NO_OPTION);
			
			if(answer == 0)
			{
				gameOver();
			}
			else
			{
				System.exit(0);
			}
		}
	}

	//Deals with reccursion
	private void showBlanks(int row, int col) 
	{
		//If it isn't a blank
		if(!(icons[row][col].equals(blank)))
		{
			labels[row][col].setIcon(icons[row][col]);
			labels[row][col].removeMouseListener(this);
			//returns nothing
			return;
		}
		//If it is a blank
		if(icons[row][col].equals(blank))
		{
			labels[row][col].setIcon(blank);
			labels[row][col].removeMouseListener(this);
			
			//These if-statements flip the ones beside it, and keeps checking for blanks until it reaches
			//a number
			
			//Checks the top left
			if(row-1 >= 0 && col-1 >= 0 &&
					//If it has mouseListeners, then it returns the number of MouseListeners it has.
					labels[row-1][col-1].getMouseListeners().length > 0)
			{
				//Uncovers this space
				showBlanks(row-1, col-1);
			}
			//Checks the top
			if(row-1 >= 0 &&
					//If it has mouseListeners, then it returns the number of MouseListeners it has.
					labels[row-1][col].getMouseListeners().length > 0)
			{
				//Uncovers this space
				showBlanks(row-1, col);
			}
			//checks top right
			if(row-1 >= 0 && col + 1 < numCols &&
					//If it has mouseListeners, then it returns the number of MouseListeners it has.
					labels[row-1][col+1].getMouseListeners().length > 0)
			{
				//Uncovers this space
				showBlanks(row-1, col+1);
			}
			//checks right
			if(col + 1 < numCols &&
					//If it has mouseListeners, then it returns the number of MouseListeners it has.
					labels[row][col+1].getMouseListeners().length > 0)
			{
				//Uncovers this space
				showBlanks(row, col+1);
			}
			//checks left
			if(col - 1 >= 0 &&
					//If it has mouseListeners, then it returns the number of MouseListeners it has.
					labels[row][col-1].getMouseListeners().length > 0)
			{
				//Uncovers this space
				showBlanks(row, col-1);
			}
			//checks bottom left
			if(row+1 < numRows && col - 1 >= 0 &&
					//If it has mouseListeners, then it returns the number of MouseListeners it has.
					labels[row+1][col-1].getMouseListeners().length > 0)
			{
				//Uncovers this space
				showBlanks(row+1, col-1);
			}
			//checks bottom
			if(row+1 < numRows &&
					//If it has mouseListeners, then it returns the number of MouseListeners it has.
					labels[row+1][col].getMouseListeners().length > 0)
			{
				//Uncovers this space
				showBlanks(row+1, col);
			}
			//Checks bottom right
			if(row+1 < numRows && col + 1 < numCols &&
					//If it has mouseListeners, then it returns the number of MouseListeners it has.
					labels[row+1][col+1].getMouseListeners().length > 0)
			{
				showBlanks(row+1, col+1);
			}
		}
	}


	
	



	@Override
	public void mouseReleased(MouseEvent e)
	{
		
		
	}


	@Override
	public void mouseEntered(MouseEvent e) 
	{
		
		
	}


	@Override
	public void mouseExited(MouseEvent e) 
	{
		
		
	}
}

