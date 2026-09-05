package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Paddle
{
	private int xPos, yPos;
	private int xSpeed = 15;
	private int velocity = 0;
	private Random rand = new Random();
	private BufferedImage paddle;
	private boolean temp = BrickPanel.temp;
	private boolean temp2 = BrickPanel.temp2;
	
	Paddle()
	{
		loadImage();
	}

	private void loadImage()
	{
		try
		{
			paddle = ImageIO.read(new File("src/images/paddle.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		//Sets bounds
		xPos = rand.nextInt(BrickPanel.WIDTH-paddle.getWidth());
		yPos = BrickPanel.HEIGHT-paddle.getHeight();
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			setXDirection(-xSpeed);
			move();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			setXDirection(xSpeed);
			move();
		}
		
		temp = BrickPanel.temp;
		if(BrickPanel.ball.get(0).ySpeed == 0 &&
				BrickPanel.ball.get(0).getBounds().y == 250 &&
				BrickPanel.ball.get(0).getBounds().x == 300)
		{
			for(int b = 0; b < BrickPanel.numBalls; b++)
			{
				BrickPanel.ball.get(b).xSpeed = BrickPanel.speed * -1;
				BrickPanel.ball.get(b).ySpeed = BrickPanel.speed * -1;
			}
			BrickPanel.temp = false;
		}
		
		if(BrickPanel.ball.get(0).ySpeed == 0)
		{
			for(int b = 0; b < BrickPanel.numBalls; b++)
			{
				BrickPanel.ball.get(b).xSpeed = BrickPanel.speed;
				BrickPanel.ball.get(b).ySpeed = BrickPanel.speed;
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			setXDirection(0);
			move();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			setXDirection(0);
			move();
		}
	}
	
	public void setXDirection(int xDirection)
	{
		velocity = xDirection;
	}
	
	public void move()
	{
		xPos += velocity;
		
		if(xPos <= 0)
		{
			xPos = 0;
		}
		
		if(xPos >= BrickPanel.WIDTH - paddle.getWidth())
		{
			xPos = BrickPanel.WIDTH - paddle.getWidth();
		}
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		g2D.drawImage(paddle, xPos, yPos, null);
	}
	
	public Rectangle getBounds()
	{
		//Rectangle directly covers where the image is.
		//Rectangle goes around the image.
		return new Rectangle(xPos, yPos, paddle.getWidth(), paddle.getHeight());
	}
	
}
