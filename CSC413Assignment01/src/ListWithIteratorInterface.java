import java.util.Iterator;

public interface ListWithIteratorInterface<E> extends ListInterface<E>{

    //Returns Iterator object.
    Iterator getIterator();
}
