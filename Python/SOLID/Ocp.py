import math
from abc import ABC, abstractmethod

# bad code
class ShapeBad:
    def __init__(
        self,
        length:str,
        breadth : float = 0,
        type: float = 0,
        radius: float = 0
    ):
        self.length=length
        self.breadth=breadth
        self.type=type
        self.radius=radius

    def calculate_area(self):
        if type == "circle":
            return math.pi*self.radius**2
        elif self.type == "rectangle":
            return self.length * self.breadth
        else:
            raise ValueError("Unknown shape type")

# good code


# ABC stands for Abstract Base Class.
# Inheriting from ABC lets Python enforce abstract-method rules.
# Shape defines a common interface for all shapes.

class ShapeGood(ABC):

    # @abstractmethod marks this method as mandatory
    # for concrete subclasses of Shape.
    @abstractmethod
    def calculate_area(self) -> float:
        """Return the area of the shape."""
        pass

# Circle inherits from Shape.
# It must implement calculate_area() to be instantiated.
class Circle(ShapeGood):
    def __init__(self, radius: float):
        self.radius = radius

    def calculate_area(self) -> float:
        return math.pi * self.radius**2


class Rectangle(ShapeGood):
    def __init__(self, length: float, breadth: float):
        self.length = length
        self.breadth = breadth

    def calculate_area(self) -> float:
        return self.length * self.breadth


class Triangle(ShapeGood):
    def __init__(self, base: float, height: float):
        self.base = base
        self.height = height

    def calculate_area(self) -> float:
        return 0.5 * self.base * self.height


def print_area(shape: Shape) -> None:
    print(f"Area: {shape.calculate_area()}")



def main() -> None:
    circle = ShapeBad(type="circle", radius=5)
    print(f"Area of circle: {circle.calculate_area()}")

    rectangle = ShapeBad(type="rectangle", length=4, breadth=6)
    print(f"Area of rectangle: {rectangle.calculate_area()}")


    shapes: list[ShapeGood] = [
        Circle(5),
        Rectangle(4, 6),
        Triangle(3, 8),
    ]

    for shape in shapes:
        print_area(shape)



if __name__ == "__main__":
    main()