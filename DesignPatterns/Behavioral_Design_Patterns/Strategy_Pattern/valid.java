package DesignPatterns.Behavioral_Design_Patterns.Strategy_Pattern;

interface PaymentStrategy{
    void processPayment();
}

class CreditCardPayment implements PaymentStrategy{
    public void processPayment(){
        System.out.println("Credit Card Payment is being processed !!");
    }
}
class DebitCardPayment implements PaymentStrategy{
    public void processPayment(){
        System.out.println("Debit Card Payment is being processed !!");
    }
}
class CryptoPayment implements PaymentStrategy{
    public void processPayment(){
        System.out.println("Crypto Payment is being processed !!");
    }
}


class PaymentProcessor{
    private PaymentStrategy paymentstrategy;


    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentstrategy = paymentStrategy;
    }
    public void setPaymentStrategy(PaymentStrategy paymentstrategy){
        this.paymentstrategy=paymentstrategy;
    }

    public void ProcessPayment(){
        paymentstrategy.processPayment();
    }
}
public class valid {
    public static void main(String [] args){
        PaymentStrategy creditCard  = new CreditCardPayment();
        PaymentStrategy debitCard  = new DebitCardPayment();
        PaymentStrategy crypto  = new CryptoPayment();
        //Initially using credit card for processing payment
        PaymentProcessor paymentprocessor=new PaymentProcessor(creditCard);
        paymentprocessor.ProcessPayment(); 
        //Switching to dedit card for processing strategy
        paymentprocessor.setPaymentStrategy(debitCard);
        paymentprocessor.ProcessPayment(); 
        //Switching to crypto for processing strategy
        paymentprocessor.setPaymentStrategy(crypto);
        paymentprocessor.ProcessPayment(); 

    }
}
