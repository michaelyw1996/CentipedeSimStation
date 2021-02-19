/*
	* Vyvy/Steven, 4/8: Completeted
*/

package sims;
import java.awt.Graphics;
import mvc.*;
import simStation.*;

class RandomWalkFactory extends SimulationFactory
{
	public Model makeModel()
	{
		return new RandomWalkSimulation();
	}
}

public class RandomWalkSimulation extends Simulation
{
	public void populate()
	{
		for (int i = 0; i < 50; i++)
		{
			addAgent(new Drunk());
		}
	}

	public static void main(String[] args)
	{
		AppPanel panel = new SimulationPanel(new RandomWalkFactory());
		panel.display();
	}
}
