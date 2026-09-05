import java.util.ArrayList;

public class Poker {
	
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

}
