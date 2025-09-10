import java.util.Scanner;

class QuadraticSolver {
    
    public static double[] findRoots(double a, double b, double c) {
        if (a == 0) {
            return new double[0];
        }
        
        double discriminant = Math.pow(b, 2) + 4 * a * c;
        
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new double[]{root1, root2};
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            return new double[]{root};
        } else {
            return new double[0];
        }
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Quadratic Equation Solver: ax² + bx + c = 0");
        System.out.print("Enter coefficient a: ");
        double a = input.nextDouble();
        
        System.out.print("Enter coefficient b: ");
        double b = input.nextDouble();
        
        System.out.print("Enter coefficient c: ");
        double c = input.nextDouble();
        
        if (a == 0) {
            System.out.println("This is not a quadratic equation (a cannot be 0).");
            input.close();
            return;
        }
        
        double[] roots = findRoots(a, b, c);
        
        System.out.println("For equation: " + a + "x² + " + b + "x + " + c + " = 0");
        
        double discriminant = Math.pow(b, 2) + 4 * a * c;
        System.out.println("Discriminant = " + discriminant);
        
        if (roots.length == 2) {
            System.out.println("Two real roots:");
            System.out.printf("Root 1: %.4f%n", roots[0]);
            System.out.printf("Root 2: %.4f%n", roots[1]);
        } else if (roots.length == 1) {
            System.out.println("One real root:");
            System.out.printf("Root: %.4f%n", roots[0]);
        } else {
            System.out.println("No real roots (discriminant is negative).");
        }
        
        input.close();
    }
}
