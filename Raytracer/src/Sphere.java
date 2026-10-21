import java.awt.*;

public class Sphere {
    private Point3d center;
    private double radius;
    private Color color;
    private double reflectivity;
    public Sphere(Point3d center, double radius, Color color, double reflectivity) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.reflectivity = reflectivity;
    }

    public Point3d getCenter() {
        return center;
    }

    public void setCenter(Point3d center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(double reflectivity) {
        this.reflectivity = reflectivity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
