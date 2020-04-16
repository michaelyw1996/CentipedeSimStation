/**
* Edit History:
* Vyvy Tran, 4/13: Initialized stop command
*/

package Simulation;
import mvc.*;

public class StopCommand extends Command
{
	public StopCommand(Model model)
	{
		super(model);
	}

	public void execute()
	{
		Simulation sim = (Simulation) model;
		sim.stop();
	}
}
