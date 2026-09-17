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

    public void climbUpLeft() {
        // assumes climber's head will begin facing the mountain and end in the same direction
        turnRight();
        move(2);
        turnLeft();
        move(1);
    }
    public void climbUpRight() {
        // assumes climber's head will begin facing the mountain and end in the same direction
        turnLeft();
        move(2);
        turnRight();
        move(1);
    }
    public void climbDownLeft() {
        // assumes climber's head will begin facing the mountain and end in the same direction
        move(1);
        turnRight();
        move(2);
        turnLeft();


    }
    public void climbDownRight() {
        // assumes climber's head will begin facing the mountain and end in the same direction
        move(1);
        turnLeft();
        move(2);
        turnRight();
    }
}