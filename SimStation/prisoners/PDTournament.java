class PDFactory extends SimulationFactory{
  public Model makeModel() { return new PDSimulation();}
}

abstract class CooperateStrategy{
  protected Prisoner owner;

  public CooperateStrategy(Prisoner myPrisoner){
    super();
    this.owner = myPrisoner;
  }
  public CooperateStrategy(){this(null);}

  public Prisoner getOwner(){return owner;}
  public void setOwner(Prisoner owner){this.owner = owner;}

  public abstract boolean cooperate();
}

class AlwaysCheat extends CooperateStrategy{
  public boolean cooperate(){ return false;}
}

class Reciprocate extends CooperateStrategy{
  public boolean cooperate(){return owner.getLastResponse;}
}

class RandomlyCheat extends CooperateStrategy{
  public boolean cooperate(){
    return(mvc.Utilities.rng.nextInt(2) == 0);
  }

class Prisoner extends Agent{
  CooperateStrategy strategy;
  boolean lastResponse;
  int fitness = 0;

  public Prisoner(CooperateStrategy strategy){
    super();
    this.strategy = strategy;
    strategy.setOwner(this);
    lastResponse = true;

  public synchronized boolean getLastResponse(){
     return lastResponse;
  }
  
  }
  }
}
