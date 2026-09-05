public class Lock {

	    private boolean locked;  
	    
	    //constructor
	    public Lock()
	    {
	    	
	    }
	
	    void open()
	    {
	    	locked = false;
	    }
	    
	    void close()
	    {
	    	locked = true;
	    }
	    
	    boolean isLocked()
	    {
	    	return locked;
	    }
	    
	    public String toString()
	    {
	    	if(locked)
	    	{
	    		return "Lock is closed";
	    	}
	    	return "Lock is open";
	    	
	    }
}
