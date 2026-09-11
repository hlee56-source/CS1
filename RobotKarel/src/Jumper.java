import edu.fcps.karel2.Robot;
public class Jumper extends Robot {
    public Jumper(){
        super();
    }

    public void jump() {
        turnLeft();
        move();
        turnLeft();
        turnLeft();
        move();
        turnLeft();
    }
}
