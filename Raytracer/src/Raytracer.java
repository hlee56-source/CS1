import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Raytracer extends JPanel {
    java.util.List<Sphere> spheres = new java.util.ArrayList<Sphere>();
    public Raytracer() {
        spheres.add(new Sphere(new Point3d(0, 0, 5), 1, Color.RED, 1));
        spheres.add(new Sphere(new Point3d(2, 0, 5), 1, Color.GREEN, 1));
        spheres.add(new Sphere(new Point3d(-2, 0, 5), 1, Color.BLUE, 1));
    }
    public void setPixel(Graphics g, Color c, int x, int y) {
        g.setColor(c);
        g.drawLine(x, y, x, y);
    }
    public Color traceRay(Point3d O, Point3d D){
        double closest_t = Double.MAX_VALUE;
        Sphere closest_sphere = null;
        for (Sphere sphere : spheres) {
            double t = intersectRaySphere(O, D, sphere);
            if (t > 0 && t < closest_t) {
                closest_t = t;
                closest_sphere = sphere;
            }
        }
        if (closest_sphere == null) {
            return Color.BLACK; // background color
        } else {
            return closest_sphere.getColor();
        }
    }

    public double intersectRaySphere(Point3d o, Point3d d, Sphere sphere) {
        double r = sphere.getRadius();
        Point3d c = sphere.getCenter();
        double a = d.getX() * d.getX() + d.getY() * d.getY() + d.getZ() * d.getZ();
        double b = 2 * (d.getX() * (o.getX() - c.getX()) + d.getY() * (o.getY() - c.getY()) + d.getZ() * (o.getZ() - c.getZ()));
        double c_val = (o.getX() - c.getX()) * (o.getX() - c.getX()) + (o.getY() - c.getY()) * (o.getY() - c.getY()) + (o.getZ() - c.getZ()) * (o.getZ() - c.getZ()) - r * r;
        double discriminant = b * b - 4 * a * c_val;
        if (discriminant < 0) {
            return Double.MAX_VALUE; // no intersection
        } else {
            double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            if (t1 > 0 && t2 > 0) {
                return Math.min(t1, t2);
            } else if (t1 > 0) {
                return t1;
            } else if (t2 > 0) {
                return t2;
            } else {
                return Double.MAX_VALUE; // both intersections are behind the ray origin
            }
        }
    }
    public Point canvasToViewport(int x, int y, int canvasWidth, int canvasHeight, double viewportWidth, double viewportHeight) {
        int viewportX = (int) Math.round((x - canvasWidth / 2.0) * (viewportWidth / canvasWidth));
        int viewportY = (int) Math.round(-(y - canvasHeight / 2.0) * (viewportHeight / canvasHeight));
        return new Point(viewportX, viewportY);
    }
    //this method "paints" things onto the panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    //Driver
    public static void main (String[] args) {
        //creates a frame and sets it's properties
        JFrame frame = new JFrame("Raytracer");
        frame.setSize(1000, 800);
        frame.setLocation(0, 0);

        //tells the java program to exit when the graphics window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //put the panel on the frame and make it visible
        frame.setContentPane(new Raytracer());
        frame.setVisible(true);
        Color x = traceRay(new Point3d(0, 0, 0), new Point3d(0, 0, 0), new Sphere((new Point3d(0, 0, 0)), 10, (new Color(0, 0, 0)), 0));
    }
}
