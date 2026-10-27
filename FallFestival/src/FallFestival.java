//Required import statements
import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;


//make sure the class extends jPanel
public class FallFestival extends JPanel {
    //this method "paints" things onto the panel
    public void drawLeaves(Graphics g, int numLeaves, int heightStart, int screenWidth, int screenHeight, double leafSize) {
        Image image = new ImageIcon("G:\\My Drive\\School\\9th grade\\CS1\\FallFestival\\src\\leave.png").getImage();
        Graphics2D g2 = (Graphics2D) g.create();

        int width = image.getWidth(this);
        int height = image.getHeight(this);

        for (int i = 0; i < numLeaves; i++) {
            int randomX = (int) ((Math.random() * (screenWidth + 1)));
            int randomY = (int) (heightStart + (Math.random() * (screenHeight - heightStart)));
            AffineTransform transform = new AffineTransform();
            transform.translate(randomX, randomY);
            double scale = (Math.random() * leafSize);
            if (scale < 0.05) {
                scale = 0.05;
            }
            transform.scale(scale, scale);
            transform.rotate(randomX);
            g2.drawImage(image, transform, this);
        }
        //g2.dispose();
    }
    public static void drawRoad(Graphics g) {
        int[] xPoints = {400, 600, 950, 50};
        int[] yPoints = {400, 400, 800, 800};
        g.fillPolygon(xPoints, yPoints, 4);
    }

    public void paintComponent (Graphics g)	{
        Sun.drawSunset(g);
        Sun.drawSunBeams(g, 500, 400, 50);
        g.setColor(new Color(52, 74, 34)); // dark green
        g.fillRect(0, 400, 1000, 800); // draw the ground
        g.setColor(new Color(51, 37, 29, 255)); // dark brown
        drawRoad(g); // draw the road
        Tree.drawTree(g, 700, 350, 125); // draw trees
        Tree.drawTree(g, 300, 350, 125);
        Tree.drawTree(g, 900, 500, 150);
        Tree.drawTree(g, 100, 500, 150);
        g.setColor(new Color(87, 59, 50, 255)); // light brown
        for (int i = 0; i < 5; i++) // make the line thicker (and also use a for loop)
            g.drawLine((800+i), 600, (800+i), 700);
        g.fillRect(750, 575, 100, 50); // draw sign
        g.setColor(new Color(0, 0, 0)); // black
        g.setFont(new Font(Font.SERIF, Font.BOLD, 30)); // set font to a decent size
        g.drawString("Fall!", 775, 610); // draw text for the sign
        drawLeaves(g, 10,300, 1000, 700, 0.1);

    }
    //Driver
    public static void main (String[] args) {
        //creates a frame and sets it's properties
        JFrame frame = new JFrame("Fall Scene");
        frame.setSize(1000, 800);
        frame.setLocation(0, 0);
        //frame.setResizable(false);
        //tells the java program to exit when the graphics window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //put the panel on the frame and make it visible
        frame.setContentPane(new FallFestival());
        frame.setVisible(true);
    }
}
