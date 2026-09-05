package feeder;

import java.util.ArrayList;



class CatFeeder
{

	
	public static GetWord word = new GetWord();
	

	public static void main(String[] args)
	{

		
		
		word.plan();
		
		
		
		int index = word.wordlist.remove(0);
		
		
		
		for(int a = 0; a < index; a++)
		{
			int cats;
			ArrayList<Integer> feedingOrder = new ArrayList<Integer>();
			int cans;
			cats = word.wordlist.get(0);
			word.wordlist.remove(0);
			
			cans = word.wordlist.get(0);
			word.wordlist.remove(0);
			
	
			
			for(int c = 0; c < cans; c++)
			{
				feedingOrder.add(word.wordlist.get(0));
				word.wordlist.remove(0);
				
			}
			
			if(Check(cats, feedingOrder, cans))
			{
				System.out.println("YES");
			}
			else
			{
				System.out.println("NO");
			}
			
			

		}
		
		

	}
	
	
	private static boolean Check(int cats, ArrayList<Integer> feedingOrder, int cans)
	{
		int[] catsFed = new int[cats];

		for(int x = 0; x < feedingOrder.size(); x++)
		{
			catsFed[feedingOrder.get(x) - 1]++;
			for(int i = 0; i < cats; i++)
			{
				if (catsFed[feedingOrder.get(x) - 1] - catsFed[i] > 1)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	

}
