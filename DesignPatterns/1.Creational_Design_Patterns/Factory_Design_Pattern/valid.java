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

class VehicleFactory{
    public static Vehicle getVehicle(String vehicleType){
        if (vehicleType.equals("Car")) {
            return new Car();
        } else if (vehicleType.equals("Truck")) {
            return new Truck();
        } else if (vehicleType.equals("Bike")) {
            return new Taxi();
        } else {
            throw new IllegalArgumentException("Unknown vehicle type");
        }
    }
}

public class valid {
    public static void main(String [] args){
        Vehicle vehicle1 = VehicleFactory.getVehicle("Car");
        vehicle1.start();
        vehicle1.stop();
        Vehicle vehicle2 = VehicleFactory.getVehicle("Truck");
        vehicle2.start();
        vehicle2.stop();
        Vehicle vehicle3 = VehicleFactory.getVehicle("Bike");
        vehicle3.start();
        vehicle3.stop();
    }
}
