package Basics.Collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 Comparator is used to define CUSTOM SORTING logic.

 Comparator<Integer> means:
 We are creating comparison logic for Integer objects.

 compare(a, b) should return:

 Negative value  -> a comes before b
 Positive value  -> b comes before a
 0               -> both are equal
*/
class CustomComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer a, Integer b) {

        /*
         Ascending order sorting

         Example:
         a = 2, b = 5
         2 - 5 = -3 (negative)
         -> 2 comes before 5

         a = 5, b = 2
         5 - 2 = 3 (positive)
         -> 5 comes after 2
        */
        return a - b;

        // Safer alternative (recommended in production):
        // return Integer.compare(a, b);
    }
}

class Student {

    String name;
    int marks;

    // Constructor
    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    // toString() controls how object is printed
    @Override
    public String toString() {
        return name + " : " + marks;
    }
}

public class ComparatorExample {

    public static void main(String[] args) {

        // List interface reference with ArrayList implementation
        List<Integer> l1 = new ArrayList<>();

        // Adding elements into list
        l1.add(5);
        l1.add(2);
        l1.add(1);

        /*
         Sorting using separate comparator class

         Internally:
         Java repeatedly calls compare(a, b)
         and arranges elements accordingly.
        */
        l1.sort(new CustomComparator());

        // After ascending sort
        System.out.println(l1); // [1, 2, 5]



        /*
         Sorting using Lambda Expression

         (a, b) -> b - a

         This creates comparator logic directly without
         creating a separate class.

         b - a gives DESCENDING order.

         Example:
         a = 2, b = 5
         5 - 2 = positive
         -> 5 comes before 2

         positive means position change and negative means position same
        */
        l1.sort((a, b) -> b - a);

        // After descending sort
        System.out.println(l1); // [5, 2, 1]

        List<String> l2= new ArrayList<>();

        l2.add("apple");
        l2.add("bananas");
        l2.add("grapes");


        l2.sort((a,b)-> a.length()-b.length()); //in ascending order of string length
        System.out.println(l2);
        l2.sort((a,b)-> b.length()-a.length()); //in descending order of string length
        System.out.println(l2);


        // Comparator on class objects
        List<Student> students = new ArrayList<>();

        students.add(new Student("Anshuman", 90));
        students.add(new Student("Rahul", 70));
        students.add(new Student("Amit", 85));



        /*
         Sorting by marks (ascending)

         compare(a, b):

         negative -> a comes before b
         positive -> a comes after b
        */
        students.sort((a, b) -> a.marks - b.marks);

        System.out.println(students);

        // Output:
        // [Rahul : 70, Amit : 85, Anshuman : 90]



        /*
         Sorting by name alphabetically
        */
        students.sort((a, b) -> a.name.compareTo(b.name));

        System.out.println(students);

        // Output:
        // [Amit : 85, Anshuman : 90, Rahul : 70]
    }
}