public class Cat extends Animal{
	
	private int lives;
	
	public Cat(String name, int birthyear, int lives)
	{
		super(name, birthyear);
		this.lives = lives;
	}
	
	public Cat(String name, int birthyear)
	{
		super(name, birthyear);
		lives = 9;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public String toString()
	{
		return super.toString() + "\nI have " + getLives() + " lives.\n";
	}
}
