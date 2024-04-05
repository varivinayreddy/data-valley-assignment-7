import java.util.*;

public class HousingPriceAnalyzer {
    public static void main(String[] args) {
        List<HousingPrice> housingPrices = Arrays.asList(
                new HousingPrice(150000, 1200),
                new HousingPrice(180000, 1300),
                new HousingPrice(220000, 1500),
                new HousingPrice(280000, 1700),
                new HousingPrice(320000, 1900),
                new HousingPrice(190000, 1400),
                new HousingPrice(210000, 1600)
        );

        Map<String, Integer> priceRanges = new LinkedHashMap<>();
        priceRanges.put("$100,000-200,000", 0);
        priceRanges.put("$200,000-300,000", 0);
        priceRanges.put("$300,000-400,000", 0);
        priceRanges.put("$400,000-500,000", 0);

        Map<String, Double> squareFootageSums = new LinkedHashMap<>();
        squareFootageSums.put("$100,000-200,000", 0.0);
        squareFootageSums.put("$200,000-300,000", 0.0);
        squareFootageSums.put("$300,000-400,000", 0.0);
        squareFootageSums.put("$400,000-500,000", 0.0);

        Map<String, Integer> counts = new LinkedHashMap<>();
        counts.put("$100,000-200,000", 0);
        counts.put("$200,000-300,000", 0);
        counts.put("$300,000-400,000", 0);
        counts.put("$400,000-500,000", 0);

        for (HousingPrice price : housingPrices) {
            String range = getPriceRange(price.getPrice());
            priceRanges.put(range, priceRanges.get(range) + 1);
            squareFootageSums.put(range, squareFootageSums.get(range) + price.getSquareFootage());
            counts.put(range, counts.get(range) + 1);
        }

        for (Map.Entry<String, Integer> entry : priceRanges.entrySet()) {
            String range = entry.getKey();
            int count = counts.get(range);
            double avgSquareFootage = squareFootageSums.get(range) / count;
            System.out.println("Number of houses sold in " + range + ": " + entry.getValue());
            System.out.println("Average square footage for " + range + ": " + avgSquareFootage);
        }
    }

    public static String getPriceRange(int price) {
        if (price >= 100000 && price < 200000) {
            return "$100,000-200,000";
        } else if (price >= 200000 && price < 300000) {
            return "$200,000-300,000";
        } else if (price >= 300000 && price < 400000) {
            return "$300,000-400,000";
        } else if (price >= 400000 && price < 500000) {
            return "$400,000-500,000";
        } else {
            return "Unknown";
        }
    }

    static class HousingPrice {
        private int price;
        private int squareFootage;

        public HousingPrice(int price, int squareFootage) {
            this.price = price;
            this.squareFootage = squareFootage;
        }

        public int getPrice() {
            return price;
        }

        public int getSquareFootage() {
            return squareFootage;
        }
    }
}
