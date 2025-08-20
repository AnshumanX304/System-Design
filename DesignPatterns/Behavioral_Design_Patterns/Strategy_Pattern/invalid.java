package DesignPatterns.Behavioral_Design_Patterns.Strategy_Pattern;

// Without Strategy Pattern
// All payment logic is hardcoded in PaymentProcessor using if-else.

class PaymentProcessor {

    private String paymentType;

    public PaymentProcessor(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void processPayment() {
        // Problem 1: Too many if-else blocks
        if (paymentType.equalsIgnoreCase("CREDIT")) {
            System.out.println("Credit Card Payment is being processed !!");
        } else if (paymentType.equalsIgnoreCase("DEBIT")) {
            System.out.println("Debit Card Payment is being processed !!");
        } else if (paymentType.equalsIgnoreCase("CRYPTO")) {
            System.out.println("Crypto Payment is being processed !!");
        } else {
            System.out.println("Invalid payment method!");
        }

        // Problem 2: Violates Open/Closed Principle
        // If we add a new payment method (like UPI, PayPal),
        // we MUST modify this class again → breaking existing code.

        // Problem 3: Code Duplication & Complexity
        // All payment logic is stuffed here, making PaymentProcessor harder to maintain.
    }
}

public class invalid {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor("CREDIT");
        processor.processPayment();

        // Switching to Debit Card → requires changing string value
        processor.setPaymentType("DEBIT");
        processor.processPayment();

        // Switching to Crypto
        processor.setPaymentType("CRYPTO");
        processor.processPayment();
    }
}
