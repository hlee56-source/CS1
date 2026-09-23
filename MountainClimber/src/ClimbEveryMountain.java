import edu.fcps.karel2.Display;

public class ClimbEveryMountain {
    static void getTreasure(Climber x) {
        x.turnRight();
        x.putBeeper();
        x.move();
        for (int i = 0; i < 4; i++) {
            x.climbUpRight();
        }
        for (int i = 0; i < 3; i++) {
            x.climbDownLeft();
        }
        x.turnAround();
        for (int i = 0; i < 3; i++) {
            x.climbUpLeft();
        }
        //a.turnAround();
        for (int i = 0; i < 4; i++) {
            x.climbDownRight();
        }
        x.move();
        x.putBeeper();
        x.turnRight();
        x.move();
        x.turnAround();
    }
    static void main() {
        Display.openWorld("G:\\My Drive\\School\\9th grade\\CS1\\JKarel Start Files\\JKarel Start Files\\maps\\mountain.map");
        Display.setSize(17, 17);
        Display.setSpeed(10);
        Climber a = new Climber(8, 1, 1, -2);
        getTreasure(a);
        Climber b = new Climber(8, 1, 1, -2);
        getTreasure(b);
        Climber c = new Climber(8, 1, 1, -2);
        getTreasure(c);
    }
}