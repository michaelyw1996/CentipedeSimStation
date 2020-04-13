/**
* Edit History:
* Vyvy Tran, 4/13: Initialized start command
*/

package simStation;
import mvc.*;

public class StartCommand extends Command
{
	public StartCommand(Model model)
	{
		super(model);
	}
	
	public void execute()
	{
		Simulation sim = (Simulation) model;
		sim.start();
	}
}
