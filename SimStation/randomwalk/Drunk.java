/*
* Michael Wong, 4/14: Created and probably finished drunks.
*/
package randomwalk;
import Simulation.*;
import mvc.*;

public class Drunk extends Agent{
	private int speed;
	public Drunk() {
		super();
	}

	@Override
	public void update() {
		setHeading(Heading.getRandomHeading());
		speed = Utilities.rng.nextInt(5)+1;
		move(speed);
	}

}
