package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//Runnable creates a seperate thread
public class BrickPanel extends JPanel implements Runnable
{
	//Other classes can use Width and Height without creating a new object
	static final int WIDTH = 700, HEIGHT = 650;
	private final int numRows = 7, numCols = 7;
	
	private Image image;
	private Graphics graphics;
	private Brick[][] bricks;
	private Paddle paddle;
	public static ArrayList<Ball> ball = new ArrayList<Ball>();
	private Thread thread;
	private Music music;
	public static int numBalls = 1;
	private int numBricks = 49;
	private int ballLives = 3;
	public static int speed;
	public static boolean temp = false;
	public static boolean temp2 = false;
	

	
	//Runs the game in a thread, and runs music in a different thread
	public BrickPanel() 
	{
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		music = new Music();
		//Music extends thread in the music class
		music.start();
//		//Uses a subclass
		addKeyListener(new MyKey());
		gameSetUp();
		this.thread = new Thread(this);
		thread.start();
	}

	private void gameSetUp() 
	{
//		ball = new Ball[5];
		paddle = new Paddle();
		ball.add(new Ball());
		bricks = new Brick[numCols][numRows];
		
		
		
		for(int row = 0; row < numRows; row++)
		{
			for(int col = 0; col < numCols; col++)
			{
				//Places a brick at that location
				bricks[row][col] = new Brick(row,col);
			}
		}
	}
	
	public void restart()
	{
		numBalls = 1;
		numBricks = 49;
		ballLives = 4;
		ball.clear();
		gameSetUp();
	}
	
	//Main move() method
	public void move()
	{
		paddle.move();
		
		for(int a = 0; a < numBalls; a++)
		{
			ball.get(a).move();
		}
		
	}
	
	public void checkCollision()
	{
		Rectangle pRect = paddle.getBounds();
		
		Rectangle ballRect;
		
		for(int a = 0; a < numBalls; a++)
		{
			
			ballRect = ball.get(a).getBounds();
			
			if(ballRect.y + ballRect.getHeight() > pRect.y &&
				ballRect.x > pRect.x - 5 &&
				ballRect.x < pRect.x + pRect.getWidth()-3)
			{
				ball.get(a).ySpeed*=-1;
			}
			
			if(ballRect.y + ballRect.getHeight() >= HEIGHT)
			{
				speed = ball.get(0).ySpeed;
				
				if(a == 0)
				{
					ball.get(0).xPos = 300;
					ball.get(0).yPos = 250;
					ball.get(0).ySpeed*=-1;
					ballLives-=1;
					
					for(int b = 0; b < numBalls; b++)
					{
						ball.get(b).xSpeed = 0;
						ball.get(b).ySpeed = 0;
					}
					
					temp = true;
					System.out.println("Touch");
					
					if(ballLives == 0)
					{
						ball.get(0).xPos = 300;
						ball.get(0).yPos = 250;
						
						int answer = JOptionPane.showConfirmDialog(null, 
								"You Lost :( ! Play again? ","Game over", JOptionPane.YES_NO_OPTION);
						if(answer == 0)
						{
							restart();
						}
						else
						{
							System.exit(0);
						}
						
						for(int b = 1; b < numBalls; b++)
						{
							ball.remove(b);
						}
					}
				}
				else
				{
					ball.remove(a);
					numBalls--;
					temp2 = true;
				}
				
				
				
				
			}
		}
		
		
		
		//Checks if ball is past the left side
		
		
		for(int rows = 0; rows < numRows; rows++)
		{
			for(int cols = 0; cols < numCols; cols++)
			{
				if(bricks[rows][cols].visible)
				{
					Rectangle brickRect = bricks[rows][cols].getBounds();
					//Detects if ball is touching the bottom of the brick
					
					for(int b = 0; b < numBalls; b++)
					{
						ballRect = ball.get(b).getBounds();
						
						if(ballRect.y < brickRect.getHeight() + brickRect.y &&
							ballRect.x > brickRect.x &&
							ballRect.x < brickRect.getWidth() + brickRect.x)
						{
							bricks[rows][cols].visible = false;
							ball.get(b).ySpeed*=-1;
						
							numBricks-=1;
							System.out.println(numBricks);
						
							if(numBricks%10 == 2 && numBricks != 49)
							{
								ball.add(numBalls, new Ball());;
								numBalls++;
							}
							
							if(numBricks%14 == 0 && numBricks != 49)
							{
								for(int c = 0; c < numBalls; c++)
								{
									ball.get(c).ySpeed *=10;
									ball.get(c).ySpeed /=8;
									ball.get(c).xSpeed *=10;
									ball.get(c).xSpeed /=8;
									
								}
							}
						}
					}	
				}
			}
		}
		
		if(numBricks==0)
		{
			int answer = JOptionPane.showConfirmDialog(null, 
					"You Won :)! Play again?","Game over", JOptionPane.YES_NO_OPTION);
			if(answer == 0)
			{
				restart();
			}
			else
			{
				System.exit(0);
			}
		}
	}
	
	public void paint(Graphics g)
	{
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	public void draw(Graphics g)
	{
		for(int rows = 0; rows < numRows; rows++)
		{
			for(int cols = 0; cols < numCols; cols++)
			{
				bricks[rows][cols].draw(g);
			}
		}
		for(int a = 0; a < numBalls; a++)
		{
			ball.get(a).draw(g);
		}
		paddle.draw(g);
	}

	@Override
	public void run()
	{
		//saved as nanoseconds(billionth of a second). That's why long is used.
        long lastTime = System.nanoTime();
        int fps = 60;
        double ns = 1000000000/fps;
        double delta = 0;
        while(true)
        {
        	//16 million nano seconds is one frame;
			// every 16 million nano seconds, updates the screen
            long now = System.nanoTime();
            
            /*delta will keep growing until it is greater than 1
			Adding tiny tiny pieces of time until it gets to one
			1 divided by 16 million */
            delta += (now - lastTime)/ns;
            
            //Has to keep growing so that "lastTime" is smaller than "now". Then they equal each other.
			//"now" keeps growing 
            lastTime = now;
            
          //For each time the ball is moved, it checks for a collision
            if(delta >= 1)
            {
            	move();
				checkCollision();
				//Doesn't have to pass anything in. This is specific to java
				repaint();
				//By subtracting by one, you retain the extra time
				delta--;
            }
        }
	}
	
	public class MyKey extends KeyAdapter
	{
		//Whatever they press is captured
		public void keyPressed(KeyEvent e)
		{
			paddle.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e)
		{
			paddle.keyReleased(e);
		}
	}
	
}


