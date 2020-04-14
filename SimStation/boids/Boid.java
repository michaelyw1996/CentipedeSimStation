package boids;

import mvc.*;
import SimStation.*;

public class Boid extends Agent {
    private int speed;

    public Boid(){
      super();
      speed = Utilities. rng.nextInt(5)+1;
    }

    public void update(){
      Boid neighbor = (Boid)world.getNeighbor(this, 20.0);
      if(neighbor != null){
        heading = neighbor.getHeading();
        speed = neighbor.getSpeed();
      }
      move(speed);
    }
    public synchronized int getSpeed(){
      return speed;
    }
}
