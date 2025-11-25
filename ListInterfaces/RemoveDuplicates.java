import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicates {
    public static <T> List<T> removeDuplicatesPreserveOrder(List<T> list) {
        Set<T> seen = new LinkedHashSet<>();
        List<T> result = new ArrayList<>();
        for (T element : list) {
            if (!seen.contains(element)) {
                seen.add(element);
                result.add(element);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(1);
        numbers.add(2);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);

        System.out.println("Input: " + numbers);
        List<Integer> uniqueNumbers = removeDuplicatesPreserveOrder(numbers);
        System.out.println("Output: " + uniqueNumbers);

        List<String> strings = new ArrayList<>();
        strings.add("apple");
        strings.add("banana");
        strings.add("apple");
        strings.add("cherry");
        strings.add("banana");
        strings.add("date");

        System.out.println("\nInput: " + strings);
        List<String> uniqueStrings = removeDuplicatesPreserveOrder(strings);
        System.out.println("Output: " + uniqueStrings);

        List<Integer> moreNumbers = new ArrayList<>();
        moreNumbers.add(5);
        moreNumbers.add(5);
        moreNumbers.add(5);
        moreNumbers.add(1);
        moreNumbers.add(2);
        moreNumbers.add(1);

        System.out.println("\nInput: " + moreNumbers);
        System.out.println("Output: " + removeDuplicatesPreserveOrder(moreNumbers));
    }
}
