import edu.fcps.karel2.Display;

public class TrackStar extends Athlete {
    private String name;
    private int laps;
    private int steps;

    public TrackStar(String name) {
        this.name = name;
        this.steps = 0;
        this.laps = 0;
        super(1, 1, Display.EAST, 0);
    }
    public String getName() {
        return name;
    }
    public int getLaps() {
        return laps;
    }
    public int getSteps() {
        return steps;
    }
    public int getMiles() {
        return (steps / 20);
    }
    public void resetCount() {
        steps = 0;
        laps = 0;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void runLaps(int numLaps) {
        for (int i = 0; i < 4 * numLaps; i++) {
            while (frontIsClear()) {
                move();
                steps++;
            }
            turnLeft();
        }
        laps += numLaps;
    }

}