import java.util.Scanner;

public class VotingEligibility {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] ages = new int[10];
        
        System.out.println("Enter ages of 10 students:");
        
        // Take input for all 10 students
        for (int i = 0; i < ages.length; i++) {
            System.out.print("Enter age of student " + (i + 1) + ": ");
            ages[i] = scanner.nextInt();
        }
        
        System.out.println("\nVoting Eligibility Status:");
        System.out.println("==========================");
        
        // Check voting eligibility for each student
        for (int i = 0; i < ages.length; i++) {
            if (ages[i] < 0) {
                System.out.println("Student " + (i + 1) + ": Invalid age");
            } else if (ages[i] >= 18) {
                System.out.println("Student " + (i + 1) + ": The student with age " + ages[i] + " can vote.");
            } else {
                System.out.println("Student " + (i + 1) + ": The student with age " + ages[i] + " cannot vote.");
            }
        }
        
        scanner.close();
    }
}
