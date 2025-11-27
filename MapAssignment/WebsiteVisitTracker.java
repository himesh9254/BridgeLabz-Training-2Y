import java.util.*;

public class WebsiteVisitTracker {
    private Map<String, Integer> pageVisits;
    private int totalVisits;
    
    public WebsiteVisitTracker() {
        pageVisits = new HashMap<>();
        totalVisits = 0;
    }
    
    public void recordVisit(String pageName) {
        pageVisits.put(pageName, pageVisits.getOrDefault(pageName, 0) + 1);
        totalVisits++;
    }
    
    public void recordMultipleVisits(String pageName, int count) {
        pageVisits.put(pageName, pageVisits.getOrDefault(pageName, 0) + count);
        totalVisits += count;
        System.out.println("Recorded " + count + " visits to: " + pageName);
    }
    
    public int getVisitCount(String pageName) {
        return pageVisits.getOrDefault(pageName, 0);
    }
    
    public void displayPageVisits(String pageName) {
        int visits = getVisitCount(pageName);
        if (visits > 0) {
            double percentage = (visits * 100.0) / totalVisits;
            System.out.printf("%s: %d visits (%.2f%% of total)%n", pageName, visits, percentage);
        } else {
            System.out.println(pageName + ": No visits recorded");
        }
    }
    
    public void displayAllPages() {
        System.out.println("\n=== All Page Visit Counts ===");
        TreeMap<String, Integer> sorted = new TreeMap<>(pageVisits);
        
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            double percentage = (entry.getValue() * 100.0) / totalVisits;
            System.out.printf("%s: %d visits (%.2f%%)%n", 
                entry.getKey(), entry.getValue(), percentage);
        }
    }
    
    public void displaySortedByVisits() {
        System.out.println("\n=== Pages Sorted by Visits (Most Popular First) ===");
        
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(pageVisits.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        int rank = 1;
        for (Map.Entry<String, Integer> entry : sorted) {
            double percentage = (entry.getValue() * 100.0) / totalVisits;
            System.out.printf("%d. %s: %d visits (%.2f%%)%n", 
                rank++, entry.getKey(), entry.getValue(), percentage);
        }
    }
    
    public String getMostVisitedPage() {
        String mostVisited = null;
        int maxVisits = 0;
        
        for (Map.Entry<String, Integer> entry : pageVisits.entrySet()) {
            if (entry.getValue() > maxVisits) {
                maxVisits = entry.getValue();
                mostVisited = entry.getKey();
            }
        }
        
        return mostVisited;
    }
    
    public String getLeastVisitedPage() {
        String leastVisited = null;
        int minVisits = Integer.MAX_VALUE;
        
        for (Map.Entry<String, Integer> entry : pageVisits.entrySet()) {
            if (entry.getValue() < minVisits) {
                minVisits = entry.getValue();
                leastVisited = entry.getKey();
            }
        }
        
        return leastVisited;
    }
    
    public void displayStatistics() {
        System.out.println("\n=== Website Statistics ===");
        System.out.println("Total Pages: " + pageVisits.size());
        System.out.println("Total Visits: " + totalVisits);
        
        if (!pageVisits.isEmpty()) {
            double avgVisits = (double) totalVisits / pageVisits.size();
            System.out.printf("Average Visits per Page: %.2f%n", avgVisits);
            
            String mostVisited = getMostVisitedPage();
            String leastVisited = getLeastVisitedPage();
            
            System.out.println("Most Visited: " + mostVisited + 
                " (" + pageVisits.get(mostVisited) + " visits)");
            System.out.println("Least Visited: " + leastVisited + 
                " (" + pageVisits.get(leastVisited) + " visits)");
        }
    }
    
    public static void main(String[] args) {
        WebsiteVisitTracker tracker = new WebsiteVisitTracker();
        
        System.out.println("=== Recording Page Visits ===");
        tracker.recordMultipleVisits("/home", 1500);
        tracker.recordMultipleVisits("/products", 1200);
        tracker.recordMultipleVisits("/about", 450);
        tracker.recordMultipleVisits("/contact", 300);
        tracker.recordMultipleVisits("/blog", 800);
        tracker.recordMultipleVisits("/cart", 650);
        tracker.recordMultipleVisits("/checkout", 200);
        tracker.recordMultipleVisits("/faq", 180);
        
        tracker.displayAllPages();
        tracker.displaySortedByVisits();
        
        System.out.println("\n=== Individual Page Lookup ===");
        tracker.displayPageVisits("/home");
        tracker.displayPageVisits("/products");
        tracker.displayPageVisits("/login");
        
        tracker.displayStatistics();
        
        System.out.println("\n=== Simulating New Visits ===");
        String[] visits = {"/home", "/products", "/cart", "/checkout", "/home", 
                          "/blog", "/products", "/home", "/about", "/products"};
        
        for (String page : visits) {
            tracker.recordVisit(page);
        }
        System.out.println("Recorded " + visits.length + " new visits");
        
        tracker.displaySortedByVisits();
        tracker.displayStatistics();
    }
}
