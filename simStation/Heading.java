/*
 *Michael Wong, 4/14: created and completed.
 */

package simStation;
import java.util.*;

public enum Heading
{
	NORTH, SOUTH, WEST, EAST;

	public static Heading random()
	{
		Random random = new Random();
		return values() [random.nextInt(values().length)];
	}
}
