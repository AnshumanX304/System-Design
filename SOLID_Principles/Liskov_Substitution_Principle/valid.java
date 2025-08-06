package SOLID_Principles.Liskov_Substitution_Principle;
// Better approach following LSP
abstract class Vehicle {
  // Common vehicle behaviors
 public
  void move() {
    // Movement logic
  }
}

abstract class EngineVehicle extends Vehicle {
 public
  void startEngine() {
    // Engine starting logic
  }
}

abstract class NonEngineVehicle extends Vehicle {
  // No engine-related methods
}

class Car extends EngineVehicle {
  @Override public void startEngine() {
    // Car-specific engine starting logic
  }
}

class Bicycle extends NonEngineVehicle {
  // Bicycle-specific methods
  // No need to implement engine-related methods
}


public class valid {
    public
    static void main(String[] args) {
        // Using EngineVehicle
        EngineVehicle car = new Car();
        car.startEngine();  // Output: Car-specific engine starting logic
        car.move();         // Output: Movement logic


        // Using NonEngineVehicle
        NonEngineVehicle bicycle = new Bicycle();
        bicycle.move();  // Output: Movement logic
    }
}


//We resolved the LSP issue by categorising the vehicles to different subclasses and defining methods 
//that can be used by all the child classes inheriting them
//We removed the startengine method inside vehicle and not all vehicles have engine 
//insted we defined two categories enginevehicle and nonenginevehicle from vehicle class 
//that will be inherited by respective vehicles 
//The new implementation can now allow child classes to implement the methods of parent classes seamlessly without 
// any exception handling on our own.


//Below is anothe brilliant example that follows LSP

abstract class Bird {
    abstract void makeSound();
}

interface FlyingBird {
    void fly();
}

class Sparrow extends Bird implements FlyingBird {
    @Override
    public void makeSound() {
        System.out.println("Chirp");
    }

    @Override
    public void fly() {
        System.out.println("Sparrow flies");
    }
}

class Penguin extends Bird {
    @Override
    public void makeSound() {
        System.out.println("Honk");
    }
}


//instead of putting fly() method in bird we made an interface for it as 
//not all birds can fly like penguin
//flightfull birds can inherit the class and implement the flyingbird interface to use fly method
//in this way all birds can inherit and use bird class without taking care of any invalid method invocation.