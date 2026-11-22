from abc import ABC,abstractmethod

class AirConditioner():
    def connectBluetooth(self):
        print("Ac connected via bluetooth !")

    def disconnectBluetooth(self):
        print("Ac disconnected via bluetooth !")

    def turnon(self):
        print("Ac turned on !")

    def turnoff(self):
        print("Ac turned off !")


class SmartLight():
    def connectBluetooth(self):
        print("SL connected via bluetooth !")

    def disconnectBluetooth(self):
        print("SL disconnected via bluetooth !")

    def turnon(self):
        print("SL turned on !")

    def turnoff(self):
        print("SL turned off !")

class SmartDevice(ABC):
    @abstractmethod
    def turnon(self):
        pass
    def turnoff(self):
        pass

class ACAdapter(SmartDevice):
    def __init__(self):
        self.ac=AirConditioner()

    def turnon(self):
        self.ac.connectBluetooth()
        self.ac.turnon()

    def turnoff(self):
        self.ac.turnoff()
        self.ac.disconnectBluetooth()
    

class SLAdapter(SmartDevice):
    def __init__(self):
        self.sl=SmartLight()

    def turnon(self):
        self.sl.connectBluetooth()
        self.sl.turnon()

    def turnoff(self):
        self.sl.turnoff()
        self.sl.disconnectBluetooth()

if __name__=="__main__":

    ac=ACAdapter()
    ac.turnon()
    ac.turnoff()

    sl=SLAdapter()
    sl.turnon()
    sl.turnoff()

# "Adapter Pattern acts as a bridge that converts an existing incompatible interface into 
# the interface expected by the client."