package DesignPatterns.Behavioral_Design_Patterns.Visitor_Design_Pattern;
interface Patient{
    void accept(Visitor visitor);
}

class ChildPatient implements Patient{
    @Override
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}

class AdultPatient implements Patient{
    @Override
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}

class SeniorPatient implements Patient{
    @Override
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}

interface Visitor{
    void visit(ChildPatient childpatient);
    void visit(AdultPatient childpatient);
    void visit(SeniorPatient childpatient);
}

class DiagnosisVisitor implements Visitor{
    @Override
    public void visit(ChildPatient childpatient){
        System.out.println("Diagnosis Visitor visited child Patient !!");
    }
    @Override
    public void visit(AdultPatient childpatient){
        System.out.println("Diagnosis Visitor visited adult Patient !!");
    }
    @Override
    public void visit(SeniorPatient childpatient){
        System.out.println("Diagnosis Visitor visited senior Patient !!");
    }
}

class BillingVisitor implements Visitor{
    @Override
    public void visit(ChildPatient childpatient){
        System.out.println("Billing Visitor visited child Patient !!");
    }
    @Override
    public void visit(AdultPatient childpatient){
        System.out.println("Billing Visitor visited adult Patient !!");
    }
    @Override
    public void visit(SeniorPatient childpatient){
        System.out.println("Billing Visitor visited senior Patient !!");
    }
}


public class valid {
    public static void main(String[] args){
        Patient[] patients = {
            new ChildPatient(), new AdultPatient(), new SeniorPatient()};
        // Create visitors for different operations
        Visitor diagnosisVisitor = new DiagnosisVisitor();
        Visitor billingVisitor = new BillingVisitor();
        // Each patient accepts the visitors to perform the operations
        for (Patient patient : patients) {
        patient.accept(diagnosisVisitor);
        patient.accept(billingVisitor);
        }
    }
}
