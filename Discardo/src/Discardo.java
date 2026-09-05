import java.util.*;

public class Discardo
{
	public static void main(String[] args) {
		Discardo.test();
	}
	
    public static void test()
    {
//        int numCards = 3;
//        int numTurns = play(new Human(), numCards, new RunGoal());
//        System.out.println("Player reached goal in " + numTurns + " turns");
    	
//    	Goal g = new RunGoal(); //RunGoal is-a Goal
//    	int[] hand1 = {4, 3, 1};
//    	System.out.println(g.hasWon(hand1));
//    	int[] hand2 = {4, 3, 1, 2};
//    	System.out.println(g.hasWon(hand2));
    	
//    	Goal goal2 = new AllSameGoal(2);
//    	int[] hand1 = {2, 2, 2, 8, 2};
//    	System.out.println(goal2.hasWon(hand1)); //false
//    	int[] hand2 = {2, 2, 2, 2, 2, 2};
//    	System.out.println(goal2.hasWon(hand2)); //true
//    	int[] hand3 = {5, 5, 5, 5};
//    	System.out.println(goal2.hasWon(hand3)); //false
//    	Goal only5 = new AllSameGoal(5);
//    	System.out.println(only5.hasWon(hand3)); //true
    	
//    	MultiGoal mg = new MultiGoal();
//    	mg.addGoal(new RunGoal()); //you only need to define ONE addGoal method
//    	mg.addGoal(new AllSameGoal(9));
//    	int[] hand1 = {4, 5, 6, 7};
//    	System.out.println(mg.hasWon(hand1)); //true
//    	int[] hand2 = {9, 9, 9};
//    	System.out.println(mg.hasWon(hand2)); //true
//    	int[] hand3 = {5, 5, 5};
//    	System.out.println(mg.hasWon(hand3)); //false
    	
//    	Discardo.play(new Human(), 4, new AllSameGoal(7));
    	Discardo.play(new Bot(), 3, new RunGoal());
    	
    }

    public static int play(Player p, int numCards, Goal goal)
    {
        int[] hand = new int[numCards];
        
        for (int i = 0; i < hand.length; i++)
            hand[i] = randomCard();

        p.init(hand, goal);

        int numTurns = 0;

        while (numTurns < 100 && !goal.hasWon(hand))
        {
            System.out.println("Turns Taken:  " + numTurns);
            System.out.println("Hand:  " + Arrays.toString(hand));
            p.maybeReplaceCard(randomCard());
            numTurns++;
        }
        System.out.println("Turns Taken:  " + numTurns);
        System.out.println("Hand:  " + Arrays.toString(hand));

        return numTurns;
    }

    public static int randomCard()
    {
        return (int)(Math.random() * 9) + 1;
    }
}