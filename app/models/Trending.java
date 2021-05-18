package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Trending model calculates the trending values that
 * are displayed on the WeatherTop application
 *
 * @author Patrick Marnane
 * @version 1.0
 * @since 2021-05-17
 */
public class Trending {
    public String tempTrend;
    public String windTrend;
    public String pressTrend;

    public static Trending trendingVals(List<Reading> readings) {
        Trending trendingVals = new Trending();
        int i = readings.size();
        List<Double> tempList = new ArrayList<Double>();
        List<Double> windList = new ArrayList<Double>();
        List<Integer> pressList = new ArrayList<Integer>();
        Double tempAvg = 0.0;
        Double windAvg = 0.0;
        Double pressAvg = 0.0;

        if (i > 3) {
            List<Reading> subReadings = readings.subList(i - 3, i - 1);
            for (Reading reading : subReadings) {
                tempList.add(reading.temperature);
                windList.add(reading.windSpeed);
                pressList.add(reading.pressure);
            }

            tempAvg = tempList.stream().mapToDouble(value -> value).average().orElse(0.0);
            windAvg = windList.stream().mapToDouble(value -> value).average().orElse(0.0);
            pressAvg = pressList.stream().mapToDouble(value -> value).average().orElse(0.0);

            if (tempAvg > readings.get(i - 4).temperature) {
                trendingVals.tempTrend = "arrow up icon";
            } else if (tempAvg < readings.get(i - 4).temperature) {
                trendingVals.tempTrend = "arrow down icon";
            } else {
                trendingVals.tempTrend = "arrows alternate horizontal icon";
            }

            if (windAvg > readings.get(i - 4).windSpeed) {
                trendingVals.windTrend = "arrow up icon";
            } else if (windAvg < readings.get(i - 4).windSpeed) {
                trendingVals.windTrend = "arrow down icon";
            } else {
                trendingVals.windTrend = "arrows alternate horizontal icon";
            }

            if (pressAvg > readings.get(i - 4).pressure) {
                trendingVals.pressTrend = "arrow up icon";
            } else if (pressAvg < readings.get(i - 4).pressure) {
                trendingVals.pressTrend = "arrow down icon";
            } else {
                trendingVals.pressTrend = "arrows alternate horizontal icon";
            }
        }
        return trendingVals;
    }
}
