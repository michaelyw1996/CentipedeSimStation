package plague;

import Simulation.*;
import mvc.Model;

public class PlagueFactory extends SimulationFactory{
	public Model makeModel(){ return new PlagueSimulation();}
}

public View getView(Model model) {
		return new PlagueView((PlagueSimulation) model);
}
