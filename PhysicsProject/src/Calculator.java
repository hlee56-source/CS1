import edu.fcps.karel2.Display;
import edu.fcps.karel2.Robot;
import javax.swing.JOptionPane;

public class Calculator {

    public static void main(String[] args) {
        Display.setSize(20, 20);
        Display.setSpeed(10);
        Robot a = new Robot(1, 1, 1, 1);

        plotLine(a, 3, 2);
        IO.println("the roots are " + higherQuadratic(2, 13, 20) + " and " + lowerQuadratic(2, 13, 20));
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static double getXComponent(double magnitude, double angle) {
        return Math.cos(Math.toRadians(angle) * magnitude);
    }

    public static double getYComponent(double magnitude, double angle) {
        return Math.sin(Math.toRadians(angle) * magnitude);
    }

    public static void plotLine(Robot a, int rise, int run) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < rise; j++) {
                a.move();
            }
            a.turnLeft();
            a.turnLeft();
            a.turnLeft();
            for (int j = 0; j < run; j++) {
                a.move();
            }
            a.turnLeft();
            new Robot(a.getX(), a.getY(), 1, 1);
        }
    }

    public static double higherQuadratic(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            throw new IllegalArgumentException("Answer is not real");
        }
        return (-b + Math.sqrt(discriminant)) / (2 * a);
    }
    public static double lowerQuadratic(double a, double b, double c) {
        double discriminant = (b * b) - 4 * a * c;
        if (discriminant < 0) {
            throw new IllegalArgumentException("Answer is not real");
        }
        return (-b - Math.sqrt(discriminant)) / (2 * a);
    }

}