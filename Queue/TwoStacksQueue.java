import java.util.Stack;

class TwoStacksQueue {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    
    void push(int x) {
        
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }

        // Push new element
        s1.push(x);

        // Move back to s1
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    // POP (easy)
    int pop() {
        if (s1.isEmpty()) {
            System.out.println("Queue Empty");
            return -1;
        }
        return s1.pop();
    }

    int peek() {
        if (s1.isEmpty()) return -1;
        return s1.peek();
    }
}