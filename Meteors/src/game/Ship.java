package game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ship 
{
	public BufferedImage ship;
	public BufferedImage[] Explode = new BufferedImage[9];
	public int turn = 0;
	public int x, y, width, height, speed = 5, xVelocity = 0;
	
	Ship(int width, int height) 
	{
		//If using bufferedImaged, Java requires a try/catch
		try
		{
			ship = ImageIO.read(new File("src/images/hero_ship.png"));
			
			for(int i = 0; i < 9; i++)
			{
				Explode[i] = ImageIO.read(new File("src/images/Explosion"+i+".png"));
			}
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		this.width = width;
		this.height = height;
		//Now perfectly lined up
		x = width/2 - ship.getWidth()/2;
		//Now the ship won't be at the exact bottom of the screen/ clipped through the bottom.
		y = height - 10 - ship.getHeight();
	}
	
	
	
	public void move()
	{
		//Either adds of subtracts for the ship's x position
		x+= xVelocity;
	}
	
	public void setXDirection(int xDirection)
	{
		//Changes the direction
		xVelocity = xDirection;
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			
			setXDirection(-speed);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			setXDirection(speed);
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			setXDirection(0);
		}
	}
	
	//Just draws the ship
	public void draw(Graphics2D g2d)
	{

			g2d.drawImage(ship, x, y, null);
	}
}
