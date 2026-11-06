
//CS1 and Physics - Integrated Projectile Project
import java.util.ArrayList;
import java.util.List;
import edu.fcps.karel2.Display;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import javax.swing.JOptionPane;
import java.awt.*;

public class PhysicsProject {
    // global variables
    // these can be referenced anywhere in the program
    public static double Ay = -9.8; //y acceleration (gravity)
    public static double Ax = 0;    //x acceleration (0 for this simulation)
    public static double dt = 0.1;  //time between calculations
    public static double initial = 0;
    public static void main(String[] args){
        // variables this method will be using
        double v0 = 0; //initial velocity of the projectile
        double angleDegrees = 0; //launch angle

        // Open default map and set speed
        //Display.setSize(100,100);
        //Display.setSpeed(10);

        // User input to define initial velocity and launch angle
        // the parseDouble method converts the string input to a double
        v0 = Double.parseDouble(JOptionPane.showInputDialog("Please enter an initial velocity in m/s"));
        try{
            Ay = Double.parseDouble(JOptionPane.showInputDialog("Please describe your planet's gravity in m/s^2 \nEnter nothing for Earth"));
        } catch (Exception e) {
            Ay = -9.8;
        }
        try{
            initial = Double.parseDouble(JOptionPane.showInputDialog("Please describe the launch height in meters\nEnter nothing for a flat launch"));
        } catch (Exception e) {
            initial = 0;
        }
        if (Ay >= 0) {
            JOptionPane.showMessageDialog(null, "Gravity needs to be negative, setting to Earth's gravity of -9.8");
            Ay = -9.8;
        }
        int choice = JOptionPane.showConfirmDialog(
                null,
                "Do you want to run a simulation instead of finding the best launch angle",
                "Options",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (choice == JOptionPane.YES_OPTION) {
            angleDegrees = Double.parseDouble(JOptionPane.showInputDialog("Please enter an initial angle in degrees"));
            calculateProjectile(v0, angleDegrees);
        } else {
            findBestAngle(v0, 1, 90);
        }


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
        return v0y * t + 0.5 * gravity * (t * t) + initial;
    }

    public static void calculateProjectile(double v0, double angleDegrees){
        double t = 0; //time elapsed since launch
        double x = 0; //current x coordinate of the projectile
        double y = 0; //current y coordinate of the projectile
        double v0x = calculateV0X(v0, angleDegrees); //initial x velocity of the projectile
        double v0y = calculateV0Y(v0, angleDegrees); //initial y velocity of the projectile
        List<Double> xPoints = new ArrayList<>();
        List<Double> yPoints = new ArrayList<>();
        System.out.println("V0x: " + v0x);
        System.out.println("V0y: " + v0y);
        while (y >= 0) {
            x = calculateX(t, v0x);
            y = calculateY(t, v0y, Ay);
            //IO.println("x: " + x + " y: " + y + " time: " + t);
            xPoints.add(x);
            yPoints.add(y);
            t += dt;
        }
        XYChart chart = QuickChart.getChart("Projectile motion", "X", "Y", "Object motion", xPoints, yPoints);
        new SwingWrapper(chart).displayChart();

    }
    public static double findX(double v0, double angleDegrees){
        double t = 0; //time elapsed since launch
        double x = 0; //current x coordinate of the projectile
        double y = 0; //current y coordinate of the projectile
        double v0x = calculateV0X(v0, angleDegrees); //initial x velocity of the projectile
        double v0y = calculateV0Y(v0, angleDegrees); //initial y velocity of the projectile
        while (y >= 0) {
            x = calculateX(t, v0x);
            y = calculateY(t, v0y, Ay);
            t += dt;
        }
        return x;
    }
    public static void findBestAngle(double v0, double angleStart, double angleEnd) {
        // floats will be clamped to int
        double x;
        double highestX = 0;
        double bestAngle = 0;
        for (int angle = (int) angleStart; angle < (int) angleEnd; angle++) {
            x = findX(v0, angle);
            if (x > highestX) {
                highestX = x;
                bestAngle = angle;
            }
            IO.println("Angle: " + angle + " degrees, X: " + x);
        }
        IO.println("The best angle was " + bestAngle + " with a launch distance of " + highestX + "\nDisplaying graph...");
        calculateProjectile(v0, bestAngle);
    }
}