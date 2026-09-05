
public class FireModel
{
    public static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;

    public FireModel(FireView view)
    {
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int r=0; r<SIZE; r++)
        {
            for (int c=0; c<SIZE; c++)
            {
                myGrid[r][c] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
    }

    /*
        recursiveFire method here
     */
    public boolean recursiveFire(int x, int y)
    {
    	if(x < 0 || )
    	
    	FireCell cell = myGrid[x][y];
    	
    	if(cell.getStatus() == cell.DIRT)
    	{
    		return false;
    	}
    	else if(cell.getStatus() == cell.GREEN)
    	{
    		cell.setStatus(3);
    		recursiveFire(x+1, y);
    		recursiveFire(x-1, y);
    		recursiveFire(x  , y+1);
    		recursiveFire(x  , y-1);
    	}
    	
    }

    public void solve()
    {
        // student code here
        myView.updateView(myGrid);
    }

}