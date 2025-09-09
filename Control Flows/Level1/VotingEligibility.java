import java.util.Scanner;

/**
 * Problem 5: Check whether a person can vote, depending on whether his/her age is greater than or equal to 18
 * Hint: Get integer input from the user and store it in the age variable.
 * If the person is 18 or older, print "The person can vote." Otherwise, print "The person cannot vote."
 * I/P => age
 * O/P => If the person's age is greater or equal to 18 then the output is 
 *         The person's age is ___ and can vote.
 *         Otherwise 
 *         The person's age is ___ and cannot vote.
 */
public class VotingEligibility {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        
        // Check if the person is eligible to vote
        if (age >= 18) {
            System.out.println("The person's age is " + age + " and can vote.");
        } else {
            System.out.println("The person's age is " + age + " and cannot vote.");
        }
        
        scanner.close();
    }
}
