package ab1.impl.Nachnamen;

import ab1.Heap;
import ab1.Sorter;

import java.util.NoSuchElementException;

/**
 * Heap-Implementierung, die auf einem Array mit begrenzter Kapazität operiert.
 * Implementiert auch HeapSort.
 *
 * @author N.N.
 */
public class HeapImpl implements Heap, Sorter
{
    /**
     * Konstruktor.
     * @param capacity gewünschte Kapazität des Heap (maximale Anzahl von Elementen).
     */
    public HeapImpl(int capacity)
    {
        // @TODO
    }

    @Override
    public void clear()
    {
        // @TODO
    }

    @Override
    public void add(int key) throws IndexOutOfBoundsException
    {
        // @TODO
    }

    @Override
    public int removeMax() throws NoSuchElementException
    {
        // @TODO
        return 0;
    }

    @Override
    public int max() throws NoSuchElementException
    {
        // @TODO
        return 0;
    }

    @Override
    public int size()
    {
        // @TODO
        return 0;
    }

    @Override
    public void sort(int[] array)
    {
        // @TODO
    }
}
