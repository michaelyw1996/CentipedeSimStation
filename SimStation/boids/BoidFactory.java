package boids;
import mvc.*;
import Simulation.*;

public class BoidFactory extends SimulationFactory{
	public Model makeModel(){ return new BoidSimulation();}
}
