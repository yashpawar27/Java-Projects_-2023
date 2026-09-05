
public class Toy {

	private String name;
	
	public Toy(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return "A " + name;
	}
	
	public boolean equals(Toy toy)
	{
		if(name.equals(toy.getName()))
		{
			return true;
		}
		return false;
	}
}
