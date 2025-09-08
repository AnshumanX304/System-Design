package DesignPatterns.Structural_Design_Patterns.Bridge_Design_Pattern;

abstract class Car {
    protected Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public abstract void drive();
}

interface Engine {
    void start();
}

class Sedan extends Car {
    public Sedan(Engine engine) {
        super(engine);
    }

    @Override
    public void drive() {
        System.out.println("You are driving a Sedan!");
        engine.start();
    }
}

class Hatchback extends Car {
    public Hatchback(Engine engine) {
        super(engine);
    }

    @Override
    public void drive() {
        System.out.println("You are driving a Hatchback!");
        engine.start();
    }
}

class Suv extends Car {
    public Suv(Engine engine) {
        super(engine);
    }

    @Override
    public void drive() {
        System.out.println("You are driving a Suv!");
        engine.start();
    }
}

class DieselEngine implements Engine {
    @Override
    public void start() {
        System.out.println("You are running a Diesel Engine!");
    }
}

class PetrolEngine implements Engine {
    @Override
    public void start() {
        System.out.println("You are running a Petrol Engine!");
    }
}

class Ev implements Engine {
    @Override
    public void start() {
        System.out.println("You are running an EV!");
    }
}

public class valid {
    public static void main(String[] args) {
        Car sedanDiesel = new Sedan(new DieselEngine());
        Car sedanEv = new Sedan(new Ev());

        sedanDiesel.drive();
        sedanEv.drive();
    }
}
