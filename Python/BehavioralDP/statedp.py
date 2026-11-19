from abc import ABC,abstractmethod

class LightState(ABC):
    @abstractmethod
    def next(self,lightcontext:LightContext):
        pass
    @abstractmethod
    def get_light(self):
        pass

class RedLight(LightState):
    def next(self,lightcontext:LightContext):
        lightcontext.currlight=YellowLight()
    def get_light(self):
        print("Red")

class YellowLight(LightState):
    def next(self,lightcontext:LightContext):
        lightcontext.currlight=GreenLight()
    def get_light(self):
        print("Yellow")

class GreenLight(LightState):
    def next(self,lightcontext:LightContext):
        lightcontext.currlight=RedLight()
    def get_light(self):
        print("Green")


class LightContext():

    def __init__(self):
        self.currlight=RedLight()

    def next(self):
        self.currlight.next(self)

    def set_context(self,light:LightState):
        self.currlight=light

    def get_light(self):
        self.currlight.get_light()

if __name__ == "__main__":

    context = LightContext()

    context.get_light()  # Red

    context.next()
    context.get_light()  # Yellow

    context.next()
    context.get_light()  # Green

    context.next()
    context.get_light()  # Red


# State Design Pattern: Allows an object to change its behavior based on its current state.
# Each state encapsulates its own behavior and decides the next state, while the Context maintains the current state.