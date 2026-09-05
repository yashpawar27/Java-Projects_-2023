package program;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieSuggester
{
	public static int best;
		
	public static int temp4;
	
	public static void main(String[] args) 
	{
		
		

		ArrayList<Integer> data = new ArrayList<>();
		// Read word in list
		try {
			File file = new File("src/files/infoD.txt");
			
			Scanner scan = new Scanner(file);
			
			while (scan.hasNextInt()) 
			{
				data.add(scan.nextInt());
			}
			scan.close();
			}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		
		int movies = data.remove(0);
		
		for(int a = 0; a < movies; a++)
		{
			ArrayList<Integer> length = new ArrayList<>();
			ArrayList<Integer> rating = new ArrayList<>();
			ArrayList<Integer> stat = new ArrayList<>();
			ArrayList<Integer> order = new ArrayList<>();
			
			int numMovies = data.remove(0);
			
			for(int b = 0; b < numMovies; b++)
			{
				length.add(data.remove(0));
			}
			for(int c = 0; c < numMovies; c++)
			{
				rating.add(data.remove(0));
			}	
			
			for(int d = 0; d < rating.size(); d++)
			{
				stat.add(length.get(d) * rating.get(d));
			}
			
			int max = stat.get(0);
			
			for(int e = 0; e < stat.size(); e++)
			{
				
				if(stat.get(e) > max)
				{
					
					order.clear();
					order.add(e);
					

					max = stat.get(e);
					
					
					
				}
				else if(stat.get(e) >= max)
				{
		
					order.add(e);
					max = stat.get(e);
					
					
				}
				
				
			}
			
			
			temp4  = rating.get(0);
				
			if(order.size() > 1)
			{
				
				
				for(int f = 0; f < order.size(); f++)
				{
					if(rating.get(order.get(f)) > temp4) 
					{
						best = order.get(f);
						temp4 = rating.get(order.get(f));
						
					}
				
				
				}
				best = best +1;
				System.out.println(best);
				
			}
			else
			{
				int temp2 = order.get(0);
				System.out.println(temp2 + 1);
			}
			
		}
		
		
	}

}
