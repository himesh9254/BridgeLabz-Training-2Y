public class AllProgramsDemo {
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("LEVEL 1 PRACTICE PROGRAMS DEMO");
        System.out.println("=====================================\n");
        
        // Demo 1: Employee Class
        System.out.println("1. EMPLOYEE DETAILS PROGRAM:");
        System.out.println("-----------------------------");
        Employee emp = new Employee("Alice Johnson", 101, 75000);
        emp.displayDetails();
        
        // Demo 2: Circle Class
        System.out.println("2. CIRCLE AREA AND CIRCUMFERENCE PROGRAM:");
        System.out.println("------------------------------------------");
        Circle circle = new Circle(7.0);
        circle.displayDetails();
        
        // Demo 3: Item Class
        System.out.println("3. INVENTORY ITEM PROGRAM:");
        System.out.println("---------------------------");
        Item item = new Item("ITM001", "Wireless Headphones", 89.99);
        item.displayItemDetails();
        item.displayTotalCost(5);
        
        // Demo 4: MobilePhone Class
        System.out.println("4. MOBILE PHONE DETAILS PROGRAM:");
        System.out.println("----------------------------------");
        MobilePhone phone = new MobilePhone("OnePlus", "11 Pro", 699.99);
        phone.displayDetails();
        
        System.out.println("\n=====================================");
        System.out.println("LEVEL 2 PRACTICE PROGRAMS DEMO");
        System.out.println("=====================================\n");
        
        // Demo 5: Student Class
        System.out.println("5. STUDENT REPORT PROGRAM:");
        System.out.println("---------------------------");
        Student student = new Student("Bob Wilson", 201, 87.5);
        student.displayStudentDetails();
        
        // Demo 6: BankAccount Class
        System.out.println("6. ATM SIMULATION PROGRAM:");
        System.out.println("---------------------------");
        BankAccount account = new BankAccount("John Smith", "ACC789", 2500.0);
        account.displayBalance();
        account.deposit(500.0);
        account.withdraw(300.0);
        
        // Demo 7: PalindromeChecker Class
        System.out.println("7. PALINDROME CHECKER PROGRAM:");
        System.out.println("-------------------------------");
        PalindromeChecker checker = new PalindromeChecker("A Santa at NASA");
        checker.displayResult();
        
        // Demo 8: MovieTicket Class
        System.out.println("8. MOVIE TICKET BOOKING PROGRAM:");
        System.out.println("----------------------------------");
        MovieTicket ticket = new MovieTicket("Spider-Man: No Way Home");
        ticket.displayTicketDetails();
        ticket.bookTicket("D12", 14.50);
        ticket.displayTicketDetails();
        
        // Demo 9: CartItem Class
        System.out.println("9. SHOPPING CART PROGRAM:");
        System.out.println("--------------------------");
        CartItem cartItem = new CartItem("Gaming Keyboard", 129.99);
        cartItem.addItem(2);
        cartItem.displayCartItem();
        cartItem.removeItem(1);
        cartItem.displayCartItem();
        
        System.out.println("\n=====================================");
        System.out.println("ALL PROGRAMS DEMONSTRATED SUCCESSFULLY!");
        System.out.println("=====================================");
    }
}
