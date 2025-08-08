package SOLID_Principles.Dependency_Inversion_Principle;

// Abstraction
interface NotificationService {
    void sendNotification(String message);
}

// Low-level module 1
class EmailNotifier implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Email sent: " + message);
    }
}

// High-level module
class OrderService {
    private final NotificationService notifier;

    public OrderService(NotificationService notifier) {
        // Depends on abstraction, not a concrete implementation
        this.notifier = notifier;
    }

    public void placeOrder() {
        System.out.println("Order placed successfully!");
        notifier.sendNotification("Your order has been placed!");
    }
}

public class valid {
    public static void main(String[] args) {
        NotificationService email_notification = new EmailNotifier();
        OrderService os = new OrderService(email_notification);
        os.placeOrder();
    }
}

/*
DESCRIPTION – Correct Implementation (DIP Compliant):

1. High-level module (OrderService) depends on abstraction (NotificationService) rather than concrete class.
2. Low-level module (EmailNotifier) also depends on the same abstraction by implementing NotificationService.
3. This allows loose coupling — we can easily swap EmailNotifier with any other notifier (e.g., SmsNotifier) without changing OrderService.
4. The code is open for extension but closed for modification (OCP) since new notifiers can be added without modifying existing classes.
5. Follows DIP because details (Email sending) depend on abstraction, not the other way around.
*/
