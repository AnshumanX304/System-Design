
from abc import ABC, abstractmethod


# ISP: Instead of creating one large interface with print(),
# scan(), and fax(), we create smaller, specific interfaces.

class Printer(ABC):
    @abstractmethod
    def print(self):
        pass


class Scanner(ABC):
    @abstractmethod
    def scan(self):
        pass


class FaxMachine(ABC):
    @abstractmethod
    def fax(self):
        pass


# BasicPrinter only inherits Printer because it supports printing.
# It is NOT forced to implement scan() or fax().
class BasicPrinter(Printer):
    def print(self):
        print("Printing...")


# AllInOnePrinter supports all three functionalities,
# so it implements all three interfaces.
class AllInOnePrinter(Printer, Scanner, FaxMachine):
    def print(self):
        print("Print stuff")

    def scan(self):
        print("Scan stuff")

    def fax(self):
        print("Fax stuff")


if __name__ == "__main__":

    aoprinter = AllInOnePrinter()
    aoprinter.print()
    aoprinter.scan()
    aoprinter.fax()

    bprinter = BasicPrinter()
    bprinter.print()


# ISP takeaway:
# Classes should not be forced to depend on methods they don't use.
# Prefer multiple small, specific interfaces over one large interface.
