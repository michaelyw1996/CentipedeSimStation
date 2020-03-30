/*
 * Edit History:
 * Michael Wong,3/30: Initialized with base classes and variables.
 */


package SimStation;
import java.util.Timer;
import java.util.TimerTask;

import mvc.*;

public class Simulation extends Model{	
	private Timer timer;
	private int clock;

	private void startTimer() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
	}

	private void stopTimer() {
		timer.cancel();
		timer.purge();
	}

	private class ClockUpdater extends TimerTask {
		public void run() {
			clock++;
			//changed();
		}
	}
	public void start() {

	}
	public void suspend() {

	}
	public void resume() {

	}
	public void stop() {

	}
	public void getNeighbor() {

	}
	public void populate() {

	}

}
