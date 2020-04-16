package sims;
import mvc.*; 
import simStation.*;

class PlagueFactory extends SimulationFactory
{
	public Model makeModel()
	{
		return new PlagueSimulation();
	}
	
	public View getView(Model model)
	{
		return new PlagueView((PlagueSimulation) model);
	}

} 
 

public class PlagueSimulation extends Simulation 
{
	public static int VIRULENCE = 50;
	public static int RESISTANCE = 2;

	public void populate()
	{
		for (int i = 0; i < 5; i++)
		{
			this.addAgent(new Host(true));
		}
		
		for (int i = 0; i < 45; i++)
		{
			this.addAgent(new Host(false));
		}
	}


	public static void main(String[] args)
	{
		AppPanel panel = new SimulationPanel(new PlagueFactory());
		panel.display();
	}
	
	@Override
	public String[] getStats()
	{		
		int infectedScore = 0;
		int numInfected = this.getAgents().size();
		double infectedAverage = 0.0;
		
		for (int i = 0; i < this.getAgents().size(); i++)
		{
			if (((Host)getAgents().get(i)).getInfected())
			{
				infectedScore++;
			}
		}
		
		infectedAverage = (infectedScore/numInfected) * 100;
		
		String[] stats = new String[3];
		stats[0] = "#agents = " + agents.size();
		stats[1] = "clock = " + clock;
		stats[2] = "% infected = " + infectedAverage;
		return stats;
		
	}
}


