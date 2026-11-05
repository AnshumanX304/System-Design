from abc import ABC, abstractmethod


# ============================================================
# ABSTRACTION
# ============================================================

# NotificationService is an abstract class.
# It defines a contract that every notification service
# must follow.
#
# Any class inheriting NotificationService MUST implement
# the sendNotification() method.
#
# It does not care HOW the notification is sent.
# It only defines WHAT functionality must be provided.

class NotificationService(ABC):

    @abstractmethod
    def sendNotification(self, message: str) -> None:
        pass


# ============================================================
# LOW-LEVEL MODULE
# ============================================================

# EmailNotifier is a low-level module because it contains
# implementation details (HOW notifications are sent).
#
# It inherits NotificationService, meaning it agrees to
# follow the contract defined by the abstraction.
#
# Here, we implement the sendNotification() method
# specifically for sending emails.

class EmailNotifier(NotificationService):

    def sendNotification(self, message: str) -> None:
        print(f"Email sent: {message}")


# ============================================================
# HIGH-LEVEL MODULE
# ============================================================

# OrderService is a high-level module because it contains
# business logic (placing an order).
#
# Notice that OrderService does NOT depend on EmailNotifier.
# Instead, it depends on the NotificationService abstraction.
#
# This is the KEY idea behind the Dependency Inversion Principle.

class OrderService:

    # Constructor Injection (Dependency Injection)
    #
    # Instead of creating an EmailNotifier object inside
    # OrderService, we receive a notification service
    # from outside through the constructor.
    #
    # The type hint NotificationService indicates that
    # any object implementing this abstraction can be passed.
    #
    # This makes OrderService independent of the actual
    # notification mechanism.

    def __init__(self, notifier: NotificationService):

        # Store the injected dependency.
        # We don't know whether it is an EmailNotifier,
        # SMSNotifier, or some other implementation.
        self.notifier = notifier

    # Business logic for placing an order.
    def place_order(self) -> None:

        print("Order placed successfully!")

        # Call the method defined by the abstraction.
        #
        # OrderService does not need to know HOW the
        # notification is sent.
        #
        # The actual implementation is determined by
        # the object injected into the constructor.
        self.notifier.sendNotification(
            "Your order has been placed!"
        )


# ============================================================
# MAIN / APPLICATION ENTRY POINT
# ============================================================

if __name__ == "__main__":

    # Create an object of the low-level implementation.
    email_notifier = EmailNotifier()

    # Inject EmailNotifier into OrderService.
    #
    # OrderService receives the dependency from outside
    # instead of creating it internally.
    #
    # This is Constructor Dependency Injection.
    os = OrderService(email_notifier)

    # Execute the business logic.
    os.place_order()