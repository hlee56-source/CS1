//Required import statements
import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;


//make sure the class extends jPanel
public class FallFestival extends JPanel {
    //this method "paints" things onto the panel
    public static void drawSunset(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        GradientPaint sunset = new GradientPaint(0, 0, new Color(140, 38, 191), 0, 500, new Color(255, 128, 0));
        g2D.setPaint(sunset);
        g2D.fillRect(0, 0, 1000, 800);
    }
    public void drawLeaves(Graphics g, int numLeaves, int screenWidth, int screenHeight, double leaveSize) {
        Image image = new ImageIcon("G:\\My Drive\\School\\9th grade\\CS1\\FallFestival\\src\\leave.png").getImage();
        Graphics2D g2 = (Graphics2D) g.create();

        int width = image.getWidth(this);
        int height = image.getHeight(this);

        for (int i = 0; i < numLeaves; i++) {
            int randomX = (int) ((Math.random() * (screenWidth + 1)));
            int randomY = (int) ((Math.random() * (screenHeight + 1)));
            AffineTransform transform = new AffineTransform();
            transform.translate(randomX, randomY);
            transform.scale(leaveSize, leaveSize);
            transform.rotate(randomX);
            g2.drawImage(image, transform, this);
        }
        //g2.dispose();
    }
    public static void drawSun(Graphics g, int x, int y, int size) {
        g.setColor(new Color(255, 219, 0, 255));
        g.fillOval(x, y, size, size);
    }
    public static void drawRoad(Graphics g) {
        int[] xPoints = {400, 600, 950, 50};
        int[] yPoints = {400, 400, 800, 800};
        g.fillPolygon(xPoints, yPoints, 4);
    }
    public void drawTree(Graphics g, int x, int y, int height) {
        g.setColor(new Color(69, 57, 45));
        g.fillRect((x-10), y, 20, height);
        g.setColor(new Color(143, 45, 45));
        g.fillOval((x-50), (y-height), 100, 150);
    }
    public static void drawSunBeams(Graphics g, int x, int y, int size) {
        g.setColor(new Color(255, 219, 0, 100));
        g.fillArc(x, y, size, size, 0, 180);
    }

    public void paintComponent (Graphics g)	{
        drawSunset(g); // draw background
        drawSun(g, 350, 250, 300); // draw the sun
        drawSunBeams(g, 300, 200, 400);
        g.setColor(new Color(52, 74, 34)); // dark green
        g.fillRect(0, 400, 1000, 800); // draw the ground
        g.setColor(new Color(51, 37, 29, 255)); // dark brown
        drawRoad(g); // draw the road
        drawTree(g, 700, 350, 125); // draw trees
        drawTree(g, 300, 350, 125);
        drawTree(g, 900, 500, 150);
        drawTree(g, 100, 500, 150);
        g.setColor(new Color(87, 59, 50, 255)); // light brown
        for (int i = 0; i < 5; i++) // make the line thicker (and also use a for loop)
            g.drawLine((800+i), 600, (800+i), 700);
        g.fillRect(750, 575, 100, 50); // draw sign
        g.setColor(new Color(0, 0, 0)); // black
        g.setFont(new Font(Font.SERIF, Font.BOLD, 30)); // set font to a decent size
        g.drawString("Fall!", 775, 610); // draw text for the sign
        drawLeaves(g, 10, 1000, 800, 0.1);

    }
    //Driver
    public static void main (String[] args) {
        //creates a frame and sets it's properties
        JFrame frame = new JFrame("Fall Scene");
        frame.setSize(1000, 800);
        frame.setLocation(0, 0);
        frame.setResizable(false);
        //tells the java program to exit when the graphics window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //put the panel on the frame and make it visible
        frame.setContentPane(new FallFestival());
        frame.setVisible(true);
    }
}
