package game;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Meteors 
{
	public int x, y, xVelocity, yVelocity;
	public int width, height, rotate;
	private Random rand;
	public BufferedImage asteroid;
	
	Meteors(int i, int width, int height)
	{
		try
		{
			asteroid = ImageIO.read(new File("src/images/asteroid"+ i +".png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		rand = new Random();
		x = rand.nextInt(width);
		
		if(rand.nextBoolean())
		{
			//Between 0 to 3
			xVelocity = rand.nextInt(3)+1;
		}
		else
		{
			//Between -3 to 0
			xVelocity = -(rand.nextInt(3)+1);
		}
		y = -(asteroid.getHeight());
		yVelocity = rand.nextInt(3)+1;
	}
	
	public void move()
	{
		x += xVelocity;
		y += yVelocity;
		rotate += 2;
	}
	
	public void draw(Graphics2D g2d)
	{
		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.rotate(Math.toRadians(rotate), asteroid.getWidth()/2, asteroid.getHeight()/2);
		g2d.drawImage(asteroid, at, null);
	}
	
}
