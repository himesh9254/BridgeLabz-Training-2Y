public class TextEditorUndoRedo {
    private static class StateNode {
        private final String content;
        private StateNode prev;
        private StateNode next;

        private StateNode(String content) {
            this.content = content;
        }
    }

    private static class TextHistory {
        private StateNode head;
        private StateNode tail;
        private StateNode current;
        private int size;
        private final int capacity;

        private TextHistory(int capacity) {
            this.capacity = capacity;
        }

        private void addState(String content) {
            StateNode node = new StateNode(content);

            if (current == null) {
                head = node;
                tail = node;
                current = node;
                size = 1;
                return;
            }

            clearRedoStates();
            current.next = node;
            node.prev = current;
            current = node;
            tail = node;
            size++;
            trimToCapacity();
        }

        private String undo() {
            if (current != null && current.prev != null) {
                current = current.prev;
            }

            return getCurrentState();
        }

        private String redo() {
            if (current != null && current.next != null) {
                current = current.next;
            }

            return getCurrentState();
        }

        private String getCurrentState() {
            return current == null ? "" : current.content;
        }

        private String getHistory() {
            StringBuilder builder = new StringBuilder();
            StateNode node = head;

            while (node != null) {
                if (builder.length() > 0) {
                    builder.append(System.lineSeparator());
                }

                builder.append(node.content);
                node = node.next;
            }

            return builder.toString();
        }

        private void clearRedoStates() {
            if (current == null || current.next == null) {
                return;
            }

            StateNode node = current.next;

            while (node != null) {
                StateNode nextNode = node.next;
                node.prev = null;
                node.next = null;
                node = nextNode;
                size--;
            }

            current.next = null;
            tail = current;
        }

        private void trimToCapacity() {
            while (size > capacity) {
                head = head.next;

                if (head != null) {
                    head.prev = null;
                } else {
                    tail = null;
                    current = null;
                }

                size--;
            }
        }
    }

    public static void main(String[] args) {
        TextHistory history = new TextHistory(10);
        history.addState("H");
        history.addState("He");
        history.addState("Hel");
        history.addState("Hell");
        history.addState("Hello");
        history.addState("Hello ");
        history.addState("Hello W");
        history.addState("Hello Wo");
        history.addState("Hello Wor");
        history.addState("Hello Worl");
        history.addState("Hello World");

        System.out.println("Current State");
        System.out.println(history.getCurrentState());
        System.out.println();

        System.out.println("Undo");
        System.out.println(history.undo());
        System.out.println(history.undo());
        System.out.println();

        System.out.println("Redo");
        System.out.println(history.redo());
        System.out.println();

        history.addState("Hello World!");
        System.out.println("Current State");
        System.out.println(history.getCurrentState());
        System.out.println();

        System.out.println("History");
        System.out.println(history.getHistory());
    }
}
