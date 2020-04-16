package plague;
import java.awt.*;
import mvc.Model;
import Simulation.*;

public class PlagueView extends SimulationView
{
	public PlagueView(Model model)
	{
		super(model);
	}

	public void paintComponent(Graphics gc)
	{
		Color oldColor = gc.getColor();
		PlagueSimulation plague = (PlagueSimulation) model;

		for (Agent a: ((PlagueSimulation)model).getAgents())
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
	}
}
