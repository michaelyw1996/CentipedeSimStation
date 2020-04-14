/*
* Wencong Liu, 4/14: 
* Vyvy Tran, 4/14: Added a few missing functions
*/

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

class AlwaysCooperate extends CooperateStrategy{
	public boolean cooperate(){ return false; }
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
    
  public synchronized void setLastResponse(boolean lastResponse){
		this.lastResponse = lastResponse;
	}
	
	public CooperateStrategy getStrategy(){
		return strategy;
	}
	
	public synchronized boolean wasCheated(){
		return lastResponse;
	}
    
   public synchronized void setWasCheated(boolean wasCheated){
		this.lastResponse = wasCheated;
	}
	
	public void setStrategy(CooperateStrategy strategy){
		this.strategy = strategy;
		strategy.setOwner(this);
	}
	
	public int getFitness(){
		return fitness;
	}
	
	public synchronized boolean cooperate(){
		return strategy.cooperate();
	}
    
   public synchronized void updateFitness(boolean myChoice, boolean nbrChoice){
	  if (myChoice){
			if (nbrChoice){ fitness += 3; }
		}
		else {
			if(nbrChoice) { fitness += 5; }
			else { fitness += 1; }
		}
	 }
    
    public void update() {
		 Prisoner neighbor = (Prisoner)world.getNeighbor(asker, radius);
		
		 if (neighbor != null) {
			 boolean myChoice = cooperate();
			 lastResponse = neighbor.cooperate();
			 updateFitness(myChoice, lastResponse);
			 neighbor.updateFitness(lastResponse, myChoice);
		 }		
		 setHeading(Heading.random());
		 move(10);
	  }
  
  }
  }
}
