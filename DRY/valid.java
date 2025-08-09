package DRY;

// valid.java
interface Printable {
    void print(String data);
}

class DocumentPrinter implements Printable {
    @Override
    public void print(String data) {
        System.out.println(data);
    }
}

public class valid{
    public static void main(String[] args) {
        Printable printer = new DocumentPrinter();

        printer.print("Invoice: Invoice #12345");
        printer.print("Receipt: Receipt #98765");
    }
}
