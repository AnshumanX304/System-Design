package DesignPatterns.Structural_Design_Patterns.Proxy_Design_Pattern;
interface IDisplay{
    public void display();
}
class ImageDisplay implements IDisplay{
    String url;
    ImageDisplay(String url){
        this.url=url;
        //expensive operations
        System.out.println("Fetch image from url "+url);
        System.out.println("Do image compression");
        System.out.println("Store Image");
    }
    @Override
    public void display(){
        
        System.out.println("Display image");
        
    }
}

class ImageDisplayProxy implements IDisplay{
    private ImageDisplay img;
    private String url;
    ImageDisplayProxy(String url){
        this.url=url;
    }
    @Override
    public void display(){
        if(img==null){
            img=new ImageDisplay(url);
        }
        img.display();
    }
}
public class VirtualProxy {
    public static void main(String [] args){
        IDisplay img=new ImageDisplayProxy("https://example.com/img.png");
        img.display(); // creates ImageDisplay and displays
        img.display(); // reuses ImageDisplay, no re-creation
    }
}
