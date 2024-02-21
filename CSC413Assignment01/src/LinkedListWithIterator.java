import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListWithIterator extends MyLList implements ListWithIteratorInterface {
    @Override
    public Iterator getIterator() {
        //Create and return a new instance of the LinkedListIterator
        return new LinkedListIterator();
    }

    private class LinkedListIterator<E> implements Iterator<E> {
        private Node<E> current;

        public LinkedListIterator() {
            // Iterator will start from the head of the list
            current = (Node<E>) head;
        }

        @Override
        // Check if there's a next element
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            //If there is no next element, throw exception
            if (!hasNext()) {
                throw new NoSuchElementException(); // Throw exception if no next element
            }
            //Otherwise, get the data of the current node
            E data = current.data;
            //Move to the next node
            current = current.next;
            //return the data
            return data;
        }
    }
}
