import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Raytracer extends JPanel {
    private final java.util.List<Sphere> spheres = new java.util.ArrayList<>();
    private static final Point3d CAMERA_POSITION = new Point3d(0, 0, 0);
    private static final Point3d LIGHT_POSITION = new Point3d(-2, 3, 4);
    private static final double VIEWPORT_WIDTH = 2.8;
    private static final double VIEWPORT_HEIGHT = 2.1;
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 800;

    public Raytracer() {
        spheres.add(new Sphere(new Point3d(-2.0, 0.4, 5.5), 1.1, Color.RED, 0.9));
        spheres.add(new Sphere(new Point3d(0.0, -0.2, 6.0), 1.2, Color.GREEN, 0.8));
        spheres.add(new Sphere(new Point3d(2.2, 0.5, 5.3), 1.0, Color.BLUE, 0.8));
        spheres.add(new Sphere(new Point3d(-2, 3, 4), 0.1, Color.WHITE, 0));
    }

    public void setPixel(Graphics g, Color c, int x, int y) {
        g.setColor(c);
        g.drawLine(x, y, x+1, y+1);
    }

    public Color traceRay(Point3d O, Point3d D) {
        double closest_t = Double.MAX_VALUE;
        Sphere closest_sphere = null;
        Point3d rayDirection = D.normalize();

        for (Sphere sphere : spheres) {
            double t = intersectRaySphere(O, rayDirection, sphere);
            if (t > 0 && t < closest_t) {
                closest_t = t;
                closest_sphere = sphere;
            }
        }

        if (closest_sphere == null) {
            return Color.BLACK;
        }

        Point3d hitPoint = O.add(rayDirection.scale(closest_t));
        Point3d normal = hitPoint.subtract(closest_sphere.getCenter()).normalize();
        Point3d lightDirection = LIGHT_POSITION.subtract(hitPoint).normalize();
        Point3d viewDirection = CAMERA_POSITION.subtract(hitPoint).normalize();

        double diffuse = Math.max(0.0, normal.dot(lightDirection));
        if (isInShadow(hitPoint, lightDirection)) {
            diffuse = 0.0;
        }

        Point3d reflected = reflect(lightDirection.scale(-1), normal);
        double specular = Math.pow(Math.max(0.0, reflected.dot(viewDirection)), 32)
                * closest_sphere.getReflectivity();
        double brightness = 0.15 + diffuse + specular;
        brightness = Math.min(1.0, brightness);

        Color base = closest_sphere.getColor();
        int red = clamp((int) (base.getRed() * brightness));
        int green = clamp((int) (base.getGreen() * brightness));
        int blue = clamp((int) (base.getBlue() * brightness));
        return new Color(red, green, blue);
    }

    private boolean isInShadow(Point3d hitPoint, Point3d lightDirection) {
        Point3d shadowOrigin = hitPoint.add(lightDirection.scale(0.0001));
        for (Sphere sphere : spheres) {
            double t = intersectRaySphere(shadowOrigin, lightDirection, sphere);
            if (t > 0 && t < 1.0) {
                return true;
            }
        }
        return false;
    }

    private Point3d reflect(Point3d incoming, Point3d normal) {
        return incoming.subtract(normal.scale(2.0 * normal.dot(incoming)));
    }

    private int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }

    public double intersectRaySphere(Point3d o, Point3d d, Sphere sphere) {
        double r = sphere.getRadius();
        Point3d c = sphere.getCenter();
        double a = d.getX() * d.getX() + d.getY() * d.getY() + d.getZ() * d.getZ();
        if (a == 0) {
            return Double.MAX_VALUE;
        }

        double b = 2 * (d.getX() * (o.getX() - c.getX())
                + d.getY() * (o.getY() - c.getY())
                + d.getZ() * (o.getZ() - c.getZ()));
        double c_val = (o.getX() - c.getX()) * (o.getX() - c.getX())
                + (o.getY() - c.getY()) * (o.getY() - c.getY())
                + (o.getZ() - c.getZ()) * (o.getZ() - c.getZ())
                - r * r;
        double discriminant = b * b - 4 * a * c_val;
        if (discriminant < 0) {
            return Double.MAX_VALUE;
        }

        double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);
        if (t1 > 0 && t2 > 0) {
            return Math.min(t1, t2);
        }
        if (t1 > 0) {
            return t1;
        }
        if (t2 > 0) {
            return t2;
        }
        return Double.MAX_VALUE;
    }

    public Point3d canvasToViewport(int x, int y, int canvasWidth, int canvasHeight,
                                   double viewportWidth, double viewportHeight) {
        double viewportX = (x - canvasWidth / 2.0) * (viewportWidth / canvasWidth);
        double viewportY = -(y - canvasHeight / 2.0) * (viewportHeight / canvasHeight);
        return new Point3d(viewportX, viewportY, 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point3d direction = canvasToViewport(x, y, width, height, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
                setPixel(g, traceRay(CAMERA_POSITION, direction), x, y);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Raytracer");
            frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            frame.setLocation(0, 0);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new Raytracer());
            frame.setVisible(true);
        });
    }
}
