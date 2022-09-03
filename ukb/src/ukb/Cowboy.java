package ukb;

// define the public class Cowboy.
public class Cowboy{
		
		int index;
		int health_points;
		
		// we declare the constructor.
		public Cowboy(int index, int health_points) {
			this.index = index;
			this.health_points = health_points;
		}
		// set the index
	    public void setIndex(int indexCowboy)
	    {
	    	index = indexCowboy;
	     
	    }
	    // set the health point
	    public void setHealthPoints(int healthPoints)
	    {
	    	health_points = healthPoints;
	    }
	    
	    // get the health point
	    public int getHealthPoints()
	    {
	    	return health_points;
	    }
	    
	    // get index
	    public int getIndex()
	    {
	    	return index;
	    }
	    
}

