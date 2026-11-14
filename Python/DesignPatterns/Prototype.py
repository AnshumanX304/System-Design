
import copy


# ==========================================
# 1. CHARACTER CLASS
# ==========================================

class Character:

    # Constructor: Called whenever we create a Character
    # directly using Character(...).
    #
    # It initializes the object's attributes.
    def __init__(self, name, health, attack_power, level):
        self.name = name
        self.health = health
        self.attack_power = attack_power
        self.level = level

    def show_character_info(self):
        print(
            f"Character [Name={self.name}, "
            f"Health={self.health}, "
            f"AttackPower={self.attack_power}, "
            f"Level={self.level}]"
        )


# ==========================================
# 2. CHARACTER FACTORY
# ==========================================

class CharacterFactory:

    def __init__(self):

        # Creating a prototype object.
        #
        # A prototype is simply an existing object
        # that we will use as a template to create
        # other objects by copying it.
        #
        # We initialize this object only once when
        # the factory is created.
        self.prototype_character = Character(
            "DefaultName", 100, 50, 1
        )

    def create_character_with_new_name(self, name):

        # copy.copy() creates a NEW object by copying
        # the attributes of the existing prototype.
        #
        # This is called SHALLOW COPYING.
        #
        # The new object has its own identity and
        # its own attributes. Changing its attributes
        # will not change the prototype's attributes.
        #
        # Unlike normal object creation, copy.copy()
        # does not call the Character constructor
        # (__init__) again.
        cloned_character = copy.copy(self.prototype_character)

        # We modify the cloned object's name.
        #
        # We do NOT modify the original prototype.
        # This ensures that the prototype remains
        # unchanged for future cloning operations.
        cloned_character.name = name

        return cloned_character

    def create_character_with_new_level(self, level):

        # Create another independent copy of the
        # original prototype.
        cloned_character = copy.copy(self.prototype_character)

        # Customize only the cloned object's level.
        cloned_character.level = level

        return cloned_character


# ==========================================
# 3. MAIN METHOD
# ==========================================

if __name__ == "__main__":

    # Creating the factory.
    #
    # Its constructor creates one prototype object
    # with the default character configuration.
    factory = CharacterFactory()

    # Clone the prototype and change its name.
    hero = factory.create_character_with_new_name("Warrior")

    # Clone the SAME original prototype and
    # change its level.
    boss = factory.create_character_with_new_level(10)

    hero.show_character_info()
    boss.show_character_info()


# ==========================================
# 4. IMPORTANT CONCEPTS TO REMEMBER
# ==========================================

# NORMAL OBJECT CREATION:
#
# hero = Character("Warrior", 100, 50, 1)
#
# Python creates a new object and calls __init__()
# to initialize its attributes.
#
#
# PROTOTYPE-BASED OBJECT CREATION:
#
# hero = copy.copy(factory.prototype_character)
#
# Python creates a new object by copying the
# attributes of an already existing object.
#
# The constructor (__init__) is not called again
# by the default copy.copy() implementation.
#
#
# SHALLOW COPY vs DEEP COPY:
#
# copy.copy() performs a shallow copy.
#
# It creates a new outer object, but if that object
# contains other objects as attributes, their
# references are copied rather than the objects
# themselves.
#
# For example:
#
# If Character contains a Weapon object, both
# the original Character and its clone will
# reference the SAME Weapon object.
#
#
# copy.deepcopy() performs a deep copy.
#
# It creates a new outer object and recursively
# copies nested objects as well.
#
# Therefore, the original and cloned Character
# will have independent Weapon objects.
#
#
# WHY USE THE PROTOTYPE DESIGN PATTERN?
#
# Imagine creating hundreds of characters with
# almost identical configurations.
#
# Instead of repeatedly initializing every
# character from scratch, we can:
#
# 1. Create one fully configured prototype.
# 2. Clone it whenever we need a new character.
# 3. Modify only the properties that are different.
#
# This is particularly useful when object creation
# involves expensive initialization or complex
# configurations.
#
#
# JAVA EQUIVALENT:
#
# Java:
# implements Cloneable
# super.clone()
#
# Python:
# import copy
# copy.copy(object)
#
# Both approaches can perform shallow copying.
#
# Python does not require a Cloneable interface
# or a dedicated clone() method.
