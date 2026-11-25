from abc import ABC,abstractmethod

class Image(ABC):
    @abstractmethod
    def display(self):
        pass

class RealImage(Image):
    def __init__(self,filename:str):
        self.filename=filename
        self.load_image()

    def load_image(self):
        print(f"The image is being loaded {self.filename}")

    def display(self):
        print(f"The image is being displayed {self.filename}")



class ImageProxy(Image):

    def __init__(self,filename):
        self.filename=filename
        self.real_image=None

    def display(self):

        if self.real_image is None:
            self.real_image=RealImage(self.filename)
        self.real_image.display()

if __name__=="__main__":
    image1 = ImageProxy("a.png")
    image2 = ImageProxy("b.png")

    image1.display()
    image2.display()

# Virtual Proxy DP: Creates a lightweight placeholder that lazily creates the expensive 
# real object only when its functionality is first requested.