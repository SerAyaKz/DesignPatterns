package org.example;

// 1. SINGLETON PATTERN
// [Previous Singleton code remains the same...]
class Singleton {
    private static Singleton instance;
    private String data;

    private Singleton() {
        data = "Singleton data";
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public String getData() {
        return data;
    }
}

// 2. BUILDER PATTERN WITH INTERFACE
// Define the builder interface
interface ComputerBuilder {
    ComputerBuilder setCPU(String cpu);
    ComputerBuilder setRAM(String ram);
    ComputerBuilder setStorage(String storage);
    ComputerBuilder setGPU(String gpu);
    Computer build();
}

class Computer {
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;

    private Computer() {}

    // Static nested Builder class implementing the interface
    public static class Builder implements ComputerBuilder {
        private Computer computer;

        public Builder() {
            computer = new Computer();
        }

        @Override
        public ComputerBuilder setCPU(String cpu) {
            computer.cpu = cpu;
            return this;
        }

        @Override
        public ComputerBuilder setRAM(String ram) {
            computer.ram = ram;
            return this;
        }

        @Override
        public ComputerBuilder setStorage(String storage) {
            computer.storage = storage;
            return this;
        }

        @Override
        public ComputerBuilder setGPU(String gpu) {
            computer.gpu = gpu;
            return this;
        }

        @Override
        public Computer build() {
            // Add validation logic
            if (computer.cpu == null || computer.ram == null) {
                throw new IllegalStateException("CPU and RAM are required!");
            }
            return computer;
        }
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram +
                ", Storage=" + storage + ", GPU=" + gpu + "]";
    }
}

// 3. FACTORY PATTERN
// [Previous Factory code remains the same...]
interface Animal {
    String makeSound();
}

class Dog implements Animal {
    @Override
    public String makeSound() {
        return "Woof!";
    }
}

class Cat implements Animal {
    @Override
    public String makeSound() {
        return "Meow!";
    }
}

class AnimalFactory {
    public Animal createAnimal(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }

        switch (type.toLowerCase()) {
            case "dog":
                return new Dog();
            case "cat":
                return new Cat();
            default:
                throw new IllegalArgumentException("Unknown animal type");
        }
    }
}

// Main class to demonstrate usage
public class DesignPatternsDemo {
    public static void main(String[] args) {
        // Singleton Pattern Demo
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println("Singleton instances are same: " + (singleton1 == singleton2));

        // Builder Pattern Demo with Interface
        ComputerBuilder builder = new Computer.Builder();
        Computer computer = builder
                .setCPU("Intel i7")
                .setRAM("16GB")
                .setStorage("512GB SSD")
                .build();
        System.out.println("Built computer: " + computer);

        // Factory Pattern Demo
        AnimalFactory factory = new AnimalFactory();
        Animal dog = factory.createAnimal("dog");
        Animal cat = factory.createAnimal("cat");
        System.out.println("Dog says: " + dog.makeSound());
        System.out.println("Cat says: " + cat.makeSound());
    }
}