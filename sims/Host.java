package sims;
import java.util.Random;

import mvc.*;
import simStation.*;

public class Host extends Agent
{
    boolean infected;
    private int speed;
    
    public Host(boolean infectedOrNot)
    {
    	super(); 
    	infected = infectedOrNot;
    	speed = Utilities.rng.nextInt(5) + 1;
    }
    
    public void infect()
    {
        int luck = Utilities.rng.nextInt(PlagueSimulation.VIRULENCE);  
        
        if (PlagueSimulation.RESISTANCE < luck) 
        { 
    	    infected = true;
        }
    }
    
    public boolean getInfected()
    {
    	return infected;
    }
    
    public void update()
    {
      if (infected)   	  
      {   	  
          Host neighbor = (Host)world.getNeighbor(this, 20.0);
          
          if (neighbor != null && !neighbor.getInfected())
          {
        	  neighbor.infect();
          }
      }
      
      move(speed);
    }
}