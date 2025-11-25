import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReverseQueue {
    public static <T> void reverseQueue(Queue<T> queue) {
        Stack<T> stack = new Stack<>();

        while (!queue.isEmpty()) {
            stack.push(queue.remove());
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue1 = new LinkedList<>();
        queue1.add(10);
        queue1.add(20);
        queue1.add(30);

        System.out.println("Original Queue: " + queue1);
        reverseQueue(queue1);
        System.out.println("Reversed Queue: " + queue1);

        Queue<Integer> queue2 = new LinkedList<>();
        queue2.add(1);
        queue2.add(2);
        queue2.add(3);
        queue2.add(4);
        queue2.add(5);

        System.out.println("\nOriginal Queue: " + queue2);
        reverseQueue(queue2);
        System.out.println("Reversed Queue: " + queue2);

        Queue<String> stringQueue = new LinkedList<>();
        stringQueue.add("First");
        stringQueue.add("Second");
        stringQueue.add("Third");
        stringQueue.add("Fourth");

        System.out.println("\nOriginal String Queue: " + stringQueue);
        reverseQueue(stringQueue);
        System.out.println("Reversed String Queue: " + stringQueue);
    }
}
