package Basics.Collections;

import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

/*
========================================================
                LINKEDLIST IN JAVA
========================================================

LinkedList:
- Implements List interface
- Implements Deque interface
- Implements Queue interface

Internal Structure:
- Doubly Linked List
- Each node stores:
    1. Data
    2. Previous pointer
    3. Next pointer

========================================================
                WHEN TO USE
========================================================

GOOD FOR:
- Frequent insertion/deletion
- Queue operations
- Stack operations

BAD FOR:
- Random access using index

========================================================
            IMPORTANT INTERVIEW CONCEPT
========================================================

List<Integer> list = new LinkedList<>();

Above reference can ONLY access:
- List methods
- Collection methods

Cannot access:
- addFirst()
- removeFirst()
- push()
- pop()
- offer()
- poll()

--------------------------------------------------------

LinkedList<Integer> list = new LinkedList<>();

Can access:
- List methods
- Queue methods
- Deque methods
- LinkedList-specific methods

========================================================
*/

public class LinkedListExample {

    public static void main(String[] args) {

        /*
        ========================================================
                    CREATING LINKEDLIST
        ========================================================
        */

        // Preferred interview syntax
        List<Integer> normalList = new LinkedList<>();


        // Full LinkedList access
        LinkedList<Integer> list = new LinkedList<>();



        /*f
        ========================================================
                    ADDING ELEMENTS
        ========================================================
        */

        // add(element)
        // From List interface
        // Adds element at end
        // Time Complexity -> O(1)
        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println("After add(): " + list);



        // addFirst(element)
        // NOT from List interface
        // Specific to LinkedList / Deque
        // Adds element at beginning
        // Time Complexity -> O(1)
        list.addFirst(5);

        System.out.println("After addFirst(): " + list);



        // addLast(element)
        // NOT from List interface
        // Specific to LinkedList / Deque
        // Adds element at end
        // Time Complexity -> O(1)
        list.addLast(40);

        System.out.println("After addLast(): " + list);



        // add(index, element)
        // From List interface
        // Inserts at specific index
        // Requires traversal
        // Time Complexity -> O(n)
        list.add(2, 15);

        System.out.println("After add(index, value): " + list);




        /*
        ========================================================
                    ACCESSING ELEMENTS
        ========================================================
        */

        // get(index)
        // From List interface
        // Random access is slow in LinkedList
        // Requires traversal
        // Time Complexity -> O(n)
        System.out.println("Element at index 2: " + list.get(2));



        // getFirst()
        // NOT from List interface
        // Specific to LinkedList / Deque
        // Returns first element
        // Time Complexity -> O(1)
        System.out.println("First element: " + list.getFirst());



        // getLast()
        // NOT from List interface
        // Specific to LinkedList / Deque
        // Returns last element
        // Time Complexity -> O(1)
        System.out.println("Last element: " + list.getLast());




        /*
        ========================================================
                    UPDATING ELEMENTS
        ========================================================
        */

        // set(index, value)
        // From List interface
        // Replaces value at index
        // Requires traversal
        // Time Complexity -> O(n)
        list.set(1, 100);

        System.out.println("After set(): " + list);




        /*
        ========================================================
                    SEARCH OPERATIONS
        ========================================================
        */

        // contains(element)
        // From Collection interface
        // Linear search
        // Time Complexity -> O(n)
        System.out.println("Contains 30 ? " + list.contains(30));



        // indexOf(element)
        // From List interface
        // Returns first occurrence index
        // Time Complexity -> O(n)
        System.out.println("Index of 30: " + list.indexOf(30));



        // lastIndexOf(element)
        // From List interface
        // Returns last occurrence index
        // Time Complexity -> O(n)
        list.add(30);

        System.out.println("List: " + list);
        System.out.println("Last index of 30: " + list.lastIndexOf(30));




        /*
        ========================================================
                    REMOVE OPERATIONS
        ========================================================
        */

        // remove()
        // NOT from List interface
        // Queue-style remove
        // Removes first element
        // Specific to LinkedList / Queue / Deque
        // Time Complexity -> O(1)
        list.remove();

        System.out.println("After remove(): " + list);



        // remove(index)
        // From List interface
        // Requires traversal
        // Time Complexity -> O(n)
        list.remove(2);

        System.out.println("After remove(index): " + list);



        // removeFirst()
        // NOT from List interface
        // Specific to LinkedList / Deque
        // Removes first element
        // Time Complexity -> O(1)
        list.removeFirst();

        System.out.println("After removeFirst(): " + list);



        // removeLast()
        // NOT from List interface
        // Specific to LinkedList / Deque
        // Removes last element
        // Time Complexity -> O(1)
        list.removeLast();

        System.out.println("After removeLast(): " + list);



        // remove(Object)
        // From Collection interface
        // Removes first occurrence
        // Time Complexity -> O(n)
        list.remove(Integer.valueOf(30));

        System.out.println("After remove(Object): " + list);




        /*
        ========================================================
                    SIZE & EMPTY
        ========================================================
        */

        // size()
        // From Collection interface
        // Time Complexity -> O(1)
        System.out.println("Size: " + list.size());



        // isEmpty()
        // From Collection interface
        // Time Complexity -> O(1)
        System.out.println("Is Empty ? " + list.isEmpty());




        /*
        ========================================================
                    ITERATION
        ========================================================
        */

        list.add(50);
        list.add(60);
        list.add(70);

        System.out.println("\nUsing normal for loop:");

        // BAD for LinkedList
        // get(i) itself is O(n)
        // Overall Time Complexity -> O(n²)

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }

        System.out.println();



        System.out.println("\nUsing enhanced for loop:");

        // BEST way to iterate LinkedList
        // Time Complexity -> O(n)

        for (Integer num : list) {
            System.out.print(num + " ");
        }

        System.out.println();




        /*
        ========================================================
                    SORTING
        ========================================================
        */

        // Collections.sort()
        // Uses merge sort internally
        // Time Complexity -> O(n log n)

        Collections.sort(list);

        System.out.println("\nAfter sorting: " + list);




        /*
        ========================================================
                    CLEARING LIST
        ========================================================
        */

        // clear()
        // From Collection interface
        // Removes all elements
        // Time Complexity -> O(n)

        list.clear();

        System.out.println("After clear(): " + list);




        /*
        ========================================================
                    LINKEDLIST AS QUEUE
        ========================================================
        */

        LinkedList<Integer> queue = new LinkedList<>();



        // offer()
        // NOT from List interface
        // From Queue / Deque
        // Inserts at end
        // Time Complexity -> O(1)

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        System.out.println("\nQueue: " + queue);



        // offerFirst()
        // NOT from List interface
        // From Deque
        // Inserts at front
        // Time Complexity -> O(1)

        queue.offerFirst(0);

        System.out.println("After offerFirst(): " + queue);



        // offerLast()
        // NOT from List interface
        // From Deque
        // Inserts at end
        // Time Complexity -> O(1)

        queue.offerLast(4);

        System.out.println("After offerLast(): " + queue);



        // poll()
        // NOT from List interface
        // From Queue / Deque
        // Removes front element
        // Time Complexity -> O(1)

        queue.poll();

        System.out.println("After poll(): " + queue);



        // pollFirst()
        // NOT from List interface
        // From Deque
        // Removes first element
        // Time Complexity -> O(1)

        queue.pollFirst();

        System.out.println("After pollFirst(): " + queue);



        // pollLast()
        // NOT from List interface
        // From Deque
        // Removes last element
        // Time Complexity -> O(1)

        queue.pollLast();

        System.out.println("After pollLast(): " + queue);



        // peek()
        // NOT from List interface
        // From Queue / Deque
        // Returns front element
        // Time Complexity -> O(1)

        System.out.println("peek(): " + queue.peek());



        // peekFirst()
        // NOT from List interface
        // From Deque
        // Time Complexity -> O(1)

        System.out.println("peekFirst(): " + queue.peekFirst());



        // peekLast()
        // NOT from List interface
        // From Deque
        // Time Complexity -> O(1)

        System.out.println("peekLast(): " + queue.peekLast());




        /*
        ========================================================
                    LINKEDLIST AS STACK
        ========================================================
        */

        LinkedList<Integer> stack = new LinkedList<>();



        // push()
        // NOT from List interface
        // From Deque
        // Stack insertion
        // Equivalent to addFirst()
        // Time Complexity -> O(1)

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("\nStack: " + stack);



        // pop()
        // NOT from List interface
        // From Deque
        // Stack removal
        // Equivalent to removeFirst()
        // Time Complexity -> O(1)

        stack.pop();

        System.out.println("After pop(): " + stack);



        // peek()
        // NOT from List interface
        // From Queue / Deque
        // Returns top element
        // Time Complexity -> O(1)

        System.out.println("Top element: " + stack.peek());



        /*
        ========================================================
                    FINAL IMPORTANT NOTES
        ========================================================

        ArrayList:
        - Fast random access
        - Slower insert/delete in middle

        LinkedList:
        - Slow random access
        - Faster insert/delete

        Memory:
        LinkedList uses more memory because every node stores:
        - data
        - previous pointer
        - next pointer

        ========================================================
        */
    }
}