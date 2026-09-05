import java.awt.Color;
import java.awt.Point;
import java.io.*;
import java.util.*;

public class StarChart {
	
	public ArrayList<Star> stars = new ArrayList<Star>();
	
	public StarChart(String fileName)
	{
		Scanner input = null;
		
        try {	
            input = new Scanner(new File(fileName));
        }
        catch (IOException e) {
            System.out.println("Can't find file!");
        }

        
        while(input.hasNextLine())
        {
        	String line = input.nextLine();
        	Scanner chop = new Scanner(line);
        	
        	stars.add(new Star(chop.nextDouble(), chop.nextDouble(), chop.nextDouble(), chop.nextInt(),
        			chop.nextDouble(), chop.nextInt(), ifnext(chop)));
        	
        }
	}
	
	private String ifnext(Scanner chop)
	{
		if(chop.hasNext())
		{
			chop.skip(" ");
			return chop.nextLine();
		}

			return null;
	}
	
	public Point coordsToPixel(double origX, double origY, int size)
	{
		
		
		Point point = new Point();
		double x,y;
		
		if(origX > 0)//good
		{
			x = (size/2) + ((size/2)*origX);
		}
		else
		{
			x = (size/2)-((size/2)*(origX*-1));
		}
		
		if(origY > 0)//good
		{
			y = (size/2) + ((size/2)*origY);
		}
		else
		{
			y = (size/2)-((size/2)*(origY*-1));
		}
		
		point.setLocation(x, y);
		return point;
	}
	
	void drawStars(int size)
	{
		StdDraw.clear(Color.black);
		StdDraw.setPenColor(Color.white);
		StdDraw.setPenRadius(2);
		
		
		for(int a = 0; a < stars.size(); a++)
		{
		
			Point p = coordsToPixel(stars.get(a).getCoordX(), stars.get(a).getCoordY(),
					size);
			StdDraw.filledCircle(p.x, p.y, (10.0 / (stars.get(a).getMagnitude() + 2)));
			
		}
	}
	
	void drawConstellation(String fileName, int size)
	{
		HashMap<String, Integer> nameToDrape = new HashMap<String, Integer>();
		ArrayList<String> nameKey = new ArrayList<String>();
		HashMap<Integer, Point> drapeToPoint = new HashMap<Integer, Point>();
		ArrayList<Integer> drapeKey = new ArrayList<Integer>();
		
		for(int a = 0; a < stars.size(); a++)
		{
			//sets nameToDrape
			String name = stars.get(a).getName();
			Integer drape = stars.get(a).getHDNumber();
			Point p = coordsToPixel(stars.get(a).getCoordX(), stars.get(a).getCoordY(), size);
			
			nameToDrape.put(name, drape);
			nameKey.add(name);
			
			//sets drapeToPoint
			drapeToPoint.put(drape, p);
			drapeKey.add(drape);
			
		}

		
		
		Scanner input = null;
		
        try {	
            input = new Scanner(new File(fileName));
        }
        catch (IOException e) {
            System.out.println("Can't find file!");
        }
		
        while(input.hasNextLine())
        {
        	String line = input.nextLine();
        	Scanner chop = new Scanner(line);
        	chop.useDelimiter(",");

        	
        	
        	String key1 = chop.next();
        	chop.skip(",");
        	String key2 = chop.nextLine();
        	


        	

        	System.out.println("Stars sets");
//        	
        	Point p1 = drapeToPoint.get(nameToDrape.get(nameKey.get(search(nameKey, key1))));
        	System.out.println(p1.x + ", " + p1.y);
        	Point p2 = drapeToPoint.get(nameToDrape.get(nameKey.get(search(nameKey, key2))));   	
        	System.out.println(p2.x + ", " + p2.y);
        	StdDraw.setPenColor(Color.yellow);
        	StdDraw.setPenRadius(.005);
        	StdDraw.line(p1.x, p1.y, p2.x, p2.y);    	
        }
	}
	private int search(ArrayList<String> list, String sim)
	{
		for(int a = 0; a < list.size(); a++)
		{
			if(list.get(a) != null && (list.get(a).contains(sim+";") || list.get(a).contains(sim)))
			{

				return a;
			}
		}	
		return 0;
	}
}
