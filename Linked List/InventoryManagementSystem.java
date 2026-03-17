import java.util.Comparator;

public class InventoryManagementSystem {
    private static class Item {
        private final int itemId;
        private final String itemName;
        private int quantity;
        private final double price;

        private Item(int itemId, String itemName, int quantity, double price) {
            this.itemId = itemId;
            this.itemName = itemName;
            this.quantity = quantity;
            this.price = price;
        }

        @Override
        public String toString() {
            return itemId + " | " + itemName + " | qty=" + quantity + " | price=" + price;
        }
    }

    private static class ItemNode {
        private Item item;
        private ItemNode next;

        private ItemNode(Item item) {
            this.item = item;
        }
    }

    private static class InventoryList {
        private ItemNode head;
        private int size;

        private void addAtBeginning(int itemId, String itemName, int quantity, double price) {
            ItemNode node = new ItemNode(new Item(itemId, itemName, quantity, price));
            node.next = head;
            head = node;
            size++;
        }

        private void addAtEnd(int itemId, String itemName, int quantity, double price) {
            ItemNode node = new ItemNode(new Item(itemId, itemName, quantity, price));

            if (head == null) {
                head = node;
                size++;
                return;
            }

            ItemNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = node;
            size++;
        }

        private boolean addAtPosition(int itemId, String itemName, int quantity, double price, int position) {
            if (position < 1 || position > size + 1) {
                return false;
            }

            if (position == 1) {
                addAtBeginning(itemId, itemName, quantity, price);
                return true;
            }

            ItemNode current = head;

            for (int index = 1; index < position - 1; index++) {
                current = current.next;
            }

            ItemNode node = new ItemNode(new Item(itemId, itemName, quantity, price));
            node.next = current.next;
            current.next = node;
            size++;
            return true;
        }

        private boolean removeByItemId(int itemId) {
            if (head == null) {
                return false;
            }

            if (head.item.itemId == itemId) {
                head = head.next;
                size--;
                return true;
            }

            ItemNode current = head;

            while (current.next != null && current.next.item.itemId != itemId) {
                current = current.next;
            }

            if (current.next == null) {
                return false;
            }

            current.next = current.next.next;
            size--;
            return true;
        }

        private boolean updateQuantity(int itemId, int newQuantity) {
            Item item = findByItemId(itemId);

            if (item == null) {
                return false;
            }

            item.quantity = newQuantity;
            return true;
        }

        private Item findByItemId(int itemId) {
            ItemNode current = head;

            while (current != null) {
                if (current.item.itemId == itemId) {
                    return current.item;
                }

                current = current.next;
            }

            return null;
        }

        private Item findByItemName(String itemName) {
            ItemNode current = head;

            while (current != null) {
                if (current.item.itemName.equalsIgnoreCase(itemName)) {
                    return current.item;
                }

                current = current.next;
            }

            return null;
        }

        private double getTotalInventoryValue() {
            double total = 0.0;
            ItemNode current = head;

            while (current != null) {
                total += current.item.quantity * current.item.price;
                current = current.next;
            }

            return total;
        }

        private void sortByName(boolean ascending) {
            head = mergeSort(head, (left, right) -> ascending
                ? left.item.itemName.compareToIgnoreCase(right.item.itemName)
                : right.item.itemName.compareToIgnoreCase(left.item.itemName));
        }

        private void sortByPrice(boolean ascending) {
            head = mergeSort(head, (left, right) -> ascending
                ? Double.compare(left.item.price, right.item.price)
                : Double.compare(right.item.price, left.item.price));
        }

        private ItemNode mergeSort(ItemNode node, Comparator<ItemNode> comparator) {
            if (node == null || node.next == null) {
                return node;
            }

            ItemNode middle = split(node);
            ItemNode left = mergeSort(node, comparator);
            ItemNode right = mergeSort(middle, comparator);
            return merge(left, right, comparator);
        }

        private ItemNode split(ItemNode node) {
            ItemNode slow = node;
            ItemNode fast = node.next;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            ItemNode secondHalf = slow.next;
            slow.next = null;
            return secondHalf;
        }

        private ItemNode merge(ItemNode first, ItemNode second, Comparator<ItemNode> comparator) {
            ItemNode dummy = new ItemNode(null);
            ItemNode tail = dummy;

            while (first != null && second != null) {
                if (comparator.compare(first, second) <= 0) {
                    tail.next = first;
                    first = first.next;
                } else {
                    tail.next = second;
                    second = second.next;
                }

                tail = tail.next;
            }

            tail.next = first != null ? first : second;
            return dummy.next;
        }

        private String toDisplayString() {
            StringBuilder builder = new StringBuilder();
            ItemNode current = head;

            while (current != null) {
                if (builder.length() > 0) {
                    builder.append(System.lineSeparator());
                }

                builder.append(current.item);
                current = current.next;
            }

            return builder.toString();
        }
    }

    public static void main(String[] args) {
        InventoryList inventory = new InventoryList();
        inventory.addAtEnd(101, "Keyboard", 10, 799.0);
        inventory.addAtEnd(102, "Monitor", 4, 12999.0);
        inventory.addAtBeginning(100, "Adapter", 20, 299.0);
        inventory.addAtPosition(103, "Mouse", 18, 599.0, 3);

        System.out.println("Inventory");
        System.out.println(inventory.toDisplayString());
        System.out.println();

        inventory.updateQuantity(102, 6);
        System.out.println("Search By ID");
        System.out.println(inventory.findByItemId(102));
        System.out.println();

        System.out.println("Search By Name");
        System.out.println(inventory.findByItemName("Mouse"));
        System.out.println();

        System.out.println("Total Value");
        System.out.println(inventory.getTotalInventoryValue());
        System.out.println();

        inventory.sortByName(true);
        System.out.println("Sorted By Name Asc");
        System.out.println(inventory.toDisplayString());
        System.out.println();

        inventory.sortByPrice(false);
        System.out.println("Sorted By Price Desc");
        System.out.println(inventory.toDisplayString());
        System.out.println();

        inventory.removeByItemId(100);
        System.out.println("After Removal");
        System.out.println(inventory.toDisplayString());
    }
}
