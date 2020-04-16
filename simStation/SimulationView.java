package simStation;
import java.awt.*;
import java.util.Iterator;
import javax.swing.*;
import mvc.*;

public class SimulationView extends View
{

	public SimulationView(Model model) 
	{
		super(model);	
	}
	 
	public void paintComponent(Graphics gc) 
	{ 
		 Color oldColor = gc.getColor();
		 gc.setColor(Color.RED);
	     Simulation simulation = (Simulation)model;    	        
	     Iterator<Agent> it = simulation.iterator();
	    
	     while(it.hasNext()) 
	     {
	    	 Agent a = it.next();
	    	 gc.fillOval(a.getXc(), a.getYc(), 5, 5);
	     }
	    
	     gc.setColor(oldColor);
	 }    
  
}