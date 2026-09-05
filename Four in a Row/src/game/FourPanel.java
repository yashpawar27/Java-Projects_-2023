package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FourPanel extends JPanel 
{
	private int playerX = 0, playerY = 0;
	private int diameter = 100;
	private int numRows = 6;
	private int numCols = 7;
	private int turn = 0;
	FourCircle[][] circles;
	
	

	public FourPanel() 
	{
		this.setPreferredSize(new Dimension(720, 700));
		this.setBackground(Color.blue);
		this.setLayout(null);
		setCircles();
		
		//Creates a new method inside this method call (MouseAdapter method created inside the addMouseListener)
		addMouseListener(new MouseAdapter()
				{
		//Created a subclass of mouseAdapter
					public void mouseReleased(MouseEvent e)
					{
						
						dropToken(e.getX(), e.getY());
					}
				});
		
		addMouseMotionListener(new MouseAdapter()
		//Created a subclass of mouseAdapter
				{
			public void mouseDragged(MouseEvent e)
			{
				//Gets x and y
				movePlayer(e.getX(), e.getY());
			}
				});
	}

	protected void movePlayer(int x, int y) 
	{
		
		
		
		//Makes sure there's a gap between the edge of the screen and the 1st column
		if(playerX == x){return;}
		repaint();
		playerX = x -5;
		playerY = 0;
		
		//Makes sure the token doesn't move off the screen
		if(playerX < 0){playerX = 0;}
		if(playerX > 620){playerX = 620;}
		
		repaint(playerX, playerY, diameter, diameter);
		
		
	}

	//Fires everytime the moouse is released
	protected void dropToken(int x, int y)
	{
		//Makes sure they click at the top of the screen
		if(y > 100){return;}
		int red, green, blue;
		if(turn%2 == 0)
		{
			red = 255;
			green = 0;
			blue = 0;
		}
		else
		{
			red = 20;
			green = 255;
			blue = 0;
		}
		
		//Finds which column the player drops the token in
		int col = 0;
		if(x > 0 && x < 70) 
		{
			col = 0;
		}
		else if(x > 73 && x < 173)
		{
			col = 1;
		}
		else if(x > 173 && x < 273)
		{
			col = 2;
		}
		else if(x > 273 && x < 373)
		{
			col = 3;
		}
		else if(x > 373 && x < 473)
		{
			col = 4;
		}
		else if(x > 473 && x < 573)
		{
			col = 5;
		}
		else if(x > 573 && x < 673)
		{
			col = 6;
		}
		
		//itterates through the rows
		for(int row = 0; row < numRows; row++)
		{
			//If the color is black
			if(circles[row][col].getRed() == 0)
			{
				circles[row][col].setRed(red);
				circles[row][col].setGreen(green);
				circles[row][col].setBlue(blue);
				turn++;
				repaint();
				break;
			}
		}
		
		checkWinner();
	}
		
		
	


	private void checkWinner()
	{
		boolean win = false;
		
		//Check Horizontal
		for(int row = 0; row < numRows; row++)
		{
			for(int col = 0; col < numCols-3; col++)
			{
				if(circles[row][col].getRed() != 0 && 
						circles[row][col].getRed()==circles[row][col+1].getRed() &&
						circles[row][col].getRed()==circles[row][col+2].getRed() &&
						circles[row][col].getRed()==circles[row][col+3].getRed())
				{
					win = true;
				}
			}
		}
		
		//Check Vertical
		for(int row = 0; row < numRows-3; row++)
		{
			for(int col = 0; col < numCols; col++)
			{
				if(circles[row][col].getRed() != 0 && 
						circles[row][col].getRed()==circles[row+1][col].getRed() &&
						circles[row][col].getRed()==circles[row+2][col].getRed() &&
						circles[row][col].getRed()==circles[row+3][col].getRed())
				{
					win = true;
				}
			}
		}
		
		//Check Positive Slope
		for(int row = 0; row < numRows-3; row++)
		{
			for(int col = 0; col < numCols-3; col++)
			{
				if(circles[row][col].getRed() != 0 && 
						circles[row][col].getRed()==circles[row+1][col+1].getRed() &&
						circles[row][col].getRed()==circles[row+2][col+2].getRed() &&
						circles[row][col].getRed()==circles[row+3][col+3].getRed())
				{
					win = true;
				}
			}
		}
			
		//Check Negative Slope
		for(int row = 3; row < numRows; row++)
		{
			for(int col = 0; col < numCols-3; col++)
			{
				if(circles[row][col].getRed() != 0 && 
						circles[row][col].getRed()==circles[row-1][col+1].getRed() &&
						circles[row][col].getRed()==circles[row-2][col+2].getRed() &&
						circles[row][col].getRed()==circles[row-3][col+3].getRed())
				{
					win = true;
				}
			}
		}
		
		if(win)
		{
			String whoWon = "";
			
			
			//Doesn't update turn in the previous move
			if(turn%2==1)
			{
				whoWon = "Red is the Winner";
			}
			else
			{
				whoWon = "Green is the Winner";
			}
			
			int restart = JOptionPane.showConfirmDialog(null,"Play agian?", whoWon,
					JOptionPane.YES_NO_OPTION);
			
			if(restart == 0)
			{
				//Start Over
				turn = 0;
				
				for(int row = 0; row < numRows; row++)
				{
					for(int col = 0; col < numCols; col++)
					{
						circles[row][col].setRed(0);
						circles[row][col].setGreen(0);
					}
				}
				repaint();
			}
			else
			{
				System.exit(0);
			}
		}
		
		
				
	}
	
	public void paintComponent(Graphics g)
	{
		//Cast the old g into the new Graphics g, which is Graphics2D(Better)
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		if(turn%2 == 0)
		{
			g2d.setColor(Color.RED);
		}
		else
		{
			g2d.setColor(Color.GREEN);
		}
		
		g2d.fillOval(playerX, playerY, diameter, diameter);
		
		//Background color
		g2d.setColor(Color.BLUE);
		g2d.fillRect(0, 100, 720, 700);
		for(int rows = 0; rows < numRows; rows++)
		{
			for(int cols = 0; cols < numCols; cols++)
			{
				g2d.setPaint(new Color(circles[rows][cols].getRed(),
						circles[rows][cols].getGreen(),
						circles[rows][cols].getBlue()));
				
				g2d.fillOval(circles[rows][cols].getxPos(), circles[rows][cols].getyPos(), circles[rows][cols].getDiameter(), circles[rows][cols].getDiameter());
			}
		}
		
	}

	//This method instantiates all the objects
	private void setCircles() 
	{
		circles = new FourCircle[numRows][numCols];
		int x = 0, y = 0;
		
		for(int rows = 0; rows < numRows; rows++)
		{
			for(int cols = 0; cols < numCols; cols++)
			{
				y = 600 - (rows*100);
				x = (cols*100) + 10;
				circles[rows][cols] = new FourCircle(x, y);
				circles[rows][cols].setRed(0);
				circles[rows][cols].setGreen(0);
				circles[rows][cols].setBlue(0);
			}
		}
	}

}
