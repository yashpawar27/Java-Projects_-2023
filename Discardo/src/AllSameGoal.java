public class AllSameGoal implements Goal
{
	int num;
	
	public AllSameGoal(int num)
	{
		this.num = num;
	}

	@Override
	public boolean hasWon(int[] hand) {
		//Works
		
		for(int a = 0; a < hand.length; a++)
		{
			if(hand[a] != num)
			{
				return false;
			}
		}
		
		return true;
	}

}