import edu.fcps.karel2.Display;
import edu.fcps.karel2.Robot;

public class RobotTest {
    public static void main(String[] args) {
        Display.openWorld("../maps/ACL.map");
        Display.setSize(10,10);
        Athlete billy = new Athlete(1, 1, 1, 6);
        billy.turnRight();
        billy.moveAndPlaceNum(3);
        billy.turnLeft();
        billy.move();
        billy.turnLeft();
        billy.move();
        billy.moveAndPlaceNum(2);
        billy.turnRight();
        billy.move();
        billy.turnRight();
        billy.move();
        billy.move();
        billy.putBeeper();
        billy.move();
        billy.turnRight();
        billy.move();
        billy.move();
        billy.turnAround();
    }
}
