package SOLID_Principles.Interface_Segregation_Principle;


// Problematic approach that violates ISP
interface Machine {
  void print();
  void scan();
  void fax();
}

class AllInOnePrinter implements Machine {
  @Override public void print() {
    // Printing functionality
  }
  @Override public void scan() {
    // Scanning functionality
  }
  @Override public void fax() {
    // Fax functionality
  }
}

class BasicPrinter implements Machine {
  @Override public void print() {
    // Printing functionality
  }
  @Override public void scan() {
    // Problem: Basic printer can't scan!
    throw new UnsupportedOperationException("Cannot scan");
  }
  @Override public void fax() {
    // Problem: Basic printer can't fax!
    throw new UnsupportedOperationException("Cannot fax");
  }
}

public abstract class invalid {
    public static void main(String[] args){
        AllInOnePrinter AOPrinter = new AllInOnePrinter();
        AOPrinter.print();
        AOPrinter.scan();

        BasicPrinter bPrinter = new BasicPrinter();
        bPrinter.scan();    //The scanning method is enforced to it even though it does not require it

    }
}


//This code violated ISP as it forces class BasicPriter to implement an interface Machine some of whose methods
//are irrelevant to BasicPrinter class.

//This issue can be resolved by breaking Machine interface more functionality wise.