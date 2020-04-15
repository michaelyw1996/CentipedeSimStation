package boids;
import mvc.*;
import Simulation.*;

public class BoidSimulation extends Simulation{
  public void populate(){
    for(int i = 0; i < 50; i++){
      this.addAgent(new Boid());
    }
  }
  public static void main(String[] args){
    AppPanel panel = new SimulationPanel(new BoidFactory());
    panel.display();
  }
}
