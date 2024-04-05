import java.util.*;

public class EmployeeWorkHoursAnalyzer {
    public static void main(String[] args) {
        List<EmployeeWorkHours> workHoursList = Arrays.asList(
                new EmployeeWorkHours("Employee1", new int[]{8, 8, 8, 8, 8}), // 40 hours
                new EmployeeWorkHours("Employee2", new int[]{7, 7, 7, 7, 7}), // 35 hours
                new EmployeeWorkHours("Employee3", new int[]{9, 9, 9, 9, 9}), // 45 hours
                new EmployeeWorkHours("Employee4", new int[]{6, 6, 6, 6, 6})  // 30 hours
        );

        Map<String, Integer> hoursCategories = new LinkedHashMap<>();
        hoursCategories.put("Less than 40 hours", 0);
        hoursCategories.put("Exactly 40 hours", 0);
        hoursCategories.put("More than 40 hours", 0);

        Map<String, Double> dailyAverageHours = new LinkedHashMap<>();
        dailyAverageHours.put("Less than 40 hours", 0.0);
        dailyAverageHours.put("Exactly 40 hours", 0.0);
        dailyAverageHours.put("More than 40 hours", 0.0);

        Map<String, Integer> counts = new LinkedHashMap<>();
        counts.put("Less than 40 hours", 0);
        counts.put("Exactly 40 hours", 0);
        counts.put("More than 40 hours", 0);

        for (EmployeeWorkHours workHours : workHoursList) {
            int totalHours = Arrays.stream(workHours.getDailyHours()).sum();
            String category = getHoursCategory(totalHours);
            hoursCategories.put(category, hoursCategories.get(category) + 1);
            dailyAverageHours.put(category, dailyAverageHours.get(category) + (double) totalHours / workHours.getDailyHours().length);
            counts.put(category, counts.get(category) + 1);
        }

        for (Map.Entry<String, Integer> entry : hoursCategories.entrySet()) {
            String category = entry.getKey();
            int count = counts.get(category);
            double avgDailyHours = dailyAverageHours.get(category) / count;
            System.out.println("Number of employees with " + category + ": " + entry.getValue());
            System.out.println("Average hours worked per day for " + category + ": " + avgDailyHours);
        }
    }

    public static String getHoursCategory(int totalHours) {
        if (totalHours < 40) {
            return "Less than 40 hours";
        } else if (totalHours == 40) {
            return "Exactly 40 hours";
        } else {
            return "More than 40 hours";
        }
    }

    static class EmployeeWorkHours {
        private String employeeId;
        private int[] dailyHours;

        public EmployeeWorkHours(String employeeId, int[] dailyHours) {
            this.employeeId = employeeId;
            this.dailyHours = dailyHours;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public int[] getDailyHours() {
            return dailyHours;
        }
    }
}
