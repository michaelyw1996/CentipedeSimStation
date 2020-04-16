package plague;
import java.awt.*;
import Simulation.*;
import mvc.*;

public class PlagueView extends SimulationView{

	public PlagueView(Model model) {
		super(model);
	}

	public void paintComponent(Graphics gc) {
		PlagueSimulation sim = (PlagueSimulation)model;
		for(Agent a:((PlagueSimulation)model).getAgents())
		{
			if(a.infected) {
				gc.setColor(Color.RED);
				gc.fillOval(a.getXc(), a.getYc(), 5, 5);
			}
			else{
				gc.setColor(Color.GREEN);
				gc.fillOval(a.getXc(), a.getYc(), 5, 5);
			}
		}
	}

}
