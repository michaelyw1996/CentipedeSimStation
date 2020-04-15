/*
* Michael Wong, 4/14: Created and probably finished drunks.
*/
package randomwalks;

import java.util.Random;

import SimStation.*;
import mvc.*;

public class Drunk extends Agent{
	private int speed;
	public Drunk() {
		super();
		speed = Utilities.rng.nextInt(5)+1;
	}

	@Override
	public void update() {
		setHeading(heading.getRandomHeading());
		move(speed);
	}

}
