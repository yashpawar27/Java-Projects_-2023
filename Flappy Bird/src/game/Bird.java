package game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird extends Rectangle
{
	private int x, y, yVelocity;
	private int gravity = 1;
	private BufferedImage[] bird;
	private int numBirds = 6;
	private int displayIdx = 0;
	
	public Bird()
	{
		resetBird();
		loadImages();

	}
	
	
	public void resetBird()
	{
		x = 100;
		y = 100;
		yVelocity = -1;
		
	}

	private void loadImages() 
	{
		//number of frames of the bird
		bird = new BufferedImage[numBirds];
		try
		{
			for(int i = 0; i < numBirds; i++)
			{
				bird[i] = ImageIO.read(new File("src/images/frame-"+i+".png"));
			}
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		
	}

}
