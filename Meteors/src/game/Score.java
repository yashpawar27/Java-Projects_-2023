package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Score 
{
	public int score;
	public int lives = 3;
	private BufferedImage heart;
	
	Score()
	{
		score = 0;
		
		//If using bufferedImaged, Java requires a try/catch
		try
		{
			heart = ImageIO.read(new File("src/images/heart.png"));
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.white);
		g2d.setFont(new Font("Consolas", Font.PLAIN, 60));
		//Going to print the score
		g2d.drawString(String.valueOf(score/10000)+
				String.valueOf(score/1000)+
				String.valueOf(score/100)+
				String.valueOf(score/10)+
				String.valueOf(score%10), 10, 50);
		
		for(int i = 0; i < lives; i++)
		{
			g2d.drawImage(heart, 635 - (i*heart.getWidth()), 5, null);
		}
	}
	
}
