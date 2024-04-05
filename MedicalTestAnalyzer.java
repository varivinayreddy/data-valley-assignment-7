import java.util.*;

public class MedicalTestAnalyzer {
    public static void main(String[] args) {
        List<MedicalTestResult> testResults = Arrays.asList(
                new MedicalTestResult("Patient1", 80),
                new MedicalTestResult("Patient2", 95),
                new MedicalTestResult("Patient3", 110),
                new MedicalTestResult("Patient4", 70),
                new MedicalTestResult("Patient5", 105),
                new MedicalTestResult("Patient6", 90)
        );

        Map<String, Integer> resultCategories = new LinkedHashMap<>();
        resultCategories.put("Normal", 0);
        resultCategories.put("Borderline", 0);
        resultCategories.put("High", 0);

        Map<String, Double> resultSums = new LinkedHashMap<>();
        resultSums.put("Normal", 0.0);
        resultSums.put("Borderline", 0.0);
        resultSums.put("High", 0.0);

        Map<String, Integer> counts = new LinkedHashMap<>();
        counts.put("Normal", 0);
        counts.put("Borderline", 0);
        counts.put("High", 0);

        for (MedicalTestResult result : testResults) {
            String category = getResultCategory(result.getValue());
            resultCategories.put(category, resultCategories.get(category) + 1);
            resultSums.put(category, resultSums.get(category) + result.getValue());
            counts.put(category, counts.get(category) + 1);
        }

        for (Map.Entry<String, Integer> entry : resultCategories.entrySet()) {
            String category = entry.getKey();
            int count = counts.get(category);
            double avgValue = resultSums.get(category) / count;
            System.out.println("Number of patients with " + category + " results: " + entry.getValue());
            System.out.println("Average value for " + category + " results: " + avgValue);
        }
    }

    public static String getResultCategory(int value) {
        if (value < 90) {
            return "Normal";
        } else if (value < 100) {
            return "Borderline";
        } else {
            return "High";
        }
    }

    static class MedicalTestResult {
        private String patientId;
        private int value;

        public MedicalTestResult(String patientId, int value) {
            this.patientId = patientId;
            this.value = value;
        }

        public String getPatientId() {
            return patientId;
        }

        public int getValue() {
            return value;
        }
    }
}
