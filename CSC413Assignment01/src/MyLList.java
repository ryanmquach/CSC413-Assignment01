public class MyLList <E> implements ListInterface {

    //create node class to use nodes
    static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    //beginning of the list
    protected Node<E> head;
    //variable to store the length of the list
    private int length;
    //method to retrieve the node at the requested position
    private Node<E> getNodeAt(int position) {
        if (position < 0 || position >= length) {
            return null; // Invalid position
        }
        Node<E> current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current;
    }

    //Constructor to make a MyLList object
    public MyLList() {
        this.head = null;
        this.length = 0;
    }
    @Override
    public void add(Object newEntry) {
        add(length, newEntry);
    }

    @Override
    public boolean add(int newPosition, Object newEntry) {
        // Check if the newPosition is valid
        if (newPosition < 0 || newPosition > length) {
            // Invalid position
            return false;
        }

        // Create a new node with newEntry as the data and cast it to type E
        Node<E> newNode = new Node<>((E) newEntry);

        // Insert the new node at the specified position
        if (newPosition == 0) {
            // If newPosition is 0, insert at the beginning of the list
            newNode.next = head;
            // Update the head to point to the new node
            head = newNode;
        } else {
            // If newPosition is not 0, insert at a position other than the beginning
            // Find the node before the position where the new node will be inserted
            Node<E> prev = getNodeAt(newPosition - 1);
            // Make the new node point to the node after the previous node
            newNode.next = prev.next;
            // Make the previous node point to the new node
            prev.next = newNode;
        }
        // Increment the length of the list
        length++;
        // Return true when addition is successful
        return true;
    }

    @Override
    public Comparable remove(int givenPosition) {
        //Check if the givenPosition is valid
        if (givenPosition < 0 || givenPosition >= length) {
            //I invalid position, return null
            return null;
        }

        Node<E> removedNode;
        if (givenPosition == 0) {
            //If givenPosition is 0, remove the node at the beginning of the list
            removedNode = head;
            //Update the head to skip the removed node
            head = head.next;
        } else {
            //If givenPosition is not 0, then remove a node at a position that is not the beginning
            //Find the node before the position where the node will be removed
            Node<E> prev = getNodeAt(givenPosition - 1);
            //Store a reference to the node being removed
            removedNode = prev.next;
            //Update the previous node to skip the removed node
            prev.next = prev.next.next;
        }

        //Decrement the length of the list
        length--;

        //Return the data of the removed node after casting it to Comparable
        return (Comparable) removedNode.data;
    }

    @Override
    public void clear() {
        //set head to null
        head = null;
        //set length to 0
        length= 0;
        //list is now empty
    }

    @Override
    public Object replace(int givenPosition, Object newEntry) {
        //Check if the givenPosition is valid
        if (givenPosition < 0 || givenPosition >= length) {
            return null; // Invalid position, return null
        }
        //Get the node at the given position
        Node<E> node = getNodeAt(givenPosition);
        //Update the data of the node with newEntry
        node.data = (E) newEntry;
        //Return newEntry
        return newEntry;
    }

    @Override
    public Comparable getEntry(int givenPosition) {
        //Check if the givenPosition is valid
        if (givenPosition < 0 || givenPosition >= length) {
            return null; // Invalid position, return null
        }
        //Retrieve the data of the node at givenPosition
        return (Comparable) getNodeAt(givenPosition).data;
    }
    @Override
    public Comparable[] toArray() {
        //Create new array to store elements of the linked list
        Comparable[] array = new Comparable[length];
        //Start from the head
        Node<E> current = head;
        //Initialize an index to keep track of position in array
        int index = 0;
        //Traverse linked list and copy the elements to the array
        while (current != null) {
            //Copy the data of the current node to the array
            array[index] = (Comparable) current.data;
            //Move to the next node in the linked list
            current = current.next;
            //Increment the index to move to the next position in the array
            index++;
        }
        //Return the array containing the elements of the linked list
        return array;
    }
    @Override
    public boolean contains(Object anEntry) {
        //Start from the head of the list
        Node<E> current = head;
        //Traverse the linked list
        while (current != null) {
            //Check if the data of the current node is equal to the given entry
            if (current.data.equals(anEntry)) {
                //If found, return true
                return true;
            }
            //Move to the next node in the linked list
            current = current.next;
        }
        // If loop finishes without finding the entry, return false
        return false;
    }

    @Override
    public int getLength() {
        //Return the current length of the linked list
        return length;
    }

    @Override
    public boolean isEmpty() {
        //Check if the length of the linked list is 0
        return length == 0;
    }
}

