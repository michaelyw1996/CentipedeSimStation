package plague;

import Simulation.*;
import mvc.Model;

public class PlagueFactory extends SimulationFactory{
	public Model makeModel(){ return new PlagueSimulation();}
}
