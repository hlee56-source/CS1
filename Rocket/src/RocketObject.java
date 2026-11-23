//Required import statements
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class RocketObject {
    private int x = 0;
    private int y = 0;
    private double width = 0;
    private double height = 0;
    private Color color = Color.BLACK;
    private Graphics graphicsObject = null;
    public RocketObject(int x, int y, double width, double height, Color color, Graphics g) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.graphicsObject = g;
    }
    public void drawRocket() {
        graphicsObject.setColor(this.color);
        int[] xPoints = {(int) (x+(50*width)), (int) (x+(100*width)), x};
        int[] yPoints = {y, (int) (y+(100*height)), (int) (y+(100*height))};
        int nPoints = 3;

        graphicsObject.setColor(color);
        graphicsObject.fillPolygon(xPoints, yPoints, nPoints);
        graphicsObject.setColor(new Color(100, 100, 100));
        int x1 = (int) (x+(100*width));
        int y1 = (int) (y+(500*height));
        int y2 = (int) (y+(100*height));
        int[] xPoints2 = {x, x1, x1, x};
        int[] yPoints2 = {y2, y2, y1, y1};
        nPoints = 4;
        graphicsObject.fillPolygon(xPoints2, yPoints2, nPoints);
        ImageIcon image = new ImageIcon("images.png");
        graphicsObject.drawImage(image.getImage(), x, (int) (y+(100 * height)), (int)(width * 100), (int)(height * 100), null);
        IO.println((int) (height * 100));
        graphicsObject.setFont(new Font("SansSerif", Font.BOLD, 10));
        graphicsObject.setColor(Color.BLUE);
        graphicsObject.drawString("Harrison's Rocket", x, (int) (y+(100 * height)));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
