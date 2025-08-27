package DesignPatterns.Behavioral_Design_Patterns.State_Design_Pattern;
interface TrafficLightState{
    public void next(StateContext statecontext);
    String getColor();
}
class RedState implements TrafficLightState{
    @Override
    public void next(StateContext statecontext){
        statecontext.setContext(new YellowState());
    }
    public String getColor(){
        return "RED";
    }
}
class YellowState implements TrafficLightState{
    @Override
    public void next(StateContext statecontext){
        statecontext.setContext(new GreenState());
    }
    public String getColor(){
        return "YELLOW";
    }
}
class GreenState implements TrafficLightState{
    @Override
    public void next(StateContext statecontext){
        statecontext.setContext(new RedState());
    }
    public String getColor(){
        return "GREEN";
    }
}

class StateContext{
    private TrafficLightState currstate;

    public StateContext(){
        this.currstate=new RedState();
    }
    public void next(){
        currstate.next(this);
    }
    public void setContext(TrafficLightState state){
        this. currstate=state;
    }

}
public class valid {
    public static void main(String [] args){
        StateContext context=new StateContext();

        TrafficLightState redlight=new RedState();
        context.setContext(redlight);
        context.next();
        context.next();
        context.next();
    }
}
