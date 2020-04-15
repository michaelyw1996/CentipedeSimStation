package SimStation;

import java.util.Random;

public enum Heading{
	NORTH,SOUTH,EAST,WEST;
	 public static Heading getRandomHeading() {
         Random random = new Random();
         return values()[random.nextInt(values().length)];
     }
}
