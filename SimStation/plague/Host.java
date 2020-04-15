package plague;

import mvc.*;

public class Host extends Agent{
    boolean infected;
    private int speed;

	public Host() {
		super();
		infected = false;
		speed = 5;
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
      move(speed);
    }

}
