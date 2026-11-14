public class Point3d {
    private double x = 0;
    private double y = 0;
    private double z = 0;

    public Point3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3d() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Point3d add(Point3d other) {
        return new Point3d(x + other.x, y + other.y, z + other.z);
    }

    public Point3d subtract(Point3d other) {
        return new Point3d(x - other.x, y - other.y, z - other.z);
    }

    public Point3d scale(double scalar) {
        return new Point3d(x * scalar, y * scalar, z * scalar);
    }

    public double dot(Point3d other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Point3d normalize() {
        double len = length();
        if (len == 0) {
            return new Point3d(0, 0, 0);
        }
        return scale(1.0 / len);
    }
}
