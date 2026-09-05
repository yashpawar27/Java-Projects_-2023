
public class GreedyPath extends Path{
	
	private Point[] gPoints;
	
	public GreedyPath(String fileName)
	{
		super(fileName);
		
		gPoints = new Point[super.getNumPoints()];
		
		Point currentPoint = super.getPoint(0);
		Point nextPoint = super.getPoint(1);
		Double minDist;
		gPoints[0] = currentPoint;
		
		//goes through all gPoints
		for(int a = 1; a < gPoints.length; a++)
		{
			//looks for closest point
			for(int b = 0; b < gPoints.length; b++)
			{
				minDist = currentPoint.getDistance(nextPoint);
				if(currentPoint != nextPoint && currentPoint.getDistance(super.getPoint(b)) < minDist 
						&& hasNoCopy(super.getPoint(b)))
				{
					nextPoint = super.getPoint(b);
				}
			}
			gPoints[a] = nextPoint;
			currentPoint = nextPoint;
			
			for(int c = 0; c < gPoints.length; c++)
			{
				if(hasNoCopy(super.getPoint(c)))
				{
					nextPoint = super.getPoint(c);
					break;
				}
			}
		}
		
	}
	
	private boolean hasNoCopy(Point p)
	{
		for(int a = 0; a < gPoints.length; a++)
		{
			if(gPoints[a] != null && gPoints[a].equals(p))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public double getDistance()
	{
		Double dist = 0.0;
		for(int a = 0; a < gPoints.length-1; a++)
		{
			dist+=gPoints[a].getDistance(gPoints[a+1]);
		}
		return dist;
	}
	
	public Point getPoint(int i)
	{
		return gPoints[i];
	}
	
	public String toString()
	{
		return "";
	}

}
