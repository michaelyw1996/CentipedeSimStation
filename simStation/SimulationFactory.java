/*
 * Edit History:
 * Michael Wong,3/30: Initialized and completed.
 * Vyvy, 4/15: finalized
 */
package simStation;
import java.awt.Component;

import mvc.*;


public class SimulationFactory implements AppFactory
{

	@Override
	public Model makeModel()
	{
		return new Simulation();
	}

	@Override
	public String[] getEditCommands()
	{
		return new String[] {"Start", "Suspend", "Resume", "Stop", "Stats"};
	}

	@Override
	public Command makeEditCommand(Model model, String type)
	{
		if (type == "Start")
			return new StartCommand(model);
		if (type == "Suspend")
			return new SuspendCommand(model);
		if (type == "Resume")
			return new ResumeCommand(model);
		if (type == "Stop")
			return new StopCommand(model);
		if (type == "Stats")
			return new StatsCommand(model);

		return null;
	}

	@Override
	public String getTitle()
	{
		return "SimStation";

	}

	@Override
	public String[] getHelp()
	{
        return new String[] {"Click buttons to do something."};

	}

	@Override
	public String about()
	{
        return "SimStation Factory version 1.0 by Team Centipede";

	}

	public View getView(Model model)
	{
		return new SimulationView((Simulation) model);
	}

}
