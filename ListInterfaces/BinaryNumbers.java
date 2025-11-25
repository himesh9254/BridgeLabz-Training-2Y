import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryNumbers {
    public static List<String> generateBinaryNumbers(int n) {
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();

        queue.add("1");

        for (int i = 0; i < n; i++) {
            String current = queue.remove();
            result.add(current);

            queue.add(current + "0");
            queue.add(current + "1");
        }

        return result;
    }

    public static void main(String[] args) {
        int n1 = 5;
        System.out.println("First " + n1 + " binary numbers: " + generateBinaryNumbers(n1));

        int n2 = 10;
        System.out.println("\nFirst " + n2 + " binary numbers: " + generateBinaryNumbers(n2));

        int n3 = 15;
        System.out.println("\nFirst " + n3 + " binary numbers: " + generateBinaryNumbers(n3));

        System.out.println("\nBinary numbers with decimal equivalents:");
        List<String> binaries = generateBinaryNumbers(10);
        for (int i = 0; i < binaries.size(); i++) {
            System.out.println((i + 1) + " -> " + binaries.get(i));
        }
    }
}
