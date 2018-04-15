package ab1.impl.Nachnamen;

import ab1.SortedList;

/**
 * Implementierung von SortedList als einfach verkettete Liste.
 *
 * @author N.N.
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
        // @TODO
    }

    @Override
    public void clear()
    {
        // @TODO
    }

    @Override
    public void insert(int key) throws IllegalArgumentException
    {
        // @TODO
    }

    @Override
    public boolean remove(int key)
    {
        // @TODO
        return false;
    }

    @Override
    public int getLength()
    {
        // @TODO
        return 0;
    }

    @Override
    public int[] toArray()
    {
        // @TODO
        return null;
    }
}
