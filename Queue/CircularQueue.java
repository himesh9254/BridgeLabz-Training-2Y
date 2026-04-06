class CircularQueue {
    int[] arr;
    int front, rear, size, capacity;

    CircularQueue(int capacity) {
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

        rear = (rear + 1) % capacity;   
        arr[rear] = data;
        size++;
    }

    
    int pop() {
        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return -1;
        }

        int value = arr[front];
        front = (front + 1) % capacity; 
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
