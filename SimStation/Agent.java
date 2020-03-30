/*
 * Edit History:
 * Michael Wong,3/30: Initialized with base classes and variables. Changes to make this generic need to be made.
 * Edited the agent class to implement Serializable.
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

	public synchronized void stop() { state = AgentState.STOPPED; }
	public synchronized boolean isStopped() { return state == AgentState.STOPPED; }
	public synchronized void suspend() { state = AgentState.SUSPENDED; }
	public synchronized boolean isSuspended() { return state == AgentState.SUSPENDED;  }
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

	public void run() {
		thread = Thread.currentThread(); // catch my thread
		while(!isStopped()) {
			state = AgentState.RUNNING;
			update();
			try {
				Thread.sleep(100); // be cooperative
				synchronized(this) {
					while(isSuspended()) { wait(); }
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public abstract void update();
}

