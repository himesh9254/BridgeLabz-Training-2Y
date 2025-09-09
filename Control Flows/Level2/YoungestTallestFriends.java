import java.util.Scanner;

/**
 * Problem 6: Create a program to find the youngest friends among 3 Amar, Akbar, and Anthony
 * based on their ages and the tallest among the friends based on their heights
 * Hint: Take user input for the age and height of the 3 friends and store it in a variable
 * Find the smallest of the 3 ages to find the youngest friend and display it
 * Find the largest of the 3 heights to find the tallest friend and display it
 */
public class YoungestTallestFriends {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input for Amar
        System.out.print("Enter Amar's age: ");
        int amarAge = scanner.nextInt();
        System.out.print("Enter Amar's height (in cm): ");
        double amarHeight = scanner.nextDouble();
        
        // Input for Akbar
        System.out.print("Enter Akbar's age: ");
        int akbarAge = scanner.nextInt();
        System.out.print("Enter Akbar's height (in cm): ");
        double akbarHeight = scanner.nextDouble();
        
        // Input for Anthony
        System.out.print("Enter Anthony's age: ");
        int anthonyAge = scanner.nextInt();
        System.out.print("Enter Anthony's height (in cm): ");
        double anthonyHeight = scanner.nextDouble();
        
        // Find youngest friend (minimum age)
        String youngest = "";
        int minAge = amarAge;
        if (amarAge <= akbarAge && amarAge <= anthonyAge) {
            youngest = "Amar";
            minAge = amarAge;
        } else if (akbarAge <= amarAge && akbarAge <= anthonyAge) {
            youngest = "Akbar";
            minAge = akbarAge;
        } else {
            youngest = "Anthony";
            minAge = anthonyAge;
        }
        
        // Find tallest friend (maximum height)
        String tallest = "";
        double maxHeight = amarHeight;
        if (amarHeight >= akbarHeight && amarHeight >= anthonyHeight) {
            tallest = "Amar";
            maxHeight = amarHeight;
        } else if (akbarHeight >= amarHeight && akbarHeight >= anthonyHeight) {
            tallest = "Akbar";
            maxHeight = akbarHeight;
        } else {
            tallest = "Anthony";
            maxHeight = anthonyHeight;
        }
        
        System.out.println("\nResults:");
        System.out.println("Youngest friend: " + youngest + " (Age: " + minAge + ")");
        System.out.println("Tallest friend: " + tallest + " (Height: " + maxHeight + " cm)");
        
        scanner.close();
    }
}
