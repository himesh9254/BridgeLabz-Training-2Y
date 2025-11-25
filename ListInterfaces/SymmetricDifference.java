import java.util.HashSet;
import java.util.Set;

public class SymmetricDifference {
    public static <T> Set<T> symmetricDifference(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>();

        for (T element : set1) {
            if (!set2.contains(element)) {
                result.add(element);
            }
        }

        for (T element : set2) {
            if (!set1.contains(element)) {
                result.add(element);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);

        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);
        System.out.println("Symmetric Difference: " + symmetricDifference(set1, set2));

        Set<String> strSet1 = new HashSet<>();
        strSet1.add("apple");
        strSet1.add("banana");
        strSet1.add("cherry");

        Set<String> strSet2 = new HashSet<>();
        strSet2.add("banana");
        strSet2.add("date");
        strSet2.add("elderberry");

        System.out.println("\nString Set1: " + strSet1);
        System.out.println("String Set2: " + strSet2);
        System.out.println("Symmetric Difference: " + symmetricDifference(strSet1, strSet2));

        Set<Integer> set3 = new HashSet<>();
        set3.add(10);
        set3.add(20);

        Set<Integer> set4 = new HashSet<>();
        set4.add(20);
        set4.add(30);

        System.out.println("\nSet3: " + set3);
        System.out.println("Set4: " + set4);
        System.out.println("Symmetric Difference: " + symmetricDifference(set3, set4));
    }
}
