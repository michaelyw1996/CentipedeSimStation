/**
* Edit History:
* Vyvy Tran, 4/13: Initialized command
*/

package simStation;
import mvc.*;

public class SuspendCommand extends Command
{
	public SuspendCommand(Model model)
	{
		super(model);
	}

	public void execute()
	{
		Simulation sim = (Simulation) model;
		sim.suspend();
	}
}
