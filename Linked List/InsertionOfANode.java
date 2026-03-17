public class InsertionOfANode {
    private static class Node {
        private final int data;
        private Node next;

        private Node(int data) {
            this.data = data;
        }
    }

    private static class SinglyLinkedList {
        private Node head;
        private int size;

        private void insertAtBeginning(int value) {
            Node node = new Node(value);
            node.next = head;
            head = node;
            size++;
        }

        private void insertAtEnd(int value) {
            Node node = new Node(value);

            if (head == null) {
                head = node;
                size++;
                return;
            }

            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = node;
            size++;
        }

        private boolean insertAtPosition(int value, int position) {
            if (position < 1 || position > size + 1) {
                return false;
            }

            if (position == 1) {
                insertAtBeginning(value);
                return true;
            }

            Node current = head;

            for (int index = 1; index < position - 1; index++) {
                current = current.next;
            }

            Node node = new Node(value);
            node.next = current.next;
            current.next = node;
            size++;
            return true;
        }

        private String toDisplayString() {
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
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertAtBeginning(20);
        list.insertAtBeginning(10);
        list.insertAtEnd(40);
        list.insertAtPosition(30, 3);

        System.out.println(list.toDisplayString());
    }
}
