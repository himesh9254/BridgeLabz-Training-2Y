import java.util.Queue;
import java.util.LinkedList;

class ReverseQueueUsingRecursion {

    static void reverse(Queue<Integer> q) {
        // Base case
        if (q.isEmpty())
            return;

        // Step 1: remove front
        int front = q.poll();

        // Step 2: recursive call
        reverse(q);

        // Step 3: add back
        q.offer(front);
    }

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();

        q.offer(10);
        q.offer(20);
        q.offer(30);

        reverse(q);

        while (!q.isEmpty()) {
            System.out.print(q.poll() + " ");
        }
    }
}