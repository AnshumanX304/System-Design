
from abc import ABC, abstractmethod


class Patient(ABC):
    @abstractmethod
    def accept(self, visitor):
        pass


class ChildPatient(Patient):
    def accept(self, visitor):
        visitor.visit_child(self)


class AdultPatient(Patient):
    def accept(self, visitor):
        visitor.visit_adult(self)


class SeniorPatient(Patient):
    def accept(self, visitor):
        visitor.visit_senior(self)


class Visitor(ABC):
    @abstractmethod
    def visit_child(self, patient: ChildPatient):
        pass

    @abstractmethod
    def visit_adult(self, patient: AdultPatient):
        pass

    @abstractmethod
    def visit_senior(self, patient: SeniorPatient):
        pass


class DiagnosisVisitor(Visitor):
    def visit_child(self, patient):
        print("Diagnosis Visitor visited child Patient !!")

    def visit_adult(self, patient):
        print("Diagnosis Visitor visited adult Patient !!")

    def visit_senior(self, patient):
        print("Diagnosis Visitor visited senior Patient !!")


class BillingVisitor(Visitor):
    def visit_child(self, patient):
        print("Billing Visitor visited child Patient !!")

    def visit_adult(self, patient):
        print("Billing Visitor visited adult Patient !!")

    def visit_senior(self, patient):
        print("Billing Visitor visited senior Patient !!")


if __name__ == "__main__":

    patients = [
        ChildPatient(),
        AdultPatient(),
        SeniorPatient()
    ]

    diagnosis_visitor = DiagnosisVisitor()
    billing_visitor = BillingVisitor()

    for patient in patients:
        patient.accept(diagnosis_visitor)
        patient.accept(billing_visitor)


# Visitor Pattern: Add new operations to existing object types without modifying them by moving 
# those operations into separate Visitor classes using double dispatch.