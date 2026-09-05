import java.util.Scanner;
public class ahhh {
	public static void main(String[]args) {
		Scanner s = new Scanner(System.in);
		String l = s.nextLine();
		String m = s.nextLine();
		int one = l.length();
		int two = m.length();
		if(one>=two)System.out.println("go");
		else System.out.println("no");
	}
}
