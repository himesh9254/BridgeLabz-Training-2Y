public class ReverseALinkedList {
    private static class Node {
        private final int data;
        private Node next;

        private Node(int data) {
            this.data = data;
        }
    }

    private static Node reverse(Node head) {
        Node previous = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        return previous;
    }

    private static Node buildList(int... values) {
        Node head = null;
        Node tail = null;

        for (int value : values) {
            Node node = new Node(value);

            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }

        return head;
    }

    private static String toDisplayString(Node head) {
        StringBuilder builder = new StringBuilder();
        Node current = head;

        while (current != null) {
            if (builder.length() > 0) {
                builder.append(" -> ");
            }

            builder.append(current.data);
            current = current.next;
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        Node head = buildList(10, 20, 30, 40);
        System.out.println("Original: " + toDisplayString(head));
        Node reversed = reverse(head);
        System.out.println("Reversed: " + toDisplayString(reversed));
    }
}
