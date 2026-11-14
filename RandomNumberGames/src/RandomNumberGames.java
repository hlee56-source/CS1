import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class RandomNumberGames {
    public static int guessMyNumber(int min, int max){
        return (int)(Math.random() * (max - min)) + min;
    }
    public static void diceGame() {
        int randomNum = (int) (Math.random() * 11) + 1;
        if (randomNum < 7) {
            IO.println("You lose :(");
        } else if (randomNum == 7) {
            IO.println("SPECIAL PRIZE!!!");
        } else {
            IO.println("You won! :)");
        }
    }
    public static void flipCoins(int coins) {
        int heads = 0;
        int tails = 0;
        for (int i = 0; i < coins; i++) {
            IO.println(i);
            int coin = (int) (Math.random() + 0.5);
            if (coin == 0) {
                tails++;
            } else {
                heads++;
            }
        }
        IO.println("Heads: " + heads + "\nTails: " + tails);
    }
    public static void makeProbabilityGraphOfCoins(int coins){ //graph probability of landing heads using xchart. Just for fun 😊
        // this should move close to 0.5 as more trials get done.
        List<Double> probability = new ArrayList<>();
        List<Double> index = new ArrayList<>();
        double heads = 0;
        double tails = 0;
        for (int i = 0; i < coins; i++) {
            IO.println(i);
            int coin = (int) (Math.random() + 0.5);
            if (coin == 0) {
                tails++;
            } else {
                heads++;
            }
            index.add((double)i);
            probability.add(heads/(heads + tails));
        }
        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Probability vs Amount of Trials")
                .xAxisTitle("Probability of heads")
                .yAxisTitle("amount of trials")
                .build();
        chart.addSeries("Probability vs trials", index, probability);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setYAxisMax(1.0);
        new SwingWrapper<>(chart).displayChart();
    }
    public static void coinGame(){
        int coin1 = 0;
        int coin2 = 0;
        while (coin1 < 3 && coin2 < 3) {
            int coinFlip = flipCoin();
            if (coinFlip == 1) {
                IO.println("player 1's coin was Heads!");
                coin1 ++;
            } else {
                IO.println("Player 1's coin was tails");
                coin1 = 0;
            }
            coinFlip = flipCoin();
            if (coinFlip == 1) {
                IO.println("player 2's coin was Heads!");
                coin2 ++;
            } else {
                IO.println("Player 2's coin was tails");
                coin2 = 0;
            }

        }
    }
    public static int flipCoin(){
        return (int) (Math.random() + 0.5);
    }
    public static void main(){
        coinGame();
        flipCoins(100000);
        makeProbabilityGraphOfCoins(100000);
    }
}
