public class CircularBuffer<T> {
    private Object[] buffer;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    public void insert(T item) {
        buffer[tail] = item;
        tail = (tail + 1) % capacity;

        if (size < capacity) {
            size++;
        } else {
            head = (head + 1) % capacity;
        }

        System.out.println("Inserted: " + item);
    }

    @SuppressWarnings("unchecked")
    public T remove() {
        if (isEmpty()) {
            System.out.println("Buffer is empty!");
            return null;
        }

        T item = (T) buffer[head];
        buffer[head] = null;
        head = (head + 1) % capacity;
        size--;

        System.out.println("Removed: " + item);
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T) buffer[head];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int getSize() {
        return size;
    }

    public void display() {
        System.out.print("Buffer: [");
        if (isEmpty()) {
            System.out.println("]");
            return;
        }

        int index = head;
        for (int i = 0; i < size; i++) {
            System.out.print(buffer[index]);
            if (i < size - 1) {
                System.out.print(", ");
            }
            index = (index + 1) % capacity;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        System.out.println("=== Circular Buffer (Size: 3) ===");
        CircularBuffer<Integer> buffer = new CircularBuffer<>(3);

        buffer.insert(1);
        buffer.display();

        buffer.insert(2);
        buffer.display();

        buffer.insert(3);
        buffer.display();

        System.out.println("\nInserting 4 (will overwrite oldest):");
        buffer.insert(4);
        buffer.display();

        System.out.println("\nInserting 5 (will overwrite oldest):");
        buffer.insert(5);
        buffer.display();

        System.out.println("\n=== Removing Elements ===");
        buffer.remove();
        buffer.display();

        buffer.remove();
        buffer.display();

        System.out.println("\n=== Adding More Elements ===");
        buffer.insert(6);
        buffer.display();

        buffer.insert(7);
        buffer.display();

        System.out.println("\n=== String Buffer (Size: 4) ===");
        CircularBuffer<String> stringBuffer = new CircularBuffer<>(4);
        stringBuffer.insert("A");
        stringBuffer.insert("B");
        stringBuffer.insert("C");
        stringBuffer.insert("D");
        stringBuffer.display();

        stringBuffer.insert("E");
        stringBuffer.display();

        stringBuffer.insert("F");
        stringBuffer.display();
    }
}
