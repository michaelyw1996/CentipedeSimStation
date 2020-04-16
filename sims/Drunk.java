package sims;
import mvc.*;
import simStation.*;

public class Drunk extends Agent
{
	private int speed;
	
	public Drunk()
	{
		super();
	}
	
	public void update()
	{
		heading = Heading.random();
		setHeading(heading);
		speed = Utilities.rng.nextInt(10);
		move(speed);
	}
}
