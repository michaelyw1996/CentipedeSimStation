package simStation;
import java.io.*;    
import java.util.Random;
import mvc.*;

public abstract class Agent implements Serializable, Runnable
{

	private Thread myThread;
	protected String name;
	protected Simulation world; //master
	private AgentState state; 
	protected Heading heading;
	protected int xc, yc;
	
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
	
	public void run()
	{
		//myThread = Thread.currentThread();
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
	
	public synchronized void start() 
	{
		onStart();
		state = AgentState.READY;
		if (myThread == null)
		{
			myThread = new Thread(this, name);
		}
		myThread.start();
		update();
	}
	
	public abstract void update();
	
	protected synchronized void onStart() {}
	protected synchronized void onExit() {}
	protected synchronized void onInterrupted() {}
	
	public synchronized void suspend() 
	{
		state = AgentState.SUSPENDED;
	}
	
	public synchronized void resume()
	{
		state = AgentState.READY;
		notify();
	}
	
	public synchronized void stop()
	{
		state = AgentState.STOPPED;
	}
	
	public synchronized boolean finished()
	{
		return state == AgentState.STOPPED;
	}
		
	public synchronized boolean isSuspended()
	{
		return state == AgentState.SUSPENDED;
	}
	
	public void join() throws InterruptedException
	{
		if (myThread != null)
		{
			myThread.join();
		}
	}
	
	public synchronized String getName()
	{
		return name;
	}
	
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

	public double distance(Agent candidate) 
	{
		return candidate.distance(candidate);
	}

}
