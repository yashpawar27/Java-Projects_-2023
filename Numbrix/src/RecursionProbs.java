import java.io.FileNotFoundException;

public class RecursionProbs {
	
	public static void main(String[] args) throws FileNotFoundException
	{
//		System.out.println(numEars(5));

//		countdown(10);
//		System.out.println(cheerlead("go team!", 5));
//		System.out.println(exponent(5,3));
//		System.out.println(fibonacci(8));
//		System.out.println(printPattern(10));
//		System.out.println(countNumA("aaHELLOa"));
//		printAtoBbyC(10, 30, 5);
//		System.out.println(countOdds(1234567890));
//		System.out.println(sumDigits(123));
	}
	
	//Works
	public static int numEars(int bunnies)
	{
		if(bunnies == 0)
		{
			return 0;
		}   
	    return 2 + numEars(bunnies - 1);
	}
	
	//Works
	public static void countdown(int num)
	{
		System.out.print(num+", ");
		
		if(num == 0) 
		{
			System.out.print("blastoff!");
			return;
		}
		countdown(num-1);
	}
	
	//Works
	public static String cheerlead(String str, int i)
	{
		if(i != 0)
		{
			return str + " " + cheerlead(str, i-1);
		}
		return "";
	}
	
	//Works
	public static int exponent(int base, int exp)
	{
		if(exp == 0)
		{
			return 1;
		}
		
		if(exp > 1)
		{
			return base * exponent(base, exp-1);
		}
		return base; 
	}
	
	//Works
	public static int fibonacci(int n)
	{
		if(n <= 1)
		{
			return n;
		}
           
		return fibonacci(n-1) + fibonacci(n-2);
        
	}
	
	//Works
	public static String printPattern(int n)
	{
		if(n >= 0)
		{
			return n+ ", " + printPattern(n-5);
		}
		return n+"";
	}
	
	//Works
	public static int countNumA(String str)
	{
		if(str.length() > 0 && str.substring(0, 1).equals("a"))
		{
			return 1 + countNumA(str.substring(1));
		}
		else if(str.length() > 0)
		{
			return countNumA(str.substring(1));
		}
		return 0;
	}
	
	//Works
	public static void printAtoBbyC(int a, int b, int c)
	{
		System.out.print(a+" ");
		if(a != b)
		{
			printAtoBbyC(a+c, b, c);
		}
	}
	
	//Works
	public static int countOdds(int a)
	{
		if(a > 0)
		{
			if((a%10) % 2 == 1)
			{
				return 1 + countOdds(a/10);
			}
			return countOdds(a/10);
			
		}
		return 0;
	}
	
	//Works
	public static int sumDigits(int a) 
	{
		if(a > 0)
		{
			return (a%10) + sumDigits(a/10);
		}
		return 0;
	}
	
}
