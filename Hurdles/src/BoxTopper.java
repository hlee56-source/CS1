import edu.fcps.karel2.Display;

public class BoxTopper extends Racer {
    public BoxTopper(int a){
        super(a);
    }
    public void jumpRight(){
        turnLeft();
        while (!rightIsClear())
            move();
        turnRight();
        while (frontIsClear())
            move();
        turnLeft();
        while (!rightIsClear())
            move();
        turnRight();
        do move();
        while (!rightIsClear());
        turnRight();
        while (frontIsClear())
            move();
        turnLeft();
    }
}
