/**
 * Comprehensive demonstration of all Classes and Objects practice programs
 * This class shows the functionality of all Level 1 and Level 2 programs
 */
public class AllProgramsDemo {
    
    public static void main(String[] args) {
        System.out.println("===================================================");
        System.out.println("    CLASSES AND OBJECTS PRACTICE PROGRAMS DEMO");
        System.out.println("===================================================");
        
        // Level 1 Programs Demo
        demoLevel1Programs();
        
        System.out.println("\\n\\n");
        
        // Level 2 Programs Demo
        demoLevel2Programs();
        
        System.out.println("\\n===================================================");
        System.out.println("           ALL PROGRAMS DEMONSTRATION COMPLETE");
        System.out.println("===================================================");
    }
    
    /**
     * Demonstrates Level 1 practice programs
     */
    public static void demoLevel1Programs() {
        System.out.println("🟢 LEVEL 1 PRACTICE PROGRAMS DEMONSTRATION 🟢");
        System.out.println("==============================================");
        
        // 1. Employee Class Demo
        System.out.println("\\n1. 👨‍💼 EMPLOYEE CLASS DEMO");
        System.out.println("---------------------------");
        Employee emp = new Employee("Alice Johnson", 101, 75000.0);
        emp.displayDetails();
        
        // 2. Circle Class Demo
        System.out.println("\\n2. 🔵 CIRCLE CLASS DEMO");
        System.out.println("------------------------");
        Circle circle = new Circle(7.5);
        circle.displayDetails();
        
        // 3. Item Class Demo
        System.out.println("\\n3. 📦 ITEM CLASS DEMO");
        System.out.println("----------------------");
        Item item = new Item("LAPTOP001", "Gaming Laptop", 1299.99);
        item.displayItemDetails();
        item.displayTotalCost(3);
        
        // 4. MobilePhone Class Demo
        System.out.println("\\n4. 📱 MOBILE PHONE CLASS DEMO");
        System.out.println("------------------------------");
        MobilePhone phone = new MobilePhone("Apple", "iPhone 15 Pro", 1199.99);
        phone.displayPhoneDetails();
    }
    
    /**
     * Demonstrates Level 2 practice programs
     */
    public static void demoLevel2Programs() {
        System.out.println("🔵 LEVEL 2 PRACTICE PROGRAMS DEMONSTRATION 🔵");
        System.out.println("==============================================");
        
        // 1. Student Class Demo
        System.out.println("\\n1. 🎓 STUDENT CLASS DEMO");
        System.out.println("-------------------------");
        Student student = new Student("Bob Wilson", 205, 87.5);
        student.displayStudentDetails();
        
        // 2. BankAccount Class Demo
        System.out.println("\\n2. 🏦 BANK ACCOUNT (ATM) CLASS DEMO");
        System.out.println("------------------------------------");
        BankAccount account = new BankAccount("Charlie Brown", "ACC12345", 1500.00);
        account.displayCurrentBalance();
        account.depositMoney(300.00);
        account.withdrawMoney(200.00);
        account.displayCurrentBalance();
        
        // 3. PalindromeChecker Class Demo
        System.out.println("\\n3. 🔄 PALINDROME CHECKER CLASS DEMO");
        System.out.println("------------------------------------");
        PalindromeChecker checker = new PalindromeChecker("A man a plan a canal Panama");
        checker.displayResult();
        
        // 4. MovieTicket Class Demo
        System.out.println("\\n4. 🎬 MOVIE TICKET CLASS DEMO");
        System.out.println("------------------------------");
        MovieTicket ticket = new MovieTicket("Inception", "A15", 18.50);
        ticket.displayTicketDetails();
        ticket.bookTicket("Diana Prince");
        ticket.displayBookingSummary();
        
        // 5. CartItem Class Demo
        System.out.println("\\n5. 🛒 SHOPPING CART CLASS DEMO");
        System.out.println("-------------------------------");
        CartItem cartItem = new CartItem("Wireless Headphones", 149.99, 2);
        cartItem.displayCartItemDetails();
        cartItem.addItem(1);
        cartItem.displayTotalCost();
    }
}
