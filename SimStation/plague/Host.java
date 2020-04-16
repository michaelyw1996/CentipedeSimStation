package plague;

import mvc.*;
import Simulation.*;



public class Host extends Agent{
	boolean infected;
	private int speed;


	public Host(boolean infectedOrNot) {
		super();
		infected = infectedOrNot;
	}
	void infect(){
		int luck = Utilities.rng.nextInt(PlagueSimulation.VIRULENCE);
		if(PlagueSimulation.RESISTANCE < luck) {infected = true;}
	}

	boolean getInfected(){
		return infected;
	}

	public void update(){
		if(infected){
			Host neighbor = (Host)world.getNeighbor(this, 20.0);
			if (neighbor != null && !neighbor.getInfected()){
				neighbor.infect();
			}
		}
		setHeading(Heading.getRandomHeading());
	  speed = Utilities.rng.nextInt(5)+1;
    move(speed);
	}

}
