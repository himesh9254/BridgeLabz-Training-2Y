import java.util.Scanner;

public class Array2DTo1D {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Take user input for rows and columns
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int columns = scanner.nextInt();
        
        // Create 2D array (Matrix)
        int[][] matrix = new int[rows][columns];
        
        // Take user input for matrix elements
        System.out.println("\nEnter elements for " + rows + "x" + columns + " matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Enter element at position [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }
        
        // Display the 2D array
        System.out.println("\n2D Array (Matrix):");
        System.out.println("==================");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + "\\t");
            }
            System.out.println();
        }
        
        // Create 1D array of size rows * columns
        int[] array = new int[rows * columns];
        
        // Copy elements from 2D array to 1D array
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[index] = matrix[i][j];
                index++;
            }
        }
        
        // Display the 1D array
        System.out.println("\n1D Array (copied from 2D array):");
        System.out.println("=================================");
        System.out.print("Array elements: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        
        System.out.println("\\nTotal elements copied: " + array.length);
        System.out.println("Original 2D array size: " + rows + "x" + columns);
        System.out.println("Resultant 1D array size: " + array.length);
        
        scanner.close();
    }
}
