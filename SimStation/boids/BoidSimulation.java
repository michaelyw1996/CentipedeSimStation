class BoidFactory extends SimulationFactory{
  public Model makeModel(){ return new BoidSimulation();}
}

public class BoidSimulation extends Simulation{
  public void populate(){
    for(int i = 0; i < 50; i++){
      addAgent(new Boid());
    }
  }
  public static void main(String[] args){
    AppPanel panel = new SimulationPanel(new BoidFactory());
    panel.display();
  }
}