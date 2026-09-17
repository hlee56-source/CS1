import edu.fcps.karel2.Display;

public class ClimbEveryMountain {
    static void main() {
        Display.openWorld("G:\\My Drive\\School\\9th grade\\CS1\\JKarel Start Files\\JKarel Start Files\\maps\\mountain.map");
        Display.setSize(17, 17);
        Display.setSpeed(10);
        Climber a = new Climber(8, 1, 1, 10);
        a.turnRight();
        a.putBeeper();
        a.move();
        for (int i = 0; i < 4; i++) {
            a.climbUpRight();
        }
        for (int i = 0; i < 3; i++) {
            a.climbDownLeft();
        }
        a.turnAround();
        for (int i = 0; i < 3; i++) {
            a.climbUpLeft();
        }
        //a.turnAround();
        for (int i = 0; i < 4; i++) {
            a.climbDownRight();
        }
        a.move();
        a.putBeeper();
        a.turnRight();
        a.move();
        a.turnAround();
    }
}