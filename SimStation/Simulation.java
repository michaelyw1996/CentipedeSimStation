/*
 * Edit History:
 * Michael Wong,3/30: Initialized with base classes and variables.
 * Vyvy Tran, 4/5: Added the missing function bodies (stop, resume, start, etc.)
 */


package SimStation;
import java.util.Timer;
import java.util.TimerTask;

import mvc.*;

public class Simulation extends Model{	
	public static int SIZE = 250;
	protected List<Agent> agents;
	private Timer timer;
	private int clock;

	public Simulation()
	{
		agents = new LinkedList<Agent>();
		clock = 0;
	}
	
	public void addAgent(Agent a)
	{
		agents.add(a);
		a.setWorld(this);
	}
	
	private class ClockUpdater extends TimerTask {
		public void run() {
			clock++;
			//changed();
		}
	}
	public void start() 
	{
		agents = new LinkedList<Agent>();
		clock = 0;
		populate();
		timer = new Timer();
		timer.schedule(new ClockUpdater(), 1000);
		
		for (Agent a: agents)
		{
			a.start();
		}
	}
	
	public synchronized void suspend()
	{
		for (Agent a: agents)
		{
			a.suspend();
		}
		
		timer.cancel();
		timer.purge();
	}
	
	public synchronized void resume()
	{
		timer = new Timer();
		timer.schedule(new ClockUpdater(), 1000);
	}
	
	public synchronized void stop()
	{
		for (Agent a: agents)
		{
			a.stop();
		}
		
		timer.cancel();
		timer.purge();
	}
	
	public synchronized int getClock()
	{
		return clock;
	}
	
	public synchronized Iterator<Agent> iterator()
	{
		return agents.iterator();
	}
	
	private synchronized void incClock()
	{
		clock++;
		changed();
	}
	
	public synchronized Agent getNeighbor(Agent asker)
	{
		Agent neighbor = null;
		boolean found = false;
		int i = Utilities.rng.nextInt(agents.size());
		int start = i;
		
		while (!found)
		{
			Agent candidate = agents.get(i);
			
			if (candidate != asker && asker.distance())
			{
				neighbor = agents.get(i);
				found = true;
			}
			else
			{
				i = (i + 1) % agents.size();
				if (i == start)
				{
					break;
				}
			}
		}		
		return neighbor;
	}
	
	public void populate() {}

}
