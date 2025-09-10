import java.util.Scanner;

public class YoungestTallestFriends {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Friend names
        String[] friends = {"Amar", "Akbar", "Anthony"};
        
        // Arrays to store ages and heights
        int[] ages = new int[3];
        double[] heights = new double[3];
        
        System.out.println("=== FRIEND DATA COLLECTION ===");
        System.out.println("Enter data for 3 friends: Amar, Akbar, and Anthony");
        System.out.println();
        
        // Take input for ages and heights
        for (int i = 0; i < 3; i++) {
            System.out.println("Friend " + (i + 1) + " - " + friends[i] + ":");
            
            System.out.print("Enter age: ");
            ages[i] = scanner.nextInt();
            
            System.out.print("Enter height (in cm): ");
            heights[i] = scanner.nextDouble();
            
            System.out.println();
        }
        
        // Find youngest friend
        int youngestIndex = 0;
        for (int i = 1; i < ages.length; i++) {
            if (ages[i] < ages[youngestIndex]) {
                youngestIndex = i;
            }
        }
        
        // Find tallest friend
        int tallestIndex = 0;
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] > heights[tallestIndex]) {
                tallestIndex = i;
            }
        }
        
        // Display all friend data
        System.out.println("=== FRIEND DATA SUMMARY ===");
        System.out.println("============================");
        System.out.printf("%-10s %-8s %-10s%n", "Friend", "Age", "Height(cm)");
        System.out.println("----------------------------");
        
        for (int i = 0; i < 3; i++) {
            System.out.printf("%-10s %-8d %-10.1f%n", friends[i], ages[i], heights[i]);
        }
        
        // Display results
        System.out.println("\\n=== ANALYSIS RESULTS ===");
        System.out.println("========================");
        System.out.println("Youngest Friend: " + friends[youngestIndex] + 
                          " (Age: " + ages[youngestIndex] + " years)");
        System.out.println("Tallest Friend:  " + friends[tallestIndex] + 
                          " (Height: " + heights[tallestIndex] + " cm)");
        
        // Additional statistics
        System.out.println("\\n=== ADDITIONAL STATISTICS ===");
        double avgAge = (ages[0] + ages[1] + ages[2]) / 3.0;
        double avgHeight = (heights[0] + heights[1] + heights[2]) / 3.0;
        
        System.out.printf("Average Age: %.1f years%n", avgAge);
        System.out.printf("Average Height: %.1f cm%n", avgHeight);
        
        // Age difference
        int oldestAge = ages[0];
        for (int i = 1; i < ages.length; i++) {
            if (ages[i] > oldestAge) {
                oldestAge = ages[i];
            }
        }
        System.out.println("Age difference between oldest and youngest: " + 
                          (oldestAge - ages[youngestIndex]) + " years");
        
        // Height difference
        double shortestHeight = heights[0];
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] < shortestHeight) {
                shortestHeight = heights[i];
            }
        }
        System.out.printf("Height difference between tallest and shortest: %.1f cm%n", 
                         (heights[tallestIndex] - shortestHeight));
        
        scanner.close();
    }
}
