package feeder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GetWord
{
	public ArrayList<Integer>wordlist = new ArrayList<Integer>();
	
	public void plan()
	
	{
		
		try
		{
			
			File file = new File("src/words/plan.txt");
			Scanner scan = new Scanner(file);

			while(scan.hasNextInt())
			{
				wordlist.add(scan.nextInt());
			}
				
				
				scan.close();
			
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
	}
	

}

