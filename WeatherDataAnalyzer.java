import java.util.*;

public class WeatherDataAnalyzer {
    public static void main(String[] args) {
        List<WeatherData> weatherDataList = Arrays.asList(
                new WeatherData(5, 60),
                new WeatherData(15, 70),
                new WeatherData(25, 80),
                new WeatherData(-5, 50),
                new WeatherData(10, 65),
                new WeatherData(20, 75),
                new WeatherData(-10, 45)
        );

        Map<String, Integer> temperatureRanges = new LinkedHashMap<>();
        temperatureRanges.put("<0°C", 0);
        temperatureRanges.put("0-10°C", 0);
        temperatureRanges.put("10-20°C", 0);
        temperatureRanges.put("20-30°C", 0);
        temperatureRanges.put(">=30°C", 0);

        Map<String, Double> humiditySums = new LinkedHashMap<>();
        humiditySums.put("<0°C", 0.0);
        humiditySums.put("0-10°C", 0.0);
        humiditySums.put("10-20°C", 0.0);
        humiditySums.put("20-30°C", 0.0);
        humiditySums.put(">=30°C", 0.0);

        Map<String, Integer> counts = new LinkedHashMap<>();
        counts.put("<0°C", 0);
        counts.put("0-10°C", 0);
        counts.put("10-20°C", 0);
        counts.put("20-30°C", 0);
        counts.put(">=30°C", 0);

        for (WeatherData data : weatherDataList) {
            String range = getTemperatureRange(data.getTemperature());
            temperatureRanges.put(range, temperatureRanges.get(range) + 1);
            humiditySums.put(range, humiditySums.get(range) + data.getHumidity());
            counts.put(range, counts.get(range) + 1);
        }

        for (Map.Entry<String, Integer> entry : temperatureRanges.entrySet()) {
            String range = entry.getKey();
            int count = counts.get(range);
            double avgHumidity = humiditySums.get(range) / count;
            System.out.println("Number of days with temperature " + range + ": " + entry.getValue());
            System.out.println("Average humidity for " + range + ": " + avgHumidity);
        }
    }

    public static String getTemperatureRange(int temperature) {
        if (temperature < 0) {
            return "<0°C";
        } else if (temperature < 10) {
            return "0-10°C";
        } else if (temperature < 20) {
            return "10-20°C";
        } else if (temperature < 30) {
            return "20-30°C";
        } else {
            return ">=30°C";
        }
    }

    static class WeatherData {
        private int temperature;
        private int humidity;

        public WeatherData(int temperature, int humidity) {
            this.temperature = temperature;
            this.humidity = humidity;
        }

        public int getTemperature() {
            return temperature;
        }

        public int getHumidity() {
            return humidity;
        }
    }
}
