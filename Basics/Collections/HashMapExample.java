package Basics.Collections;

import java.util.HashMap;
import java.util.Objects;

/*
========================================================
                    HASHMAP IN JAVA
========================================================

HashMap stores:
KEY -> VALUE pairs

Example:
101 -> "Anshuman"

========================================================
                IMPORTANT FEATURES
========================================================

1. Stores key-value pairs
2. Keys must be unique
3. Values can be duplicate
4. Allows one null key
5. Allows multiple null values
6. Not thread-safe
7. Unordered collection

========================================================
                INTERNAL WORKING
========================================================

HashMap internally uses:

1. Array of buckets
2. Hashing
3. hashCode()
4. equals()

========================================================
                IMPORTANT CONCEPT
========================================================

HashMap uses TWO things:

1. hashCode()
   -> decides bucket location

2. equals()
   -> checks equality inside bucket

========================================================
                TIME COMPLEXITIES
========================================================

put()          -> O(1) average
get()          -> O(1) average
remove()       -> O(1) average

containsKey()  -> O(1) average

Worst case can become O(n)

========================================================
*/

class Student {

    int id;
    String name;

    Student(int id, String name) {

        this.id = id;
        this.name = name;
    }

    /*
    ========================================================
            IMPORTANT FOR HASHMAP
    ========================================================

    Overriding hashCode() and equals()

    Otherwise:
    Different objects are treated differently
    even if data is same.

    ========================================================
    */

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null ||
                getClass() != obj.getClass())
            return false;

        Student other = (Student) obj;

        return id == other.id &&
                Objects.equals(name, other.name);
    }

    @Override
    public String toString() {

        return id + " " + name;
    }
}

public class HashMapExample {

    public static void main(String[] args) {

        /*
        ========================================================
                    HASHMAP WITH INTEGER KEY
        ========================================================
        */

        HashMap<Integer, String> intMap =
                new HashMap<>();



        // put(key, value)
        // Adds key-value pair
        // Time Complexity -> O(1) average

        intMap.put(101, "Anshuman");
        intMap.put(102, "Rahul");
        intMap.put(103, "Aman");

        System.out.println("Integer Key Map:");
        System.out.println(intMap);



        // get(key)
        // Time Complexity -> O(1) average

        System.out.println("Value for key 101: "
                + intMap.get(101));



        // containsKey(key)
        // Time Complexity -> O(1) average

        System.out.println("Contains key 102 ? "
                + intMap.containsKey(102));



        // remove(key)
        // Time Complexity -> O(1) average

        intMap.remove(103);

        System.out.println("After remove(): "
                + intMap);




        /*
        ========================================================
                    HASHMAP WITH STRING KEY
        ========================================================
        */

        HashMap<String, Integer> stringMap =
                new HashMap<>();



        stringMap.put("Apple", 100);
        stringMap.put("Banana", 50);
        stringMap.put("Mango", 80);

        System.out.println("\nString Key Map:");
        System.out.println(stringMap);




        /*
        ========================================================
            IMPORTANT STRING HASHMAP CONCEPT
        ========================================================

        String overrides:
        - hashCode()
        - equals()

        So TWO strings with SAME content
        are treated as SAME key.

        ========================================================
        */

        String s1 = new String("Hello");
        String s2 = new String("Hello");



        System.out.println("\ns1 == s2 : "
                + (s1 == s2));

        /*
        false

        Because:
        Different memory locations
        */



        System.out.println("s1.equals(s2) : "
                + s1.equals(s2));

        /*
        true

        Because:
        Content same
        */



        System.out.println("s1 hashCode: "
                + s1.hashCode());

        System.out.println("s2 hashCode: "
                + s2.hashCode());



        HashMap<String, Integer> map =
                new HashMap<>();



        map.put(s1, 1);

        /*
        Since s2 has SAME hashCode
        and equals() returns true,

        it replaces old value.
        */

        map.put(s2, 2);



        System.out.println("\nString HashMap:");
        System.out.println(map);

        /*
        Output:
        {Hello=2}

        NOT:
        {Hello=1, Hello=2}

        Because:
        Strings are considered equal.
        */




        /*
        ========================================================
                    HASHMAP WITH OBJECT KEY
        ========================================================
        */

        /*
        CASE 1:
        Without overriding hashCode()
        and equals()

        Different objects would become
        different keys.

        BUT:
        Here we HAVE overridden them.
        */

        Student st1 =
                new Student(1, "Anshuman");

        Student st2 =
                new Student(1, "Anshuman");



        System.out.println("\nObject Comparison:");



        System.out.println("st1 == st2 : "
                + (st1 == st2));

        /*
        false

        Different memory locations
        */



        System.out.println("st1.equals(st2) : "
                + st1.equals(st2));

        /*
        true

        Because:
        We overrode equals()
        */



        System.out.println("st1 hashCode: "
                + st1.hashCode());

        System.out.println("st2 hashCode: "
                + st2.hashCode());



        HashMap<Student, String> studentMap =
                new HashMap<>();



        studentMap.put(st1, "First Student");



        /*
        Since:
        hashCode same
        equals() true

        This REPLACES old value.
        */

        studentMap.put(st2, "Second Student");



        System.out.println("\nObject Key HashMap:");
        System.out.println(studentMap);




        /*
        ========================================================
                WHAT IF WE DON'T OVERRIDE?
        ========================================================

        Default Object class behavior:

        equals() ->
        compares memory addresses

        hashCode() ->
        generated from memory location

        Meaning:

        Even same-looking objects become
        DIFFERENT keys.

        ========================================================
        */




        /*
        ========================================================
                    ITERATING HASHMAP
        ========================================================
        */

        System.out.println("\nIterating Integer Map:");



        // keySet()
        // Time Complexity -> O(n)

        for (Integer key : intMap.keySet()) {

            System.out.println(
                    key + " -> " + intMap.get(key)
            );
        }




        /*
        ========================================================
                    entrySet() ITERATION
        ========================================================
        */

        // Most efficient iteration

        System.out.println("\nUsing entrySet():");



        for (HashMap.Entry<Integer, String> entry
                : intMap.entrySet()) {

            System.out.println(
                    entry.getKey()
                            + " -> "
                            + entry.getValue()
            );
        }




        /*
        ========================================================
                    IMPORTANT METHODS
        ========================================================
        */

        // size()
        // Time Complexity -> O(1)

        System.out.println("\nSize: "
                + intMap.size());



        // isEmpty()
        // Time Complexity -> O(1)

        System.out.println("Is Empty ? "
                + intMap.isEmpty());



        // clear()
        // Time Complexity -> O(n)

        intMap.clear();

        System.out.println("After clear(): "
                + intMap);




        /*
        ========================================================
                FINAL IMPORTANT INTERVIEW NOTES
        ========================================================

        Integer:
        - Overrides hashCode()
        - Overrides equals()

        String:
        - Overrides hashCode()
        - Overrides equals()

        Object:
        Default behavior:
        - hashCode based on memory
        - equals compares memory reference

        Custom objects should override:
        - equals()
        - hashCode()

        whenever used as:
        - HashMap key
        - HashSet element

        ========================================================
        */
    }
}