import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetToSortedList {
    public static <T extends Comparable<T>> List<T> convertToSortedList(Set<T> set) {
        List<T> list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }

    public static void main(String[] args) {
        Set<Integer> numberSet = new HashSet<>();
        numberSet.add(5);
        numberSet.add(3);
        numberSet.add(9);
        numberSet.add(1);

        System.out.println("Input HashSet: " + numberSet);
        List<Integer> sortedList = convertToSortedList(numberSet);
        System.out.println("Output Sorted List: " + sortedList);

        Set<Integer> anotherSet = new HashSet<>();
        anotherSet.add(100);
        anotherSet.add(25);
        anotherSet.add(50);
        anotherSet.add(75);
        anotherSet.add(10);

        System.out.println("\nInput HashSet: " + anotherSet);
        System.out.println("Output Sorted List: " + convertToSortedList(anotherSet));

        Set<String> stringSet = new HashSet<>();
        stringSet.add("zebra");
        stringSet.add("apple");
        stringSet.add("mango");
        stringSet.add("banana");

        System.out.println("\nInput String HashSet: " + stringSet);
        System.out.println("Output Sorted List: " + convertToSortedList(stringSet));
    }
}
