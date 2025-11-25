public class CompareValues {
    public static <T> boolean isEqual(T a, T b) {
        return a.equals(b);
    }

    public static void main(String[] args) {
        System.out.println("Comparing 10 and 10: " + isEqual(10, 10));
        System.out.println("Comparing 10 and 20: " + isEqual(10, 20));
        System.out.println("Comparing 'Hello' and 'Hello': " + isEqual("Hello", "Hello"));
        System.out.println("Comparing 'Hello' and 'World': " + isEqual("Hello", "World"));
        System.out.println("Comparing 5.5 and 5.5: " + isEqual(5.5, 5.5));
    }
}
