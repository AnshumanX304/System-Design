
from __future__ import annotations


# Product class:
# This is the actual object we want to create.
# It contains the attributes and business logic of a Car.
class Car:
    def __init__(
        self,
        color: str = "red",
        sunroof: bool = False,
        wheels: int = 4
    ):
        # Default values are assigned during object initialization.
        self.color = color
        self.sunroof = sunroof
        self.wheels = wheels

    # Defines how the Car object should be represented when printed.
    def __str__(self):
        return (
            f"Car(color={self.color}, "
            f"sunroof={self.sunroof}, "
            f"wheels={self.wheels})"
        )


# Builder class:
# Responsible for constructing a Car object step by step.
# It separates the object construction logic from the Car class.
class CarBuilder:

    def __init__(self):
        # Create and store a default Car object.
        # The builder will progressively configure this object.
        self.car = Car()

    # Each setter method configures one attribute of the Car.
    # It returns 'self' (the current CarBuilder instance).
    # This allows method chaining.
    def add_color(self, color: str) -> CarBuilder:
        self.car.color = color
        return self

    def add_sunroof(self, sunroof: bool) -> CarBuilder:
        self.car.sunroof = sunroof
        return self

    def add_wheels(self, wheels: int) -> CarBuilder:
        self.car.wheels = wheels
        return self

    # Final construction step:
    # Returns the configured Car object, NOT the builder instance.
    def build(self) -> Car:
        return self.car


if __name__ == "__main__":

    # Step-by-step object construction using method chaining.
    #
    # 1. CarBuilder() creates a builder instance.
    # 2. add_color() configures the car's color.
    # 3. add_sunroof() configures the sunroof.
    # 4. add_wheels() configures the number of wheels.
    # 5. build() returns the final Car object.
    #
    # Notice that each add_* method returns the builder itself,
    # allowing us to chain the next configuration method.

    car = (
        CarBuilder()
        .add_color("Yellow")
        .add_sunroof(True)
        .add_wheels(4)
        .build()
    )

    print(car)
