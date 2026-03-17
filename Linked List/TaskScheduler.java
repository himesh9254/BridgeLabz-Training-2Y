public class TaskScheduler {
    private static class Task {
        private final int taskId;
        private final String taskName;
        private final String priority;
        private final String dueDate;

        private Task(int taskId, String taskName, String priority, String dueDate) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.priority = priority;
            this.dueDate = dueDate;
        }

        @Override
        public String toString() {
            return taskId + " | " + taskName + " | " + priority + " | due=" + dueDate;
        }
    }

    private static class TaskNode {
        private Task task;
        private TaskNode next;

        private TaskNode(Task task) {
            this.task = task;
        }
    }

    private static class CircularTaskList {
        private TaskNode head;
        private TaskNode tail;
        private TaskNode current;
        private int size;

        private void addAtBeginning(int taskId, String taskName, String priority, String dueDate) {
            TaskNode node = new TaskNode(new Task(taskId, taskName, priority, dueDate));

            if (head == null) {
                head = node;
                tail = node;
                current = node;
                node.next = node;
            } else {
                node.next = head;
                head = node;
                tail.next = head;
            }

            size++;
        }

        private void addAtEnd(int taskId, String taskName, String priority, String dueDate) {
            if (head == null) {
                addAtBeginning(taskId, taskName, priority, dueDate);
                return;
            }

            TaskNode node = new TaskNode(new Task(taskId, taskName, priority, dueDate));
            node.next = head;
            tail.next = node;
            tail = node;
            size++;
        }

        private boolean addAtPosition(int taskId, String taskName, String priority, String dueDate, int position) {
            if (position < 1 || position > size + 1) {
                return false;
            }

            if (position == 1) {
                addAtBeginning(taskId, taskName, priority, dueDate);
                return true;
            }

            if (position == size + 1) {
                addAtEnd(taskId, taskName, priority, dueDate);
                return true;
            }

            TaskNode previous = head;

            for (int index = 1; index < position - 1; index++) {
                previous = previous.next;
            }

            TaskNode node = new TaskNode(new Task(taskId, taskName, priority, dueDate));
            node.next = previous.next;
            previous.next = node;
            size++;
            return true;
        }

        private boolean removeByTaskId(int taskId) {
            if (head == null) {
                return false;
            }

            TaskNode previous = tail;
            TaskNode node = head;

            do {
                if (node.task.taskId == taskId) {
                    if (size == 1) {
                        head = null;
                        tail = null;
                        current = null;
                    } else {
                        previous.next = node.next;

                        if (node == head) {
                            head = node.next;
                        }

                        if (node == tail) {
                            tail = previous;
                        }

                        if (current == node) {
                            current = node.next;
                        }
                    }

                    size--;
                    return true;
                }

                previous = node;
                node = node.next;
            } while (node != head);

            return false;
        }

        private Task viewCurrentAndMoveNext() {
            if (current == null) {
                return null;
            }

            Task task = current.task;
            current = current.next;
            return task;
        }

        private String displayAll() {
            if (head == null) {
                return "No tasks scheduled";
            }

            StringBuilder builder = new StringBuilder();
            TaskNode node = head;

            do {
                if (builder.length() > 0) {
                    builder.append(System.lineSeparator());
                }

                builder.append(node.task);
                node = node.next;
            } while (node != head);

            return builder.toString();
        }

        private String searchByPriority(String priority) {
            if (head == null) {
                return "No tasks scheduled";
            }

            StringBuilder builder = new StringBuilder();
            TaskNode node = head;

            do {
                if (node.task.priority.equalsIgnoreCase(priority)) {
                    if (builder.length() > 0) {
                        builder.append(System.lineSeparator());
                    }

                    builder.append(node.task);
                }

                node = node.next;
            } while (node != head);

            return builder.length() == 0 ? "No tasks found" : builder.toString();
        }
    }

    public static void main(String[] args) {
        CircularTaskList tasks = new CircularTaskList();
        tasks.addAtBeginning(1, "Design UI", "High", "2026-03-20");
        tasks.addAtEnd(2, "Set Up Database", "Medium", "2026-03-22");
        tasks.addAtEnd(4, "Write Tests", "Low", "2026-03-28");
        tasks.addAtPosition(3, "Integrate APIs", "High", "2026-03-25", 3);

        System.out.println("All Tasks");
        System.out.println(tasks.displayAll());
        System.out.println();

        System.out.println("Current Then Next");
        System.out.println(tasks.viewCurrentAndMoveNext());
        System.out.println(tasks.viewCurrentAndMoveNext());
        System.out.println();

        System.out.println("High Priority");
        System.out.println(tasks.searchByPriority("High"));
        System.out.println();

        tasks.removeByTaskId(2);
        System.out.println("After Removal");
        System.out.println(tasks.displayAll());
    }
}
