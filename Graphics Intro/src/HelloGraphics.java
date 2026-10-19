//Required import statements
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
//make sure the class extends jPanel
public class HelloGraphics extends JPanel {


    //this method "paints" things onto the panel
    public void paintComponent (Graphics g)	{
        g.fillRect(0, 0, 1000, 800);
        g.setColor(Color.RED);
        g.fillOval(50, 50, 100, 100);
        g.setColor(new Color(50, 100, 255));
        g.fillRect(700, 600, 100, 100);
        g.setColor(new Color(0, 0, 0));
        g.drawLine(0, 100, 1000, 100);
        g.drawLine(100, 0, 100, 800);
        g.setColor(new Color(50, 184, 50));
        g.drawString("Hello", 500, 500);
        g.setColor(new Color(243, 103, 0));
        for (int i = 0; i < 5; i ++) {
            g.fillOval((i*100), 500, 50, 50);
        }

    }
    //Driver
    public static void main (String[] args) {
        //creates a frame and sets it's properties
        JFrame frame = new JFrame("Hello CS1!!!!!");
        frame.setSize(1000, 800);
        frame.setLocation(50, 0);

        //tells the java program to exit when the graphics window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //put the panel on the frame and make it visible
        frame.setContentPane(new HelloGraphics());
        frame.setVisible(true);
    }
}
