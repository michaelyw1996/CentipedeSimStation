/**
* Edit History:
* Vyvy Tran, 4/13: Initialized stats command
*/

package Simulation;
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
