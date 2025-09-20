interface Worker {
    void performDuties();
}

class Person {
    String name;
    int id;

    Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
    }
}

class Chef extends Person implements Worker {
    String speciality;
    int experienceYears;

    Chef(String name, int id, String speciality, int experienceYears) {
        super(name, id);
        this.speciality = speciality;
        this.experienceYears = experienceYears;
    }

    @Override
    public void performDuties() {
        System.out.println(name + " is preparing " + speciality + " dishes");
        System.out.println("Experience: " + experienceYears + " years in culinary arts");
    }

    void displayChefInfo() {
        displayInfo();
        System.out.println("Role: Chef");
        System.out.println("Speciality: " + speciality);
        System.out.println("Experience: " + experienceYears + " years");
    }
}

class Waiter extends Person implements Worker {
    int tablesAssigned;
    String shift;

    Waiter(String name, int id, int tablesAssigned, String shift) {
        super(name, id);
        this.tablesAssigned = tablesAssigned;
        this.shift = shift;
    }

    @Override
    public void performDuties() {
        System.out.println(name + " is serving " + tablesAssigned + " tables");
        System.out.println("Working " + shift + " shift");
    }

    void displayWaiterInfo() {
        displayInfo();
        System.out.println("Role: Waiter");
        System.out.println("Tables Assigned: " + tablesAssigned);
        System.out.println("Shift: " + shift);
    }
}

public class RestaurantSystem {
    public static void main(String[] args) {
        Chef headChef = new Chef("Gordon Ramsay", 101, "Italian Cuisine", 25);
        Waiter waiter1 = new Waiter("Sarah Miller", 201, 5, "Morning");
        
        System.out.println("=== Restaurant Management System ===\n");
        
        System.out.println("Chef Details:");
        headChef.displayChefInfo();
        System.out.println("\nChef Performing Duties:");
        headChef.performDuties();
        
        System.out.println("\n\nWaiter Details:");
        waiter1.displayWaiterInfo();
        System.out.println("\nWaiter Performing Duties:");
        waiter1.performDuties();
        
        System.out.println("\n\nDemonstrating Hybrid Inheritance:");
        System.out.println("Both Chef and Waiter extend Person class");
        System.out.println("Both Chef and Waiter implement Worker interface");
        
        Worker[] workers = {headChef, waiter1};
        System.out.println("\nAll workers performing their duties:");
        for (Worker worker : workers) {
            worker.performDuties();
            System.out.println();
        }
    }
}