import java.util.*;

public class MultiGoal implements Goal
{
    private List<Goal> goals =  new ArrayList<>();

	@Override
	public boolean hasWon(int[] hand) 
	{
		for(int a = 0; a < goals.size(); a++)
		{
			if(goals.get(a).hasWon(hand))
			{
				return true;
			}	
		}
		return false;
	}

	public void addGoal(Goal goal) 
	{
		goals.add(goal);
	}
	
}