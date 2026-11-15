from abc import ABC,abstractmethod


class PaymentStrategy(ABC):
    @abstractmethod
    def make_payment(self):
        pass

class CreditCardPayment(PaymentStrategy):

    def make_payment(self):
        print("Credit card payment completed !!")

class DebitCardPayment(PaymentStrategy):

    def make_payment(self):
        print("Debit card payment completed !!")

class CryptoPayment(PaymentStrategy):

    def make_payment(self):
        print("Crypto payment completed !!")

class PaymentProcessor():

    def __init__(self,payment_strategy:PaymentStrategy):
        self.__payment_strategy=payment_strategy

    def set_payment_strategy(self,payment_strategy:PaymentStrategy):
        self.__payment_strategy=payment_strategy

    def ProcessPayment(self):
        self.__payment_strategy.make_payment()

if __name__=="__main__":
    credit_card = CreditCardPayment()
    debit_card =DebitCardPayment()
    crypto=CryptoPayment()

    payment_processor = PaymentProcessor(credit_card)
    payment_processor.ProcessPayment()

    payment_processor.set_payment_strategy(debit_card)
    payment_processor.ProcessPayment()

    payment_processor.set_payment_strategy(crypto)
    payment_processor.ProcessPayment()



    
