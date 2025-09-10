import java.util.Scanner;

class RecursiveNaturalSum {
    
    public static long sumUsingRecursion(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return n + sumUsingRecursion(n - 1);
    }
    
    public static long sumUsingFormula(int n) {
        return (long) n * (n + 1) / 2;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a natural number: ");
        int n = input.nextInt();
        
        if (n <= 0) {
            System.out.println("Please enter a natural number (positive integer).");
            return;
        }
        
        long recursiveSum = sumUsingRecursion(n);
        long formulaSum = sumUsingFormula(n);
        
        System.out.println("Sum using recursion: " + recursiveSum);
        System.out.println("Sum using formula n*(n+1)/2: " + formulaSum);
        
        if (recursiveSum == formulaSum) {
            System.out.println("Both methods give the same result: CORRECT");
        } else {
            System.out.println("Results differ: INCORRECT");
        }
        
        input.close();
    }
}
