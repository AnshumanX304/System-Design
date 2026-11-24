
from abc import ABC, abstractmethod


# Component: Common interface for Mario and all decorators
class ICharacter(ABC):

    @abstractmethod
    def get_abilities(self):
        pass


# Concrete Component: Original object
class Mario(ICharacter):

    def get_abilities(self):
        return "I am a normal Mario"


# Base Decorator: Common parent for all concrete decorators
class CharacterDecorator(ICharacter):

    def __init__(self, character: ICharacter):

        # Stores the existing character object inside this decorator.
        # This object could be Mario OR another decorator object.
        self.character = character

    @abstractmethod
    def get_abilities(self):
        pass


# Concrete Decorator 1
class HeightUpDecorator(CharacterDecorator):

    def get_abilities(self):

        # Calls get_abilities() on the object stored inside self.character.
        # Initially, this will call Mario.get_abilities().
        # Then appends its own additional functionality.
        return self.character.get_abilities() + " with heightup,"


# Concrete Decorator 2
class SpeedupDecorator(CharacterDecorator):

    def get_abilities(self):

        # Calls get_abilities() on the wrapped object.
        # If GunupDecorator is wrapped inside this object,
        # it calls GunupDecorator.get_abilities().
        return self.character.get_abilities() + " with speedup,"


# Concrete Decorator 3
class GunupDecorator(CharacterDecorator):

    def get_abilities(self):

        # Calls get_abilities() on the wrapped object.
        # If HeightUpDecorator is wrapped inside this object,
        # it calls HeightUpDecorator.get_abilities().
        return self.character.get_abilities() + " with gun,"


if __name__ == "__main__":

    mario = Mario()

    # Creates HeightUpDecorator.
    # Its constructor stores the original Mario object in self.character.
    mario = HeightUpDecorator(mario)

    # Creates GunupDecorator.
    # Its constructor stores the HeightUpDecorator OBJECT in self.character.
    # No get_abilities() method is called during this wrapping.
    mario = GunupDecorator(mario)

    # Creates SpeedupDecorator.
    # Its constructor stores the GunupDecorator OBJECT in self.character.
    # Again, no get_abilities() method is called yet.
    mario = SpeedupDecorator(mario)

    # ACTUAL METHOD EXECUTION STARTS HERE!

    # mario currently references SpeedupDecorator.
    # SpeedupDecorator.get_abilities() executes first.
    #
    # self.character.get_abilities() calls GunupDecorator.get_abilities().
    # GunupDecorator then calls HeightUpDecorator.get_abilities().
    # HeightUpDecorator finally calls Mario.get_abilities().
    #
    # Results return in reverse order:
    # Mario -> HeightUp -> Gun -> Speed
    print(mario.get_abilities())


# Decorator Pattern:
# Wrap an existing object with other objects implementing the same interface
# to dynamically add responsibilities without modifying the original object.
