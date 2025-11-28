package DesignPatterns.Creational_Design_Patterns.Singleton_Design_Pattern;
class Logger{
    private static Logger logger = null;
    private Logger(){}
    public static Logger getLogger(){
        if(logger==null){
            logger=new Logger();
        }
        return logger;
    }
    public void logMessage(String msg){
        System.out.println("Hello "+msg+" !");
    }
}
public class Singleton {
    public static void main(String [] args){
        Logger logger = Logger.getLogger();
        logger.logMessage("Simon, Go Back !!");

    }
}
