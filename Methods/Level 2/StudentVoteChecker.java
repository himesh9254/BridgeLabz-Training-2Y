import java.util.Scanner;

class StudentVoteChecker {
    
    public static boolean canStudentVote(int age) {
        if (age < 0) {
            return false;
        }
        return age >= 18;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] ages = new int[10];
        
        System.out.println("Enter ages of 10 students:");
        
        for (int i = 0; i < ages.length; i++) {
            System.out.print("Student " + (i + 1) + " age: ");
            ages[i] = input.nextInt();
        }
        
        System.out.println("\nVoting Eligibility Results:");
        for (int i = 0; i < ages.length; i++) {
            boolean canVote = canStudentVote(ages[i]);
            
            if (ages[i] < 0) {
                System.out.println("Student " + (i + 1) + " (Age: " + ages[i] + ") - Invalid age, cannot vote");
            } else if (canVote) {
                System.out.println("Student " + (i + 1) + " (Age: " + ages[i] + ") - Can vote");
            } else {
                System.out.println("Student " + (i + 1) + " (Age: " + ages[i] + ") - Cannot vote");
            }
        }
        
        input.close();
    }
}
