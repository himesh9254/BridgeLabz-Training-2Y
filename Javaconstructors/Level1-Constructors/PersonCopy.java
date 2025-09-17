/**
 * Person class demonstrating copy constructor
 * Shows how to create a new object by copying attributes from another object
 */
public class PersonCopy {
    // Instance variables
    private String name;
    private int age;
    private String email;
    private String address;
    private String phoneNumber;
    
    // Default constructor
    public PersonCopy() {
        this.name = "Unknown";
        this.age = 0;
        this.email = "unknown@example.com";
        this.address = "Not specified";
        this.phoneNumber = "000-000-0000";
        System.out.println("Default constructor called - Person created with default values");
    }
    
    // Parameterized constructor
    public PersonCopy(String name, int age, String email, String address, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        System.out.println("Parameterized constructor called - Person created: " + name);
    }
    
    // Copy constructor - creates a new Person by copying another Person's attributes
    public PersonCopy(PersonCopy other) {
        if (other != null) {
            this.name = other.name;
            this.age = other.age;
            this.email = other.email;
            this.address = other.address;
            this.phoneNumber = other.phoneNumber;
            System.out.println("Copy constructor called - Cloned person: " + this.name);
        } else {
            // If null object is passed, use default values
            this();
            System.out.println("Warning: Null object passed to copy constructor, using defaults");
        }
    }
    
    // Partial copy constructor (copy with name and age only)
    public PersonCopy(PersonCopy other, boolean partialCopy) {
        if (other != null && partialCopy) {
            this.name = other.name;
            this.age = other.age;
            this.email = "copied@example.com";
            this.address = "Address to be updated";
            this.phoneNumber = "Phone to be updated";
            System.out.println("Partial copy constructor called - Copied name and age from: " + other.name);
        } else {
            this(other); // Call full copy constructor
        }
    }
    
    // Deep copy method (alternative to copy constructor)
    public PersonCopy createCopy() {
        return new PersonCopy(this.name, this.age, this.email, this.address, this.phoneNumber);
    }
    
    // Clone method (implements cloning interface concept)
    public PersonCopy clone() {
        System.out.println("Clone method called for: " + this.name);
        return new PersonCopy(this);
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    // Setter methods with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
            System.out.println("Name updated to: " + name);
        } else {
            System.out.println("Invalid name provided!");
        }
    }
    
    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
            System.out.println("Age updated to: " + age);
        } else {
            System.out.println("Invalid age! Age must be between 0 and 150.");
        }
    }
    
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
            System.out.println("Email updated to: " + email);
        } else {
            System.out.println("Invalid email format!");
        }
    }
    
    public void setAddress(String address) {
        if (address != null && !address.trim().isEmpty()) {
            this.address = address;
            System.out.println("Address updated");
        } else {
            System.out.println("Invalid address provided!");
        }
    }
    
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            this.phoneNumber = phoneNumber;
            System.out.println("Phone number updated to: " + phoneNumber);
        } else {
            System.out.println("Invalid phone number provided!");
        }
    }
    
    // Display person details
    public void displayPersonDetails() {
        System.out.println("=== Person Details ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age + " years");
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("=====================");
    }
    
    // Method to check if person is adult
    public boolean isAdult() {
        return age >= 18;
    }
    
    // Method to get age category
    public String getAgeCategory() {
        if (age < 13) return "Child";
        else if (age < 20) return "Teenager";
        else if (age < 60) return "Adult";
        else return "Senior";
    }
    
    // Method to update contact info
    public void updateContactInfo(String email, String address, String phoneNumber) {
        setEmail(email);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        System.out.println("Contact information updated for: " + name);
    }
    
    // Method to compare two persons
    public boolean hasSameNameAs(PersonCopy other) {
        if (other == null) return false;
        return this.name.equalsIgnoreCase(other.name);
    }
    
    // Override toString method
    @Override
    public String toString() {
        return String.format("Person{name='%s', age=%d, email='%s'}", name, age, email);
    }
    
    // Override equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PersonCopy person = (PersonCopy) obj;
        return age == person.age &&
               name.equals(person.name) &&
               email.equals(person.email);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Person Copy Constructor Demonstration ===\n");
        
        System.out.println("1. Creating original person with parameterized constructor:");
        PersonCopy originalPerson = new PersonCopy("Alice Johnson", 28, 
            "alice.johnson@email.com", "123 Main St, New York", "555-1234");
        originalPerson.displayPersonDetails();
        
        System.out.println("\n2. Creating copy using copy constructor:");
        PersonCopy copiedPerson = new PersonCopy(originalPerson);
        copiedPerson.displayPersonDetails();
        
        System.out.println("\n3. Verifying they are separate objects:");
        System.out.println("Original person object: " + originalPerson);
        System.out.println("Copied person object: " + copiedPerson);
        System.out.println("Are they the same object? " + (originalPerson == copiedPerson));
        System.out.println("Do they have same content? " + originalPerson.equals(copiedPerson));
        
        System.out.println("\n4. Modifying copied person to show independence:");
        copiedPerson.setName("Alice Johnson Jr.");
        copiedPerson.setAge(25);
        copiedPerson.setEmail("alice.jr@email.com");
        
        System.out.println("\nOriginal person after modification:");
        originalPerson.displayPersonDetails();
        System.out.println("\nCopied person after modification:");
        copiedPerson.displayPersonDetails();
        
        System.out.println("\n5. Creating partial copy:");
        PersonCopy partialCopy = new PersonCopy(originalPerson, true);
        partialCopy.displayPersonDetails();
        
        System.out.println("\n6. Using clone method:");
        PersonCopy clonedPerson = originalPerson.clone();
        clonedPerson.displayPersonDetails();
        
        System.out.println("\n7. Using createCopy method:");
        PersonCopy createdCopy = originalPerson.createCopy();
        createdCopy.displayPersonDetails();
        
        System.out.println("\n8. Testing copy constructor with null:");
        PersonCopy nullCopy = new PersonCopy(null);
        nullCopy.displayPersonDetails();
        
        System.out.println("\n9. Creating person with default constructor:");
        PersonCopy defaultPerson = new PersonCopy();
        defaultPerson.displayPersonDetails();
        
        System.out.println("\n10. Additional person information:");
        System.out.println("Is " + originalPerson.getName() + " an adult? " + originalPerson.isAdult());
        System.out.println("Age category: " + originalPerson.getAgeCategory());
        System.out.println("Do original and copied person have same name? " + 
                         originalPerson.hasSameNameAs(copiedPerson));
        
        System.out.println("\n=== Copy Constructor Benefits ===");
        System.out.println("✓ Object Cloning: Creates independent copies of objects");
        System.out.println("✓ Data Protection: Original object remains unchanged");
        System.out.println("✓ Initialization: New object gets all attributes from source");
        System.out.println("✓ Flexibility: Can create modified copies easily");
    }
}
