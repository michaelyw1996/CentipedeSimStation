/*
* Wencong Liu, 4/14:
* Vyvy Tran, 4/14: Added a few missing functions
*/
package prisoners;
import mvc.*;

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

public class PDTournament extends Simulation{
  public static int numAgents = 10;

  public void populate(){
    super.populate();
    for(int i =0; i < numAgents; i++){
      this.addAgent(new Prisoner(new AlwaysCheat()));
    }
    for(int i =0; i < numAgents; i++){
      this.addAgent(new Prisoner(new AlwaysCooperate()));
    }
    for(int i =0; i < numAgents; i++){
      this.addAgent(new Prisoner(new RandomlyCheat()));
    }
    for(int i =0; i < numAgents; i++){
      this.addAgent(new Prisoner(new Reciprocate()));
    }
  }
  public String[] getStats(){
    int cheaterScore = 0;
    int numCheaters = 0;
    int cooperatorScore = 0;
    int numCooperators = 0;
    int randomScore = 0;
    int numRandoms = 0;
    int reciprocatorScore = 0;
    int numReciprocators = 0;

    double cheaterAvg = 0.0;
    double cooperatorAvg = 0.0;
    double randomAvg = 0.0;
    double reciprocatorAvg = 0.0;

    for(Agent a: agents){
      Prisoner p = (Prisoner)a;
      if(p.getStrategy() instanceof AlwaysCheat){
        cheaterScore += p.getFitness();
        numCheaters++;
      } else if(p.getStrategy() instanceof AlwaysCooperate){
        cooperatorScore += p.getFitness();
        numCooperators++;
      } else if(p.getStrategy() instanceof Reciprocate){
        reciprocatorScore += p.getFitness();
        numReciprocators++;
      } else if(p.getStrategy() instanceof RandomlyCheat){
        randomScore += p.getFitness();
        numRandoms++;
      }

      if(numCheaters > 0) cheaterAvg = ((double) cheaterScore/numCheaters);
      if(numCooperators > 0) cooperatorAvg = ((double)cooperatorScore/numCooperators);
      if(numReciprocators > 0) reciprocatorAvg = ((double)reciprocatorScore/numReciprocators);
      if(numRandoms > 0) randomAvg = ((double)randomScore/numRandoms);

      String[] stats1 = super.getStats();
      String[] stats = new String[6];
      stats[0] = stats1[0];
      stats[1] = stats1[1];
      stats[2] = "Cheater's average = " + cheaterAvg;
      stats[3] = "Cooperater's average = " + cooperatorAvg;
      stats[4] = "Reciprocator's average = " + reciprocatorAvg;
      stats[5] = "Random's average = " + randomAvg;
      return stats;
    }

    public static void main(String[] args){
      AppPanel panel = new SimulationPanel(new PDFactory());
      panel.display();
    }
  }
}
}
