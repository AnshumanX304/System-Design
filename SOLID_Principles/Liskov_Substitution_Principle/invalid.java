package SOLID_Principles.Liskov_Substitution_Principle;

// Problematic approach that violates LSP
class Vehicle {
 public
  void startEngine() {
    // Engine starting logic
  }
}

class Car extends Vehicle {
  @Override public void startEngine() {
    // Car-specific engine starting logic
  }
}

class Bicycle extends Vehicle {
  @Override public void startEngine() {
    // Problem: Bicycles don't have engines!
    throw new UnsupportedOperationException("Bicycles don't have engines");
  }
}

public class invalid {
    public
    static void main(String[] args) {
        // Creating objects of different subclasses
        Vehicle car = new Car();
        Vehicle bicycle = new Bicycle();
        // Using polymorphism
        System.out.println("Car:");
        car.startEngine();  // Output: Car engine started.
        System.out.println("nBicycle:");
        try {
        bicycle.startEngine();  // Throws UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
        System.out.println("Error: " + e.getMessage());
        }
    }    
}

//This is not a good example of liskov's substitution principle as Bicycle class extending the vehicle class cannot 
//fulfill its startengine method.
//So we need to define a class structure such that its follows lipkov's substitution principle.
