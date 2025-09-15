public class Person {
    private String name;
    private int age;
    private String address;
    private String phoneNumber;
    
    // Default constructor
    public Person() {
        this.name = "Unknown";
        this.age = 0;
        this.address = "Unknown";
        this.phoneNumber = "Unknown";
    }
    
    // Parameterized constructor
    public Person(String name, int age, String address, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    // Copy constructor - clones another person's attributes
    public Person(Person other) {
        if (other != null) {
            this.name = other.name;
            this.age = other.age;
            this.address = other.address;
            this.phoneNumber = other.phoneNumber;
        } else {
            // If null object is passed, initialize with default values
            this.name = "Unknown";
            this.age = 0;
            this.address = "Unknown";
            this.phoneNumber = "Unknown";
        }
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    // Setter methods
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        } else {
            System.out.println("Age cannot be negative!");
        }
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    // Display person information
    public void displayPerson() {
        System.out.println("Person Information:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("------------------------");
    }
    
    // Check if two persons are equal (same attributes)
    public boolean equals(Person other) {
        if (other == null) return false;
        return this.name.equals(other.name) && 
               this.age == other.age && 
               this.address.equals(other.address) && 
               this.phoneNumber.equals(other.phoneNumber);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        // Create original person
        Person originalPerson = new Person("John Doe", 25, "123 Main St", "555-1234");
        System.out.println("Original Person:");
        originalPerson.displayPerson();
        
        // Create a copy using copy constructor
        Person copiedPerson = new Person(originalPerson);
        System.out.println("Copied Person:");
        copiedPerson.displayPerson();
        
        // Verify they have the same attributes
        System.out.println("Are they equal? " + originalPerson.equals(copiedPerson));
        
        // Modify the copied person to show they are independent objects
        copiedPerson.setName("Jane Smith");
        copiedPerson.setAge(30);
        
        System.out.println("After modifying copied person:");
        System.out.println("Original Person:");
        originalPerson.displayPerson();
        System.out.println("Modified Copied Person:");
        copiedPerson.displayPerson();
        
        System.out.println("Are they still equal? " + originalPerson.equals(copiedPerson));
        
        // Test copy constructor with null
        Person nullCopy = new Person(null);
        System.out.println("Copy created from null:");
        nullCopy.displayPerson();
    }
}
