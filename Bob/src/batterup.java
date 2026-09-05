import java.util.Scanner;

public class batterup {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		double sum = 0;
		for(int a = 0; a < num; a++)
		{
			if(s.hasNextInt())
			{
				sum+=s.nextInt();
			}
			else
			{
				sum+=s.nextDouble();
			}
		}
		System.out.println(""+ sum/num);
	}
}
