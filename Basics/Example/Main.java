package Basics.Example;

import java.util.List;
import java.util.ArrayList;

class SubjectInfo {

    // Data members
    private String subject_name;
    private int marks;

    // Default constructor
    public SubjectInfo() {
        this.subject_name = "";
        this.marks = 0;
    }

    // Parameterized constructor
    public SubjectInfo(String subject_name, int marks) {
        this.subject_name = subject_name;
        this.marks = marks;
    }

    // Getter for subject_name
    public String get_subject_name() {
        return this.subject_name;
    }

    // Setter for subject_name
    public void set_subject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    // Getter for marks
    public int get_marks() {
        return this.marks;
    }

    // Setter for marks
    public void set_marks(int marks) {
        this.marks = marks;
    }
}

class Student {

    // Data members
    private String name;
    private List<SubjectInfo> s_info;

    // Default constructor
    public Student() {
        this.name = "";
        this.s_info = new ArrayList<>();
    }

    // Parameterized constructor
    public Student(String name) {

        System.out.println("I am parameterized constructor");

        this.name = name;
        this.s_info = new ArrayList<>();
    }

    // Setter
    public void set_name(String name) {
        this.name = name;
    }

    // Getter
    public String get_name() {
        return this.name;
    }

    // Add subject info
    public void add_subject_info(String s_name, int marks) {

        this.s_info.add(new SubjectInfo(s_name, marks));
    }

    // Total marks
    public int get_total() {

        int sum = 0;

        for(int i = 0; i < this.s_info.size(); i++) {

            SubjectInfo curr = this.s_info.get(i);

            sum += curr.get_marks();
        }

        return sum;
    }

    // Average marks
    public double get_average() {

        if(this.s_info.size() == 0) {
            return 0;
        }

        double sum = 0;

        for(int i = 0; i < this.s_info.size(); i++) {

            SubjectInfo curr = this.s_info.get(i);

            sum += curr.get_marks();
        }

        return sum / this.s_info.size();
    }

    // Percentage
    public double get_percentage() {

        if(this.s_info.size() == 0) {
            return 0;
        }

        return get_average();
    }
}

public class Main {

    public static void main(String[] args) {

        Student s1 = new Student("Anshuman");

        s1.add_subject_info("Maths", 90);
        s1.add_subject_info("Science", 85);
        s1.add_subject_info("English", 88);

        System.out.println("Student Name: " + s1.get_name());

        System.out.println("Total Marks: " + s1.get_total());

        System.out.println("Average Marks: " + s1.get_average());

        System.out.println("Percentage: " + s1.get_percentage() + "%");
    }
}



