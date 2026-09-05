import java.util.*;

public class Blackjack
{
    public static ArrayList<Card> makeDeck()
    {
    	ArrayList<Card> deck = new ArrayList<Card>();

        for (int rank = 1; rank <= 13; rank = rank + 1)
        {
            for (int suit = 0; suit <= 3; suit = suit + 1)
            {
            	deck.add(new Card(rank, suit));
            }
        }

        return deck;
    }

    public static void swap(ArrayList<Card> cards, int i, int j)
    {
    	Card card = cards.get(i);
    	cards.set(i, cards.get(j));
    	cards.set(j, card);
    }

    public static void dealOneCard(ArrayList<Card> deck, ArrayList<Card> hand)
    {
    	hand.add(deck.remove(0));
    }

    public static int getScore(ArrayList<Card> hand)
    {
    	int sum = 0;
    	for(int a = 0; a < hand.size(); a++)
    	{
    		if(hand.get(a).getRank()>10)
    		{
    			sum+=10;
    		}
    		else if(hand.get(a).getRank()==1)
    		{
    			sum+=1;
    		}
    		else
    		{
    			sum+=hand.get(a).getRank();
    		}

    	}
    	
        return sum;  //change
    }
}