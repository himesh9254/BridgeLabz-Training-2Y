import java.util.ArrayList;
import java.util.List;

public class RotateElements {
    public static <T> void rotateList(List<T> list, int positions) {
        if (list == null || list.size() <= 1) {
            return;
        }
        int n = list.size();
        positions = positions % n;
        if (positions < 0) {
            positions += n;
        }

        List<T> rotated = new ArrayList<>();
        for (int i = positions; i < n; i++) {
            rotated.add(list.get(i));
        }
        for (int i = 0; i < positions; i++) {
            rotated.add(list.get(i));
        }

        for (int i = 0; i < n; i++) {
            list.set(i, rotated.get(i));
        }
    }

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);

        System.out.println("Original list: " + numbers);
        rotateList(numbers, 2);
        System.out.println("After rotating by 2: " + numbers);

        List<String> letters = new ArrayList<>();
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");

        System.out.println("\nOriginal list: " + letters);
        rotateList(letters, 3);
        System.out.println("After rotating by 3: " + letters);

        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);

        System.out.println("\nOriginal list: " + nums);
        rotateList(nums, 1);
        System.out.println("After rotating by 1: " + nums);
    }
}
