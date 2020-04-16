package plague;

import SimStation.*;

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
}
