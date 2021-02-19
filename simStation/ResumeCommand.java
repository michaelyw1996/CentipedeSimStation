/**
* Edit History:
* Vyvy Tran, 4/13: Initialized resume command
*/

package simStation;
import mvc.*;

public class ResumeCommand extends Command
{
	public ResumeCommand(Model model)
	{
		super(model);
	}

	public void execute()
	{
		Simulation sim = (Simulation) model;
		sim.resume();
	}
}
