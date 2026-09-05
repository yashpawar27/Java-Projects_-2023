package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Lasers
{
	public BufferedImage laser;
	public int x, y, yVelocity = -4;
	//Since laser never changes direction, we can assign right now
	
	Lasers(int x, int y)
	{
		//If using bufferedImaged, Java requires a try/catch
		try
		{
			laser = ImageIO.read(new File("src/images/laser.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(laser, x, y, null);
	}
	
	public void move()
	{
		y += yVelocity;
	}
}
