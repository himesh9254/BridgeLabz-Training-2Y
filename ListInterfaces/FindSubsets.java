import java.util.HashSet;
import java.util.Set;

public class FindSubsets {
    public static <T> boolean isSubset(Set<T> subset, Set<T> superset) {
        for (T element : subset) {
            if (!superset.contains(element)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(1);
        set2.add(2);
        set2.add(3);
        set2.add(4);

        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);
        System.out.println("Is Set1 a subset of Set2? " + isSubset(set1, set2));

        Set<Integer> set3 = new HashSet<>();
        set3.add(2);
        set3.add(5);

        System.out.println("\nSet3: " + set3);
        System.out.println("Set2: " + set2);
        System.out.println("Is Set3 a subset of Set2? " + isSubset(set3, set2));

        Set<String> strSet1 = new HashSet<>();
        strSet1.add("apple");
        strSet1.add("banana");

        Set<String> strSet2 = new HashSet<>();
        strSet2.add("apple");
        strSet2.add("banana");
        strSet2.add("cherry");
        strSet2.add("date");

        System.out.println("\nString Set1: " + strSet1);
        System.out.println("String Set2: " + strSet2);
        System.out.println("Is String Set1 a subset of String Set2? " + isSubset(strSet1, strSet2));

        Set<Integer> emptySet = new HashSet<>();
        System.out.println("\nEmpty Set: " + emptySet);
        System.out.println("Set2: " + set2);
        System.out.println("Is Empty Set a subset of Set2? " + isSubset(emptySet, set2));
    }
}
