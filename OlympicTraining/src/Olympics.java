import edu.fcps.karel2.Display;

import javax.sound.midi.Track;
import javax.swing.JOptionPane;
public class Olympics {
    public static final String[] choices = {"track1.map", "track2.map", "track3.map"};
    static void main() {
        String mapChoice = null;
        while (mapChoice == null) {
            mapChoice = (String) JOptionPane.showInputDialog(null, "Choose an map.", "Map Choices", JOptionPane.PLAIN_MESSAGE, null, choices, choices[0]);
            if (mapChoice == null) {
                JOptionPane.showMessageDialog(null, "Please select a map");
            }
        }
        // open selected map and set size and speed
        Display.openWorld("G:\\My Drive\\School\\9th grade\\CS1\\JKarel Start Files\\JKarel Start Files\\maps\\" + mapChoice);
        Display.setSize(17, 17);
        Display.setSpeed(10);

        TrackStar a = new TrackStar("a");
        a.runLaps(1);
        IO.println(a.getName() + " ran " + String.valueOf(a.getLaps()) + " laps and " + String.valueOf(a.getMiles()) + " miles. That was " + String.valueOf(a.getSteps()) + " steps!");
        a.runLaps(1);
        IO.println(a.getName() + " ran " + String.valueOf(a.getLaps()) + " laps and " + String.valueOf(a.getMiles()) + " miles. That was " + String.valueOf(a.getSteps()) + " steps!");
        TrackStar b = new TrackStar("b");
        b.runLaps(4);
        IO.println(b.getName() + " ran " + String.valueOf(b.getLaps()) + " laps and " + String.valueOf(b.getMiles()) + " miles. That was " + String.valueOf(b.getSteps()) + " steps!");
        TrackStar c = new TrackStar("c");
        c.runLaps(6);
        IO.println(c.getName() + " ran " + String.valueOf(c.getLaps()) + " laps and " + String.valueOf(c.getMiles()) + " miles. That was " + String.valueOf(c.getSteps()) + " steps!");

    }
}