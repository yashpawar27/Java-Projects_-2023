package game;

public class FourCircle 
{
	private int xPos;
	private int yPos;
	private int red, green, blue;
	private int diameter = 100;
	
	FourCircle(int xPos, int yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	
	//Getter method gets the value of the private variable
	//Setter method sets the value of the private variable
	public int getxPos() 
	{
		return xPos;
	}


	public void setxPos(int xPos) 
	{
		this.xPos = xPos;
	}


	public int getyPos() 
	{
		return yPos;
	}


	public void setyPos(int yPos) 
	{
		this.yPos = yPos;
	}


	public int getRed() 
	{
		return red;
	}


	public void setRed(int red) 
	{
		this.red = red;
	}


	public int getGreen()
	{
		return green;
	}


	public void setGreen(int green)
	{
		this.green = green;
	}


	public int getBlue() 
	{
		return blue;
	}


	public void setBlue(int blue) 
	{
		this.blue = blue;
	}


	public int getDiameter()
	{
		return this.diameter;
	}
	
	
	
}


