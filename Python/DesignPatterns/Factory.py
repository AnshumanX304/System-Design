
from abc import ABC, abstractmethod

class Vehicle(ABC):
    @abstractmethod
    def start(self):
        pass

    @abstractmethod
    def stop(self):
        pass


class Car(Vehicle):

    def start(self):
        print("Car is starting !!")

    def stop(self):
        print("Car is stopped !!")

    # PUBLIC METHOD: Accessible from anywhere
    def check_fuel(self):
        print("Checking fuel !!")

    # PROTECTED METHOD: Single leading underscore
    # Conventionally intended for internal use and subclasses
    def _check_engine(self):
        print("Checking engine !!")

    # PRIVATE METHOD: Double leading underscore
    # Python performs name mangling on this method
    def __check_battery(self):
        print("Checking battery !!")

    def service(self):
        # All three methods can be accessed from within the class
        self.check_fuel()
        self._check_engine()
        self.__check_battery()


class Bus(Vehicle):

    def start(self):
        print("Bus is starting !!")

    def stop(self):
        print("Bus is stopped !!")


class VehicleFactory:
    @staticmethod
    def get_vehicle(vehicletype: str):
        if vehicletype == "Car":
            return Car()
        elif vehicletype == "Bus":
            return Bus()
        else:
            raise ValueError("Unknown vehicle type")


if __name__ == "__main__":

    vehicle1 = VehicleFactory.get_vehicle("Car")

    vehicle1.start()
    vehicle1.stop()

    vehicle1.service()

    # PUBLIC: Accessible
    vehicle1.check_fuel()

    # PROTECTED: Accessible, but convention says don't access externally
    vehicle1._check_engine()

    # PRIVATE: Direct access raises AttributeError
    # vehicle1.__check_battery()

    # NAME MANGLING: Technically accessible
    vehicle1._Car__check_battery()

    vehicle2 = VehicleFactory.get_vehicle("Bus")
    vehicle2.start()
    vehicle2.stop()
