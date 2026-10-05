import edu.fcps.karel2.Display;
import edu.fcps.karel2.Robot;

public class Athlete extends Robot {
    public Athlete(int x, int y, int direction, int beepers) {
        super(x, y, direction, beepers);
    }
    void move(int amount){
        for (int i = 0; i < amount; i++) {
            move();
        }
    }
    public void turnRight() {
        turnLeft();
        turnLeft();
        turnLeft();
    }
    public void turnAround() {
        turnLeft();
        turnLeft();

    }
}
