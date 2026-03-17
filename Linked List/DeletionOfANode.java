public class DeletionOfANode {
    private static class Node {
        private final int data;
        private Node next;

        private Node(int data) {
            this.data = data;
        }
    }

    private static class SinglyLinkedList {
        private Node head;

        private void append(int value) {
            Node node = new Node(value);

            if (head == null) {
                head = node;
                return;
            }

            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = node;
        }

        private boolean deleteFirstOccurrence(int value) {
            if (head == null) {
                return false;
            }

            if (head.data == value) {
                head = head.next;
                return true;
            }

            Node current = head;

            while (current.next != null && current.next.data != value) {
                current = current.next;
            }

            if (current.next == null) {
                return false;
            }

            current.next = current.next.next;
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
        list.append(10);
        list.append(20);
        list.append(30);
        list.append(40);

        System.out.println("Before Deletion: " + list.toDisplayString());
        System.out.println("Deleted 20: " + list.deleteFirstOccurrence(20));
        System.out.println("After Deletion: " + list.toDisplayString());
    }
}
