import java.util.LinkedList;

public class NthFromEnd {
    public static <T> T findNthFromEnd(LinkedList<T> list, int n) {
        if (list == null || n <= 0) {
            return null;
        }

        T slow = null;
        T fast = null;
        int slowIndex = 0;
        int fastIndex = 0;

        java.util.Iterator<T> slowIter = list.iterator();
        java.util.Iterator<T> fastIter = list.iterator();

        for (int i = 0; i < n && fastIter.hasNext(); i++) {
            fast = fastIter.next();
            fastIndex++;
        }

        if (fastIndex < n) {
            return null;
        }

        slow = slowIter.next();

        while (fastIter.hasNext()) {
            fast = fastIter.next();
            slow = slowIter.next();
        }

        return slow;
    }

    public static void main(String[] args) {
        LinkedList<String> letters = new LinkedList<>();
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");

        System.out.println("List: " + letters);
        System.out.println("2nd element from end: " + findNthFromEnd(letters, 2));
        System.out.println("1st element from end: " + findNthFromEnd(letters, 1));
        System.out.println("5th element from end: " + findNthFromEnd(letters, 5));

        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);
        numbers.add(60);

        System.out.println("\nList: " + numbers);
        System.out.println("3rd element from end: " + findNthFromEnd(numbers, 3));
        System.out.println("4th element from end: " + findNthFromEnd(numbers, 4));

        LinkedList<String> names = new LinkedList<>();
        names.add("John");
        names.add("Jane");
        names.add("Bob");
        names.add("Alice");

        System.out.println("\nList: " + names);
        System.out.println("2nd element from end: " + findNthFromEnd(names, 2));
    }
}
