package Basics.Collections;

import java.util.Stack;
import java.util.Collections;

/*
========================================================
                    STACK IN JAVA
========================================================

Stack:
- Follows LIFO principle
- LIFO = Last In First Out

Example:

Push:
10
20
30

Top -> 30

Pop removes:
30 first

========================================================
                IMPORTANT FEATURES
========================================================

1. Legacy class
2. Synchronized (thread-safe)
3. Extends Vector class

Inheritance:

Object
   ↑
Vector
   ↑
Stack

========================================================
                MODERN RECOMMENDATION
========================================================

Stack is old legacy class.

Modern Java prefers:
- Deque
- ArrayDeque

for stack implementation.

But Stack is still important for:
- Interviews
- Learning
- Legacy systems

========================================================
                TIME COMPLEXITIES
========================================================

push()              -> O(1)
pop()               -> O(1)
peek()              -> O(1)

search()            -> O(n)

get(index)          -> O(1)

contains()          -> O(n)

size()              -> O(1)

empty()             -> O(1)

Collections.sort()  -> O(n log n)

========================================================
*/

public class StackExample {

    public static void main(String[] args) {

        /*
        ========================================================
                    CREATING STACK
        ========================================================
        */

        Stack<Integer> stack = new Stack<>();




        /*
        ========================================================
                    PUSH OPERATION
        ========================================================
        */

        // push(element)
        // Specific to Stack
        // Adds element at top
        // Time Complexity -> O(1)

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("After push(): " + stack);




        /*
        ========================================================
                    PEEK OPERATION
        ========================================================
        */

        // peek()
        // Specific to Stack
        // Returns top element
        // Does NOT remove it
        // Time Complexity -> O(1)

        System.out.println("Top element using peek(): "
                + stack.peek());




        /*
        ========================================================
                    POP OPERATION
        ========================================================
        */

        // pop()
        // Specific to Stack
        // Removes top element
        // Time Complexity -> O(1)

        stack.pop();

        System.out.println("After pop(): " + stack);




        /*
        ========================================================
                    SEARCH OPERATION
        ========================================================
        */

        // search(element)
        // Specific to Stack
        // Returns position from top
        //
        // Example:
        // Stack = [10, 20]
        //
        // Top is 20
        //
        // search(20) -> 1
        // search(10) -> 2
        //
        // Returns -1 if not found
        //
        // Time Complexity -> O(n)

        System.out.println("Position of 20 from top: "
                + stack.search(20));



        System.out.println("Position of 100 from top: "
                + stack.search(100));




        /*
        ========================================================
                    CHECK EMPTY
        ========================================================
        */

        // empty()
        // Specific to Stack
        // Returns true if stack is empty
        // Time Complexity -> O(1)

        System.out.println("Is stack empty? "
                + stack.empty());




        /*
        ========================================================
                    SIZE OPERATIONS
        ========================================================
        */

        // size()
        // Inherited from Vector
        // Time Complexity -> O(1)

        System.out.println("Stack size: "
                + stack.size());




        /*
        ========================================================
                    ACCESSING ELEMENTS
        ========================================================
        */

        stack.push(40);
        stack.push(50);



        // get(index)
        // Inherited from Vector
        // Random access
        // Time Complexity -> O(1)

        System.out.println("Element at index 1: "
                + stack.get(1));



        // firstElement()
        // Inherited from Vector
        // Time Complexity -> O(1)

        System.out.println("First element: "
                + stack.firstElement());



        // lastElement()
        // Inherited from Vector
        // Top element internally
        // Time Complexity -> O(1)

        System.out.println("Last element: "
                + stack.lastElement());




        /*
        ========================================================
                    UPDATING ELEMENTS
        ========================================================
        */

        // set(index, value)
        // Inherited from List
        // Time Complexity -> O(1)

        stack.set(1, 999);

        System.out.println("After set(): "
                + stack);




        /*
        ========================================================
                    SEARCH METHODS
        ========================================================
        */

        // contains(element)
        // From Collection interface
        // Linear search
        // Time Complexity -> O(n)

        System.out.println("Contains 40 ? "
                + stack.contains(40));



        // indexOf(element)
        // From List interface
        // Time Complexity -> O(n)

        System.out.println("Index of 40: "
                + stack.indexOf(40));




        /*
        ========================================================
                    REMOVE OPERATIONS
        ========================================================
        */

        // remove(index)
        // From List interface
        // Requires shifting
        // Time Complexity -> O(n)

        stack.remove(1);

        System.out.println("After remove(index): "
                + stack);



        // remove(Object)
        // From Collection interface
        // Removes first occurrence
        // Time Complexity -> O(n)

        stack.remove(Integer.valueOf(40));

        System.out.println("After remove(Object): "
                + stack);




        /*
        ========================================================
                    ITERATION
        ========================================================
        */

        stack.push(60);
        stack.push(70);



        // Normal for loop
        // Time Complexity -> O(n)

        System.out.println("\nUsing normal for loop:");

        for (int i = 0; i < stack.size(); i++) {
            System.out.print(stack.get(i) + " ");
        }

        System.out.println();



        // Enhanced for loop
        // Time Complexity -> O(n)

        System.out.println("\nUsing enhanced for loop:");

        for (Integer num : stack) {
            System.out.print(num + " ");
        }

        System.out.println();



        // forEach()
        // Java 8+
        // Time Complexity -> O(n)

        System.out.println("\nUsing forEach():");

        stack.forEach(num -> System.out.print(num + " "));

        System.out.println();




        /*
        ========================================================
                    SORTING
        ========================================================
        */

        // Collections.sort()
        // Uses TimSort internally
        // Time Complexity -> O(n log n)

        Collections.sort(stack);

        System.out.println("\nAfter Collections.sort(): "
                + stack);



        // sort(null)
        // Modern Java 8+ sorting
        // Time Complexity -> O(n log n)

        stack.sort(null);

        System.out.println("After stack.sort(null): "
                + stack);



        // Descending order sort

        stack.sort((a, b) -> b - a);

        System.out.println("Descending sort: "
                + stack);




        /*
        ========================================================
                    CLEAR STACK
        ========================================================
        */

        // clear()
        // From Collection interface
        // Removes all elements
        // Time Complexity -> O(n)

        stack.clear();

        System.out.println("After clear(): "
                + stack);




        /*
        ========================================================
                    IMPORTANT STACK METHODS
        ========================================================

        push()
        pop()
        peek()
        empty()
        search()

        These are Stack-specific methods.

        ========================================================
        */




        /*
        ========================================================
                    IMPORTANT INTERVIEW NOTES
        ========================================================

        Stack extends Vector:
        - Synchronized
        - Thread-safe
        - Slower than ArrayDeque

        Modern preferred stack:
        - ArrayDeque

        Example:

        Deque<Integer> stack = new ArrayDeque<>();

        push()  -> addFirst()
        pop()   -> removeFirst()
        peek()  -> peekFirst()

        ========================================================
        */
    }
}