package ab1.impl.GagglGundackerKopali;

import ab1.SortedList;

/**
 * Implementierung von SortedList als einfach verkettete Liste.
 *
 * @author Michael Gundacker
 */
public class SortedListImpl implements SortedList
{
    private class ListElement
    {
        int key;
        ListElement next;

        ListElement(int key, ListElement next)
        {
            this.key = key;
            this.next = next;
        }

        ListElement(int key)
        {
            this(key, null);
        }
    }

    private ListElement firstElement;
    private int length;

    /**
     * Konstruktor.
     */
    public SortedListImpl()
    {
        this.firstElement = null;
        this.length = 0;
    }

    @Override
    public void clear()
    {
        firstElement = null;
        length = 0;
    }

    @Override
    public void insert(int key) throws IllegalArgumentException
    {
        ListElement element = new ListElement(key);

        //Check if first element exists, if not, insert new Element as head
        if(firstElement == null){
            firstElement = element;
            length++;
            return;     //If element was inserted as head - return
        }
        ListElement curr = firstElement;
        ListElement prev = null;

        boolean inserted = false;
        while(!inserted) {
            /*
            Check if element >= current element and current element has successor element
            If true, set current element to current.next element and redo loop
             */
            if(element.key >= curr.key && curr.next != null){
                prev = curr;
                curr = curr.next;
                continue;
            }
            /*
            Check if element >= current element, but no successor
            If true, element is last element in list
             */
            else if(element.key >= curr.key){
                if(element.key == curr.key)
                    throw new IllegalArgumentException("Element " + element.key + " already exists in List!");
                curr.next = element;
                inserted = true;
                length++;
            }
            else if(element.key < curr.key && prev != null){
                if(element.key == curr.key || element.key == prev.key)
                    throw new IllegalArgumentException("Element " + element.key + " already exists in List!");
                prev.next = element;
                element.next = curr;
                inserted = true;
                length++;
            }
            //Case element is on first position in list
            else{
                if(element.key == curr.key)
                    throw new IllegalArgumentException("Element " + element.key + " already exists in List!");
                element.next = curr;
                firstElement = element;
                inserted = true;
                length++;
            }
        }
    }

    @Override
    public boolean remove(int key)
    {
        //If list is empty, nothing to remove
        if(firstElement == null)
            return false;

        ListElement curr = firstElement;
        ListElement prev = null;

        for (int i = 0; i < length; i++) {
            //If not equal, go to next element
            if (curr.key != key) {
                prev = curr;
                curr = curr.next;
            }
            //Found as first element in list
            else if (curr.key == key && prev == null) {
                firstElement = curr.next;
                curr.next = null;
                length--;
                return true;
            }
            //Found as element in between two elements
            else if (curr.key == key && curr.next != null) {
                prev.next = curr.next;
                curr.next = null;
                length--;
                return true;
            }
            //Found as last element in list
            else {
                prev.next = null;
                length--;
                return true;
            }
        }
        //If element was not found in list, return false
        return false;
    }

    @Override
    public int getLength()
    {
        return length;
    }

    @Override
    public int[] toArray()
    {
        //If SortedList is empty return new empty array
        int[] intArray;
        if(firstElement == null)
            return new int[0];

        ListElement curr = firstElement;

        //Create array with length and fill with Elements
        intArray = new int[length];
        for (int i = 0; i < length; i++) {
            intArray[i] = curr.key;
            curr = curr.next;
        }

        return intArray;
    }
}
