/*
 * Edit History:
 * Michael Wong,3/30: Initialized with base classes and variables.
 * Vyvy Tran, 4/5: Added the missing function bodies (stop, resume, start, etc.)
 * Michael Wong, 4/6: Edited a few of the functions that were missing some parts. (resume)
 */


package SimStation;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import mvc.*;


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

	public List<Agent> getAgents()
	{
		return agents;
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
		for(Agent a: agents) {
			a.resume();
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
	public synchronized Agent getNeighbor(Agent asker, double radius)
	{
		Agent neighbor = null;
		boolean found = false;
		int i = Utilities.rng.nextInt(agents.size());
		int start = i;

		while (!found)
		{
			Agent candidate = agents.get(i);
			double myDistance = Math.pow((candidate.getXc() - asker.getXc()),2) + Math.pow((asker.getYc() - candidate.getYc()),2);
			double tester = Math.sqrt(myDistance);

			if (candidate != asker && tester< radius)
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

	public String[] getStats() {
		String[] stats = new String[2];
		stats[0] = "#agents = " + agents.size();
		stats[1] = "clock = " + clock;
		return stats;
	}
}
