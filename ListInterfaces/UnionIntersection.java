import java.util.HashSet;
import java.util.Set;

public class UnionIntersection {
    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>();
        for (T element : set1) {
            if (set2.contains(element)) {
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
        System.out.println("Union: " + union(set1, set2));
        System.out.println("Intersection: " + intersection(set1, set2));

        Set<String> strSet1 = new HashSet<>();
        strSet1.add("apple");
        strSet1.add("banana");
        strSet1.add("cherry");

        Set<String> strSet2 = new HashSet<>();
        strSet2.add("banana");
        strSet2.add("date");
        strSet2.add("cherry");

        System.out.println("\nString Set1: " + strSet1);
        System.out.println("String Set2: " + strSet2);
        System.out.println("Union: " + union(strSet1, strSet2));
        System.out.println("Intersection: " + intersection(strSet1, strSet2));
    }
}
