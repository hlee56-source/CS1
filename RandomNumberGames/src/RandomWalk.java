// made with copilot, just for fun
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWalk {
    private static final int DEFAULT_STEPS = 10000;

    public static void main(String[] args) {
        int steps = args.length == 0 ? DEFAULT_STEPS : Integer.parseInt(args[0]);
        showWalk(steps);
    }

    public static void showWalk(int steps) {
        if (steps < 1) {
            throw new IllegalArgumentException("The number of steps must be positive.");
        }

        Random random = new Random();
        List<Double> xCoordinates = new ArrayList<>();
        List<Double> yCoordinates = new ArrayList<>();
        int x = 0;
        int y = 0;
        int extent = 0;

        xCoordinates.add((double) x);
        yCoordinates.add((double) y);

        for (int step = 0; step < steps; step++) {
            switch (random.nextInt(4)) {
                case 0 -> x++;
                case 1 -> x--;
                case 2 -> y++;
                default -> y--;
            }
            xCoordinates.add((double) x);
            yCoordinates.add((double) y);
            extent = Math.max(extent, Math.max(Math.abs(x), Math.abs(y)));
        }

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("2D Random Walk (" + steps + " steps)")
                .xAxisTitle("X position")
                .yAxisTitle("Y position")
                .build();
        XYSeries path = chart.addSeries("Path", xCoordinates, yCoordinates);
        path.setMarker(SeriesMarkers.NONE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setXAxisMin((double) -extent - 1);
        chart.getStyler().setXAxisMax((double) extent + 1);
        chart.getStyler().setYAxisMin((double) -extent - 1);
        chart.getStyler().setYAxisMax((double) extent + 1);

        new SwingWrapper<>(chart).displayChart();
    }
}
