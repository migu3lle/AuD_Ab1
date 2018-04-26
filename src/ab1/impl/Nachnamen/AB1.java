package ab1.impl.Nachnamen;

import ab1.Heap;
import ab1.SortedList;
import ab1.Sorter;

/**
 * Dient der Erzeugung von Implementierungen (Objekten) der vorgegebenen Interfaces.
 *
 * @author N.N.
 */
public class AB1
{
    /**
     * Erzeugt ein Objekt, das die Schnittstelle SortedList implementiert.
     */
    public static SortedList newSortedList()
    {
        return new SortedListImpl();
    }

    /**
     * Erzeugt ein Objekt, das die Schnittstelle Sorter für den
     * QuickSort-Algorithmus implementiert.
     */
    public static Sorter newQuickSort()
    {
        return new QuickSort();
    }

    /**
     * Erzeugt ein Objekt, das die Schnittstelle Heap implementiert.
     */
    public static Heap newHeap(int capacity)
    {
        // @TODO: eigene Implementierung zurückliefern
        return new HeapImpl(capacity);
    }

    /**
     * Erzeugt ein Objekt, das die Schnittstelle Sorter für den
     * HeapSort-Algorithmus implementiert.
     */
    public static Sorter newHeapSort(int capacity)
    {
        // @TODO: eigene Implementierung zurückliefern
        return new HeapImpl(capacity);
    }
}
