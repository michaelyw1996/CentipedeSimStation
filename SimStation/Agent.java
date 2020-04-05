/*
 * Edit History:
 * Michael Wong,3/30: Initialized with base classes and variables. Changes to make this generic need to be made.
 * Edited the agent class to implement Serializable.
 * Vyvy Tran 4/5: Added a few functions  
 *
 */

package SimStation;

import java.io.Serializable;

enum Heading{
	NORTH,SOUTH,EAST,WEST;
}

enum AgentState{
	READY, RUNNING, SUSPENDED, STOPPED;
}

abstract class Agent implements Runnable,Serializable {

	protected Simulation world;
	private String name;
	private AgentState state;
	private Thread thread;
	Heading heading;
	int xc = 0;
	int yc = 0;

	public Agent(String name,Integer xc, Integer yc) {
		super();
		this.name = name;
		state = AgentState.READY;
	     this.xc = xc;
	     this.yc = yc;
	}
	
	public Agent(String name)
	{
		this.name = name;
		state = AgentState.READY;
		myThread = null;
		xc = Utilities.rng.nextInt(Simulation.SIZE);
		yc = Utilities.rng.nextInt(Simulation.SIZE);
		heading = Heading.random();
	}
	
	public Agent()
	{
		this("Agent_" + mvc.Utilities.getID());
	}

	public synchronized void stop() { state = AgentState.STOPPED; }
	public synchronized boolean isStopped() { return state == AgentState.STOPPED; }
	public synchronized void suspend() { state = AgentState.SUSPENDED; }
	public synchronized boolean isSuspended() { return state == AgentState.SUSPENDED;  }
	public synchronized boolean finished()
	{
		return (state == AgentState.STOPPED); //missing one more statement
	}
	public synchronized void resume() {
		if (!isStopped()) {
			notify();
		}
	}
	public String getName() { return name; }
	public synchronized AgentState getState() { return state; }
	public synchronized void join() throws InterruptedException {
		if (thread != null) thread.join();
	}
	public synchronized String toString() { return name + ".state = " + state; }

	public void run()
	{
		try {
			while(!finished())
			{
				state = AgentState.RUNNING;
				update();
				Thread.sleep(20);
				synchronized(this)
				{
					while(state == AgentState.SUSPENDED)
					{
						wait();
					}
				}
			}
		} catch (InterruptedException e) {
			onInterrupted();
		}
		
		onExit();
	}

	public abstract void update();
	protected synchronized void onStart() {}
	protected synchronized void onExit() {}
	protected synchronized void onInterrupted() {}
	
	public Simulation getWorld()
	{
		return world;
	}
	
	public void setWorld(Simulation world)
	{
		this.world = world;
	}
	
	public Heading getHeading()
	{
		return heading;
	}
	
	public void setHeading(Heading heading)
	{
		this.heading = heading;
	}
	
	public int getXc()
	{
		return xc;
	}
	
	public int getYc()
	{
		return yc;
	}
	
	public void move(int steps)
	{
		switch(heading) {
		case NORTH:
			yc = (yc - steps);
			if (yc < 0)
			{
				yc = Simulation.SIZE + yc;
 			}
			break;
		case SOUTH:
			yc = (yc + steps) % Simulation.SIZE;
			break;
		case EAST:
			xc = (xc + steps) % Simulation.SIZE;
		case WEST:
			xc = (xc - steps);
			if (xc < 0)
			{
				xc = Simulation.SIZE + xc;
				break;
			}
		}
		world.changed();
	}

}

