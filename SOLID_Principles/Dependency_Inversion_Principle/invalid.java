package SOLID_Principles.Dependency_Inversion_Principle;


// Problematic approach that violates DIP
class EmailNotifier {
  public void sendEmail(String message) {
    // Configure SMTP
    // Set up email templates
    // Send email implementation
    System.out.println("Email sent: " + message);
    
  }
}

class OrderService {
  private EmailNotifier emailNotifier;
  public OrderService() {
    // Direct dependencies on concrete implementations
    this.emailNotifier = new EmailNotifier();
  }
  public void placeOrder() {
        System.out.println("Order placed successfully!");
        // Directly using the low-level module
        emailNotifier.sendEmail("Your order has been placed!");
    }

}

public class invalid {
    public static void main(String[] args){
        OrderService os=new OrderService();
        os.placeOrder();
    }
}


// This code violates the Dependency Inversion Principle (DIP) because the
// high-level module (OrderService) depends directly on a low-level module 
// (EmailNotifier) — a concrete class.

// According to DIP:
// 1. High-level modules should depend on abstractions, not concrete classes.
// 2. Abstractions should not depend on details, but details should depend on abstractions.

// Why is this bad?
// If we want to change the notification method (e.g., send SMS instead of email),
// we would have to modify OrderService directly, breaking the Open/Closed Principle as well.
// This creates tight coupling between OrderService and EmailNotifier, making the
// code harder to maintain, extend, and test.

// A better approach:
// Define a Notifier interface with a sendNotification method.
// Let EmailNotifier, SmsNotifier, etc. implement that interface.
// Make OrderService depend on the Notifier abstraction.
// This way, we can switch implementations without touching OrderService.
