import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Path
{
	private Point[] points;
	private double  minX, minY; //min X and Y values, for setting canvas scale
	private double  maxX, maxY; //maxes
	private int numPoints;

	/** construct a path from a given file */
	public Path(String fileName)
	{
		//TODO
		Scanner input = null;
		
        try {	
            input = new Scanner(new File(fileName));
        }
        catch (IOException e) {
            System.out.println("Can't find file!");
        }

        numPoints = input.nextInt();
        points = new Point[numPoints];
        
        
        int a = 0;
        while(input.hasNextDouble())
        {
        	points[a] = new Point(input.nextDouble(), input.nextDouble());
        	a++;
        }
        
        for(int b = 0; b < points.length; b++)
        {
        	if(points[b].getX() < minX)
        	{
        		minX = points[b].getX();
        	}
        	if(points[b].getY() < minY)
        	{
        		minY = points[b].getY();
        	}
        	if(points[b].getX() > maxX)
        	{
        		maxX = points[b].getX();
        	}
        	if(points[b].getY() > maxY)
        	{
        		maxY = points[b].getY();
        	}
        }
	}
	
	public Point getPoint(int i)
	{
		return points[i];
	}
	
	public Double getMinX()
	{
		return minX;
	}
	
	public Double getMinY()
	{
		return minY;
	}
	
	public Double getMaxX()
	{
		return maxX;
	}
	
	public Double getMaxY()
	{
		return maxY;
	}

	/** returns the distance traveled going point to point, in order given in file */
	public double getDistance()
	{
		Double dist = 0.0;
		for(int a = 0; a < getNumPoints()-1; a++)
		{
			dist+=points[a].getDistance(points[a+1]);
		}
		return dist;
	}
	
	public int getNumPoints()
	{
		return numPoints;
	}

	@Override
	public String toString()
	{
		//TODO
		String out = "";
		
		for(int a = 0; a < numPoints-1; a++)
		{
			out = points[a].toString();
		}

		return out;
	}
}
