import java.util.HashSet;
import java.util.Set;

public class CheckSetsEqual {
    public static <T> boolean areSetsEqual(Set<T> set1, Set<T> set2) {
        if (set1.size() != set2.size()) {
            return false;
        }
        for (T element : set1) {
            if (!set2.contains(element)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(3);
        set2.add(2);
        set2.add(1);

        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);
        System.out.println("Are sets equal? " + areSetsEqual(set1, set2));

        Set<Integer> set3 = new HashSet<>();
        set3.add(1);
        set3.add(2);
        set3.add(4);

        System.out.println("\nSet1: " + set1);
        System.out.println("Set3: " + set3);
        System.out.println("Are sets equal? " + areSetsEqual(set1, set3));

        Set<String> strSet1 = new HashSet<>();
        strSet1.add("apple");
        strSet1.add("banana");

        Set<String> strSet2 = new HashSet<>();
        strSet2.add("banana");
        strSet2.add("apple");

        System.out.println("\nString Set1: " + strSet1);
        System.out.println("String Set2: " + strSet2);
        System.out.println("Are sets equal? " + areSetsEqual(strSet1, strSet2));
    }
}
