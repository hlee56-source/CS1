import edu.fcps.karel2.Display;

public class MountainClimber {
    static void main() {
        Display.openWorld("../maps/ACL.map");
        Display.setSize(10,10);
        Athlete a = new Athlete(1, 1, 1, 1);
        a.move(1);
    }
}