
public class BacktrackingProbs {
	
	public static void main(String[] args) {
		climbStairs(4, "");
	}
	
	private static void printBinary(int n, String soFar)
	{
		if(n==soFar.length())
		{
			System.out.print(soFar + " ");
			return;
		}
		
		printBinary(n, soFar + 0);
		printBinary(n, soFar + 1);
	}
	
	private static void climbStairs(int steps, String out)
	{
		if(steps <= 0)
		{
			System.out.println("");
			return;
		}
		
		
		climbStairs(steps-1, 1 + "");
	}
	
}