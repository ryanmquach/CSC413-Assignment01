//Name: Ryan Quach
//Date: 02/19/24

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Create a sample instance of LinkedListWithIterator
        ListWithIteratorInterface myList = new LinkedListWithIterator();

// Adding elements to the list to test the iterator
        myList.add("Safari");
        myList.add("Chrome");
        myList.add("Firefox");

// Get iterator object for this instance
        Iterator<String> iterator = myList.getIterator();

// Iterate through the list and print its elements using hasNext() and next()
        System.out.println("Elements of list should be as follows in this order: Safari, Chrome, Firefox");
        System.out.println("Elements of list: ");
        while (iterator.hasNext()) {
            String entry = iterator.next();
            System.out.println(entry);
        }
    }
}
