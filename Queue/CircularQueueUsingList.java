class CircularQueueUsingList {
    Node front = null, rear = null;

    // Enqueue
    void enqueue(int data) {
        Node newNode = new Node(data);

        // If queue is empty
        if (front == null) {
            front = rear = newNode;
            rear.next = front; // circular link
        } else {
            rear.next = newNode;
            rear = newNode;
            rear.next = front; // maintain circular
        }
    }

    // Dequeue
    int dequeue() {
        if (front == null) {
            System.out.println("Queue is Empty");
            return -1;
        }

        int value = front.data;

        // Only one element
        if (front == rear) {
            front = rear = null;
        } else {
            front = front.next;
            rear.next = front; // maintain circular
        }

        return value;
    }

    // Display
    void display() {
        if (front == null) {
            System.out.println("Queue is Empty");
            return;
        }

        Node temp = front;
        do {
            System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != front);

        System.out.println();
    }
}