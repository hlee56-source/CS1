import edu.fcps.karel2.Display;
import edu.fcps.karel2.Robot;

public class RobotTest {
    public static void main(String[] args) {
        Display.openWorld(".../maps/ACL.map");
        Display.setSize(10,10);

        Jumper billy = new Jumper();
        billy.jump();
    }
}
