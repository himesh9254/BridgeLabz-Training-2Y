import java.util.Scanner;

public class VotingEligibility {
    
    public static int[] generateRandomAges(int numStudents) {
        int[] ages = new int[numStudents];
        for (int i = 0; i < numStudents; i++) {
            ages[i] = (int)(Math.random() * 90) + 10; // Random age between 10-99
        }
        return ages;
    }
    
    public static String[][] checkVotingEligibility(int[] ages) {
        String[][] result = new String[ages.length][2];
        
        for (int i = 0; i < ages.length; i++) {
            result[i][0] = String.valueOf(ages[i]);
            
            if (ages[i] < 0) {
                result[i][1] = "false"; // Negative age cannot vote
            } else if (ages[i] >= 18) {
                result[i][1] = "true";  // Can vote
            } else {
                result[i][1] = "false"; // Cannot vote
            }
        }
        
        return result;
    }
    
    public static void displayTable(String[][] data) {
        System.out.println("Student\tAge\tCan Vote");
        System.out.println("---------------------------");
        
        for (int i = 0; i < data.length; i++) {
            int age = Integer.parseInt(data[i][0]);
            String canVote = data[i][1];
            System.out.println((i + 1) + "\t" + age + "\t" + canVote);
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of students (default 10): ");
        int numStudents = scanner.nextInt();
        
        if (numStudents <= 0) {
            numStudents = 10;
        }
        
        // Generate random ages
        int[] ages = generateRandomAges(numStudents);
        
        // Check voting eligibility
        String[][] eligibilityData = checkVotingEligibility(ages);
        
        System.out.println("\n--- Voting Eligibility Results ---");
        displayTable(eligibilityData);
        
        // Count eligible voters
        int eligibleCount = 0;
        for (int i = 0; i < eligibilityData.length; i++) {
            if (eligibilityData[i][1].equals("true")) {
                eligibleCount++;
            }
        }
        
        System.out.println("\nSummary:");
        System.out.println("Total students: " + numStudents);
        System.out.println("Eligible to vote: " + eligibleCount);
        System.out.println("Not eligible: " + (numStudents - eligibleCount));
        
        scanner.close();
    }
}
