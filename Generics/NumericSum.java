import java.util.Arrays;
import java.util.List;

public class NumericSum {
    public static double sumNumbers(List<? extends Number> list) {
        double sum = 0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Sum of integers: " + sumNumbers(integers));

        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5, 4.5, 5.5);
        System.out.println("Sum of doubles: " + sumNumbers(doubles));

        List<Float> floats = Arrays.asList(1.1f, 2.2f, 3.3f);
        System.out.println("Sum of floats: " + sumNumbers(floats));
    }
}
