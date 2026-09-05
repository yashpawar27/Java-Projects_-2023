	public class Dog extends Animal{

	private boolean goodBoy;
	
	public Dog(String name, int birthyear, boolean goodBoy)
	{
		super(name, birthyear);
		this.goodBoy = goodBoy;
	}
	
	public boolean goodBoy()
	{
		return goodBoy;
	}
	
	public String toString()
	{
		if(goodBoy)
		{
			return super.toString() + "\nI am a good boy.\n";
		}
		else
		{
			return super.toString() + "\nI am not a good boy.\n";
		}
	}
}
