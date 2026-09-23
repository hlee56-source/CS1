import edu.fcps.karel2.Display;

public class Racer extends Athlete {
    public Racer(int y) {
        super(1, y, Display.EAST, Display.INFINITY);
    }
    public void jumpRight() {
        //facing east
        turnLeft();
        move();
        turnRight();
        move();
        turnRight();
        move();
        turnLeft();
    }
    public void jumpLeft() {
        //facing west
        turnRight();
        move();
        turnLeft();
        move();
        turnLeft();
        move();
        turnRight();
    }
    public void sprint(int n) {
        for (int i = 0; i < n; i++) {
            move();
        }
    }
    public void pick(int n) {
        for (int i = 0; i < n; i++) {
            pickBeeper();
        }
    }
    public void put(int n) {
        for (int i = 0; i < n; i++) {
            putBeeper();
        }
    }
}
