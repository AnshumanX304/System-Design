
from abc import ABC, abstractmethod


# ============================================================
# 1. COMMAND INTERFACE
# ============================================================
# Defines a common contract for all commands.
# Every concrete command must implement the execute() method.
#
# The Invoker (RemoteControl) will interact with commands
# through this interface without knowing their implementation.

class Command(ABC):

    @abstractmethod
    def execute(self):
        pass


# ============================================================
# 2. RECEIVERS
# ============================================================
# Receivers are the actual objects that perform the operations.
# They contain the business logic for carrying out an action.
#
# The Command objects delegate the actual work to these receivers.


class Light:

    def turnon(self):
        print("Light turned on !!")

    def turnoff(self):
        print("Light turned off !!")


class Fan:

    def turnon(self):
        print("Fan turned on !!")

    def turnoff(self):
        print("Fan turned off !!")


# ============================================================
# 3. CONCRETE COMMANDS
# ============================================================
# Concrete commands implement the Command interface.
#
# Each command:
# 1. Stores a reference to the receiver it needs.
# 2. Encapsulates a specific request.
# 3. Implements execute() to delegate the operation
#    to the appropriate receiver.
#
# Notice that creating a command does not execute the action.
# The action happens only when execute() is called.


class LightOnCommand(Command):

    def __init__(self, light: Light):
        # Store the receiver on which this command will operate.
        self.light = light

    def execute(self):
        # Delegate the actual operation to the receiver.
        self.light.turnon()


class LightOffCommand(Command):

    def __init__(self, light: Light):
        self.light = light

    def execute(self):
        self.light.turnoff()


class FanOnCommand(Command):

    def __init__(self, fan: Fan):
        self.fan = fan

    def execute(self):
        self.fan.turnon()


class FanOffCommand(Command):

    def __init__(self, fan: Fan):
        self.fan = fan

    def execute(self):
        self.fan.turnoff()


# ============================================================
# 4. INVOKER
# ============================================================
# The Invoker is responsible for triggering a command.
#
# It does not know:
# - Which receiver is involved.
# - What operation the command performs.
# - How the operation is implemented.
#
# It only knows that every command has an execute() method.
#
# This decouples the Invoker from concrete commands and receivers.


class RemoteControl:

    def __init__(self, command: Command):
        # Store the command that should be executed when
        # the remote control's execute() method is called.
        self.command = command

    def execute(self):
        # Ask the currently assigned command to execute itself.
        # The command handles the actual interaction with the receiver.
        self.command.execute()

    def setCommand(self, command: Command):
        # Replace the currently assigned command at runtime.
        # This allows the same remote control to operate
        # different devices and perform different actions.
        self.command = command


# ============================================================
# 5. CLIENT
# ============================================================
# The Client is responsible for creating and connecting
# all the objects involved in the Command Pattern.
#
# It creates:
# - Receivers (Light, Fan)
# - Concrete Commands (LightOnCommand, FanOnCommand, etc.)
# - Invoker (RemoteControl)
#
# The Client decides which command should be assigned
# to the Invoker.


if __name__ == "__main__":

    # Create receiver objects.
    light = Light()
    fan = Fan()

    # Create concrete commands and associate them with
    # their respective receivers.
    lightoncommand = LightOnCommand(light)
    lightoffcommand = LightOffCommand(light)

    fanoncommand = FanOnCommand(fan)
    fanoffcommand = FanOffCommand(fan)

    # Create the Invoker and assign the initial command.
    remotecontrol = RemoteControl(lightoncommand)

    # Execute the currently assigned command.
    # Output: Light turned on !!
    remotecontrol.execute()

    # Replace the command with LightOffCommand.
    remotecontrol.setCommand(lightoffcommand)
    remotecontrol.execute()

    # Replace the command with FanOnCommand.
    remotecontrol.setCommand(fanoncommand)
    remotecontrol.execute()

    # Replace the command with FanOffCommand.
    remotecontrol.setCommand(fanoffcommand)
    remotecontrol.execute()
