package Basics.Collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListExample {

    public static void main(String[] args) {

        // =========================================================
        // 1. Creating an ArrayList with Initial Capacity
        // =========================================================

        // Initial capacity is 3
        // Capacity = internal storage allocated
        // Current size = 0
        ArrayList<Integer> arr = new ArrayList<>(3);

        // =========================================================
        // 2. Adding Elements to ArrayList
        // =========================================================

        arr.add(1);
        arr.add(5);
        arr.add(80);

        // =========================================================
        // 3. Accessing Elements using get(index)
        // =========================================================

        // Access element using get(index)
        System.out.println(arr.get(2));

        // =========================================================
        // 4. Checking Current Size using size()
        // =========================================================

        // size() gives the number of elements currently present
        System.out.println("Size of array list : " + arr.size());

        // =========================================================
        // 5. Traversing ArrayList using Normal for Loop
        // =========================================================

        // Traversing using normal for loop
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }

        // =========================================================
        // 6. Traversing ArrayList using Enhanced for Loop
        // =========================================================

        // Traversing using enhanced for loop
        for (int x : arr) {
            System.out.println(x);
        }

        // =========================================================
        // 7. Dynamic Resizing of ArrayList
        // =========================================================

        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(6);
        arr.add(7);

        /*
         * Initially the ArrayList capacity was 3.
         * After filling those 3 positions, adding more elements causes resizing.
         *
         * Internally ArrayList creates a new larger array
         * (usually around 1.5x the previous capacity),
         * copies all existing elements into the new array,
         * and then adds the new element.
         */

        System.out.println("Elements after adding more values:");

        for (int x : arr) {
            System.out.println(x);
        }

        // =========================================================
        // 8. Removing Elements from ArrayList
        // =========================================================

        /*
         * Even after removing elements, the internal capacity
         * of the ArrayList usually does not decrease automatically.
         */

        arr.remove(0);
        arr.remove(0);
        arr.remove(0);
        arr.remove(0);
        arr.remove(0);

        // =========================================================
        // 9. Reducing Internal Capacity using trimToSize()
        // =========================================================

        /*
         * trimToSize() reduces the internal capacity
         * to match the current number of elements,
         * helping save memory.
         */

        arr.trimToSize();

        // =========================================================
        // 10. Difference between size and capacity
        // =========================================================

        /*
         * size() only tells the number of elements currently stored.
         * It does NOT tell the internal capacity of the ArrayList.
         */

        System.out.println("Size after removals: " + arr.size());

        // =========================================================
        // 11. Converting Integer[] Array to Fixed-size List
        // =========================================================

        Integer[] arr_1 = {1, 2, 3};

        /*
         * Arrays.asList() converts the array into a fixed-size List.
         * Elements can be modified,
         * but elements cannot be added or removed.
         */

        List<Integer> new_arr = Arrays.asList(arr_1);

        System.out.println("Elements of converted list:");

        for (int x : new_arr) {
            System.out.println(x);
        }

        // new_arr.add(10); // This will throw UnsupportedOperationException

        // =========================================================
        // 12. Converting Primitive int[] Array to List<Integer>
        // =========================================================

        int[] arr_2 = {2, 3, 4};

        /*
         * Arrays.asList() does not work properly with primitive arrays like int[].
         *
         * If we directly do:
         *
         * Arrays.asList(arr_2)
         *
         * Java treats the entire int[] array as a single object,
         * resulting in List<int[]> instead of List<Integer>.
         *
         * Therefore we manually convert each int into Integer
         * and store it inside a List<Integer>.
         */

        List<Integer> new_arr_2 = new ArrayList<>();

        for (int x : arr_2) {
            new_arr_2.add(x);
        }

        System.out.println("Elements of converted list:");

        for (int x : new_arr_2) {
            System.out.println(x);
        }

        // =========================================================
        // 13. Creating an Empty Mutable ArrayList
        // =========================================================

        List<Integer> l1 = new ArrayList<>();

        /*
         * Creates an empty mutable ArrayList.
         *
         * Elements can be added, removed, and modified.
         */

        // =========================================================
        // 14. Creating an Immutable List using List.of()
        // =========================================================

        List<Integer> l2 = List.of(1, 2, 3, 4, 5);

        System.out.println(l2);

        /*
         * List.of() creates an immutable (non-modifiable) list.
         *
         * Elements cannot be added, removed, or updated.
         *
         * Example:
         *
         * l2.add(6);      // Throws UnsupportedOperationException
         * l2.remove(0);   // Throws UnsupportedOperationException
         */

        // =========================================================
        // 15. Copying Elements using addAll()
        // =========================================================

        List<Integer> l3 = new ArrayList<>();

        /*
         * addAll() copies all elements from l2 into l3.
         *
         * Since l3 is an ArrayList, it is mutable.
         */

        l3.addAll(l2);

        l3.add(6);

        System.out.println(l3);

        /*
         * Output:
         * [1, 2, 3, 4, 5, 6]
         */


        // =========================================================
        // 16. String ArrayList and remove(Object)
        // =========================================================

        List<String> ls = new ArrayList<>();

        ls.add("Apple");
        ls.add("Banana");
        ls.add("Melon");

        System.out.println(ls);

        /*
         * remove(Object obj)
         *
         * Here "Apple" is a String object,
         * so removal happens based on value.
         *
         * "Apple" will be searched and removed from the list.
         */

        ls.remove("Apple");

        // =========================================================
        // 17. Checking if an Element Exists using contains()
        // =========================================================

        boolean applefound=ls.contains("Apple");
        System.out.println(applefound);

        System.out.println(ls);



        // =========================================================
        // 18. Integer ArrayList and remove(index)
        // =========================================================

        List<Integer> li = new ArrayList<>();

        li.add(1);
        li.add(2);
        li.add(3);

        System.out.println(li);

        /*
         * remove(int index)
         *
         * Here 1 is treated as an index,
         * not as an Integer object.
         *
         * So element at index 1 will be removed.
         *
         * Initial list:
         * [1, 2, 3]
         *
         * After removal:
         * [1, 3]
         */

        li.remove(1);

        // =========================================================
        // 19. Removing Integer Value using remove(Object)
        // =========================================================

        /*
         * remove(Object obj)
         *
         * Integer.valueOf(3) creates an Integer object.
         *
         * Therefore removal happens based on value.
         *
         * Element 3 will be searched and removed.
         */

        li.remove(Integer.valueOf(3));

        System.out.println(li);


        // =========================================================
        // 20. Converting List<Integer> to Integer[] Array
        // =========================================================

        List<Integer> numberList = new ArrayList<>();

        numberList.add(1);
        numberList.add(2);
        numberList.add(3);

        /*
         * toArray(new Integer[0])
         * converts List<Integer> into Integer[] array.
         *
         * new Integer[0] helps Java determine
         * the required array type.
         */

        Integer[] numberArray = numberList.toArray(new Integer[0]);

        /*
         * Arrays.toString() is used to print
         * array elements properly.
         * Arrays.toString(arr) works somewhat like:
         * "[1, 2, 3]"
         * It creates a formatted string representation of the array.
         */

        System.out.println(Arrays.toString(numberArray));



        // =========================================================
        // 21. Converting List<String> to String[] Array
        // =========================================================

        List<String> fruitList = new ArrayList<>();

        fruitList.add("Apple");
        fruitList.add("Banana");
        fruitList.add("Mango");

        /*
         * Converts List<String> into String[] array.
         */

        String[] fruitArray = fruitList.toArray(new String[0]);

        System.out.println(Arrays.toString(fruitArray));

    }
}
