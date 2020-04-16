package simStation;
import mvc.*;

public class StatsCommand extends Command
{
	public StatsCommand(Model model)
	{
		super(model);
	}
	
	public void execute()
	{
		Simulation sim = (Simulation) model;
		Utilities.inform(sim.getStats());
	}
}
