package game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class BirdPanel extends JPanel implements ActionListener
{
	public static int WIDTH = 700, HEIGHT = 700, DELAY = 50, score = 0;
	private Bird bird;
	private Pipes pipes;	//Change font type later
	private Font gameFont = new Font("Times New Roman", Font.BOLD, 30);
	private boolean running;
	private Timer gameTimer;
	
	
	public BirdPanel()
	{
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		//You want to instantly start
		//Switches focus to panel
		this.setFocusable(true);
		running = true;
		pipes = new Pipes();
		bird = new Bird();
		//KL stands for KeyListener
		addKeyListener(new KL());
		gameTimer = new Timer(DELAY,this);
		gameTimer.start();
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw(g);
		
	}

	private void draw(Graphics g) 
	{
		pipes.draw();
		bird.draw();
		g.setFont(gameFont);
		g.drawString("Score: " + score, 20, 40);
		
	}
	
	private void move()
	{
		pipes.move();
		bird.move();
	}
	
	public void checkCollision()
	{
		int[] coords = pipes.getPipeCoordinates();
		if((coords[0] < 188 && coords[0]+100 > 188)&&
				(coords[1]> bird.getY()|| coords[1] + 200 < bird.getY()))
		{
			gameOver();
		}
			
			
	}
	
	private void updateScore()
	{
		int[] coords = pipes.getPipeCoordinates();
		if(coords[0] <= -100)
		{
			score++;
		}
	}

	private void gameOver() 
	{
		bird.resetBird();
		pipes.resetPipes();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//Calls all metheds every 15 miliseconds
		if(running) 
		{
			move();
			checkCollision();
			updateScore();
			repaint();
		}
	}
	
	//Subclass
	public class KL extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			bird.keyPressed(e);
		}
		public void keyReleased(KeyEvent e)
		{
			bird.keyReleased(e);
		}
	}
	
	
	
	
	
	
}
