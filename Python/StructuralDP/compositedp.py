from abc import ABC,abstractmethod

class FileSystem(ABC):
    @abstractmethod
    def get_size():
        pass

class File(FileSystem):

    def __init__(self,size):
        self.size=size
    def get_size(self):
        return self.size

class Folder(FileSystem):


    def __init__(self):
        self.components:list[FileSystem]=[]

    def get_size(self):
        size=0
        for component in self.components:
            size+=component.get_size()
        return size

    def add_component(self,component:FileSystem):
        self.components.append(component)


if __name__ == "__main__":
    folder1=Folder()
    file1_1=File(10)
    file1_2=File(20)

    folder1.add_component(file1_1)
    folder1.add_component(file1_2)

    folder1_2=Folder()
    file1_2_1=File(30)
    file1_2_2=File(4)

    folder1_2.add_component(file1_2_1)
    folder1_2.add_component(file1_2_2)
    folder1.add_component(folder1_2)


    print(folder1.get_size())


# "Composite Pattern lets you treat individual objects and groups of 
# objects uniformly by organizing them into a recursive tree structure."