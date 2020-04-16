package sims;
import mvc.*;  
import simStation.*;
 
class PDFactory extends SimulationFactory
{
	public Model makeModel()
	{
		return new PDTournament();
	}
}

abstract class CooperateStrategy
{
	protected Prisoner owner;
	
	public CooperateStrategy(Prisoner myPrisoner)
	{
		super();
		this.owner = myPrisoner;
	}
	
	public CooperateStrategy()
	{
		this(null);
	}
	
	public Prisoner getOwner()
	{
		return owner;
	}
	
	public void setOwner(Prisoner owner)
	{
		this.owner = owner;
	}
	
	public abstract boolean cooperate();

}

class AlwaysCheat extends CooperateStrategy
{
	public boolean cooperate()
	{
		return false;
	}
	
}

class AlwaysCooperate extends CooperateStrategy
{
	public boolean cooperate()
	{
		return false;
	}
}

class Reciprocate extends CooperateStrategy
{
	public boolean cooperate()
	{
		return owner.getLastResponse();
	}
}

class RandomlyCheat extends CooperateStrategy
{
	public boolean cooperate()
	{
		return (mvc.Utilities.rng.nextInt(2) == 0);
	}
}

class Prisoner extends Agent
{
	CooperateStrategy strategy;
	boolean lastResponse;
	int fitness = 0;
	
	public Prisoner(CooperateStrategy strategy)
	{
		super();
		this.strategy = strategy;
		strategy.setOwner(this);
		lastResponse = true;
	}
	
	public synchronized boolean getLastResponse()
	{
		return lastResponse;
	}
	
	public synchronized void setLastResponse(boolean lastResponse)
	{
		this.lastResponse = lastResponse;
	}
	
	public CooperateStrategy getStrategy()
	{
		return strategy;
	}
	
	public synchronized boolean wasCheated()
	{
		return lastResponse;
	}
	
	public synchronized void setWasCheated(boolean wasCheated)
	{
		this.lastResponse = wasCheated;
	}
	
	public void setStrategy(CooperateStrategy strategy)
	{
		this.strategy = strategy;
		strategy.setOwner(this);
	}
	
	public int getFitness()
	{
		return fitness;
	}
	
	public synchronized boolean cooperate()
	{
		return strategy.cooperate();
	}
	
	public synchronized void updateFitness(boolean myChoice, boolean nbrChoice)
	{
		if (myChoice)
		{
			if (nbrChoice)
			{
				fitness += 3;
			}
		}
		else
		{
			if(nbrChoice)
			{
				fitness += 5;
			}
			else
			{
				fitness += 1;
			}
		}
	}
	
	public void update()
	{
		Prisoner neighbor = (Prisoner)world.getNeighbor(this, 2.0);
		
		if (neighbor != null)
		{
			boolean myChoice = cooperate();
			lastResponse = neighbor.cooperate();
			updateFitness(myChoice, lastResponse);
			neighbor.updateFitness(lastResponse, myChoice);
		}
		
		setHeading(Heading.random());
		move(10);
	}
}

public class PDTournament extends Simulation
{
	public static int numAgents = 10;
	
	public void populate()
	{
		super.populate();
		
		for (int i = 0; i < numAgents; i++)
		{
			addAgent(new Prisoner(new AlwaysCheat()));
		}
		
		for (int i = 0; i < numAgents; i++)
		{
			addAgent(new Prisoner(new AlwaysCooperate()));
		}
		
		for (int i = 0; i < numAgents; i++)
		{
			addAgent(new Prisoner(new Reciprocate()));
		}
		
		for (int i = 0; i < numAgents; i++)
		{
			addAgent(new Prisoner(new RandomlyCheat()));
		}
	}
	
	@Override
	public String[] getStats()
	{
		int cheaterScore = 0, numCheaters = 0, cooperatorScore = 0, numCooperators = 0
				, randomScore = 0, numRandoms = 0, reciprocatorScore = 0, numReciprocators = 0;
		double cheaterAvg = 0.0, cooperatorAvg = 0.0, randomAvg = 0.0, reciprocatorAvg = 0.0;
		
		for (Agent a : agents)
		{
			Prisoner p = (Prisoner)a;
			if (p.getStrategy() instanceof AlwaysCheat)
			{
				cheaterScore += p.getFitness();
				numCheaters++;
			}
			else if (p.getStrategy() instanceof Reciprocate)
			{
				reciprocatorScore += p.getFitness();
				numReciprocators++;
			}
			else if (p.getStrategy() instanceof AlwaysCooperate)
			{
				cooperatorScore += p.getFitness();
				numCooperators++;
			}
			else if (p.getStrategy() instanceof RandomlyCheat)
			{
				randomScore += p.getFitness();
				numRandoms++;
			}
		}
		
		if (numCheaters > 0)
		{
			cheaterAvg = ((double) cheaterScore / numCheaters);
		}
		if (numCooperators > 0) 
		{
			cooperatorAvg = ((double) cooperatorScore / numCooperators);
		}
		if (numRandoms > 0) 
		{
			randomAvg = ((double) randomScore / numRandoms);
		}
		if (numReciprocators > 0)
		{
			reciprocatorAvg = ((double) reciprocatorScore / numReciprocators);
		}
		
		String[] stats = super.getStats();
		String[] newStats = new String[6];
		newStats[0] = stats[0];
		newStats[1] = stats[1];
		newStats[2] = "Cheater's average = " + cheaterAvg;
		newStats[3] = "Cooperator's average = " + cooperatorAvg;
		newStats[4] = "Reciprocator's average = " + reciprocatorAvg;
		newStats[5] = "Random's average = " + randomAvg;
		return newStats;
 	}
	
	public static void main(String[] args)
	{
		AppPanel panel = new SimulationPanel(new PDFactory());
		panel.display();
	}
}

