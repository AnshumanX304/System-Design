package Basics.Collections;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;

/*
========================================================
            COPYONWRITEARRAYLIST IN JAVA
========================================================

Package:
java.util.concurrent

CopyOnWriteArrayList is a thread-safe version
of ArrayList.

========================================================
                INTERNAL WORKING
========================================================

NORMAL ARRAYLIST:
- Modifies SAME internal array

COPYONWRITEARRAYLIST:
- Creates NEW COPY of array on every write operation

Meaning:
Whenever you:
- add()
- remove()
- set()

A NEW ARRAY gets created internally.

Then:
- old array replaced with new array

========================================================
                WHY DOES THIS EXIST?
========================================================

Problem with ArrayList:

If one thread modifies list while another thread
iterates:

-> ConcurrentModificationException may happen

CopyOnWriteArrayList solves this.

========================================================
            MAIN USE CASE
========================================================

BEST FOR:
- Read-heavy applications
- Very frequent reads
- Very few writes

Examples:
- Caching
- Subscriber lists
- Configuration data
- Event listeners

========================================================
            WHEN NOT TO USE
========================================================

BAD FOR:
- Frequent insertions/deletions

Because:
EVERY write creates NEW ARRAY COPY

Very expensive.

========================================================
            ARRAYLIST vs COPYONWRITEARRAYLIST
========================================================

ArrayList:
- Not thread-safe
- Fast writes
- Fail-fast iterator
- No copying during write

CopyOnWriteArrayList:
- Thread-safe
- Slow writes
- Safe iteration
- Copies whole array during writes

========================================================
                TIME COMPLEXITIES
========================================================

READ OPERATIONS:

get(index)            -> O(1)
contains()            -> O(n)
size()                -> O(1)

WRITE OPERATIONS:

add(element)          -> O(n)
add(index, element)   -> O(n)

remove(index)         -> O(n)
remove(Object)        -> O(n)

set(index, value)     -> O(n)

ITERATION:
Traversal             -> O(n)

SORTING:
sort()                -> O(n log n)

========================================================
*/

public class CopyOnWriteArrayListExample {

    public static void main(String[] args) {

        /*
        ========================================================
                CREATING COPYONWRITEARRAYLIST
        ========================================================
        */

        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();




        /*
        ========================================================
                    ADDING ELEMENTS
        ========================================================
        */

        // add(element)
        // Thread-safe write operation
        // Creates NEW ARRAY COPY internally
        // Time Complexity -> O(n)

        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println("After add(): " + list);



        // add(index, element)
        // Requires shifting + array copy
        // Time Complexity -> O(n)

        list.add(1, 15);

        System.out.println("After add(index, value): "
                + list);



        // addIfAbsent(element)
        // Specific to CopyOnWriteArrayList
        // Adds only if element not present
        // Time Complexity -> O(n)

        list.addIfAbsent(100);
        list.addIfAbsent(100);

        System.out.println("After addIfAbsent(): " + list);




        /*
        ========================================================
                    ACCESSING ELEMENTS
        ========================================================
        */

        // get(index)
        // Fast random access
        // Time Complexity -> O(1)

        System.out.println("Element at index 2: "
                + list.get(2));



        // size()
        // Time Complexity -> O(1)

        System.out.println("Size: "
                + list.size());




        /*
        ========================================================
                    UPDATING ELEMENTS
        ========================================================
        */

        // set(index, value)
        // Creates NEW ARRAY COPY
        // Time Complexity -> O(n)

        list.set(1, 999);

        System.out.println("After set(): "
                + list);




        /*
        ========================================================
                    SEARCH OPERATIONS
        ========================================================
        */

        // contains(element)
        // Linear search
        // Time Complexity -> O(n)

        System.out.println("Contains 30 ? "
                + list.contains(30));



        // indexOf(element)
        // Time Complexity -> O(n)

        System.out.println("Index of 100: "
                + list.indexOf(100));



        // lastIndexOf(element)
        // Time Complexity -> O(n)

        list.add(100);

        System.out.println("List: " + list);

        System.out.println("Last index of 100: "
                + list.lastIndexOf(100));




        /*
        ========================================================
                    REMOVE OPERATIONS
        ========================================================
        */

        // remove(index)
        // Creates NEW ARRAY COPY
        // Time Complexity -> O(n)

        list.remove(1);

        System.out.println("After remove(index): "
                + list);



        // remove(Object)
        // Creates NEW ARRAY COPY
        // Time Complexity -> O(n)

        list.remove(Integer.valueOf(100));

        System.out.println("After remove(Object): "
                + list);




        /*
        ========================================================
                    ITERATION
        ========================================================
        */

        /*
        IMPORTANT:
        Iteration happens on SNAPSHOT of array.

        Meaning:
        Iterator sees OLD ARRAY copy.

        Even if another thread modifies list,
        iteration remains safe.

        No ConcurrentModificationException.
        */

        list.add(40);
        list.add(50);



        // Enhanced for loop
        // Time Complexity -> O(n)

        System.out.println("\nUsing enhanced for loop:");

        for (Integer num : list) {
            System.out.print(num + " ");
        }

        System.out.println();



        // forEach()
        // Time Complexity -> O(n)

        System.out.println("\nUsing forEach():");

        list.forEach(num ->
                System.out.print(num + " "));

        System.out.println();




        /*
        ========================================================
            SAFE MODIFICATION DURING ITERATION
        ========================================================
        */

        /*
        NORMAL ArrayList would throw:
        ConcurrentModificationException

        CopyOnWriteArrayList allows it safely.
        */

        System.out.println("\nSafe modification during iteration:");

        for (Integer num : list) {

            System.out.println("Reading: " + num);

            if (num == 30) {

                // Safe operation
                list.add(999);
            }
        }

        System.out.println("After iteration: " + list);




        /*
        ========================================================
                    SORTING
        ========================================================
        */

        // sort(null)
        // Uses TimSort internally
        // Time Complexity -> O(n log n)

        list.sort(null);

        System.out.println("\nAfter sort(null): "
                + list);



        // Descending order sorting

        list.sort((a, b) -> b - a);

        System.out.println("Descending sort: "
                + list);




        /*
        ========================================================
                    CHECK EMPTY
        ========================================================
        */

        // isEmpty()
        // Time Complexity -> O(1)

        System.out.println("Is Empty ? "
                + list.isEmpty());




        /*
        ========================================================
                    CLEARING LIST
        ========================================================
        */

        // clear()
        // Creates new empty array
        // Time Complexity -> O(n)

        list.clear();

        System.out.println("After clear(): "
                + list);




        /*
        ========================================================
            IMPORTANT COPYONWRITEARRAYLIST METHODS
        ========================================================

        addIfAbsent()

        addAllAbsent()

        These are special methods.

        ========================================================
        */




        /*
        ========================================================
                REAL MULTITHREADING EXAMPLE
        ========================================================
        */

        CopyOnWriteArrayList<Integer> threadSafeList =
                new CopyOnWriteArrayList<>();

        threadSafeList.add(1);
        threadSafeList.add(2);
        threadSafeList.add(3);



        Thread t1 = new Thread(() -> {

            for (Integer num : threadSafeList) {

                System.out.println("Thread-1 Reading: "
                        + num);

                try {
                    Thread.sleep(500);
                } catch (Exception e) {

                }
            }
        });



        Thread t2 = new Thread(() -> {

            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }

            // Safe modification
            threadSafeList.add(999);

            System.out.println("Thread-2 Added 999");
        });



        t1.start();
        t2.start();




        /*
        ========================================================
                    FINAL IMPORTANT NOTES
        ========================================================

        CopyOnWriteArrayList:
        - Thread-safe
        - Excellent for reads
        - Very expensive writes

        ArrayList:
        - Faster writes
        - Not thread-safe

        Iterator behavior:

        ArrayList:
        -> Fail-fast iterator

        CopyOnWriteArrayList:
        -> Fail-safe iterator
        -> Iterates on snapshot copy

        ========================================================
        */
    }
}