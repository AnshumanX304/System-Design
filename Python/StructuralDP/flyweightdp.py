class Character():
    def __init__(self,value,font,size):
        self.value = value
        self.font = font
        self.size = size

    def display(self,x,y):
        print(f"{self.value} | Font: {self.font} "f"| Size: {self.size} | Position: ({x}, {y})")

class CharacterFactory():

    def __init__(self):
        self.characters={}

    def get_character(self,value,font,size):
        key = (value, font, size)
        if key not in self.characters:
            self.characters[key] = Character(
                value, font, size
            )

        return self.characters[key]
    
if __name__=="__main__":

    factory = CharacterFactory()

    char1 = factory.get_character("A", "Arial", 12)
    char2 = factory.get_character("A", "Arial", 12)
    char3 = factory.get_character("B", "Arial", 12)

    char1.display(10, 20)
    char2.display(30, 40)
    char3.display(50, 60)

    print(char1 is char2)  # True
    print(char1 is char3)  # False



    # Flyweight DP: Shares common (intrinsic) state among multiple objects and keeps 
    # unique (extrinsic) state separate to reduce memory consumption.