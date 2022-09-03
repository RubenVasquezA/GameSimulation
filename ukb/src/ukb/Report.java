package ukb;

//define the public class Report.
public class Report {
	int who_fired;
	int who_was_hit;
	int hp_lost;
	int hp_target_left;
	// Constructor
	public Report(int whoFired, int whoWasHit, int hpLost, int hpTargetLeft) {
		this.who_fired = whoFired;
		this.who_was_hit = whoWasHit;
		this.hp_lost = hpLost;
		this.hp_target_left = hpTargetLeft;
	}
	
	// set the who fired
    public void setWhoFired(int whoFired)
    {
    	who_fired = whoFired;
     
    }
 
    // set who was hit
    public void setWasHit(int whoWasHit)
    {
    	who_was_hit = whoWasHit;
    }
    
    // set hp lost
    public void setHpLost(int hpLost)
    {
    	hp_lost = hpLost;
    }
    
    // set target left lost
    public void setTargetLeft(int hpTargetLeft)
    {
    	hp_target_left = hpTargetLeft;
    }
    
    // get the who fired
    public int getWhoFired()
    {
    	return who_fired;
    } 
    // get who was hit
    public int getWasHit()
    {
    	return who_was_hit;
    }
    
    // get hp lost
    public int getHpLost()
    {
    	return hp_lost;
    }
    
    // get target left lost
    public int getTargetLeft()
    {
    	return hp_target_left;
    }

}
