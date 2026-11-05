
"""
=========================================================
             ABSTRACT CLASSES IN PYTHON
=========================================================

Topics covered:
1. Normal (concrete) classes
2. Abstract classes
3. ABC (Abstract Base Class)
4. @abstractmethod
5. Abstract methods vs normal methods
6. Inheritance of abstract and normal methods
7. Using super() with abstract methods
8. Partially implemented abstract classes
9. Java vs Python comparison
"""

from abc import ABC, abstractmethod


# =========================================================
# 1. NORMAL CLASS (CONCRETE CLASS)
# =========================================================

# A normal class can be instantiated directly.
# It can contain methods with implementations.
# Python does not enforce any abstract method contract here.

class NormalAnimal:

    def make_sound(self):
        print("Animal makes a sound")


# We can directly create an object.
animal = NormalAnimal()
animal.make_sound()


# =========================================================
# 2. ABSTRACT CLASS
# =========================================================

# ABC stands for Abstract Base Class.
# Inheriting from ABC enables Python's abstract class mechanism.
#
# An abstract class:
# - Cannot be instantiated while abstract methods remain.
# - Can contain abstract methods.
# - Can contain normal methods.
# - Acts as a blueprint/contract for subclasses.

class Animal(ABC):

    # ABSTRACT METHOD
    # @abstractmethod marks this method as mandatory.
    # Concrete subclasses MUST override it before instantiation.
    #
    # IMPORTANT:
    # An abstract method CAN contain an implementation.
    @abstractmethod
    def make_sound(self):
        print("Animal makes a sound")

    # NORMAL METHOD
    # This method already has an implementation.
    # Subclasses inherit it and can use it directly.
    # They are NOT forced to override it.

    def sleep(self):
        print("Animal is sleeping")


# We cannot instantiate Animal because make_sound()
# is an abstract method.
#
# animal = Animal()  # TypeError!


# =========================================================
# 3. CONCRETE CHILD CLASS
# =========================================================

class Dog(Animal):

    # Dog MUST implement the abstract method make_sound().
    def make_sound(self):
        print("Dog barks")


# Dog has implemented all abstract methods.
# Therefore, we can instantiate it.

dog = Dog()

dog.make_sound()  # Dog barks

# sleep() is a normal method inherited from Animal.
# No need to override it.
dog.sleep()       # Animal is sleeping


# =========================================================
# 4. WHAT HAPPENS IF CHILD DOES NOT IMPLEMENT
#    THE ABSTRACT METHOD?
# =========================================================

class Cat(Animal):

    # We intentionally do NOT implement make_sound().
    pass


# This raises TypeError because Cat still has
# an unimplemented abstract method.
#
# cat = Cat()


# =========================================================
# 5. ABSTRACT METHOD WITH IMPLEMENTATION + super()
# =========================================================

# An abstract method can have actual code inside it.
#
# Even if the parent abstract method has an implementation,
# the child MUST override it before it can be instantiated.
#
# super() allows the child to call the parent's implementation.

class Bird(Animal):

    def make_sound(self):

        # Calls the implementation of make_sound()
        # defined inside the parent class Animal.
        super().make_sound()

        # Child can add its own functionality as well.
        print("Bird chirps")


bird = Bird()
bird.make_sound()

# Output:
# Animal makes a sound
# Bird chirps


# =========================================================
# 6. ABSTRACT CLASS CAN HAVE BOTH TYPES OF METHODS
# =========================================================

class Vehicle(ABC):

    # Abstract method: Child must override it.
    @abstractmethod
    def start_engine(self):
        pass

    # Normal method: Child inherits it directly.
    def stop_engine(self):
        print("Engine stopped")


class Car(Vehicle):

    def start_engine(self):
        print("Car engine started")


car = Car()

car.start_engine()
car.stop_engine()


# =========================================================
# 7. ABSTRACT CLASS CAN BE INHERITED BY ANOTHER
#    ABSTRACT CLASS
# =========================================================

# A subclass does NOT have to implement all abstract methods
# immediately if it is itself intended to remain abstract.

class ElectricVehicle(Vehicle, ABC):

    # We implement start_engine() here.
    def start_engine(self):
        print("Electric vehicle started")

    # We introduce another abstract method.
    @abstractmethod
    def charge(self):
        pass


# ElectricVehicle is still abstract because charge()
# has not been implemented.
#
# ev = ElectricVehicle()  # TypeError!


class Tesla(ElectricVehicle):

    def charge(self):
        print("Tesla is charging")


tesla = Tesla()
tesla.start_engine()
tesla.charge()
tesla.stop_engine()


# =========================================================
# 8. REAL-WORLD EXAMPLE: PAYMENT SYSTEM
# =========================================================

# Abstract class defines a common contract for all
# payment implementations.
#
# Every payment method MUST implement pay().
# Every payment method can reuse generate_receipt().

class Payment(ABC):

    @abstractmethod
    def pay(self, amount):

        # Abstract methods can contain common functionality.
        print(f"Processing payment of {amount}")

    def generate_receipt(self):
        print("Receipt generated")


class CreditCardPayment(Payment):

    def pay(self, amount):

        # Reuse parent's implementation.
        super().pay(amount)

        print("Payment made using Credit Card")


class UPIPayment(Payment):

    def pay(self, amount):

        # Reuse parent's implementation.
        super().pay(amount)

        print("Payment made using UPI")


payment1 = CreditCardPayment()
payment1.pay(1000)
payment1.generate_receipt()

print()

payment2 = UPIPayment()
payment2.pay(500)
payment2.generate_receipt()


# =========================================================
# 9. ABSTRACT CLASS VS NORMAL CLASS
# =========================================================

"""
NORMAL CLASS:
- Can be instantiated directly.
- Can contain normal methods.
- Does not enforce abstract method implementation.

ABSTRACT CLASS:
- Cannot be instantiated while abstract methods remain.
- Can contain abstract AND normal methods.
- Concrete subclasses must implement inherited abstract methods.
- Provides a common contract and shared functionality.
"""


# =========================================================
# 10. JAVA VS PYTHON
# =========================================================

"""
JAVA                         PYTHON
-------------------------------------------------------
abstract class Animal        class Animal(ABC)

abstract void sound()        @abstractmethod
                             def sound(self):

extends Animal               class Dog(Animal)

super.sound()                super().sound()

Python does not have a separate interface keyword.
ABC is commonly used to implement interface-like contracts.
"""


# =========================================================
# FINAL REVISION NOTES
# =========================================================

"""
1. ABC:
   Enables the abstract base class mechanism.

2. @abstractmethod:
   Marks a method as abstract.
   Concrete subclasses must override it.

3. Abstract class:
   Can contain both abstract and normal methods.

4. Inheritance:
   Both abstract and normal methods are inherited.

5. Abstract methods:
   Must be overridden before a concrete subclass
   can be instantiated.

6. Normal methods:
   Can be used directly without overriding.

7. super():
   Allows a child class to reuse the parent's implementation.

8. Abstract methods with implementation:
   An abstract method can contain actual code.
   The child still has to override it.

9. Abstract subclasses:
   Can leave some abstract methods unimplemented.
   They remain abstract until all are implemented.

10. MAIN TAKEAWAY:
    Abstract classes provide TWO things:

    - CONTRACT: What child classes MUST implement.
    - CODE REUSE: Common functionality child classes
      can inherit and use directly.
"""
