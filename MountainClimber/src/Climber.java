import edu.fcps.karel2.Display;
import edu.fcps.karel2.Robot;

public class Climber extends Athlete {
    public Climber(int x, int y, int direction, int beepers) {
        super(x, y, direction, beepers);
    }

    public Climber() {
        super(1, 1, Display.NORTH, 1);
    }
    public Climber(int x){
        super(x, 1, Display.NORTH, 1);
    }

    public void climbUpLeft(int stairHeight, int stairLength) {
        // assumes climber's head will begin facing the mountain and end in the same direction
        turnRight();
        move(stairHeight);
        turnLeft();
        move(stairLength);
    }
    public void climbUpRight(int stairHeight, int stairLength) {
        // assumes climber's head will begin facing the mountain and end in the same direction
        turnLeft();
        move(stairHeight);
        turnRight();
        move(stairLength);
    }
}