import java.util.ArrayList;
import java.util.List;

class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    public void bark() {
        System.out.println(getName() + " says: Woof!");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    public void meow() {
        System.out.println(getName() + " says: Meow!");
    }
}

public class AnimalHierarchy {
    public static void printAnimals(List<? extends Animal> animals) {
        System.out.println("Animals in the list:");
        for (Animal animal : animals) {
            System.out.println("- " + animal.getName());
        }
    }

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Buddy"));
        dogs.add(new Dog("Max"));
        dogs.add(new Dog("Charlie"));

        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("Whiskers"));
        cats.add(new Cat("Luna"));

        System.out.println("Dogs:");
        printAnimals(dogs);

        System.out.println("\nCats:");
        printAnimals(cats);
    }
}
