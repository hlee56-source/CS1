import java.awt.*;

public class Tree {
    public static void drawTree(Graphics g, int x, int y, int height) {
        g.setColor(new Color(69, 57, 45));
        g.fillRect((x - 10), y, 20, height);
        g.setColor(new Color(143, 45, 45));
        g.fillOval((x - 50), (y - height), 100, 150);
    }
}