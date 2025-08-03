//GOOD CODE AS IT FOLLOWS SRP EACH CLASS HAS A SINGLE RESPONSIBILITY TO TAKE CARE OF 

package SOLID_Principles.Single_Responsibility;


// Class for baking bread
class BreadBaker {
 public
  void bakeBread() { System.out.println("Baking high-quality bread..."); }
}

// Class for managing inventory
class InventoryManager {
 public
  void manageInventory() { System.out.println("Managing inventory..."); }
}

// Class for ordering supplies
class SupplyOrder {
 public
  void orderSupplies() { System.out.println("Ordering supplies..."); }
}

// Class for serving customers
class CustomerService {
 public
  void serveCustomer() { System.out.println("Serving customers..."); }
}

// Class for cleaning the bakery
class BakeryCleaner {
 public
  void cleanBakery() { System.out.println("Cleaning the bakery..."); }
}

public class valid {
    public static void main(String[] args) {
        BreadBaker baker = new BreadBaker();
        InventoryManager inventoryManager = new InventoryManager();
        SupplyOrder supplyOrder = new SupplyOrder();
        CustomerService customerService = new CustomerService();
        BakeryCleaner cleaner = new BakeryCleaner();

        baker.bakeBread();
        inventoryManager.manageInventory();
        supplyOrder.orderSupplies();
        customerService.serveCustomer();
        cleaner.cleanBakery();
  }
}


//This code structure assigns different functionalities to different classes
//i.e each class has a single responsibility to follow 
//any functionality change requires to follow just one class change and all other classes
//using that class will automatically use the changed code 

//Using SRP will allow developers to maintain code more efficiently 

//Each class has a clear and focused responsibility, making the system more modular and easier to manage.