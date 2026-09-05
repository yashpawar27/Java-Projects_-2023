import java.util.List;

public abstract class Board {

	String[] ranks;
	String[] suites;
	Card[] cards;
	int size;
	Deck deck;
	 
	public Board(String[] ranks, String[] suites, Card[] cards, int size, Deck deck)
	{
		 this.ranks = ranks;
		 this.suites = suites;
		 this.cards = cards;
		 this.size = size;
		 this.deck = deck;
	}
	
	public String ranks(int idx)
	{
		return ranks[idx];
	}
	
	public String suites(int idx)
	{
		return suites[idx];
	}
	
	public Card cardAt(int k) 
	{
		return cards[k];
	}
	
	public int deckSize() 
	{
		return deck.size();
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean anotherPlayIsPossible()
	{
		for(int a = 0; a < cards.length; a++)
		{
			for(int b = 0; b < cards.length; b++)
			{
				if(cards[a].pointValue() + cards[b].pointValue() == 11)
				{
					return true;
				}
				if(cards[a].pointValue() + cards[b].pointValue() == 0)
				{
					return true;
				}
			}
		}
		return false;
	}
	

		
		private boolean containsJQK(List<Integer> selectedCards) {
			//Doesn't make sense
			boolean j = false, q = false, k = false;
			
			for(int a = 0; a < selectedCards.size(); a++)
			{
				if(cards[selectedCards.get(a)].rank().equals("jack"))
				{
					j = true;
				}
				
				if(cards[selectedCards.get(a)].rank().equals("queen"))
				{
					q = true;
				}
				
				if(cards[selectedCards.get(a)].rank().equals("king"))
				{
					k = true;
				}
			}
			
			return j && q && k;
		}
		
		public boolean isEmpty()
		{
			for(int a = 0; a < cards.length; a++)
			{
				if(cards[a] != null)
				{
					return false;
				}
			}
			return true;
		}
		
		public boolean isLegal(List<Integer> selectedCards) {
			
			int sum = 0;
			for(int a = 0; a < selectedCards.size(); a++)
			{
				sum += selectedCards.get(a);
			}
			
			if((selectedCards.size() == 2 && sum == 11) || 
					(selectedCards.size() == 3 && containsJQK(selectedCards)))
			{
				return true;
			}

			return false;
		}

		public void newGame()
		{
			
		}

		public void replaceSelectedCards(List<Integer> selection)
		{
			
		}

}
