//This is a much better code that follows Open/Closed principle the class Shape is a generic abstract class 
//that carries common methods of every shape, which can be extended to any shape type 
//The shapes are defined into different classes which extend the shape class and can be used
//to override the common shape methods depending on the usecase of every shape.
//In this way we can extend the shape class without modification of another type, this also solves the 
//problem of coupling and each shape is independent of other 

// This approach prevents breaking existing code and encourages the creation of reusable components.


package SOLID_Principles.Open_Closed_Principle;
abstract class Shape{
    abstract public double CalculateArea();
}

class Circle extends Shape{
    private
    double radius;
    Circle(double radius){
        this.radius=radius;
    }
    @Override
    public double CalculateArea(){
        return Math.PI * radius * radius;
    }
}
 
class Rectangle extends Shape{
    private
    double length;
    private
    double breadth;
    Rectangle(double length,double breadth){
        this.length=length;
        this.breadth=breadth;
    }
    @Override
    public double CalculateArea(){
        return length*breadth;
    }
}


class Triangle extends Shape{
    private
    double base;
    private 
    double height;
    Triangle(double base, double height){
        this.base=base;
        this.height=height;
    }
    @Override
    public double CalculateArea(){
        return 0.5*base*height;
    }
}

public class Valid {
    public static void main(String[] args){
        Circle c = new Circle(3.0);
        Rectangle r=new Rectangle(5,10);
        Triangle t=new Triangle(10,4);

        System.out.println(c.CalculateArea());
        System.out.println(r.CalculateArea());
        System.out.println(t.CalculateArea());



    }
}
