
//CS1 and Physics - Integrated Projectile Project

import edu.fcps.karel2.Display;
import edu.fcps.karel2.Robot;
import javax.swing.JOptionPane;

public class PhysicsProject {
    // global variables
    // these can be referenced anywhere in the program
    public static double Ay = -9.8; //y acceleration (gravity)
    public static double Ax = 0;    //x acceleration (0 for this simulation)
    public static double dt = 0.1;  //time between calculations

    public static void main(String[] args){
        // variables this method will be using
        double v0 = 0; //initial velocity of the projectile
        double angleDegrees = 0; //launch angle

        // Open default map and set speed
        Display.setSize(100,100);
        Display.setSpeed(10);

        // User input to define initial velocity and launch angle
        // the parseDouble method converts the string input to a double
        v0 = Double.parseDouble(JOptionPane.showInputDialog("Please enter an initial velocity in m/s"));
        angleDegrees = Double.parseDouble(JOptionPane.showInputDialog("Please enter an initial angle in degrees"));

        calculateProjectile(v0, angleDegrees);


    }

    public static double calculateV0X(double v0, double angleDegrees) {
        return v0 * Math.cos(Math.toRadians(angleDegrees));
    }

    public static double calculateV0Y(double v0, double angleDegrees) {
        return v0 * Math.sin(Math.toRadians(angleDegrees));
    }

    public static double calculateX(double t, double v0x) {
        return v0x * t;
    }
    public static double calculateY(double t, double v0y, double gravity) {
        return v0y * t + 0.5 * gravity * (t * t);
    }

    public static void calculateProjectile(double v0, double angleDegrees){
        double t = 0; //time elapsed since launch
        double x = 0; //current x coordinate of the projectile
        double y = 0; //current y coordinate of the projectile
        double v0x = calculateV0X(v0, angleDegrees); //initial x velocity of the projectile
        double v0y = calculateV0Y(v0, angleDegrees); //initial y velocity of the projectile

        System.out.println("V0x: " + v0x);
        System.out.println("V0y: " + v0y);
        while (y >= 0) {
            x = calculateX(t, v0x);
            y = calculateY(t, v0y, Ay);
            IO.println("x: " + x + " y: " + y + " time: " + t);
            new Robot((int) x, (int) y, 0, 0);
            t += dt;
        }

    }

    // TODO: Part 2 methods will be added here

}