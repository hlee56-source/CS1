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

    public void climbUpLeft(int stairSize) {
        // assumes climber's head will begin facing the mountain and end in the same direction
        turnRight();
        move(stairSize);
        turnLeft();
        move(stairSize);
    }
}