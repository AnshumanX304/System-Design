package SOLID_Principles.Interface_Segregation_Principle;

// Better approach following ISP
interface Printer {
  void print();
}

interface Scanner {
  void scan();
}

interface FaxMachine {
  void fax();
}

class BasicPrinter implements Printer {
  @Override
  public void print() {
    // Printing functionality
  }
}

class AllInOnePrinter implements Printer, Scanner, FaxMachine {
  @Override
  public void print() {
    // Printing functionality
  }
  @Override
  public void scan() {
    // Scanning functionality
  }
  @Override
  public void fax() {
    // Fax functionality
  }
}

public class valid {
    public static void main(String [] args){
        AllInOnePrinter aoprinter= new AllInOnePrinter();
        aoprinter.print();
        aoprinter.scan();
        aoprinter.fax();

        BasicPrinter bprinter=new BasicPrinter();
        bprinter.print();   //now basci printer is not forced to implement the interface that is irrelvent to it. 


    }
}


//This code follows ISP.
