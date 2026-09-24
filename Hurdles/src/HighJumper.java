import edu.fcps.karel2.Display;

public class HighJumper extends Racer {
    public HighJumper(int a){
        super(a);
    }
    public void jumpRight(){
        turnLeft();
        while (!rightIsClear())
            move();
        turnRight();
        move();
        turnRight();
        while (frontIsClear())
            move();
        turnLeft();
    }
}
