package DesignPatterns.Structural_Design_Patterns.Adapter_Design_Pattern;

// --- Adaptee (Existing class with its own interface) ---
class Airconditioner {
    public void ConnectToBluetooth() {
        System.out.println("Air Conditioner is connected via bluetooth !!");
    }
    public void StartCooling() {
        System.out.println("Air conditioner has started cooling !!");
    }
    public void StopCooling() {
        System.out.println("Air conditioner has stopped cooling !!");
    }
    public void DisconnectBluetooth() {
        System.out.println("The air conditioner has stopped cooling !!");
    }
}

// --- Adaptee (Another existing class with a different interface) ---
class SmartLight {
    public void connectToWiFi() {
        System.out.println("Smart Light connected to Wi-Fi.");
    }
    public void switchOn() {
        System.out.println("Smart Light is now ON.");
    }
    public void switchOff() {
        System.out.println("Smart Light is now OFF.");
    }
    public void disconnectWiFi() {
        System.out.println("Smart Light disconnected from Wi-Fi.");
    }
}

// --- Target Interface (The common interface that the client expects) ---
interface SmartDevice {
    public void turnon();
    public void turnoff();
}

// --- Adapter (Wraps Airconditioner and makes it compatible with SmartDevice) ---
class AirconditionerAdapter implements SmartDevice {
    Airconditioner airconditioner = new Airconditioner();

    @Override
    public void turnon() {
        airconditioner.ConnectToBluetooth();
        airconditioner.StartCooling();
    }

    @Override
    public void turnoff() {
        airconditioner.StopCooling();
        airconditioner.DisconnectBluetooth();
    }
}

// --- Adapter (Wraps SmartLight and makes it compatible with SmartDevice) ---
class SmartLightAdapter implements SmartDevice {
    SmartLight smartlight = new SmartLight();

    @Override
    public void turnon() {
        smartlight.connectToWiFi();
        smartlight.switchOn();
    }

    @Override
    public void turnoff() {
        smartlight.switchOff();
        smartlight.disconnectWiFi();
    }
}

// --- Client (Uses SmartDevice interface without worrying about underlying implementation) ---
public class valid {
    public static void main(String[] args) {
        SmartDevice airconditionerAdapter = new AirconditionerAdapter();
        airconditionerAdapter.turnon();
        airconditionerAdapter.turnoff();

        SmartDevice smartlightAdapter = new SmartLightAdapter();
        smartlightAdapter.turnon();
        smartlightAdapter.turnoff();
    }
}
