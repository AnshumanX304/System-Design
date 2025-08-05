
//This is a bad code, the code here is tightly coupled 
//all the shapes types are coupled into a single class and if we want to add a 
//method or change existing techniques we'll be MODIFYING the existing class  
//shape but our code should be open to extension not modification so we need
//to build a structure that should be open to extenstion for any new shape 
//or even if there is any modification it should be there in the type of shape itself 
//rather than the entire shape class. 

package SOLID_Principles.Open_Closed_Principle;

class Shape {
    public String type;
    public double radius;
    public double length;
    public double breadth;

    public double calculateArea() {
        if (type.equals("circle")) {
            return Math.PI * radius * radius;
        } else if (type.equals("rectangle")) {
            return length * breadth;
        } else {
            throw new UnsupportedOperationException("Unknown shape type");
        }
    }
}

public class Invalid {
    public static void main(String[] args) {
        // Circle example
        Shape circle = new Shape();
        circle.type = "circle";
        circle.radius = 5;
        System.out.println("Area of circle: " + circle.calculateArea());

        // Rectangle example
        Shape rectangle = new Shape();
        rectangle.type = "rectangle";
        rectangle.length = 4;
        rectangle.breadth = 6;
        System.out.println("Area of rectangle: " + rectangle.calculateArea());

        // If we now want to add triangle — we’ll have to modify Shape again, violating OCP
    }
}

