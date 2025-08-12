package DesignPatterns.Creational_Design_Patterns.Factory_Design_Pattern;

interface Vehicle{
    void start();
    void stop();
}

class Car implements Vehicle {
  public void start() {
    System.out.println("Car is starting...");
  }
  public void stop() {
    System.out.println("Car is stopping...");
  }
}
class Truck implements Vehicle {
    public void start(){
        System.out.println("The truck has started !!");
    }
    public void stop() {
        System.out.println("Truck is stopping...");
  }

}

class Taxi implements Vehicle {
    public void start(){
        System.out.println("The taxi has started !!");
    }
    public void stop() {
        System.out.println("Taxi is stopping...");
  }

}

public class invalid {
    public static void main(String[] args){
      Vehicle car=new Car();
      car.start();
      car.stop();
      Vehicle truck=new Truck();
      truck.start();
      truck.stop();
      Vehicle taxi=new Taxi();
      taxi.start();
      taxi.stop();


    }
}
