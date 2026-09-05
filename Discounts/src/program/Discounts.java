package program;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Discounts 
{

	public static void main(String[] args) 
	{
		
		ArrayList<Integer> data = new ArrayList<>();
		// Read word in list
		try {
			File file = new File("src/program/discounts.txt");
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
		
		int numCases = data.remove(0);
		for (int i = 0; i < numCases; i++)
		{
			String numStr = Integer.toString(data.remove(0));
			int numDigits = numStr.length();
			int min = Integer.parseInt(numStr.substring(1, numDigits));
			int temp;
			for (int j = 0; j < numDigits; j++) 
			{
				temp = Integer.parseInt(numStr.substring(0, j) + numStr.substring(j+1, numDigits));
				if (min > temp) 
				{
					min = temp;
				}
			}
			System.out.println(min);
		}
		
	}

}

