//Required import statements
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
//make sure the class extends jPanel
public class Rocket extends JPanel {

    public static void makeBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 600, 600);
        g.setColor(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            g.fillOval((int) (Math.random() * 600), (int) (Math.random() * 600), 2, 2); // Stars!
        }
    }
    //this method "paints" things onto the panel
    public void paintComponent (Graphics g)	{
        makeBackground(g);
        RocketObject rocket1 = new RocketObject(0, 0, 0.5, 0.5, Color.RED, g);
        rocket1.drawRocket();
        RocketObject rocket2 = new RocketObject(400, 200, 0.1, 0.1, Color.GREEN, g);
        rocket2.drawRocket();
        RocketObject rocket3 = new RocketObject(200, 300, 1, 1, Color.YELLOW, g);
        rocket3.drawRocket();
        RocketObject rocket4 = new RocketObject(100, 100, 0.8, 0.8, Color.GREEN, g);
        rocket4.drawRocket();

    }
    //Driver
    public static void main (String[] args) {
        //creates a frame and sets it's properties
        JFrame frame = new JFrame("Rocket");
        frame.setSize(600, 600);
        frame.setLocation(0, 0);

        //tells the java program to exit when the graphics window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //put the panel on the frame and make it visible
        frame.setContentPane(new Rocket());
        frame.setVisible(true);
    }
}
