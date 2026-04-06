class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Queue {
    Node front, rear;

    
    void push(int data) {
        Node newNode = new Node(data);

        
        if (rear == null) {
            front = rear = newNode;
            return;
        }

        rear.next = newNode;
        rear = newNode;
    }

    
    int pop() {
        if (front == null) {
            System.out.println("Queue is Empty");
            return -1;
        }

        int value = front.data;
        front = front.next;

        
        if (front == null) {
            rear = null;
        }

        return value;
    }

    // Peek
    int peek() {
        if (front == null) return -1;
        return front.data;
    }

    boolean isEmpty() {
        return front == null;
    }
}