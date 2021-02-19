/*
 * Edit History:
 * Michael Wong,3/30: Initialized with base classes and variables.
 * Vyvy Tran, 4/5: Added the missing function bodies (stop, resume, start, etc.)
 * Michael Wong, 4/6: Edited a few of the functions that were missing some parts. (resume)
 */
package simStation;
import mvc.*;
import java.util.*;

public class Simulation extends Model
{
	public static int SIZE = 250;
	protected List<Agent> agents;
	private Timer timer;
	protected int clock;

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

	public List<Agent> getAgents()
	{
		return agents;
	}

	public synchronized Agent getNeighbor(Agent asker, double radius)
	{
		Agent neighbor = null;
		boolean found = false;
		int i = Utilities.rng.nextInt(agents.size());
		int start = i;

		while (!found)
		{
			Agent candidate = agents.get(i);

			if (candidate != asker) //&& asker.distance(candidate) < radius)
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

	public synchronized void start()
	{
		agents = new LinkedList<Agent>();
		clock = 0;
		populate();
		timer = new Timer();
		timer.schedule(new ClockUpdater(), 1000, 1000);

		for (Agent a: agents)
		{
			a.start();
		}
	}

	public void populate(){	}

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
		timer.schedule(new ClockUpdater(), 1000, 1000);
		for (Agent a: agents)
		{
			a.resume();
		}
	}

	private class ClockUpdater extends TimerTask
	{
		public void run()
		{
			clock++;
			changed();
		}
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

	public String[] getStats()
	{
		String[] stats = new String[2];
		stats[0] = "#agents = " + agents.size();
		stats[1] = "clock = " + clock;
		return stats;
	}

	public void update()
	{
		for (Agent a: agents)
		{
			a.update();
		}
	}
}
