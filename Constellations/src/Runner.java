public class Runner {

	final static int SIZE = 700;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		StdDraw.setCanvasSize(SIZE, SIZE);
		StdDraw.setXscale(0, SIZE); 
		StdDraw.setYscale(0, SIZE);
		StarChart starChart = new StarChart("src/stars.txt");
		starChart.drawStars(SIZE);
		starChart.drawConstellation("src/BigDipper.txt",SIZE);

	}
	
}
