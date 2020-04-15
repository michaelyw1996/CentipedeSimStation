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

   	//public void paintComponent(Graphics gc)
    	//{
	   	// Simulation simulation = (Simulation)model;
			//
	    // 	for (Agent a: ((Simulation)model).getAgents())
	    // 	{
	    //  	   gc.setColor(Color.RED);
	    //  	   gc.fillOval(a.getXc(), a.getYc(), 5, 5);
	    // 	}

			public void paintComponent(Graphics gc)
			{
				Color oldColor = gc.getColor();
				gc.setColor(Color.RED);
				Simulation sim = (Simulation)model;
				Iterator<Agent> it = sim.iterator();
				while(it.hasNext()) {
					Agent a= it.next();
					gc.fillOval(a.getXc(), a.getYc(), 5, 5);
				}
				gc.setColor(oldColor);
   	 	}
}
