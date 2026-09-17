// Lab03: Climb Every Mountain 
// ClimberTester.java

import edu.fcps.karel2.Display;
import edu.fcps.karel2.Robot;
import javax.swing.JOptionPane;

public class ClimberTester {

    // Prints the message to a dialog box
    public static void printFinishMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }



    public static void main (String[] args) {
        Display.openWorld("maps/climbertester.map");
        Display.setSize(10, 10);
        Display.setSpeed(3);

        // Test constructors
        Climber righty = new Climber();
        righty.turnRight();
        printFinishMessage("Default constructor created.");

        Climber lefty = new Climber(7);
        lefty.turnLeft();
        printFinishMessage("1-argument constructor created.");


        // Test climb methods
        righty.climbUpRight();
        printFinishMessage("climbUpRight complete.");

        lefty.climbUpLeft();
        printFinishMessage("climbUpLeft complete.");

        righty.climbDownRight();
        printFinishMessage("climbDownRight complete.");

        lefty.climbDownLeft();
        printFinishMessage("climbDownLeft complete.");

    }
}