package Basics;

class A {
    void printdata() {
        System.out.println("Class a ran !!");
    }
}

class B {
    void printdata() {
        System.out.println("Class b ran !!");
    }
}

public class Running_java { 
    public static void main(String[] args) {
        A a = new A();
        a.printdata();

        B b = new B();
        b.printdata();
    }
}