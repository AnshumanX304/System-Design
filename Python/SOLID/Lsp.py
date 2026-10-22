
# INVALID: Violates LSP
class VehicleBad:
    def start_engine(self) -> None:
        """Starts the engine."""
        pass


class Car(VehicleBad):
    def start_engine(self) -> None:
        print("Car engine started.")


class Bicycle(VehicleBad):
    def start_engine(self) -> None:
        # Violates LSP because a Bicycle cannot fulfill
        # the engine-starting behavior expected from VehicleBad.
        raise NotImplementedError("Bicycles don't have engines.")


# VALID: Follows LSP
class VehicleGood:
    def move(self) -> None:
        print("It goes vroommm..... !")


class EngineVehicle(VehicleGood):
    def start_engine(self) -> None:
        print("Engine started !!")


class NonEngineVehicle(VehicleGood):
    """Vehicles without engines; no engine-related methods."""


class CarGood(EngineVehicle):
    def start_engine(self) -> None:
        # Without super(): executes only this implementation.
        # super().start_engine() would call EngineVehicle's
        # implementation first, then this code could run.
        print("Car-specific engine starting logic")


class BicycleGood(NonEngineVehicle):
    """Bicycle inherits move() without needing engine behavior."""


def main() -> None:
    # --- INVALID CASE: Violates LSP ---
    car: VehicleBad = Car()
    bicycle: VehicleBad = Bicycle()

    print("Car:")
    car.start_engine()

    print("\nBicycle:")
    try:
        bicycle.start_engine()
    except NotImplementedError as error:
        print(f"Error: {error}")

    # --- VALID CASE: Follows LSP ---
    car2: EngineVehicle = CarGood()
    car2.start_engine()
    car2.move()

    bicycle2: NonEngineVehicle = BicycleGood()
    bicycle2.move()

    # --- super() example ---
    # Uncomment the super().start_engine() call inside
    # CarGood.start_engine() to reuse the parent implementation.
    #
    # super() calls the next implementation in the MRO.
    # If the immediate parent doesn't define the method,
    # Python continues searching through the MRO.


if __name__ == "__main__":
    main()
