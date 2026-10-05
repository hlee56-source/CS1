import edu.fcps.karel2.Robot;

public class Athlete extends Robot {
    public Athlete(int x, int y, int direction, int beepers) {
        super(x, y, direction, beepers);
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

    public void move(int num) {
        for (int i = 0; i < num; i++) {
            move();
        }
    }
}
