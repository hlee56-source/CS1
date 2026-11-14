// fair warning, I didn't make this, I asked copilot
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.None;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RealTimeProjectile {

    public static final double DEFAULT_GRAVITY = -9.8;
    public static final double DEFAULT_DT = 0.1;

    private final double gravity;
    private final double initialHeight;
    private double dt;

    private double x;
    private double y;
    private final double vx;
    private final double vy;
    private long animationStep = 0;

    private final List<Double> xData = new ArrayList<>();
    private final List<Double> yData = new ArrayList<>();

    private XYChart chart;
    private XChartPanel<XYChart> panel;

    public RealTimeProjectile(double v0, double angleDeg) {
        this(v0, angleDeg, DEFAULT_GRAVITY, 0.0, DEFAULT_DT);
    }

    public RealTimeProjectile(double v0, double angleDeg, double gravity, double initialHeight) {
        this(v0, angleDeg, gravity, initialHeight, DEFAULT_DT);
    }

    public RealTimeProjectile(double v0, double angleDeg, double gravity, double initialHeight, double dt) {
        this.gravity = gravity;
        this.initialHeight = initialHeight;
        this.dt = Math.max(0.001, dt);

        double angleRad = Math.toRadians(angleDeg);
        vx = v0 * Math.cos(angleRad);
        vy = v0 * Math.sin(angleRad);
        x = 0;
        y = initialHeight;

        xData.add(0.0);
        yData.add(initialHeight);
    }

    public double calculateV0X(double v0, double angleDegrees) {
        return v0 * Math.cos(Math.toRadians(angleDegrees));
    }

    public double calculateV0Y(double v0, double angleDegrees) {
        return v0 * Math.sin(Math.toRadians(angleDegrees));
    }

    public double calculateX(double t, double v0x) {
        return v0x * t;
    }

    public double calculateY(double t, double v0y, double gravity) {
        return v0y * t + 0.5 * gravity * (t * t) + initialHeight;
    }

    private static double timeAtStep(long step, double interval) {
        return BigDecimal.valueOf(interval).multiply(BigDecimal.valueOf(step)).doubleValue();
    }

    private double calculateFlightTime(double v0y) {
        double a = 0.5 * gravity;
        double b = v0y;
        double c = initialHeight;

        if (Math.abs(a) < 1e-12) {
            return Math.max(0.0, -c / b);
        }

        double discriminant = (b * b) - (4.0 * a * c);
        if (discriminant < 0) {
            return 0.0;
        }

        double sqrtDiscriminant = Math.sqrt(discriminant);
        double t1 = (-b - sqrtDiscriminant) / (2.0 * a);
        double t2 = (-b + sqrtDiscriminant) / (2.0 * a);

        double positiveTime = Math.max(t1, t2);
        return positiveTime > 0 ? positiveTime : 0.0;
    }

    public void calculateProjectile(double v0, double angleDegrees) {
        double v0x = calculateV0X(v0, angleDegrees);
        double v0y = calculateV0Y(v0, angleDegrees);
        double totalTime = calculateFlightTime(v0y);
        int stepCount = (int) Math.ceil(totalTime / dt) + 1;

        List<Double> pathX = new ArrayList<>();
        List<Double> pathY = new ArrayList<>();

        for (int step = 0; step <= stepCount; step++) {
            double t = Math.min(step * dt, totalTime);
            double xPos = calculateX(t, v0x);
            double yPos = calculateY(t, v0y, gravity);
            pathX.add(xPos);
            pathY.add(yPos);

            if (t >= totalTime) {
                break;
            }
        }

        XYChart projectileChart = QuickChart.getChart("Projectile motion", "X", "Y", "Object motion", pathX, pathY);
        new SwingWrapper<>(projectileChart).displayChart();
    }

    public double findX(double v0, double angleDegrees) {
        double v0x = calculateV0X(v0, angleDegrees);
        double v0y = calculateV0Y(v0, angleDegrees);
        double totalTime = calculateFlightTime(v0y);
        return calculateX(totalTime, v0x);
    }

    public int findBestAngle(double v0, double angleStart, double angleEnd) {
        double bestX = 0;
        double bestAngle = 0;

        for (int angle = (int) angleStart; angle < (int) angleEnd; angle++) {
            double xAtAngle = findX(v0, angle);
            if (xAtAngle > bestX) {
                bestX = xAtAngle;
                bestAngle = angle;
            }
        }

        calculateProjectile(v0, bestAngle);
        return (int) bestAngle;
    }

    public void graphOptimalAngle(double v0) {
        List<Double> angles = new ArrayList<>();
        List<Double> distances = new ArrayList<>();

        for (double angle = 0; angle < 90; angle += 1) {
            angles.add(angle);
            distances.add(findX(v0, angle));
        }

        XYChart scatterChart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Launch Angle vs Horizontal Distance")
                .xAxisTitle("Launch Angle (degrees)")
                .yAxisTitle("Horizontal Distance (meters)")
                .build();

        scatterChart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        scatterChart.getStyler().setChartTitleVisible(true);
        scatterChart.getStyler().setLegendVisible(true);
        scatterChart.addSeries("Launch Angle vs Horizontal Distance\nWith V0 = " + v0 + " m/s", angles, distances);
        new SwingWrapper<>(scatterChart).displayChart();
    }

    public void start() {
        chart = new XYChart(600, 400);
        chart.setTitle("Real-Time Projectile Motion");
        chart.setXAxisTitle("X");
        chart.setYAxisTitle("Y");
        chart.getStyler().setMarkerSize(6);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        chart.addSeries("trajectory", xData, yData).setMarker(new None());

        panel = new XChartPanel<>(chart);
        JFrame frame = new JFrame("XChart Real-Time Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        double landingTime = calculateFlightTime(vy);
        double timeStep = Math.clamp(landingTime / 200.0, 0.001, 0.03);
        animationStep = 0;

        Timer timer = new Timer(30, e -> {
            double elapsedTime = timeAtStep(++animationStep, timeStep);
            x = calculateX(elapsedTime, vx);
            y = calculateY(elapsedTime, vy, gravity);

            if (elapsedTime >= landingTime) {
                x = calculateX(landingTime, vx);
                y = 0;
                xData.add(x);
                yData.add(y);
                chart.updateXYSeries("trajectory", xData, yData, null);
                panel.repaint();
                IO.println("time: " + landingTime + " seconds. Distance: " + x + " m");
                ((Timer) e.getSource()).stop();
                return;
            }

            xData.add(x);
            yData.add(y);
            chart.updateXYSeries("trajectory", xData, yData, null);
            panel.repaint();
            IO.println("time: " + elapsedTime + " seconds. Distance: " + x + " m");
        });

        timer.start();

    }

    public static void main() {
        int choice1 = JOptionPane.showConfirmDialog(null, "Do you want to output a scatter plot of IV vs DV?", "Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        double v0 = Double.parseDouble(JOptionPane.showInputDialog("Please enter an initial velocity in m/s"));

        if (choice1 == JOptionPane.NO_OPTION) {
            double gravity;
            double initialHeight;

            try {
                gravity = Double.parseDouble(JOptionPane.showInputDialog("Please describe your planet's gravity in m/s^2\nEnter nothing for Earth"));
            } catch (Exception ignored) {
                gravity = DEFAULT_GRAVITY;
            }

            try {
                initialHeight = Double.parseDouble(JOptionPane.showInputDialog("Please describe the launch height in meters\nEnter nothing for a flat launch"));
            } catch (Exception ignored) {
                initialHeight = 0.0;
            }

            if (gravity >= 0) {
                JOptionPane.showMessageDialog(null, "Gravity needs to be negative, setting to Earth's gravity of -9.8");
                gravity = DEFAULT_GRAVITY;
            }

            int choice = JOptionPane.showConfirmDialog(null, "Do you want to run a simulation instead of finding the best launch angle", "Options", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                double angleDegrees = Double.parseDouble(JOptionPane.showInputDialog("Please enter an initial angle in degrees"));
                RealTimeProjectile sim = new RealTimeProjectile(v0, angleDegrees, gravity, initialHeight, DEFAULT_DT);
                sim.start();
            } else {
                RealTimeProjectile sim = new RealTimeProjectile(v0, 45, gravity, initialHeight, DEFAULT_DT);
                String angle = String.valueOf(sim.findBestAngle(v0, 1, 90));
            }
        } else {
            RealTimeProjectile sim = new RealTimeProjectile(v0, 45, DEFAULT_GRAVITY, 0.0, DEFAULT_DT);
            sim.graphOptimalAngle(v0);

        }
    }
}