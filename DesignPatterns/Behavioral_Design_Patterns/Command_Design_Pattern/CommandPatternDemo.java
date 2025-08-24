package DesignPatterns.Behavioral_Design_Patterns.Command_Design_Pattern;

// Receiver class: knows how to perform the actual actions
class Light {
    public void turnOn() {
        System.out.println("Light is turned on !!");
    }
    public void turnOff() {
        System.out.println("Light is turned off !!");
    }
}

// Receiver class: another device (not used in this example but can be extended)
class Fan {
    public void turnOn() {
        System.out.println("Fan is turned on !!");
    }
    public void turnOff() {
        System.out.println("Fan is turned off !!");
    }
}

// Command interface: declares the execute method
interface Command {
    void execute();
}

// Concrete Command: turns the light on
class TurnLightOn implements Command {
    private Light light;

    TurnLightOn(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOn();
    }
}

// Concrete Command: turns the light off
class TurnLightOff implements Command {
    private Light light;

    TurnLightOff(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOff();
    }
}

// Invoker: holds a command and executes it
class RemoteControl {
    private Command command;

    RemoteControl(Command command) {
        this.command = command;
    }

    // Execute the current command
    public void execute() {
        command.execute();
    }

    // Change the command at runtime
    public void setCommand(Command command) {
        this.command = command;
    }
}

// Client: creates receivers, commands, and assigns them to the invoker
public class CommandPatternDemo {
    public static void main(String[] args) {
        Light currLight = new Light();

        // Create commands for the light
        Command lightOnCommand = new TurnLightOn(currLight);
        Command lightOffCommand = new TurnLightOff(currLight);

        // Create remote control with initial command (light off)
        RemoteControl remoteControl = new RemoteControl(lightOffCommand);

        // Execute the light off command
        remoteControl.execute();

        // Switch to light on command and execute
        remoteControl.setCommand(lightOnCommand);
        remoteControl.execute();
    }
}
