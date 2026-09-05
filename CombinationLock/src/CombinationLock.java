
public class CombinationLock extends Lock{
	
	private String combination;
	
	public CombinationLock()
	{
		super();
		combination = "";
	}
	
	public CombinationLock(String combo)
	{
		super();
		combination = combo;
	}
	
	void open(String combo)
	{
		if(combo.equals(combination))
		{
			super.open();
		}
	}
	
	public String toString()
	{
		return super.toString() + "\n" + "Combination = " + combination + "\n";
	}
	
	void setCombination(String combo)
	{
		combination = combo;
	}
	
	String getCombination()
	{
		return combination;
	}
	
}
