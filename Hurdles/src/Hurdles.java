// Hurdles.java
// ITCS

import edu.fcps.karel2.Display;
import javax.swing.JOptionPane;

public class Hurdles {
    public static void race(Racer a) {
        while (!a.nextToABeeper()) {
            if (a.frontIsClear()) {
                a.move();
            } else {
                a.jumpRight();
            }
        }
    }
    // declare and initialize array of map choices for the dialogue drop-down options
    public static final String[] choices = {"hurdle1.map", "hurdle2.map", "hurdle3.map", "steeple1.map", "steeple2.map", "steeple3.map", "boxtop1.map", "boxtop2.map", "boxtop3.map"};

    public static void main(String[] args)
    {
        // open dialogue box and request user input of map choices
        String mapChoice = (String)JOptionPane.showInputDialog(null,"Choose an map.", "Map Choices", JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);

        // open selected map and set size and speed
        Display.openWorld("G:\\My Drive\\School\\9th grade\\CS1\\JKarel Start Files\\JKarel Start Files\\maps\\" + mapChoice);
        Display.setSize(18, 10);
        Display.setSpeed(10);

        Racer turbo = null;

        if (mapChoice.equals("hurdle1.map") || mapChoice.equals("hurdle2.map") || mapChoice.equals("hurdle3.map")) {
            turbo = new Racer(1);
        }
        else if (mapChoice.equals("steeple1.map") || mapChoice.equals("steeple2.map") || mapChoice.equals("steeple3.map")){
            turbo = new HighJumper(1);
        }
        else if (mapChoice.equals("boxtop1.map") || mapChoice.equals("boxtop2.map") || mapChoice.equals("boxtop3.map")) {
            turbo = new BoxTopper(1);
        }


        // raceTest(turbo);
        // System.out.println("test complete");
        race(turbo);
    }


    // Test the jumpRight method.
    // The racer will move, move, then jumpRight and jump over the hurdle.
    // The racer will finish next to the hurdle, facing to the east (right).
    public static void raceTest(Racer racer) {
        racer.move();
        racer.move();
        racer.jumpRight();

    }


}