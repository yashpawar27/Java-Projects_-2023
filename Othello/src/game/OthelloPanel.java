package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OthelloPanel extends JPanel implements MouseListener, Runnable
{
	private static int WIDTH = 490, HEIGHT = 530;
	private static int tileSize = 60;
	private static int numCols = WIDTH/tileSize;
	private static int numRows = HEIGHT/tileSize;
	private JLabel[][] labels;
	private Music music;
	private Thread thread;
	private ImageIcon empty = new ImageIcon("src/images/EmptyOthelloTile2.jpeg");
	private ImageIcon Black = new ImageIcon("src/images/BlackOthelloPiece2.jpeg");
	private ImageIcon White = new ImageIcon("src/images/WhiteOthelloPiece2.jpeg");
	private int initialRowClick;
	private int initialColClick;
	private String turn;
	public static Boolean freeClickMode;

	
	
	private JLabel title;
	private JLabel blank = new JLabel("");
	private JLabel turnLBL;
	private JPanel centerPanel;
	private JPanel titlePanel;
	

	public OthelloPanel() 
	{
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLayout(new BorderLayout());

		
		turn = "Black";
		//Shows working with centerPanel
		centerPanel = new JPanel();
		titlePanel = new JPanel();
		//Inserts a GridLayout panel inside the main BorderLayout panel
		centerPanel.setLayout(new GridLayout(numRows, numCols));
		//Shows working with the orginal panel
		title = new JLabel("     Othello", SwingConstants.LEFT);
		title.setFont(new Font("Serif", Font. BOLD, 28));
		
		this.centerPanel.setBackground(new Color(6, 71, 6));
		this.titlePanel.setBackground(new Color(219, 176, 91));
		titlePanel.setLayout(new GridLayout(0,3));
		turnLBL = new JLabel("                   Turn: Black");
		titlePanel.add(blank);
		titlePanel.add(title);
		titlePanel.add(turnLBL);
		//adds top label to the center
		this.add(centerPanel, BorderLayout.CENTER);
		//adds top label to the north side
		this.add(titlePanel, BorderLayout.NORTH);
		
		
		initialRowClick = 0;
		initialColClick = 0;
		
		thread = new Thread(this);
		thread.start();
		

		setBoard();
		
	}
	
	private void setBoard()
	{
		labels = new JLabel[numRows][numCols];
		for(int row = 0; row < numRows; row++)
		{
			for(int col = 0; col < numCols; col++)
			{
				labels[row][col] = new JLabel();
				labels[row][col].setIcon(empty);
				labels[row][col].addMouseListener(this);
				centerPanel.add(labels[row][col]);
			}
		}
		labels[3][3].setIcon(Black);
		labels[3][3].removeMouseListener(this);
		labels[4][4].setIcon(Black);
		labels[4][4].removeMouseListener(this);
		labels[3][4].setIcon(White);
		labels[3][4].removeMouseListener(this);
		labels[4][3].setIcon(White);
		labels[4][3].removeMouseListener(this);
		
		
		
		//Music extends thread in the music class
		music = new Music();
		music.start();

	}
	
	private void checkUp(int row, int col)
	{
		if(labels[row][col].getIcon().equals(labels[initialRowClick][initialColClick].getIcon()))
		{
			//Used to check pieces above
			for(int a = initialRowClick; a > row; a--)
			{
				if(!(labels[a][col].getIcon().equals(empty)))
				{
					labels[a][col].setIcon(labels[initialRowClick][initialColClick].getIcon());
					labels[a][col].removeMouseListener(this);
				}
			}
		}
		if(row > 0)
		{
			if(!labels[row-1][col].getIcon().equals(empty))
			{
				checkUp(row-1, col);
			}
		}
	}
	
	private void checkDown(int row, int col)
	{
		if(labels[row][col].getIcon().equals(labels[initialRowClick][initialColClick].getIcon()))
		{
			//Used to check pieces below, as in goes down by 1 each time
			for(int a = initialRowClick; a < row; a++)
			{
				if(!(labels[a][col].getIcon().equals(empty)))
				{
					labels[a][col].setIcon(labels[initialRowClick][initialColClick].getIcon());
					labels[a][col].removeMouseListener(this);
				}
			}
		}
		if(row < numRows-1)
		{
			if(!labels[row+1][col].getIcon().equals(empty))
			{
				checkDown(row+1, col);
			}
		}
	}
	
	private void checkRight(int row, int col)
	{
		if(labels[row][col].getIcon().equals(labels[initialRowClick][initialColClick].getIcon()))
		{
			for(int a = initialColClick; a < col; a++)
			{
				if(!(labels[row][a].getIcon().equals(empty)))
				{
					labels[row][a].setIcon(labels[initialRowClick][initialColClick].getIcon());
					labels[row][a].removeMouseListener(this);
				}
			}
		}
		if(col < numCols-1)
		{
			if(!labels[row][col+1].getIcon().equals(empty))
			{
				checkRight(row, col+1);
			}
		}
	}
	
	private void checkLeft(int row, int col)
	{
		if(labels[row][col].getIcon().equals(labels[initialRowClick][initialColClick].getIcon()))
		{
			//Used to check pieces left
			for(int a = initialColClick; a > col; a--)
			{
				if(!(labels[row][a].getIcon().equals(empty)))
				{
					labels[row][a].setIcon(labels[initialRowClick][initialColClick].getIcon());
					labels[row][a].removeMouseListener(this);
				}
			}
		}
		if(col > 0)
		{
			if(!labels[row][col-1].getIcon().equals(empty)) 
			{
				checkLeft(row, col-1);
			}
		}
	}
	
	private void checkTopRight(int row, int col)
	{
		if(labels[row][col].getIcon().equals(labels[initialRowClick][initialColClick].getIcon()))
		{
			int b = initialRowClick;
			//Used to check pieces left
			for(int a = initialColClick; a < col; a++)
			{
				if(!(labels[b][a].getIcon().equals(empty)))
				{
					labels[b][a].setIcon(labels[initialRowClick][initialColClick].getIcon());
					labels[b][a].removeMouseListener(this);
				}
				
				b--;
			}
		}
		if(col < numCols-1 && row > 0)
		{
			if(!labels[row-1][col+1].getIcon().equals(empty))
			{
				checkTopRight(row-1, col+1);
			}
		}
	}
	
	private void checkTopLeft(int row, int col)
	{
		if(labels[row][col].getIcon().equals(labels[initialRowClick][initialColClick].getIcon()))
		{
			int b = initialRowClick;
			//Used to check pieces top left
			for(int a = initialColClick; a > col; a--)
			{
				if(!(labels[b][a].getIcon().equals(empty)))
				{
					labels[b][a].setIcon(labels[initialRowClick][initialColClick].getIcon());
					labels[b][a].removeMouseListener(this);
				}
				b--;
			}
		}
		if(col > 0 && row > 0)
		{
			if(!labels[row-1][col-1].getIcon().equals(empty))
			{
				checkTopLeft(row-1, col-1);
			}
		}
	}
	
	private void checkBottomLeft(int row, int col)
	{
		if(labels[row][col].getIcon().equals(labels[initialRowClick][initialColClick].getIcon()))
		{
			int b = initialRowClick;
			for(int a = initialColClick; a > col; a--)
			{
				if(!(labels[b][a].getIcon().equals(empty)))
				{
					labels[b][a].setIcon(labels[initialRowClick][initialColClick].getIcon());
					labels[b][a].removeMouseListener(this);
				}
				b++;
			}
		}
		if(col > 0 && row < numRows-1)
		{
			if(!labels[row+1][col-1].getIcon().equals(empty))
			{
				checkBottomLeft(row+1, col-1);
			}
		}
	}
	
	private void checkBottomRight(int row, int col)
	{
		if(labels[row][col].getIcon().equals(labels[initialRowClick][initialColClick].getIcon()))
		{
			int b = initialRowClick;
			for(int a = initialColClick; a < col; a++)
			{
				if(!(labels[b][a].getIcon().equals(empty)))
				{
					labels[b][a].setIcon(labels[initialRowClick][initialColClick].getIcon());
					labels[b][a].removeMouseListener(this);
				}
				b++;
			}
		}
		if(col < numCols-1 && row < numRows-1)
		{
			if(!labels[row+1][col+1].getIcon().equals(empty))
			{
				checkBottomRight(row+1, col+1);
			}
			
		}
	}
	
	public void checkWin()
	{
		int BlackPieces = 0;
		int WhitePieces = 0;
		String winner = "";
		
		for(int row = 0; row < numRows; row++)
		{
			for(int col = 0; col < numCols; col++)
			{
				if(!labels[row][col].getIcon().equals(empty))
				{
					if(labels[row][col].getIcon().equals(Black))
					{
						BlackPieces++;
					}
					else
					{
						WhitePieces++;
					}
				}
			}
		}
		
		if(BlackPieces > WhitePieces || WhitePieces == 0)
		{
			winner = "Black Wins!";
		}
		else if(WhitePieces > BlackPieces || BlackPieces == 0)
		{
			winner = "White Wins!";
		}
		else
		{
			winner = "Draw!";
		}
		
		
		if(BlackPieces + WhitePieces == (numRows*numCols) || BlackPieces == 0 || WhitePieces == 0)
		{
				turnLBL.setText("                   Turn: Black");
			
			
			BlackPieces = 0;
			WhitePieces = 0;
			music.clip.close();
			int answer = JOptionPane.showConfirmDialog(null, 
					winner + " Play again?","Game over", JOptionPane.YES_NO_OPTION);
			
			if(answer == 0)
			{
				for(int row = 0; row < numRows; row++)
				{
					for(int col = 0; col < numCols; col++)
					{
						labels[row][col].setIcon(empty);
					}
				}
				
				centerPanel.removeAll();
				setBoard();
				
				
				labels[3][3].setIcon(Black);
				labels[4][4].setIcon(Black);
				labels[3][4].setIcon(White);
				labels[4][3].setIcon(White);
				

					turn = "Black";
			}
			else
			{
				System.exit(0);
			}
		}
	}
	
	private void restrictClick(int row, int col)
	{
		if((row-1 >= 0 && col-1 >= 0 && 
				(!(labels[row-1][col-1].getIcon().equals(empty))) ||
				//Checks top
				(row-1 >= 0 && 
					!(labels[row-1][col].getIcon().equals(empty))) ||
				//Checks top right
				(row-1 >= 0 && col + 1 < numCols &&
					!(labels[row-1][col+1].getIcon().equals(empty))) ||
				//Checks right
				(col + 1 < numCols &&
					!(labels[row][col+1].getIcon().equals(empty))) ||
				//Checks left
				(col - 1 >= 0 &&
					!(labels[row][col-1].getIcon().equals(empty))) ||
				//Checks bottom left
				(row+1 < numRows && col - 1 >= 0 &&
					!(labels[row+1][col-1].getIcon().equals(empty))) ||
				//Checks bottom
				(row+1 < numRows &&
					!(labels[row+1][col].getIcon().equals(empty))) ||
				//Checks bottom right
				(row+1 < numRows && col + 1 < numCols &&
					!(labels[row+1][col+1].getIcon().equals(empty)))))
		{
			if(turn.equals("Black"))
			{
				labels[row][col].setIcon(Black);
				labels[row][col].removeMouseListener(this);
			}
			else
			{
				labels[row][col].setIcon(White);
				labels[row][col].removeMouseListener(this);
			}
			checkUp(row,col);
			checkDown(row,col);
			checkRight(row, col);
			checkLeft(row, col);
			checkTopLeft(row, col);
			checkBottomRight(row, col);
			checkBottomLeft(row, col);
			checkTopRight(row, col);
			
			
			if(turn.equals("Black"))
			{
				turn = "White";
				turnLBL.setText("                   Turn: White");
			}
			else
			{
				turn = "Black";
				turnLBL.setText("                   Turn: Black");
			}
			
		}
		else
		{
		System.out.println("You can't put a tile there!");
		}
	}
	
	private void freeClick(int row, int col)
	{
		labels[row][col].removeMouseListener(this);
		
		if(turn.equals("Black"))
		{
			labels[row][col].setIcon(Black);
		}
		else if(turn.equals("White"))
		{
			labels[row][col].setIcon(White);
		}
		checkUp(row,col);
		checkDown(row,col);
		checkRight(row, col);
		checkLeft(row, col);
		checkTopLeft(row, col);
		checkBottomRight(row, col);
		checkBottomLeft(row, col);
		checkTopRight(row, col);
		
		
		if(turn.equals("Black"))
		{
			turn = "White";
			turnLBL.setText("                   Turn: White");
		}
		else
		{
			turn = "Black";
			turnLBL.setText("                   Turn: Black");
		}
		
		
	}


	public void flip(int row, int col)
	{
		//Checks above
		for(int a = 0; a < row; a++)
		{
			//Checks from top down;
			if(labels[a][col].getIcon().equals(labels[row][col].getIcon()))
			{
				for(int b = a; b < row; b++)
				{
					
					labels[b][col].setIcon(labels[row][col].getIcon());
				}
			}
		}
		
		//Checks below
		for(int a = numRows-1; a > row; a--)
		{
			//Checks from down to top;
			if(labels[a][col].getIcon().equals(labels[row][col].getIcon()))
			{
				for(int b = a; b > row; b--)
				{
					labels[b][col].setIcon(labels[row][col].getIcon());
				}
			}
		}
	
		//Checks right
		for(int a = col; a < numCols; a++)
		{
			if(labels[row][a].getIcon().equals(labels[row][col].getIcon()))
			{
				for(int b = a; b > row; b--)
				{
					labels[row][b].setIcon(labels[row][col].getIcon());
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e)
	{
		Object src = e.getSource();
		
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			for(int row = 0; row < numRows; row++)
			{
				for(int col = 0; col < numCols; col++)
				{
					if(labels[row][col].equals(src))
					{
						initialRowClick = row;
						initialColClick = col;
						//Checks the top left
						
						if(freeClickMode)
						{
							freeClick(row, col);
						}
						else
						{
							restrictClick(row, col);
						}
					}
				}
			}
		}
		checkWin();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
