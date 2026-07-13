package Basics.Collections;

import java.util.Vector;
import java.util.Collections;

/*
========================================================
                    VECTOR IN JAVA
========================================================

Vector:
- Dynamic Array implementation
- Similar to ArrayList
- Implements:
    1. List
    2. Collection
    3. Iterable

========================================================
                IMPORTANT FEATURES
========================================================

1. Dynamic resizing
2. Maintains insertion order
3. Allows duplicate elements
4. Allows null values
5. Thread-safe (synchronized)

========================================================
                VECTOR vs ARRAYLIST
========================================================

Vector:
- Thread-safe
- Slower
- Legacy class

ArrayList:
- Not synchronized
- Faster
- Preferred in modern applications

========================================================
                INTERNAL WORKING
========================================================

Vector internally uses:
- Resizable Array

When capacity becomes full:
- Capacity increases automatically

Default capacity:
- 10

========================================================
                TIME COMPLEXITIES
========================================================

add(element)              -> O(1) average
add(index, element)       -> O(n)

get(index)                -> O(1)

set(index, value)         -> O(1)

remove(index)             -> O(n)
remove(Object)            -> O(n)

contains()                -> O(n)

size()                    -> O(1)

Collections.sort()        -> O(n log n)

========================================================
*/

public class VectorExample {

    public static void main(String[] args) {

        /*
        ========================================================
                    CREATING VECTOR
        ========================================================
        */

        // Default capacity = 10
        Vector<Integer> vector = new Vector<>();


        // Vector with initial capacity
        Vector<Integer> vector2 = new Vector<>(20);


        // Vector with capacity increment
        // If full, size increases by 5
        Vector<Integer> vector3 = new Vector<>(10, 5);




        /*
        ========================================================
                    ADDING ELEMENTS
        ========================================================
        */

        // add(element)
        // From List interface
        // Adds element at end
        // Time Complexity -> O(1) average

        vector.add(10);
        vector.add(20);
        vector.add(30);

        System.out.println("After add(): " + vector);



        // add(index, element)
        // From List interface
        // Inserts at specific index
        // Requires shifting
        // Time Complexity -> O(n)

        vector.add(1, 15);

        System.out.println("After add(index, value): " + vector);



        // addElement(element)
        // Specific to Vector (legacy method)
        // Similar to add()
        // Time Complexity -> O(1) average

        vector.addElement(100);

        System.out.println("After addElement(): " + vector);




        /*
        ========================================================
                    ACCESSING ELEMENTS
        ========================================================
        */

        // get(index)
        // From List interface
        // Random access
        // Time Complexity -> O(1)

        System.out.println("Element at index 2: " + vector.get(2));



        // firstElement()
        // Specific to Vector
        // Returns first element
        // Time Complexity -> O(1)

        System.out.println("First Element: " + vector.firstElement());



        // lastElement()
        // Specific to Vector
        // Returns last element
        // Time Complexity -> O(1)

        System.out.println("Last Element: " + vector.lastElement());



        // elementAt(index)
        // Specific to Vector
        // Similar to get()
        // Time Complexity -> O(1)

        System.out.println("elementAt(1): " + vector.elementAt(1));




        /*
        ========================================================
                    UPDATING ELEMENTS
        ========================================================
        */

        // set(index, value)
        // From List interface
        // Time Complexity -> O(1)

        vector.set(1, 999);

        System.out.println("After set(): " + vector);



        // setElementAt(value, index)
        // Specific to Vector
        // Legacy version of set()
        // Time Complexity -> O(1)

        vector.setElementAt(555, 2);

        System.out.println("After setElementAt(): " + vector);




        /*
        ========================================================
                    SEARCH OPERATIONS
        ========================================================
        */

        // contains(element)
        // From Collection interface
        // Linear search
        // Time Complexity -> O(n)

        System.out.println("Contains 30 ? " + vector.contains(30));



        // indexOf(element)
        // From List interface
        // Time Complexity -> O(n)

        System.out.println("Index of 100: " + vector.indexOf(100));



        // lastIndexOf(element)
        // From List interface
        // Time Complexity -> O(n)

        vector.add(100);

        System.out.println("Vector: " + vector);
        System.out.println("Last index of 100: " + vector.lastIndexOf(100));




        /*
        ========================================================
                    REMOVE OPERATIONS
        ========================================================
        */

        // remove(index)
        // From List interface
        // Requires shifting
        // Time Complexity -> O(n)

        vector.remove(1);

        System.out.println("After remove(index): " + vector);



        // remove(Object)
        // From Collection interface
        // Removes first occurrence
        // Time Complexity -> O(n)

        vector.remove(Integer.valueOf(100));

        System.out.println("After remove(Object): " + vector);



        // removeElement(element)
        // Specific to Vector
        // Legacy method
        // Time Complexity -> O(n)

        vector.removeElement(100);

        System.out.println("After removeElement(): " + vector);



        // removeElementAt(index)
        // Specific to Vector
        // Time Complexity -> O(n)

        vector.removeElementAt(0);

        System.out.println("After removeElementAt(): " + vector);




        /*
        ========================================================
                    SIZE & CAPACITY
        ========================================================
        */

        // size()
        // From Collection interface
        // Returns number of elements
        // Time Complexity -> O(1)

        System.out.println("Size: " + vector.size());



        // capacity()
        // Specific to Vector
        // Returns current internal capacity
        // Time Complexity -> O(1)

        System.out.println("Capacity: " + vector.capacity());



        // ensureCapacity(n)
        // Specific to Vector
        // Ensures minimum capacity
        // Time Complexity -> O(n) sometimes during resize

        vector.ensureCapacity(50);

        System.out.println("Capacity after ensureCapacity(): "
                + vector.capacity());



        // trimToSize()
        // Specific to Vector
        // Removes unused capacity
        // Time Complexity -> O(n)

        vector.trimToSize();

        System.out.println("Capacity after trimToSize(): "
                + vector.capacity());




        /*
        ========================================================
                    ITERATION
        ========================================================
        */

        vector.add(10);
        vector.add(20);
        vector.add(30);



        // Normal for loop
        // Time Complexity -> O(n)

        System.out.println("\nUsing normal for loop:");

        for (int i = 0; i < vector.size(); i++) {
            System.out.print(vector.get(i) + " ");
        }

        System.out.println();



        // Enhanced for loop
        // Time Complexity -> O(n)

        System.out.println("\nUsing enhanced for loop:");

        for (Integer num : vector) {
            System.out.print(num + " ");
        }

        System.out.println();



        // forEach() with lambda
        // Java 8+
        // Time Complexity -> O(n)

        System.out.println("\nUsing forEach():");

        vector.forEach(num -> System.out.print(num + " "));

        System.out.println();




        /*
        ========================================================
                    SORTING
        ========================================================
        */

        // Collections.sort()
        // Uses TimSort internally
        // Time Complexity -> O(n log n)

        Collections.sort(vector);

        System.out.println("\nAfter Collections.sort(): " + vector);



        // sort(null)
        // Modern Java 8+ way
        // Uses natural ordering
        // Time Complexity -> O(n log n)

        vector.sort(null);

        System.out.println("After vector.sort(null): " + vector);



        // Descending sort using comparator

        vector.sort((a, b) -> b - a);

        System.out.println("Descending sort: " + vector);




        /*
        ========================================================
                    CHECKING EMPTY
        ========================================================
        */

        // isEmpty()
        // From Collection interface
        // Time Complexity -> O(1)

        System.out.println("Is Empty ? " + vector.isEmpty());




        /*
        ========================================================
                    CLEARING VECTOR
        ========================================================
        */

        // clear()
        // From Collection interface
        // Removes all elements
        // Time Complexity -> O(n)

        vector.clear();

        System.out.println("After clear(): " + vector);




        /*
        ========================================================
                IMPORTANT VECTOR-SPECIFIC METHODS
        ========================================================

        addElement()
        elementAt()
        firstElement()
        lastElement()
        removeElement()
        removeElementAt()
        setElementAt()
        capacity()
        ensureCapacity()
        trimToSize()

        These are mostly legacy methods.

        ========================================================
        */




        /*
        ========================================================
                    FINAL IMPORTANT NOTES
        ========================================================

        Vector is synchronized:
        - Thread-safe
        - Slower than ArrayList

        ArrayList is preferred in modern applications
        unless synchronization is required.

        Vector internally uses dynamic arrays.

        Random access is fast because:
        - Array-based structure

        Insertions/deletions in middle are slow because:
        - Elements need shifting

        ========================================================
        */
    }
}