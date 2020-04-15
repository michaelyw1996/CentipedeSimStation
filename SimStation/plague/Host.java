package plague;

import mvc.*;

public class Host extends Agent{
    boolean infected;
    void infect(){
      int luck = Utilities.rng(Plague.VIRULENCE);
      if(Plague.RESISTANCE < luck) {infected = true;}
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
