package DRY;

class InvoicePrinter {
    public void printInvoice(String invoiceData) {
        System.out.println("Invoice: " + invoiceData);
    }
}

class ReceiptPrinter {
    public void printReceipt(String receiptData) {
        System.out.println("Receipt: " + receiptData);
    }
}

public class invalid {
    public static void main(String[] args) {
        InvoicePrinter invoicePrinter = new InvoicePrinter();
        invoicePrinter.printInvoice("Invoice #12345");

        ReceiptPrinter receiptPrinter = new ReceiptPrinter();
        receiptPrinter.printReceipt("Receipt #98765");
    }
}
