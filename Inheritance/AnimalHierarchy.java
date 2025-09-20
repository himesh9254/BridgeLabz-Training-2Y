class Animal {
    String name;
    int age;

    Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void makeSound() {
        System.out.println(name + " makes a sound");
    }
}

class Dog extends Animal {
    Dog(String name, int age) {
        super(name, age);
    }

    @Override
    void makeSound() {
        System.out.println(name + " barks: Woof! Woof!");
    }
}

class Cat extends Animal {
    Cat(String name, int age) {
        super(name, age);
    }

    @Override
    void makeSound() {
        System.out.println(name + " meows: Meow! Meow!");
    }
}

class Bird extends Animal {
    Bird(String name, int age) {
        super(name, age);
    }

    @Override
    void makeSound() {
        System.out.println(name + " chirps: Tweet! Tweet!");
    }
}

public class AnimalHierarchy {
    public static void main(String[] args) {
        Animal dog = new Dog("Buddy", 5);
        Animal cat = new Cat("Whiskers", 3);
        Animal bird = new Bird("Tweety", 2);

        dog.makeSound();
        cat.makeSound();
        bird.makeSound();

        System.out.println("\nUsing polymorphism:");
        Animal[] animals = {dog, cat, bird};
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }
}