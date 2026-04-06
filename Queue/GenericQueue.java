public class GenericQueue<T> {
    private T[] arr;
    private int front;
    private int rear;
    private int capacity;
    private int currentSize;

    @SuppressWarnings("unchecked")
    public GenericQueue(int size) {
        this.capacity = size;
        this.arr = (T[]) new Object[size];
        this.front = 0;
        this.rear = -1;
        this.currentSize = 0;
    }

    
    public void enqueue(T item) {
        if (isFull()) {
            System.out.println("Queue Overflow! Cannot add " + item);
            return;
        }
        
        rear = (rear + 1) % capacity;
        arr[rear] = item;
        currentSize++;
    }

    
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow!");
            return null;
        }
        T item = arr[front];
        arr[front] = null; 
       
        
        front = (front + 1) % capacity;
        currentSize--;
        return item;
    }

    public T peek() {
        return isEmpty() ? null : arr[front];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == capacity;
    }
}