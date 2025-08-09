package YAGNI;
// valid.java
class ReportGenerator {
    public void generatePDFReport(String data) {
        System.out.println("PDF Report generated: " + data);
    }
}

public class valid {
    public static void main(String[] args) {
        ReportGenerator generator = new ReportGenerator();
        generator.generatePDFReport("Sales Data - Q1");
    }
}

