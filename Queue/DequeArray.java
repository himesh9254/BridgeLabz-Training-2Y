class DequeArray {

    int[] arr;
    int front, rear, size, capacity;

    public DequeArray(int k) {
        capacity = k;
        arr = new int[k];
        front = -1;
        rear = 0;
        size = 0;
    }

    
    public boolean isFull() {
        return size == capacity;
    }

    
    public boolean isEmpty() {
        return size == 0;
    }

    
    public boolean insertFront(int value) {
        if (isFull()) return false;

        if (isEmpty()) {
            front = rear = 0;
        } else {
            front = (front - 1 + capacity) % capacity;
        }

        arr[front] = value;
        size++;
        return true;
    }

  
    public boolean insertLast(int value) {
        if (isFull()) return false;

        if (isEmpty()) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }

        arr[rear] = value;
        size++;
        return true;
    }

    
    public boolean deleteFront() {
        if (isEmpty()) return false;

        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % capacity;
        }

        size--;
        return true;
    }

    
    public boolean deleteLast() {
        if (isEmpty()) return false;

        if (front == rear) {
            front = rear = -1;
        } else {
            rear = (rear - 1 + capacity) % capacity;
        }

        size--;
        return true;
    }

   
    public int getFront() {
        return isEmpty() ? -1 : arr[front];
    }

    
    public int getRear() {
        return isEmpty() ? -1 : arr[rear];
    }
}