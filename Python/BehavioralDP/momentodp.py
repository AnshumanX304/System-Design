
class Momento:
    def __init__(self, text):
        self.__text = text

    def get_text(self):
        return self.__text


class TextEditor:
    def __init__(self):
        self.text = ""

    def write(self, text):
        self.text += text

    def save(self, history):
        history.add_text(Momento(self.text))

    def restore(self, history):
        momento = history.get_last()

        if momento:
            self.text = momento.get_text()
        else:
            print("Nothing to undo !!")

    def display(self):
        print(self.text)


class History:
    def __init__(self):
        self.history = []

    def add_text(self, momento: Momento):
        self.history.append(momento)

    def get_last(self):
        if self.history:
            return self.history.pop()
        return None


if __name__ == "__main__":

    editor = TextEditor()
    history = History()

    editor.write("N")
    editor.save(history)

    editor.write("I")
    editor.save(history)

    editor.write("G")
    editor.save(history)

    editor.write("G")
    editor.save(history)

    editor.write("A")
    editor.save(history)

    editor.display()
    # NIGGA

    editor.restore(history)
    editor.display()
    # NIGG

    editor.restore(history)
    editor.display()
    # NIG

# "Memento saves snapshots of an object's state, and Caretaker maintains those snapshots 
# to enable Undo/Restore without exposing the object's internals."