public class AddTwoLinkedList {
    private static class Node {
        private final int data;
        private Node next;

        private Node(int data) {
            this.data = data;
        }
    }

    private static Node addLists(Node first, Node second) {
        Node dummy = new Node(0);
        Node tail = dummy;
        int carry = 0;

        while (first != null || second != null || carry != 0) {
            int sum = carry;

            if (first != null) {
                sum += first.data;
                first = first.next;
            }

            if (second != null) {
                sum += second.data;
                second = second.next;
            }

            tail.next = new Node(sum % 10);
            tail = tail.next;
            carry = sum / 10;
        }

        return dummy.next;
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
        Node first = buildList(2, 4, 3);
        Node second = buildList(5, 6, 4);
        Node result = addLists(first, second);

        System.out.println("First List: " + toDisplayString(first));
        System.out.println("Second List: " + toDisplayString(second));
        System.out.println("Sum List: " + toDisplayString(result));
    }
}
