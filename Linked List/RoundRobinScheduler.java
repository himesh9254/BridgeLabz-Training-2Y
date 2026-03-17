public class RoundRobinScheduler {
    private static class Process {
        private final int processId;
        private final int burstTime;
        private final int priority;
        private int remainingTime;

        private Process(int processId, int burstTime, int priority) {
            this.processId = processId;
            this.burstTime = burstTime;
            this.priority = priority;
            this.remainingTime = burstTime;
        }

        @Override
        public String toString() {
            return "P" + processId + "(burst=" + burstTime + ", remaining=" + remainingTime + ", priority=" + priority + ")";
        }
    }

    private static class ProcessNode {
        private Process process;
        private ProcessNode next;

        private ProcessNode(Process process) {
            this.process = process;
        }
    }

    private static class SimulationResult {
        private final String roundSummary;
        private final double averageWaitingTime;
        private final double averageTurnaroundTime;

        private SimulationResult(String roundSummary, double averageWaitingTime, double averageTurnaroundTime) {
            this.roundSummary = roundSummary;
            this.averageWaitingTime = averageWaitingTime;
            this.averageTurnaroundTime = averageTurnaroundTime;
        }
    }

    private static class CircularProcessQueue {
        private ProcessNode head;
        private ProcessNode tail;
        private int size;

        private void addProcessAtEnd(int processId, int burstTime, int priority) {
            ProcessNode node = new ProcessNode(new Process(processId, burstTime, priority));

            if (head == null) {
                head = node;
                tail = node;
                node.next = node;
            } else {
                node.next = head;
                tail.next = node;
                tail = node;
            }

            size++;
        }

        private boolean removeByProcessId(int processId) {
            if (head == null) {
                return false;
            }

            ProcessNode previous = tail;
            ProcessNode node = head;

            do {
                if (node.process.processId == processId) {
                    if (size == 1) {
                        head = null;
                        tail = null;
                    } else {
                        previous.next = node.next;

                        if (node == head) {
                            head = node.next;
                        }

                        if (node == tail) {
                            tail = previous;
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

        private String displayProcesses() {
            if (head == null) {
                return "No active processes";
            }

            StringBuilder builder = new StringBuilder();
            ProcessNode node = head;

            do {
                if (builder.length() > 0) {
                    builder.append(System.lineSeparator());
                }

                builder.append(node.process);
                node = node.next;
            } while (node != head);

            return builder.toString();
        }

        private SimulationResult simulate(int timeQuantum) {
            if (timeQuantum <= 0) {
                throw new IllegalArgumentException("Time quantum must be greater than zero");
            }

            int originalProcessCount = size;
            int currentTime = 0;
            double totalWaitingTime = 0.0;
            double totalTurnaroundTime = 0.0;
            int round = 1;
            StringBuilder summary = new StringBuilder();

            while (head != null) {
                int processesThisRound = size;
                ProcessNode node = head;

                for (int step = 0; step < processesThisRound && head != null; step++) {
                    ProcessNode nextNode = node.next;
                    int executionTime = Math.min(node.process.remainingTime, timeQuantum);
                    currentTime += executionTime;
                    node.process.remainingTime -= executionTime;

                    if (node.process.remainingTime == 0) {
                        int turnaroundTime = currentTime;
                        int waitingTime = turnaroundTime - node.process.burstTime;
                        totalTurnaroundTime += turnaroundTime;
                        totalWaitingTime += waitingTime;
                        removeByProcessId(node.process.processId);
                    }

                    if (head != null) {
                        node = nextNode;
                    }
                }

                if (summary.length() > 0) {
                    summary.append(System.lineSeparator()).append(System.lineSeparator());
                }

                summary.append("After Round ").append(round).append(System.lineSeparator());
                summary.append(displayProcesses());
                round++;
            }

            return new SimulationResult(
                summary.toString(),
                totalWaitingTime / originalProcessCount,
                totalTurnaroundTime / originalProcessCount
            );
        }
    }

    public static void main(String[] args) {
        CircularProcessQueue queue = new CircularProcessQueue();
        queue.addProcessAtEnd(1, 5, 2);
        queue.addProcessAtEnd(2, 3, 1);
        queue.addProcessAtEnd(3, 8, 3);
        queue.addProcessAtEnd(4, 6, 2);

        System.out.println("Initial Queue");
        System.out.println(queue.displayProcesses());
        System.out.println();

        SimulationResult result = queue.simulate(3);

        System.out.println(result.roundSummary);
        System.out.println();
        System.out.println("Average Waiting Time");
        System.out.println(result.averageWaitingTime);
        System.out.println();
        System.out.println("Average Turnaround Time");
        System.out.println(result.averageTurnaroundTime);
    }
}
