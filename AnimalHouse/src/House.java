import java.util.ArrayList;

public class House {
	
	ArrayList <Animal>animals;
	
	public House()
	{
		animals = new ArrayList<Animal>();
	}
	
	void printAnimals()
	{
		 for(int a = 0; a < animals.size(); a++)
		 {
			 System.out.println(animals.get(a).toString());
		 }
	}
	
	void cleanHouse()
	{
		//Gets rid of dups for each animal
		for(int a = 0; a < animals.size(); a++)
		{
			int size = animals.get(a).getToys().size();
			
			//Compares toy b with toy c
			for(int b = 0; b < size; b++)
			{
				for(int c = b+1; c < size; c++)
				{
					if(animals.get(a).getToys().get(b).equals(animals.get(a).getToys().get(c)))
					{
						animals.get(a).getToys().remove(c);
						size = size-1;
						
					}
				}
			}
		}
	}
	
	void add(Animal a)
	{
		animals.add(a);
	}
	
	void remove(Animal a)
	{
		animals.remove(a);
	}
}
