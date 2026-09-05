package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ball 
{
	public int xPos = 300, yPos = 250;
	public int xSpeed = 4, ySpeed = 4;
	//Keep moving while getting it ready, so it doesn't slow down the game.
	private BufferedImage ball;
	
	
	Ball()
	{
		loadImage();
	}


	private void loadImage() 
	{
		try 
		{
			ball = ImageIO.read(new File("src/images/redball.png"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void move()
	{
		xPos += xSpeed;
		if(xPos < 0)
		{
			xPos = 0;
			
			xSpeed*=-1;
		}
		if(xPos > BrickPanel.WIDTH - ball.getWidth())
		{
			xPos = BrickPanel.WIDTH - ball.getWidth();
			xSpeed*=-1;
		}
		
		yPos += ySpeed;
		if(yPos < 0)
		{
			yPos = 0;
			ySpeed*=-1;
		}
		if(yPos > BrickPanel.HEIGHT - ball.getHeight())
		{
			yPos = BrickPanel.HEIGHT - ball.getHeight();
			ySpeed*=-1;
			
		}
	}
	
	public void draw(Graphics g)
	{
		//Casting Graphics to Graphics2D
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(ball, xPos, yPos, null);
	}

	
	//Can't check collisions of Images, only shapes
	public Rectangle getBounds()
	{
		return new Rectangle(xPos, yPos, ball.getWidth(), ball.getHeight());
	}
}
