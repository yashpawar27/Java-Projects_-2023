import java.util.ArrayList;

public class Animal {
	
	private String name;
	private int birthyear;
	private Animal friend;
	static int currentyear;
	private ArrayList<Toy> toys = new ArrayList<Toy>();
	
	public Animal(String name, int birthyear)
	{
		this.name = name;
		this.birthyear = birthyear;
		currentyear = 2017;
	}
	
	void addToy(Toy t)
	{
		toys.add(t);
	}
	
	void removeToy(Toy t)
	{
		if(toys.indexOf(t)!=-1)
		{
			toys.remove(toys.indexOf(t));
		}
	}
	
	void removeToy(int t)
	{
		if(toys.size()>t)
		{
			toys.remove(t);
		}
	}
	
	void setFriend(Animal a)
	{
		friend = a;
	}
	
	public int getAge()
	{
		return currentyear-birthyear;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getBirthyear()
	{
		return birthyear;
	}
	
	public Animal getFriend()
	{
		return friend;
	}
	
	public ArrayList<Toy> getToys()
	{
		return toys;
	}
	
	public String toString()
	{
		String out = "Hello, I am " + name + ". I am " + getAge() + " years old.";
		
		if(friend != null)
		{
			out += "\nI have a friend named " + friend.getName();
		}
		else
		{
			out += "\nI have no friends";
		}
		
		out += "\nI have the following toys: " + toys;
		
		
		return out;
	}
}
