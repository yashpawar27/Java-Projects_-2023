/**
 * Numbrix.java  6/11/2011
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 *
 * Solves Numbrix puzzles
 * http://www.parade.com/numbrix
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Represents a Numbrix puzzle.
 */
public class Numbrix
{
	/** The puzzle data */
	private int[][] grid;

	/** Indicates whether numbers are used in the original puzzle
	 *  If the number n is used, then used[n] is true;  Otherwise it's false */
	private boolean[] used;


	private int rows, cols;
	/**
	 * Constructs a Numbrix puzzle object.
	 * @param fileName the name of the file containing the puzzle data.
	 * @throws FileNotFoundException when fileName file does not exist.
	 */
	public Numbrix(String fileName) throws FileNotFoundException
    {
		
		
		Scanner scan = new Scanner(new File(fileName));
		
		rows = scan.nextInt();
		cols = scan.nextInt();
		
		
		grid = new int[rows][cols];
		used = new boolean[(rows*cols)+1];
		
		int num = 1;
		for(int row = 0; row < rows; row++)
		{
			for(int col = 0; col < cols; col++)
			{
				grid[row][col] = scan.nextInt();
				
				used[num] = false;
				num++;
				
				if(grid[row][col] != 0)
				{
					used[grid[row][col]] = true;
				}
			}
		}

		
		
		
    }

	/**
	 * Finds all solutions to the Numbrix puzzle stored in grid by
	 * calling recursiveSolve for every possible cell in grid that
	 * originally contains a 0 or a 1.  Each of these calls should
	 * attempt to solve the puzzle beginning with the number 1.
	 */
	public void solve()
	{
		for(int row = 0; row < rows; row++)
		{
			for(int col = 0; col < cols; col++)
			{
				if(grid[row][col] == 0)
				{
					recursiveSolve(row, col, 1);
				}
			}
		}
	}

	/**
	 * Attempts to solve the Numbrix puzzle by placing n in grid[r][c]
	 * and then makeing recursive calls (up, down, left, and right) to
	 * place n+1 and higher numbers.
	 * @param r the row of the cell in which to place n.
	 * @param c the column of the cell in which to place n.
	 * @param n the number to place in grid[r][c].
	 */
	private void recursiveSolve(int r, int c, int n)
	{
		
		//if in bounds
		if(r < 0 || r >= rows || c < 0 || c >= cols)
		{
			return;
		}
		
		boolean zero;
		if(grid[r][c] == 0)
		{
			zero = true;
		}
		else
		{
			zero = false;
		}
		
		if(zero && used[n])
		{
			return;
		}
		
		
		if(!zero && grid[r][c] != n)
		{
			return;
		}
		
		grid[r][c] = n;
		used[n] = true;
		
		if(n == rows*cols)
		{
			System.out.println(this);
		}
		
		
		recursiveSolve(r+1,c, n+1);
		recursiveSolve(r-1,c, n+1);
		recursiveSolve(r,c+1, n+1);
		recursiveSolve(r,c-1, n+1);
		
		if(zero)
		{
			used[n] = false;
			grid[r][c] = 0;
		}
	}

	/**
	 * Returns a String which represents the puzzle.
	 * @return the puzzle numbers with a tab after each number in a row
	 *         and a new line character after each row.
	 *         '-' characters should replace 0s in the output.
	 */
	public String toString()
	{
		
		String result = "";
		
		for(int row = 0; row < rows; row++)
		{
			for(int col = 0; col < cols; col++)
			{
				//if empty
				if(grid[row][col] == 0)
				{
					result += "-  ";
				}
				//if double-digit
				else if(String.valueOf(grid[row][col]).length() > 1)
				{
					result += grid[row][col]+" ";
				}
				//if single digit
				else
				{
					result += grid[row][col]+"  ";
				}
			}
			result += "\n";
		}

		return result;
	}
}