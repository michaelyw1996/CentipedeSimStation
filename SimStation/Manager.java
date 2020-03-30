/*
 * Edit History:
 * Michael Wong,3/30: Initialized with professor code. 
 */

package SimStation;

public class Manager extends Console {
	private Agent[] agents;

	public Manager() {
		agents = new Agent[2];
		// populate:
		//agents[0] = new Boids();
		//agents[2] = new Agent("Agent 3");
	}

	protected String execute(String cmmd) throws Exception {
		if (cmmd.equalsIgnoreCase("suspend")) {
			for(int i = 0; i < agents.length; i++) {
				agents[i].suspend();
			}
		} else if (cmmd.equalsIgnoreCase("resume")) {
			for(int i = 0; i < agents.length; i++) {
				agents[i].resume();
			}
		} else if (cmmd.equalsIgnoreCase("start")) {
			for(int i = 0; i < agents.length; i++) {
				Thread thread = new Thread(agents[i]);
				thread.start();
			}
		} else if (cmmd.equalsIgnoreCase("stop")) {
			for(int i = 0; i < agents.length; i++) {
				agents[i].stop();
			}
		} else if (cmmd.equalsIgnoreCase("status")) {
			for(int i = 0; i < agents.length; i++) {
				System.out.println(agents[i]);
			}
		} else {
			throw new Exception("unrecognized command: " + cmmd);
		}
		return "ok";
	}


	public static void main(String[] args) {
		Manager manager = new Manager();
		manager.controlLoop();
	}
}





