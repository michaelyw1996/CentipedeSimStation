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