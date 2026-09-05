
public class TreeNode implements Comparable<TreeNode>{
	int freq;
	int ascii;
	TreeNode left,right;

	public TreeNode(int freq, int ascii) 
	{ 
		this.freq = freq;
		this.ascii = ascii;
	}
	
	public TreeNode(int freq)
	{
		this.freq = freq;
	}
	
	public int getFreq()
	{
		return freq;
	}
	
	public int getAscii()
	{
		return ascii;
	}
	


	@Override
	public String toString() { return "" + (char)this.ascii; }

	@Override
	public int compareTo(TreeNode o) {
		
		return o.freq - this.freq;
	}
}
