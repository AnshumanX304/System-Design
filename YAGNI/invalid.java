package YAGNI;

// invalid.java
class ReportGenerator {
    public void generatePDFReport(String data) {
        System.out.println("PDF Report generated: " + data);
    }

    // This is extra functionality we THINK we might need later, but not required now
    public void generateExcelReport(String data) {
        System.out.println("Excel Report generated: " + data);
    }

    public void sendReportByEmail(String email, String reportData) {
        System.out.println("Report sent to: " + email);
    }
}

public class invalid {
    public static void main(String[] args) {
        ReportGenerator generator = new ReportGenerator();
        generator.generatePDFReport("Sales Data - Q1");

        // Unused extra methods exist but are never called
    }
}
