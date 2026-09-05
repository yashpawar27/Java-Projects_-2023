import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class HuffmanCompressor {
	
	public static void main(String[] args)
	{
		HuffmanCompressor n = new HuffmanCompressor();
		HuffmanTree t = new HuffmanTree(n.countFrequencies("src/short.txt"));
		t.write("src/short.txt");
	}
	
	int[] countFrequencies(String fileName)
	{
		int[] out = new int[256];
		
		File file = new File(fileName);
		
		Scanner input = null;
		
		try
		{
			
			input = new Scanner(file);
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
		
		while(input.hasNextLine())
		{
			String line = input.nextLine();
			char letter = line.charAt(0);
			
			int num = 0;
			while(line.contains(line.substring(0, 1)))
			{
				line = line.substring(0, line.indexOf(letter)) +
						line.substring(line.indexOf(letter));
				num++;
			}
			out[letter] = num;
		}
		
		return out;
	}
}
