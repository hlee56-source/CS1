import edu.fcps.karel2.Display;

public class MountainClimber {
    static void main() {
        Display.openWorld("G:\\My Drive\\School\\9th grade\\CS1\\JKarel Start Files\\JKarel Start Files\\maps\\mountain1.map");
        Display.setSize(10, 10);
        Display.setSpeed(10);
        Climber a = new Climber(1, 1, 1, 1000);
        a.turnRight();
        for (int i = 0; i < 3; i++) {
            a.climbUpRight();
        }

    }
}