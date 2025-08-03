//BAD CODE VIOLATES SINGLE RESPONSIBILITY PRINCIPLE

package SOLID_Principles.Single_Responsibility;

class BreadBaker {
 public
  void bakeBread() { System.out.println("Baking high-quality bread..."); }

 public
  void manageInventory() { System.out.println("Managing inventory..."); }

 public
  void orderSupplies() { System.out.println("Ordering supplies..."); }

 public
  void serveCustomer() { System.out.println("Serving customers..."); }

 public
  void cleanBakery() { System.out.println("Cleaning the bakery..."); }

}

public class invalid {
    public static void main(String[] args){
        BreadBaker baker = new BreadBaker();
        baker.bakeBread();
        baker.manageInventory();
        baker.orderSupplies();
        baker.serveCustomer();
        baker.cleanBakery();
    }
}


//In this the bread baker class violates single responsibility principle
//The bread baker class should have just one functionality i.e to bake bread 
//but it is doing different works like inventory management, supply management
//customer management which is not advisible.

//A class must adhere to a single funcitonality and its method must 
//be written to follow that functionality only.

//The coupling of different unrelated functionalities together leads to maintainability issues 
//hence this type of code should be avoided.
