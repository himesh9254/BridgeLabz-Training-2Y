import java.util.*;

public class CustomerFeedbackAnalysis {
    private List<String> allFeedback;
    private Set<String> uniqueFeedback;
    private Queue<String> processingQueue;
    private Stack<String> recentFeedback;

    public CustomerFeedbackAnalysis() {
        allFeedback = new ArrayList<>();
        uniqueFeedback = new LinkedHashSet<>();
        processingQueue = new LinkedList<>();
        recentFeedback = new Stack<>();
    }

    public void addFeedback(String feedback) {
        allFeedback.add(feedback);
        System.out.println("Feedback received: \"" + truncate(feedback, 50) + "\"");
    }

    public void removeDuplicates() {
        System.out.println("\n=== Removing Duplicate Feedback ===");
        int originalCount = allFeedback.size();

        for (String feedback : allFeedback) {
            if (!uniqueFeedback.add(feedback)) {
                System.out.println("Duplicate removed: \"" + truncate(feedback, 30) + "\"");
            }
        }

        System.out.println("Original count: " + originalCount);
        System.out.println("Unique count: " + uniqueFeedback.size());
        System.out.println("Duplicates removed: " + (originalCount - uniqueFeedback.size()));
    }

    public void queueForProcessing() {
        System.out.println("\n=== Queueing Unique Feedback for Processing ===");
        for (String feedback : uniqueFeedback) {
            processingQueue.add(feedback);
        }
        System.out.println("Queued " + processingQueue.size() + " feedback items");
    }

    public void processFeedback() {
        System.out.println("\n=== Processing Feedback (FIFO) ===");
        int count = 1;

        while (!processingQueue.isEmpty()) {
            String feedback = processingQueue.poll();
            recentFeedback.push(feedback);
            System.out.println(count + ". Processed: \"" + truncate(feedback, 40) + "\"");
            count++;
        }
    }

    public void displayRecentFeedback(int count) {
        System.out.println("\n=== Last " + count + " Processed Feedback (Most Recent First) ===");
        
        if (recentFeedback.isEmpty()) {
            System.out.println("  No feedback processed yet");
            return;
        }

        Stack<String> temp = new Stack<>();
        temp.addAll(recentFeedback);

        int displayed = 0;
        while (!temp.isEmpty() && displayed < count) {
            System.out.println((displayed + 1) + ". \"" + truncate(temp.pop(), 50) + "\"");
            displayed++;
        }
    }

    public void displayAllFeedback() {
        System.out.println("\n=== All Received Feedback (" + allFeedback.size() + ") ===");
        int count = 1;
        for (String feedback : allFeedback) {
            System.out.println(count + ". \"" + truncate(feedback, 50) + "\"");
            count++;
        }
    }

    public void displayUniqueFeedback() {
        System.out.println("\n=== Unique Feedback (" + uniqueFeedback.size() + ") ===");
        int count = 1;
        for (String feedback : uniqueFeedback) {
            System.out.println(count + ". \"" + truncate(feedback, 50) + "\"");
            count++;
        }
    }

    public void analyzeFeedback() {
        System.out.println("\n=== Feedback Analysis ===");
        
        Map<String, Integer> keywordCount = new HashMap<>();
        String[] keywords = {"great", "good", "excellent", "bad", "poor", "improve", "love", "hate"};

        for (String feedback : uniqueFeedback) {
            String lowerFeedback = feedback.toLowerCase();
            for (String keyword : keywords) {
                if (lowerFeedback.contains(keyword)) {
                    keywordCount.put(keyword, keywordCount.getOrDefault(keyword, 0) + 1);
                }
            }
        }

        System.out.println("Keyword frequency in feedback:");
        for (Map.Entry<String, Integer> entry : keywordCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }

        int positive = keywordCount.getOrDefault("great", 0) + 
                      keywordCount.getOrDefault("good", 0) + 
                      keywordCount.getOrDefault("excellent", 0) +
                      keywordCount.getOrDefault("love", 0);
        int negative = keywordCount.getOrDefault("bad", 0) + 
                      keywordCount.getOrDefault("poor", 0) +
                      keywordCount.getOrDefault("hate", 0);

        System.out.println("\nSentiment Summary:");
        System.out.println("  Positive mentions: " + positive);
        System.out.println("  Negative mentions: " + negative);
    }

    private String truncate(String text, int maxLength) {
        if (text.length() <= maxLength) return text;
        return text.substring(0, maxLength - 3) + "...";
    }

    public static void main(String[] args) {
        CustomerFeedbackAnalysis system = new CustomerFeedbackAnalysis();

        System.out.println("=== Collecting Feedback ===");
        system.addFeedback("Great product! I love it.");
        system.addFeedback("Good quality but shipping was slow.");
        system.addFeedback("Excellent customer service!");
        system.addFeedback("Great product! I love it.");
        system.addFeedback("Poor packaging, item was damaged.");
        system.addFeedback("Could improve the mobile app experience.");
        system.addFeedback("Good quality but shipping was slow.");
        system.addFeedback("Bad experience with returns.");
        system.addFeedback("Love the new features!");
        system.addFeedback("Excellent value for money.");

        system.displayAllFeedback();
        system.removeDuplicates();
        system.displayUniqueFeedback();
        system.queueForProcessing();
        system.processFeedback();
        system.displayRecentFeedback(5);
        system.analyzeFeedback();
    }
}
