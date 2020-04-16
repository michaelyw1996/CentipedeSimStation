/*
* Michael Wong, 4/15: Created the file and completed the populate function.
*/

package plague;

import Simulation.*;
import mvc.*;

public class PlagueSimulation extends Simulation{
	public static int VIRULENCE = 2;
	public static double RESISTANCE = 80;
	public void populate(){
		    for(int i = 0; i < 5; i++){
		      this.addAgent(new Host(true));
		    }
		    for(int i = 0; i < 45; i++){
			   this.addAgent(new Host(false));
			}
	  }

	public static void main(String[] args) {
		AppPanel panel = new SimulationPanel(new PlagueFactory());
		panel.display();
	}

	@Override
	public String[] getStats() {
		int infectedScore = 0;
		int numInfected = this.getAgents().size();
		double infectedAverage = 0.0;

		for (int i = 0; i < this.getAgents().size(); i++) {
			if (((Host)getAgents().get(i)).getInfected()) {
				infectedScore++;
			}
		}

		infectedAverage = (infectedScore/numInfected) * 100;

		String[] stats = super.getStats();
		String[] newStats = new String[2];
		newStats[0] = stats[0];
		newStats[1] = stats[1];
		newStats[2] = "% infected = " + infectedAverage;
		return newStats;
	}
}
