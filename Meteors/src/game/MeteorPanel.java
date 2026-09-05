package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MeteorPanel extends JPanel implements ActionListener {

	private int WIDTH = 700, HEIGHT = 700, numAsteroids = 7;
	private int numBooms = 9, boomsCount = 0;
	private Timer gameTimer;
	private int delay = 20;
	private BufferedImage BG;
	public static boolean alive = true, paused = false;
	
	//boss is a ship
	private Ship boss;
	public BufferedImage[] Explode = new BufferedImage[9];
	private Meteors[] asteroids;
	private Explosion[] booms;
	private ArrayList<Lasers> lasers;
	private Score score;
	private Sound laserSound, explode, alarm;
	
	public MeteorPanel() 
	{
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		//KL is a subclass  that stands for KeyListener
		addKeyListener(new KL());
		setUp();
		gameTimer = new Timer(delay, this);
		gameTimer.start();
	}
	private void setUp()
	{
		try
		{
			/*java has a class called File, and you have tp call that file
			for images and sound and stuff*/
			BG = ImageIO.read(new File("src/images/starfield.png"));

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		asteroids = new Meteors[numAsteroids];
		for(int a = 0; a < numAsteroids; a++)
		{
			asteroids[a] = new Meteors(a, WIDTH, HEIGHT);
		}
		
		booms = new Explosion[numBooms];
		for(int a = 0; a < numBooms; a++)
		{
			booms[a]= new Explosion(a);
		}
		
		boss = new Ship(WIDTH, HEIGHT);
		lasers = new ArrayList<Lasers>();
		score = new Score();
		laserSound = new Sound("src/images/Laser_Shoot.wav");
		explode = new Sound("src/images/rumble1.wav");
		alarm = new Sound("src/images/alarm.wav");
	}
	
	public void paint(Graphics g)
	{
		super.paintComponent(g);
		//Cast g to g2d
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawImage(BG, 0, 0, null);
		
		if(alive)
		{
			draw(g2d);
		}
		else
		{
			//What draws the spaceship explosions
			
			booms[boomsCount%9].draw(g2d,boss.x);
			boomsCount++;
		}
		if(boomsCount > 8)
		{
			
			restart();
			boomsCount = 0;
		}
		
		if(!alive)
		{
			for(int i = 0; i < numAsteroids; i++)
			{
				asteroids[i].draw(g2d);
			}
			for(int i = 0; i < lasers.size(); i++)
			{
				lasers.get(i).draw(g2d);
			}
			
		}
	}
	
	private void move()
	{
		boss.move();
		
		for(int i = 0; i < numAsteroids; i++)
		{
			asteroids[i].move();
		}
		for(int i = 0; i < lasers.size(); i++)
		{
			lasers.get(i).move();
		}
	}
	
	private void checkCollisions()
	{
		//Asteroids
		for(int i = 0; i < numAsteroids; i++)
		{
			//If it goes off the screen, we create a new asteroid at the top
			if(asteroids[i].x <= (0-asteroids[i].width/2) ||
					asteroids[i].x > WIDTH||
					asteroids[i].y > HEIGHT)
			{
				asteroids[i] = new Meteors(i, WIDTH, HEIGHT);
			}
		}
		
		//Asteroid and Laser
		for(int i = 0; i < numAsteroids; i++)
		{
			//Creates a rectangle on top of the Meteor/Asteroid BufferedImage
			Rectangle r1 = new Rectangle(asteroids[i].x, asteroids[i].y,
					asteroids[i].asteroid.getWidth(),asteroids[i].asteroid.getHeight());
			for(int j = 0; j < lasers.size(); j++)
			{
				//Create another rectangle and put it around the laser(similar to the loop above)
				Rectangle r2 = new Rectangle(lasers.get(j).x,lasers.get(j).y,
						lasers.get(j).laser.getWidth(),lasers.get(j).laser.getHeight());
				//If both rectangles are touching
				if(r1.intersects(r2))
				{
					//explode noise was extremely loud, so we turned it down
					explode.play(-10);
					lasers.remove(j);
					asteroids[i] = new Meteors(i, WIDTH, HEIGHT);
					
					if(i==0)
					{
						score.score+=5;
					}
					else if(i<=3)
					{
						score.score+=3;
					}
					else
					{
						score.score+=1;
					}
					//So it doesn't matter if the laser is touching 2 asteroids
					break;
				}
			}
		}
		
		//Asteroid and Ship
		
		
		for(int i = 0; i < numAsteroids; i++)
		{
			//Creates a rectangle on top of the Meteor/Asteroid BufferedImage
			Rectangle r1 = new Rectangle(asteroids[i].x, asteroids[i].y,
					asteroids[i].asteroid.getWidth(),asteroids[i].asteroid.getHeight());
			Rectangle r2 = new Rectangle(boss.x, boss.y,
					boss.ship.getWidth(), boss.ship.getHeight());
			if(r1.intersects(r2))
			{
				alarm.play(5);
				asteroids[i] = new Meteors(i, WIDTH, HEIGHT);
				score.lives--;
				if(score.lives < 0)
				{
					repaint();
					gameOver();
					restart();
				}
			}
		}
	}
	
	private void gameOver()
	{
		alive = false;
		//explode sound effect is too loud
		explode.play(-10);
		repaint();
		
	}
	
	private void restart()
	{
		paused = true;
		int over = JOptionPane.showConfirmDialog(null, "Would you like to play again?",
				"Game Over", JOptionPane.YES_NO_OPTION);
		
		//Want to play again
		if(over==0)
		{
			boomsCount = 0;
			alive = true;
			paused = false;
			//Resets lives and score
			score = new Score();
			boss = new Ship(WIDTH, HEIGHT);
		}
		else
		{
			System.exit(0);
		}
	}
	
	private void draw(Graphics2D g2d)
	{
		//Drawing the BufferedImages
		
		boss.draw(g2d);
		score.draw(g2d);
		
		for(int i = 0; i < numAsteroids; i++)
		{
			asteroids[i].draw(g2d);
		}
		for(int i = 0; i < lasers.size(); i++)
		{
			lasers.get(i).draw(g2d);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		move();
		checkCollisions();
		repaint();	
	}
	
	public class KL extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode()== KeyEvent.VK_LEFT)
			{
				boss.keyPressed(e);
			}
			if(e.getKeyCode()== KeyEvent.VK_RIGHT)
			{
				boss.keyPressed(e);
			}
			
			if(e.getKeyCode()==KeyEvent.VK_SPACE)
			{
				laserSound.play(-10);
				//Center of the ship
				int xCoord = boss.x + (boss.ship.getWidth()/2);
				//Top of the ship
				int yCoord = boss.y;
				//Creates a new Laser
				lasers.add(new Lasers(xCoord, yCoord));
			}
			
			
		}
		
		public void keyReleased(KeyEvent e)
		{
			if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				boss.keyReleased(e);
			}
		}
	}
}
