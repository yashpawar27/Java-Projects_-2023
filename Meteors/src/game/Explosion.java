package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Explosion 
{
	public BufferedImage image;
	
	public Explosion(int numImage)
	{
		loadImage(numImage);
	}
	
	private void loadImage(int x)
	{
		try
		{
			image = ImageIO.read(new File("src/images/Explosion" + x + ".png"));
		}
		catch(Exception e) //Generic Catch
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2d, int x) // That X is the draw for the explosion
	{
		g2d.drawImage(image, x, 500, null);
	}
}


