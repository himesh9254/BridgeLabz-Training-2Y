import java.util.Scanner;

class HandshakeCalculator {
    
    public static int calculateHandshakes(int numberOfStudents) {
        return (numberOfStudents * (numberOfStudents - 1)) / 2;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter number of students: ");
        int numberOfStudents = input.nextInt();
        
        int handshakes = calculateHandshakes(numberOfStudents);
        
        System.out.println("Maximum number of possible handshakes among " + 
                          numberOfStudents + " students is: " + handshakes);
        
        input.close();
    }
}
