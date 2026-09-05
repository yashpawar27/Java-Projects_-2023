package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class MasterMindPanel extends JPanel implements MouseListener, ActionListener
{
private int HEIGHT = 750, WIDTH = 600;
private Dimension size = new Dimension(WIDTH, HEIGHT);

private String colorCode[] = new String[4];
private Color colorCode2[] = new Color[4];
private String guessColor[] = new String[4];
private Color passedMoves[] = new Color[40];
private Random rand = new Random();
private ImageIcon background = new ImageIcon("src/images/mastmind4.jpeg");
private Image backgroundFin = background.getImage();

private int passedCoordinates[][] = new int[40][2];
private int correctOvals[][] = new int[40][2];
private int inputs = -1;
private int clickY;
private int clickX;
private int turn = 0;
private int hint;
private int guesses = 0;
private int temp = 0;
private boolean showCorrect = false;
private boolean paintHint = false;
private Color guessHints[][] = new Color[10][4];
//row is used
private int row = 40;


JButton deleteButton = new JButton("Delete");
JButton gambleButton = new JButton("Gamble");



	public MasterMindPanel() 
	{
		this.setPreferredSize(size);
		this.setBackground(Color.BLACK);
		this.addMouseListener(this);
		setLayout(null);
		
		JLabel titleLBL = new JLabel("MasterMind");
		titleLBL.setForeground(Color.WHITE);
		titleLBL.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 28));
		titleLBL.setHorizontalAlignment(SwingConstants.CENTER);
		titleLBL.setBounds(208, 6, 195, 26);
		add(titleLBL);
		
		JLabel codeLBL = new JLabel("Code:");
		codeLBL.setForeground(Color.WHITE);
		codeLBL.setBounds(280, 645, 195, 30);
		codeLBL.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 28));
		add(codeLBL);
		
		JLabel chooseLBL = new JLabel("Choose");
		chooseLBL.setForeground(Color.WHITE);
		chooseLBL.setBounds(5, 10, 190, 40);
		chooseLBL.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 18));
		add(chooseLBL);
		
		JLabel correctLBL = new JLabel("Amount Correct");
		correctLBL.setForeground(Color.WHITE);
		correctLBL.setBounds(460, 5, 190, 40);
		correctLBL.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 18));
		add(correctLBL);
		
		
		deleteButton.setBounds(10, 350, 50, 20);
		deleteButton.addActionListener(this);
		add(deleteButton);
		
		
		gambleButton.setBounds(10, 380, 55, 20);
		gambleButton.addActionListener(this);
		add(gambleButton);
				
		
		for(int a = 0; a < 4; a++)
		{
			guessColor[a] = "";
		}
		
		computerCode();
		

	}
	

	
	public void fillInput(String color)
	{
		if(color.equals("RED"))
		{
			passedMoves[inputs]= Color.red;
		}
		else if(color.equals("BLUE"))
		{
			passedMoves[inputs]= Color.blue;
		}
		else if(color.equals("GREEN"))
		{
			passedMoves[inputs]= Color.green;
		}
		else if(color.equals("YELLOW"))
		{
			passedMoves[inputs]= Color.yellow;
		}
		else if(color.equals("MAGENTA"))
		{
			passedMoves[inputs]= Color.magenta;
		}
		else if(color.equals("CYAN"))
		{
			passedMoves[inputs]= Color.cyan;
		}


		if(turn == 4)
		{
			guesses++;
			paintHint = true;
			
			
			for(int b = 0; b < guessColor.length; b++)
			{
				if(colorCode[b].equals(guessColor[b]))
				{
					temp++;
				}
			}
			
			hint = temp;
			
			if(turn == 4)
			{
				System.out.println("Switching rows");	
				for(int a = 0; a < 4; a++)
				{
					guessColor[a] = "";
				}
				row += 60;
				turn = 0;
			}
			revalidate();
			repaint();
			if(temp == 4)
				{
				showCorrect = true;
				revalidate();
				repaint();
				
				
					System.out.println("Player wins!");
					
					
					int answer = JOptionPane.showConfirmDialog(null, 
							"You Won! Play again?","Game over", JOptionPane.YES_NO_OPTION);
					
					if(answer == 0)
					{
						restart();
					}
					else
					{
						System.exit(0);
					}
				}
			temp = 0;
			

		}
		
		for(int a = 0; a < 4; a++)
		{
			if(guessColor[a].equals("") || guessColor.length == 4)
			{
				validate();
				repaint();
				break;
			}
		}
		
		if(guesses == 10)
		{
			
			showCorrect = true;
			revalidate();
			repaint();
			
			int answer = JOptionPane.showConfirmDialog(null, 
					"You Lost. Play again?","Game over", JOptionPane.YES_NO_OPTION);
			
			if(answer == 0)
			{
				restart();
			}
			else
			{
				//Shut down completely
				System.exit(0);
			}
		}
		
		
	}
	
	public void computerCode()
	{
		for(int a = 0; a < 4; a++)
		{
			int temp = rand.nextInt(6);
		
			if(temp == 0)
			{
				colorCode[a] = "RED";
				colorCode2[a] = Color.red;
			}
			else if(temp == 1)
			{
				colorCode[a] = "BLUE";
				colorCode2[a] = Color.blue;
			}
			else if(temp == 2)
			{
				colorCode[a] = "GREEN";
				colorCode2[a] = Color.green;
			}
			else if(temp == 3)
			{
				colorCode[a] = "YELLOW";
				colorCode2[a] = Color.yellow;
			}
			else if(temp == 4)
			{
				colorCode[a] = "MAGENTA";
				colorCode2[a] = Color.magenta;
			}
			else if(temp == 5)
			{
				colorCode[a] = "CYAN";
				colorCode2[a] = Color.cyan;
			}
		}
		for(int b = 0; b < 4; b++)
		{
			System.out.println(colorCode[b]);
		}
		
	}
	
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw(g);
		

	}
	
	public void draw(Graphics g)
	{
		g.drawImage(backgroundFin, 0, 0, this);
		
		g.setColor(Color.RED);
		g.fillOval(10, 50, 40, 40);
		g.setColor(Color.BLUE);
		g.fillOval(10, 100, 40, 40);
		g.setColor(Color.GREEN);
		g.fillOval(10, 150, 40, 40);
		g.setColor(Color.YELLOW);
		g.fillOval(10, 200, 40, 40);
		g.setColor(Color.MAGENTA);
		g.fillOval(10, 250, 40, 40);
		g.setColor(Color.CYAN);
		g.fillOval(10, 300, 40, 40);
		
		int ytemp = 40;
		int xtemp = 190;
		
		int turnTemp = 0;
		for(int a = 0; a < 10; a++)
		{
			g.setColor(Color.WHITE);
			
			for(int b = 0; b < 4; b++)
			{
			g.fillOval(xtemp, ytemp, 40, 40);
			passedCoordinates[turnTemp][0] = xtemp;
			passedCoordinates[turnTemp][1] = ytemp;
			turnTemp++;
			xtemp += 60;
			
			}
		xtemp = 190;
		ytemp += 60;
		
		}
		ytemp = 690;
		xtemp = 190;
		
		
		if(showCorrect)
		{
			
			for(int a = 0; a < 04; a++)
			{
				g.setColor(colorCode2[a]);
				
				g.fillOval(xtemp, ytemp, 40, 40);
				xtemp += 60;
			}
			showCorrect = false;
			
		}

		
		if(inputs!=-1)
		{
			for(int a = 0; a < inputs + 1; a++)
			{
			g.setColor(passedMoves[a]);
			g.fillOval(passedCoordinates[a][0]+2, passedCoordinates[a][1]+2, 36, 36);
			}
			for(int b = 0; b < 4; b++)
			{
				g.setColor(Color.white);
				g.fillOval(correctOvals[b][0], correctOvals[b][1], 12, 12);
			}
		}
		
		
		g.setColor(Color.gray);
		int tempx2 = 480;
		int tempy2 = 40;
		
		int temp2 = 0;
		for(int a = 0; a < 10; a++)
		{
			for(int b = 0; b < 2; b++)
			{
				for(int c = 0; c < 2; c++)
				{
				tempx2 +=20;
				correctOvals[temp2][0] = tempx2;
				correctOvals[temp2][1] = tempy2;
				temp2++;
				g.fillOval(tempx2,tempy2 , 15, 15);
				
				}
				
			tempx2 = 480;
			tempy2 +=20;
			}
			tempy2+= 20;
		}		
		
		//temp is used to show how many iterations this loop has been through
		temp = 0;
		int guessTill = guesses -1;

		//Up till new guess
			if(guesses >= 1)
			{
				for(int a = 0; a < guessTill; a++)
				{
					for(int b = 0; b < 4; b++)
					{
						g.setColor(guessHints[a][b]);
						g.fillOval(correctOvals[(a*4)+b][0]+2, correctOvals[(a*4)+b][1]+2, 11, 11);
						
					}
				}
			}
			if(paintHint)
			{

				for(int a = 0; a < 4; a++)
				{
				
					if(hint > temp)
					{
						guessHints[guesses-1][a] = Color.black;
						g.setColor(Color.black);
					}
					else
					{
						guessHints[guesses-1][a] = Color.white;
						g.setColor(Color.white);
					}
					g.fillOval(correctOvals[((guesses-1)*4)+a][0]+2, correctOvals[((guesses-1)*4)+a][1]+2, 11, 11);
					temp++;
				}
			}
		temp = 0;
	}

	public void restart()
	{
		for(int a = 0; a < 4; a++)
		{
			guessColor[a] = "";
		}	
		inputs = -1;
		turn = 0;
		hint= 0;
		temp = 0;
		guesses = 0;
		paintHint = false;
		row = 40;
		temp = 0;
		computerCode();	
		revalidate();
		repaint();
		
		for(int a = 0; a < 4; a++)
		{
			System.out.println(colorCode[a]);
		}
			
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) 
	{
		clickX =e.getX();
	    clickY =e.getY();
	    
	    
	    //Red Button
	    if(clickX >= 10 && clickX <= 70 && clickY >= 50 && clickY <= 90)
	    {
	    	guessColor[turn] = "RED";
			inputs++;
			turn++;
	    	System.out.println("RED");
	    	fillInput("RED");
	    }
	    else if(clickX >= 10 && clickX <= 70 && clickY >= 100 && clickY <= 140)
	    {
	    	guessColor[turn] = "BLUE";
			inputs++;
			turn++;
	    	System.out.println("BLUE");
	    	fillInput("BLUE");
	    }
	    else if(clickX >= 10 && clickX <= 70 && clickY >= 150 && clickY <= 190)
	    {
	    	guessColor[turn] = "GREEN";
			inputs++;
			turn++;
	    	System.out.println("GREEN");
	    	fillInput("GREEN");
	    }
	    else if(clickX >= 10 && clickX <= 70 && clickY >= 200 && clickY <= 240)
	    {
	    	guessColor[turn] = "YELLOW";
			inputs++;
			turn++;
	    	System.out.println("YELLOW");
	    	fillInput("YELLOW");
	    }
	    else if(clickX >= 10 && clickX <= 70 && clickY >= 250 && clickY <= 290)
	    {
	    	guessColor[turn] = "MAGENTA";
			inputs++;
			turn++;
	    	System.out.println("MAGENTA");
	    	fillInput("MAGENTA");
	    }
	    else if(clickX >= 10 && clickX <= 70 && clickY >= 300 && clickY <= 340)
	    {
	    	guessColor[turn] = "CYAN";
			inputs++;
			turn++;
	    	System.out.println("CYAN");
	    	fillInput("CYAN");
	    }
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == deleteButton)
		{
			if(turn != 0)
			{
			turn--;
			inputs--;	
			}
			revalidate();
			repaint();
			
		}
		else if(e.getSource() == gambleButton)
		{
			int chanceGamble = rand.nextInt(4);
			int rowGamble = rand.nextInt(4);
			
			if(chanceGamble != 1)
			{
				for(int a = 0; a < 4; a++)
				{
					//Bug here
					//Bug w turning them all yellow or red or which ever color they picked last
					//Bug w the Hint Ovals being in the wrong place then where they were supposed to be
					passedMoves[(guesses*4)+a] = Color.black;
				}
				inputs = (inputs - turn)+4;	
				turn = 0;
				guesses++;
				revalidate();	
				repaint();	
				
				
				if(guesses == 10)
				{
					showCorrect = true;
					revalidate();
					repaint();
					
					int answer = JOptionPane.showConfirmDialog(null, 
							"You Lost. Play again?","Game over", JOptionPane.YES_NO_OPTION);
					
					if(answer == 0)
					{
						restart();
					}
					else
					{
						//Shut down completely
						System.exit(0);
					}
				}
				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "The color in spot number " + (rowGamble+1) + " is " + colorCode[rowGamble]);
				
			}
		}
		
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
}


