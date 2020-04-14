/**
* Edit History:
* Vyvy Tran, 4/13: Initialized suspend command
*/

package SimStation;
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
