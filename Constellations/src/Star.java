
public class Star {

	private double coordX;
	private double coordY;
	private double coordZ;
	private int HDNumber;
	private double magnitude;
	private int HRNumber;
	private String name;
	
	
	public Star(double x, double y, double z, int HD ,double mag, int HR, String nam)
	{
		coordX = x;
		coordY = y;
		coordZ = z;
		magnitude = mag;
		HDNumber = HD;
		HRNumber = HR;
		name = nam;
	}
	
	public double getCoordX()
	{
		return coordX;	
	}
	
	public double getCoordY()
	{
		return coordY;	
	}
	
	public double getCoordZ()
	{
		return coordZ;	
	}
	
	public double getMagnitude()
	{
		return magnitude;
	}
	
	public int getHDNumber()
	{
		return HDNumber;
	}
	
	public int getHRNumber()
	{
		return HRNumber;
	}
	
	public String getName()
	{
		return name;
	}
}
