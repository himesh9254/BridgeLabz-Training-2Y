public class OnlineTicketReservationSystem {
    private static class Ticket {
        private final int ticketId;
        private final String customerName;
        private final String movieName;
        private final String seatNumber;
        private final String bookingTime;

        private Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
            this.ticketId = ticketId;
            this.customerName = customerName;
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.bookingTime = bookingTime;
        }

        @Override
        public String toString() {
            return ticketId + " | " + customerName + " | " + movieName + " | " + seatNumber + " | " + bookingTime;
        }
    }

    private static class TicketNode {
        private Ticket ticket;
        private TicketNode next;

        private TicketNode(Ticket ticket) {
            this.ticket = ticket;
        }
    }

    private static class CircularTicketList {
        private TicketNode head;
        private TicketNode tail;
        private int size;

        private void addReservationAtEnd(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
            TicketNode node = new TicketNode(new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime));

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

        private boolean removeByTicketId(int ticketId) {
            if (head == null) {
                return false;
            }

            TicketNode previous = tail;
            TicketNode node = head;

            do {
                if (node.ticket.ticketId == ticketId) {
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

        private String displayTickets() {
            if (head == null) {
                return "No tickets booked";
            }

            StringBuilder builder = new StringBuilder();
            TicketNode node = head;

            do {
                if (builder.length() > 0) {
                    builder.append(System.lineSeparator());
                }

                builder.append(node.ticket);
                node = node.next;
            } while (node != head);

            return builder.toString();
        }

        private String searchByCustomerName(String customerName) {
            if (head == null) {
                return "No tickets booked";
            }

            StringBuilder builder = new StringBuilder();
            TicketNode node = head;

            do {
                if (node.ticket.customerName.equalsIgnoreCase(customerName)) {
                    if (builder.length() > 0) {
                        builder.append(System.lineSeparator());
                    }

                    builder.append(node.ticket);
                }

                node = node.next;
            } while (node != head);

            return builder.length() == 0 ? "No tickets found" : builder.toString();
        }

        private String searchByMovieName(String movieName) {
            if (head == null) {
                return "No tickets booked";
            }

            StringBuilder builder = new StringBuilder();
            TicketNode node = head;

            do {
                if (node.ticket.movieName.equalsIgnoreCase(movieName)) {
                    if (builder.length() > 0) {
                        builder.append(System.lineSeparator());
                    }

                    builder.append(node.ticket);
                }

                node = node.next;
            } while (node != head);

            return builder.length() == 0 ? "No tickets found" : builder.toString();
        }

        private int countBookedTickets() {
            return size;
        }
    }

    public static void main(String[] args) {
        CircularTicketList tickets = new CircularTicketList();
        tickets.addReservationAtEnd(701, "Aarav", "Dune", "A1", "2026-03-16 18:00");
        tickets.addReservationAtEnd(702, "Diya", "Dune", "A2", "2026-03-16 18:02");
        tickets.addReservationAtEnd(703, "Kabir", "Interstellar", "B5", "2026-03-16 18:05");

        System.out.println("Tickets");
        System.out.println(tickets.displayTickets());
        System.out.println();

        System.out.println("Search Customer");
        System.out.println(tickets.searchByCustomerName("Diya"));
        System.out.println();

        System.out.println("Search Movie");
        System.out.println(tickets.searchByMovieName("Dune"));
        System.out.println();

        tickets.removeByTicketId(702);
        System.out.println("Updated Tickets");
        System.out.println(tickets.displayTickets());
        System.out.println();
        System.out.println("Total Tickets");
        System.out.println(tickets.countBookedTickets());
    }
}
