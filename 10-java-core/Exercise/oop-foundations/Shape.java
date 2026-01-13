package oop_foundation;

public abstract class Shape {

    // Abstract methods
    public abstract double area();
    public abstract double perimeter();

    // Concrete method
    public void displayInfo() {
        System.out.println("Area: " + area());
        System.out.println("Perimeter: " + perimeter());
        System.out.println("-----------------------");
    }
}

