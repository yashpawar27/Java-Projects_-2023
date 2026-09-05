import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class HuffmanTree{

	Queue<TreeNode> q = new PriorityQueue<TreeNode>();
	Queue<String> routes = new PriorityQueue<String>();
	TreeNode root;
	
	HuffmanTree(int[] count)
	{
		
		for(int a = 0; a < count.length; a++)
		{
			if(count[a] > 0)
			{
				q.add(new TreeNode(a, (char) count[a]));
			}
		}
		
		root = createTree(q);
	}


	private TreeNode createTree(Queue<TreeNode> q)
	{
		
		while(q.size() > 1)
		{
			TreeNode n = new TreeNode(-1);
			if(!q.isEmpty())
			{
				n.right = q.poll();
			}
			if(!q.isEmpty())
			{
				n.left = q.poll();
			}
			q.add(n);
		}
		
		return q.poll();
	}
	
	
	void write(String fileName)
	{
		String outputFileName = fileName;
		PrintWriter diskFile = null;
		
		try {
			diskFile = new PrintWriter(new File(outputFileName + ".code"));
		}
		catch(IOException io){
			System.out.println("Could not create file: " + outputFileName);
		}
		
		postOrderRoutes(root, "");
		
		for(int a = 0; a < q.size(); a++)
		{
			System.out.println(q.peek());
			diskFile.println(q.poll());
			System.out.println(routes.peek());
			diskFile.println((routes.poll()));
		}
	}


	private void postOrderRoutes(TreeNode root, String path) {
		
		if(root.left == null && root.right == null)
		{
			routes.offer(path);
		}
		else
		{
			postOrderRoutes(root.left, path + 0);
			postOrderRoutes(root.right, path + 1);
		}
		
	}


	void encode(BitOutputStream out, String file)
	{
		
	}
}
