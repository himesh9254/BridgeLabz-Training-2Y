import java.util.LinkedList;
import java.util.Queue;

class MyStack<T> {
    private Queue<T> queue1;
    private Queue<T> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(T item) {
        queue2.add(item);

        while (!queue1.isEmpty()) {
            queue2.add(queue1.remove());
        }

        Queue<T> temp = queue1;
        queue1 = queue2;
        queue2 = temp;

        System.out.println("Pushed: " + item);
    }

    public T pop() {
        if (queue1.isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        T item = queue1.remove();
        System.out.println("Popped: " + item);
        return item;
    }

    public T top() {
        if (queue1.isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return queue1.peek();
    }

    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    public int size() {
        return queue1.size();
    }

    public void display() {
        System.out.println("Stack (top to bottom): " + queue1);
    }
}

public class StackUsingQueues {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();

        System.out.println("=== Pushing Elements ===");
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.display();
        System.out.println("Top element: " + stack.top());

        System.out.println("\n=== Popping Elements ===");
        stack.pop();
        stack.display();
        System.out.println("Top element: " + stack.top());

        System.out.println("\n=== More Operations ===");
        stack.push(4);
        stack.push(5);
        stack.display();

        System.out.println("\n=== Pop All Elements ===");
        while (!stack.isEmpty()) {
            stack.pop();
        }
        stack.display();

        System.out.println("\n=== String Stack ===");
        MyStack<String> stringStack = new MyStack<>();
        stringStack.push("First");
        stringStack.push("Second");
        stringStack.push("Third");
        stringStack.display();

        stringStack.pop();
        stringStack.display();
    }
}
