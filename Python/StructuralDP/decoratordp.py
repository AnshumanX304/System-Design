from abc import ABC,abstractmethod

class ICharacter(ABC):
    @abstractmethod
    def get_abilities(self):
        pass

class Mario(ICharacter):

    def get_abilities(self):
        return "I am a normal Mario"

class CharacterDecorator(ICharacter):

    def __init__(self, character: ICharacter):
        self.character = character

    @abstractmethod
    def get_abilities(self):
        pass


class HeightUpDecorator(CharacterDecorator):

    def get_abilities(self):
        return self.character.get_abilities() + " with heightup,"

class SpeedupDecorator(CharacterDecorator):

    def get_abilities(self):
        return self.character.get_abilities() + " with speedup,"


class GunupDecorator(CharacterDecorator):

    def get_abilities(self):
        return self.character.get_abilities() + " with gun,"

if __name__ == "__main__":

    mario = Mario()

    mario = HeightUpDecorator(mario)
    mario = GunupDecorator(mario)
    mario = SpeedupDecorator(mario)

    print(mario.get_abilities())


# Decorator Pattern: Wrap an existing object with other objects implementing the same interface to dynamically
# add responsibilities without modifying the original object.