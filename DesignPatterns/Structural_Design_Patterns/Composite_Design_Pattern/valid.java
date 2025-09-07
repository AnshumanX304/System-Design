package DesignPatterns.Structural_Design_Patterns.Composite_Design_Pattern;
import java.util.ArrayList;
import java.util.List;


interface SmartComponent{
    public void turnoff();
    public void turnon();
}

class AirConditioner implements SmartComponent{
    @Override
    public void turnon(){
        System.out.println("Air conditioner is turned on !");
    }
    @Override
    public void turnoff(){
        System.out.println("Air conditioner is turned off !");
    }
}
class Smartlight implements SmartComponent{
    @Override
    public void turnon(){
        System.out.println("Smartlight is turned on !");
    }
    @Override
    public void turnoff(){
        System.out.println("Smartlight is turned off !");
    }
}

class CompositeSmartComponent implements SmartComponent{

    List<SmartComponent> components= new ArrayList<>();
    public void AddComponent(SmartComponent smartcomponent){
        components.add(smartcomponent);
    }
    public void  RemoveComponents(SmartComponent smartcomponent){
        components.remove(smartcomponent);
    }
    @Override
    public void turnon(){
        for(SmartComponent component:components){
            component.turnon();
        }
    }
    @Override
    public void turnoff(){
        for(SmartComponent component:components){
            component.turnoff();
        }

    }
}
public class valid {

    public static void main(String[] args){
        //infividual items
        SmartComponent ac=new AirConditioner();
        SmartComponent smartlight= new Smartlight();

        //rooms
        CompositeSmartComponent room1=new CompositeSmartComponent();
        room1.AddComponent(ac);
        room1.AddComponent(smartlight);

        CompositeSmartComponent room2=new CompositeSmartComponent();
        room2.AddComponent(new AirConditioner());
        room2.AddComponent(new Smartlight());

        //floor
        CompositeSmartComponent floor1=new CompositeSmartComponent();
        floor1.AddComponent(room1);
        floor1.AddComponent(room2);

        //house
        CompositeSmartComponent house=new CompositeSmartComponent();
        house.AddComponent(floor1);

        house.turnon();
        house.turnoff();

    }
    
}
