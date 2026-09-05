package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Brick 
{
	private int xPos, yPos;
	private BufferedImage brick;
	private Random rand = new Random();
	public boolean visible = true;
	
	
	
	
	//All constructors are public so no need for public, private whatever
	Brick(int row, int col)
	{
					creatBrick(row, col);
		
	}
	
//	private void sBrick()
//	{
//		try
//		{
//			brick = ImageIO.read(new File("src/images/b0.png"));
//			System.out.println("It gets here");
//			xPos = 70;
//			yPos = 0;
//		} 
//		catch (IOException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}


	//Each brick will be its own unique object
	private void creatBrick(int row, int col) 
	{
		
		try 
		{
			
			//Int num from 0 - 9
			int i = rand.nextInt(10);
			brick = ImageIO.read(new File("src/images/b"+i +".png"));
			
			xPos = col*brick.getWidth();
			yPos = row*brick.getHeight();
			

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g)
	{
		if(visible)
		{
			Graphics2D g2d = (Graphics2D)g;
			g2d.drawImage(brick, xPos, yPos, null);
		}
		
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(xPos, yPos, brick.getWidth(), brick.getHeight());
	}
}
