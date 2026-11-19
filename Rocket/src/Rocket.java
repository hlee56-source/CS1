import javax.swing.*;

public class Rocket extends JPanel {
    public static void paintComponent() {

    }
    public static void main (String[] args) {
        //creates a frame and sets it's properties
        JFrame frame = new JFrame("Fall Scene");
        frame.setSize(1000, 800);
        frame.setLocation(0, 0);
        //frame.setResizable(false);
        //tells the Java program to exit when the graphics window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //put the panel on the frame and make it visible
        frame.setContentPane(new Rocket());
        frame.setVisible(true);
    }
}
