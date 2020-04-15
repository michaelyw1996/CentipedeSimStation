/*
 * Edit History:
 * Michael Wong,3/30: Initialized.
 */

package SimStation;

import java.awt.Graphics;
import java.util.Iterator;

import mvc.*;

public class SimulationView extends View{

	public SimulationView(Model model) {
		super(model);
	}
	
   	public void paintComponent(Graphics gc)
    	{
	   	Simulation simulation = (Simulation)model;    	        
	     
	    	for (Agent a: ((Simulation)model).getAgents())
	    	{
	     	   gc.setColor(Color.RED);
	     	   gc.fillOval(a.getXc(), a.getYc(), 5, 5);
	    	}
   	 }
}
