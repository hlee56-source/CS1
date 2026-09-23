import edu.fcps.karel2.Display;

public class ShuttleRun {
    public static void shuttle(Racer a) {
        a.move();
        a.jumpRight();
        while (!a.nextToABeeper()) {
            a.move();
        }
        while (a.nextToABeeper()) {
            a.pick(1);
        }
        a.turnAround();
        while (a.frontIsClear()) {
            a.move();
        }
        a.jumpLeft();
        a.move();
        a.turnAround();
    }
    public static void runRace(Racer a) {
        for (int i = 0; i<4; i++) {
            shuttle(a);
        }
    }
    static void main() {
        Display.openWorld("G:\\My Drive\\School\\9th grade\\CS1\\JKarel Start Files\\JKarel Start Files\\maps\\shuttlerun.map");
        Display.setSize(13, 10);
        Display.setSpeed(10);

        Racer racer1 = new Racer(1);
        Racer racer2 = new Racer(4);
        Racer racer3 = new Racer(7);
        runRace(racer1);
        runRace(racer2);
        runRace(racer3);
    }
}
