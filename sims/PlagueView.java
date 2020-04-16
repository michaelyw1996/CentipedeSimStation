package sims;
import java.awt.*;
import java.util.List;
import mvc.AppPanel;
import mvc.Model;
import simStation.*;

public class PlagueView extends SimulationView
{
	public PlagueView(Model model) 
	{
		super(model);	
	}
	
	@Override
	public void paintComponent(Graphics gc)
	{
		Color oldColor = gc.getColor();
		PlagueSimulation plague = (PlagueSimulation) model;
		
		for (Agent a: plague.getAgents())
		{
			Host h = (Host) a;
			if (h.getInfected())
			{
				gc.setColor(Color.RED);
			}
			else
			{
				gc.setColor(Color.GREEN);
			}
			
			gc.fillOval(a.getXc(), a.getYc(), 5, 5);
		}
		gc.setColor(oldColor);
		
	  }
	  
}

