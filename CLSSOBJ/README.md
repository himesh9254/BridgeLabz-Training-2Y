# Classes and Objects Practice Programs

This folder contains comprehensive practice programs for learning Classes and Objects concepts in Java. The programs are divided into two levels of difficulty.

## 📁 Folder Structure

```
CLSSOBJ/
├── README.md                    # This file
├── AllProgramsDemo.java        # Demonstrates all programs
├── Level 1 Programs/
│   ├── Employee.java           # Employee details management
│   ├── Circle.java             # Circle area and circumference calculation
│   ├── Item.java               # Inventory item tracking
│   └── MobilePhone.java        # Mobile phone details handler
└── Level 2 Programs/
    ├── Student.java            # Student report with grade calculation
    ├── BankAccount.java        # ATM simulation with transactions
    ├── PalindromeChecker.java  # Palindrome string checker
    ├── MovieTicket.java        # Movie ticket booking system
    └── CartItem.java           # Shopping cart management
```

## 🎯 Level 1 Practice Programs (Basic Concepts)

### 1. Employee Details (`Employee.java`)
**Concepts Covered:** Basic class structure, attributes, constructor, methods
- **Attributes:** name, id, salary
- **Methods:** displayDetails()
- **Purpose:** Learn basic class creation and object instantiation

### 2. Circle Area Calculator (`Circle.java`)
**Concepts Covered:** Mathematical calculations, static constants
- **Attributes:** radius
- **Methods:** calculateArea(), calculateCircumference(), displayDetails()
- **Purpose:** Practice method creation and mathematical operations

### 3. Inventory Item Tracker (`Item.java`)
**Concepts Covered:** Cost calculations, method parameters
- **Attributes:** itemCode, itemName, price
- **Methods:** displayItemDetails(), calculateTotalCost(), displayTotalCost()
- **Purpose:** Learn method parameters and return values

### 4. Mobile Phone Details (`MobilePhone.java`)
**Concepts Covered:** Object attributes, information display
- **Attributes:** brand, model, price
- **Methods:** displayDetails(), getPhoneInfo()
- **Purpose:** Practice object-oriented data organization

## 🚀 Level 2 Practice Programs (Advanced Concepts)

### 5. Student Report System (`Student.java`)
**Concepts Covered:** Conditional logic, grade calculation
- **Attributes:** name, rollNumber, marks
- **Methods:** calculateGrade(), displayStudentDetails()
- **Purpose:** Implement business logic with conditional statements

### 6. ATM Simulation (`BankAccount.java`)
**Concepts Covered:** State management, validation, transactions
- **Attributes:** accountHolder, accountNumber, balance
- **Methods:** deposit(), withdraw(), displayBalance(), getBalance()
- **Purpose:** Learn state modification and validation logic

### 7. Palindrome Checker (`PalindromeChecker.java`)
**Concepts Covered:** String manipulation, algorithm implementation
- **Attributes:** text
- **Methods:** isPalindrome(), isPalindromeReverse(), displayResult(), setText()
- **Purpose:** Practice string processing and algorithm implementation

### 8. Movie Ticket Booking (`MovieTicket.java`)
**Concepts Covered:** Booking system, status management
- **Attributes:** movieName, seatNumber, price, isBooked
- **Methods:** bookTicket(), cancelBooking(), displayTicketDetails(), isAvailable()
- **Purpose:** Implement a complete booking system with state management

### 9. Shopping Cart System (`CartItem.java`)
**Concepts Covered:** Inventory management, multiple operations
- **Attributes:** itemName, price, quantity
- **Methods:** addItem(), removeItem(), clearCart(), calculateTotalCost(), displayCartItem(), updatePrice()
- **Purpose:** Create a comprehensive system with multiple related operations

## 🏃‍♂️ How to Run

### Individual Programs
Each program has its own `main` method for testing:
```bash
# Compile individual program
javac Employee.java

# Run individual program
java Employee
```

### All Programs Demo
Run the comprehensive demo to see all programs in action:
```bash
# Compile all programs
javac *.java

# Run the demo
java AllProgramsDemo
```

## 🎓 Learning Objectives

By completing these programs, you will learn:

1. **Basic OOP Concepts:**
   - Class definition and structure
   - Attributes (instance variables)
   - Methods (behaviors)
   - Constructors
   - Object instantiation

2. **Intermediate Concepts:**
   - Method parameters and return types
   - Private/Public access modifiers
   - Method overloading
   - State management

3. **Advanced Concepts:**
   - Input validation
   - Business logic implementation
   - Error handling
   - System design patterns

4. **Programming Skills:**
   - Code organization
   - Method design
   - Problem-solving approach
   - Real-world application modeling

## 📋 Program Features

### Error Handling
- Input validation in banking operations
- Quantity checks in cart operations
- Price validation
- Booking status verification

### User-Friendly Output
- Clear, formatted display methods
- Informative error messages
- Professional output formatting
- Status indicators

### Real-World Applications
- Employee management systems
- E-commerce platforms
- Banking applications
- Entertainment booking systems
- Educational management systems

## 🔧 Compilation Tips

1. **Compile all at once:**
   ```bash
   javac *.java
   ```

2. **Run specific program:**
   ```bash
   java ClassName
   ```

3. **Clean compilation:**
   ```bash
   del *.class
   javac *.java
   ```

## 📚 Next Steps

After mastering these programs:
1. Add exception handling
2. Implement file I/O operations
3. Create collections-based versions
4. Add GUI interfaces
5. Integrate with databases

---

**Happy Coding! 🎉**

*These programs provide a solid foundation for understanding Classes and Objects in Java programming.*
