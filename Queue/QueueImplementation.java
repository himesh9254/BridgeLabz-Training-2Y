class Queue {
    int[] arr;
    int front, rear, size, capacity;

    Queue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    void push(int data) {
        if (isFull()) {
            System.out.println("Queue is Full");
            return;
        }

        arr[++rear] = data;
        size++;
    }

    int pop() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return -1;
        }

        int value = arr[front];
        front++;
        size--;

        return value;
    }

    int peek() {
        if (isEmpty()) return -1;
        return arr[front];
    }

    boolean isFull() {
        return size == capacity;
    }

    boolean isEmpty() {
        return size == 0;
    }
}

public class QueueImplementation {
    public static void main(String[] args) {
        Queue q = new Queue(5);

        q.push(10);
        q.push(20);
        q.push(30);

        System.out.println(q.pop());   // 10
        System.out.println(q.peek());  // 20
    }
}