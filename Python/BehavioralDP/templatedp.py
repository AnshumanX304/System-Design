from abc import ABC,abstractmethod

class BeverageWithHook(ABC):

    def prepare_recipe(self):
        self.boil_water()
        self.brew()
        self.pour_in_cup()
        if self.customer_wants_condiments():
            self.add_condiments()

    def boil_water(self):
        print("Water is boiling !!")

    def pour_in_cup(self):
        print("Pouring in cup !!")

    @abstractmethod
    def brew(self):
        pass

    @abstractmethod
    def add_condiments(self):
        pass

    def customer_wants_condiments(self):
        return True


class CustomCoffee(BeverageWithHook):

    def brew(self):
        print("Brewing coffee...")

    def add_condiments(self):
        print("Adding sugar and milk...")

    # This customer doesn't want condiments
    def customer_wants_condiments(self):
        return False


class CustomTea(BeverageWithHook):

    def brew(self):
        print("Steeping the tea...")

    def add_condiments(self):
        print("Adding lemon...")

    def customer_wants_condiments(self):
        return True


if __name__ == "__main__":

    coffee = CustomCoffee()
    print("Making custom coffee...")
    coffee.prepare_recipe()

    print("\nMaking custom tea...")
    tea = CustomTea()
    tea.prepare_recipe()



# Template Method Pattern: Define the skeleton of an algorithm in the parent class, letting child classes implement or 
# customize specific steps without changing the overall sequence.

# Hook: An optional method that lets subclasses influence a step or decision in the parent algorithm.