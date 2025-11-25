import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopyListElements {
    public static void copyList(List<? super Number> dest, List<? extends Number> src) {
        for (Number num : src) {
            dest.add(num);
        }
    }

    public static void main(String[] args) {
        List<Integer> integerSource = Arrays.asList(1, 2, 3, 4, 5);
        List<Number> destination = new ArrayList<>();

        copyList(destination, integerSource);
        System.out.println("After copying integers: " + destination);

        List<Double> doubleSource = Arrays.asList(6.5, 7.5, 8.5);
        copyList(destination, doubleSource);
        System.out.println("After copying doubles: " + destination);
    }
}
