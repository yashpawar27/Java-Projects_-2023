import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Bot implements Player
{
	
	private int[] hand;
	private Goal goal;
	
	@Override
	public void init(int[] h, Goal g)
    {
        hand = h;
        goal = g;
    }

	@Override
	public void maybeReplaceCard(int card) 
	{
		System.out.println("Card Drawn:  " + card);
        System.out.print("Which card to discard?  ");

        if(goal.getClass().equals(RunGoal.class) || goal.getClass().equals(MultiGoal.class))
        {
        	for(int a = 0; a < hand.length; a++)
        	{
        		if(hand[a] != card)
        		{
        			hand[a] = card;
        			return;
        		}
        	}
        }
//        else if(goal.getClass().equals(AllSameGoal.class) || goal.getClass().equals(MultiGoal.class))
//        {
//            int max_count = 0;
//            int maxfreq = 0;
//            
//            for (int a = 0; a < hand.length; a++)
//            {
//            	int count = 0;
//            	
//                for (int b = 0; b < hand.length; b++)
//                {
//                   if (hand[a] == hand[b])
//                   {
//                      count++;
//                   }
//                }
//                if(count > max_count)
//                {
//                    max_count = count;
//                    maxfreq = hand[a];
//                }
//            }
//            
//            for(int a = 0; a < hand.length; a++)
//            {
//            	if(hand[a] != maxfreq)
//            	{
//            		hand[a] = card;
//            		break;
//            	}
//            }
//        }
        
//        if(goal.getClass().equals(new AllSameGoal(9).getClass()))
//        {
//        	for(int a = 0; a < hand.length; a++)
//        	{
//        		if(hand[a] != card)
//        		{
//        			hand[a] = card;
//        			break;
//        		}
//        	}
//        }

	}
	
}