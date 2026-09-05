public class Point 
{
	private double  x, y;
	private boolean visited;
	
	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public boolean visited()
	{
		return visited;
	}
	
	void setVisited(boolean bool)
	{
		visited = bool;
	}
	
	/** get the Euclidean distance between two points */
	public double getDistance(Point other)
	{
		return Math.sqrt(((x-other.x)*(x-other.x)) + ((y-other.y)*(y-other.y)));
	}
	
	@Override
	public String toString()
	{
		return "Point:("+x+","+y+")";
	}
}
