package DesignPatterns.Creational_Design_Patterns.Builder_Design_Pattern;


class Car{
    private String color;
    private boolean sunroof;
    private int wheels;

    public String color(){
        return color;
    }
    public boolean sunroof(){
        return sunroof;
    }

    public int wheels(){
        return wheels;
    }
    private Car(CarBuilder carbuilder){
        this.color=carbuilder.color;
        this.sunroof=carbuilder.sunroof;
        this.wheels=carbuilder.wheels;
    }

    public static class CarBuilder{
        private String color="Red";
        private boolean sunroof=false;
        private int wheels=4;

        public CarBuilder set_color(String color){
            this.color=color;
            return this;
        }

        public CarBuilder set_sunroof(boolean sunroof){
            this.sunroof=sunroof;
            return this;
        }
        public CarBuilder set_wheels(int wheels){
            this.wheels=wheels;
            return this;
        }

        public Car build_car(){
            Car car=new Car(this);
            return car;
        }

    }
}
public class builder {
    public static void main(String [] args){

        //Method 1 of using builder to create car instance
        Car.CarBuilder builder1 = new Car.CarBuilder()
        .set_color("Magenta")
        .set_sunroof(true)
        .set_wheels(4);

        
        Car car1=builder1.build_car();
        System.out.println(car1.color());
        System.out.println(car1.sunroof());
        System.out.println(car1.wheels());

        

        //Method 2 of using builder to create car instance
        Car.CarBuilder builder2 = new Car.CarBuilder();
        builder2.set_color("Purple");
        builder2.set_sunroof(false);
        builder2.set_wheels(6);

        Car car2=builder2.build_car();
        System.out.println(car2.color());
        System.out.println(car2.sunroof());
        System.out.println(car2.wheels());





    }
}
