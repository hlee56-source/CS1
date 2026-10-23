import java.awt.*;

public class Sun {
    private static void drawFilledArcFromCenter(Graphics g, Color color, int centerX, int centerY, int radius) {
        g.setColor(color);
        g.fillArc((centerX - radius), (centerY - radius), (2 * radius), (2 * radius), 0, 180);
    }
    public static void drawSunset(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        GradientPaint sunset = new GradientPaint(0, 0, new Color(140, 38, 191), 0, 500, new Color(255, 128, 0));
        g2D.setPaint(sunset);
        g2D.fillRect(0, 0, 1000, 800);
    }
    public static void drawSunBeams(Graphics g, int x, int y, int size) {
        for (int i = 0; i < 650; i++) {
            int alpha = (int)Math.round(Math.sqrt(i) * 10);
            Color color = new Color(255, 225, 0, 255-alpha);
            drawFilledArcFromCenter(g, color, x, y, size+(i/5));
        }
    }
}
