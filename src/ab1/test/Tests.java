package ab1.test;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

import ab1.*;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import ab1.impl.Nachnamen.AB1;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();

	public final static Random rand = new Random(System.currentTimeMillis());

	public final static int ARRAY_SIZE = 100000;
	public final static int REPEATS = 10;

    /**
     * Test Sorter implementation using not more than 5 array elements.
     */
	private void test_Sorter_small(Sorter s)
    {
        int[] a = new int[] { 5, 4, 3, 2, 1 };
        s.sort(a);
        Assert.assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, a);

        a = new int[] { 4, 4, 1, 2, 2 };
        s.sort(a);
        Assert.assertArrayEquals(new int[] { 1, 2, 2, 4, 4 }, a);

        a = new int[] { 4, 1, 5, 2, 3 };
        s.sort(a);
        Assert.assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, a);

        a = new int[] { 1, 2, 3, 4 };
        s.sort(a);
        Assert.assertArrayEquals(new int[] { 1, 2, 3, 4 }, a);

        a = new int[] { 2, 1, 2, 1 };
        s.sort(a);
        Assert.assertArrayEquals(new int[] { 1, 1, 2, 2 }, a);

        a = new int[] { 1, 1, 1 };
        s.sort(a);
        Assert.assertArrayEquals(new int[] { 1, 1, 1 }, a);

        a = new int[] { 2, 1 };
        s.sort(a);
        Assert.assertArrayEquals(new int[] { 1, 2 }, a);
    }

    /**
     * Test Sorter implementation using randomly initialized arrays
     * of length ARRAY_SIZE repeatedly.
     */
    private void test_Sorter_large(Sorter s)
    {
        int[] a = new int[ARRAY_SIZE];
        int[] b = new int[ARRAY_SIZE];

        for (int rep = 0; rep < REPEATS; rep++) {
            for (int i = 0; i < ARRAY_SIZE; i++)
                a[i] = b[i] = rand.nextInt();
            s.sort(a);
            Arrays.sort(b);
            Assert.assertArrayEquals(b, a);
        }
    }

	@Test
	public void t01_SortedList_insert()
    {

		SortedList l = AB1.newSortedList();

		l.insert(1);
		Assert.assertEquals(1, l.getLength());
		Assert.assertArrayEquals(new int[] { 1 }, l.toArray());

		l.clear();
		Assert.assertEquals(0, l.getLength());
		Assert.assertArrayEquals(new int[] {}, l.toArray());

		l.insert(3);
		l.insert(1);
		Assert.assertEquals(2, l.getLength());
		Assert.assertArrayEquals(new int[] { 1, 3 }, l.toArray());

		l.clear();
		l.insert(0);
		l.insert(2);
		l.insert(3);
		l.insert(1);
		Assert.assertEquals(4, l.getLength());
		Assert.assertArrayEquals(new int[] { 0, 1, 2, 3 }, l.toArray());
	}

	@Test
    public void t02_SortedList_insert_exception()
    {
        SortedList l = AB1.newSortedList();

        l.insert(1);
        l.insert(2);
        thrown.expect(IllegalArgumentException.class);
        l.insert(2);
    }

	@Test
	public void t03_SortedList_remove()
    {
        SortedList l = AB1.newSortedList();

        l.insert(4);
        l.insert(1);
        l.insert(7);
        l.insert(2);
        l.insert(3);
        l.insert(5);
        l.insert(6);

		l.remove(3);
		Assert.assertEquals(6, l.getLength());
		Assert.assertArrayEquals(new int[] { 1, 2, 4, 5, 6, 7 }, l.toArray());
		
		l.remove(1);
		Assert.assertEquals(5, l.getLength());
		Assert.assertArrayEquals(new int[] { 2, 4, 5, 6, 7 }, l.toArray());
		
		l.remove(7);
		Assert.assertEquals(4, l.getLength());
		Assert.assertArrayEquals(new int[] { 2, 4, 5, 6 }, l.toArray());
	}

	@Test
	public void t04_QuickSort_small()
	{
		test_Sorter_small(AB1.newQuickSort());
	}

    @Test
    public void t05_QuickSort_large()
    {
        test_Sorter_large(AB1.newQuickSort());
    }

    private void test_Heap_add(int[] values)
    {
        Heap h = AB1.newHeap(values.length);
        int max = Integer.MIN_VALUE;
        h.clear();
        Assert.assertEquals(0, h.size());
        for (int i=0; i < values.length; i++) {
            h.add(values[i]);
            if (values[i] > max)
                max = values[i];
            Assert.assertEquals(max, h.max());
            Assert.assertEquals(i+1, h.size());
        }
    }

    @Test
    public void t06_Heap_add()
    {
        test_Heap_add(new int[] {1, 2, 3, 7, 4, 7, 6});
        test_Heap_add(new int[] {2, 2, 3, 7, 4, 6, 8, 5});
    }

    private void test_Heap_remove(int[] values)
    {
        Heap h = AB1.newHeap(values.length);
        for (int v : values)
            h.add(v);
        Assert.assertEquals(values.length, h.size());
        int[] sorted = Arrays.copyOf(values, values.length);
        Arrays.sort(sorted);
        for (int i = sorted.length-1; i >= 0; i--) {
            Assert.assertEquals(sorted[i], h.max());
            Assert.assertEquals(sorted[i], h.removeMax());
            Assert.assertEquals(i, h.size());
        }
    }

    @Test
    public void t07_Heap_remove()
    {
        test_Heap_remove(new int[] {5, 2, 3, 5, 4, 7, 6});
        test_Heap_remove(new int[] {2, 2, 3, 7, 4, 6, 8, 5});
    }

    @Test
    public void t08_Heap_add_exception()
    {
        Heap h = AB1.newHeap(3);
        h.add(1);
        h.add(2);
        h.add(3);

        thrown.expect(IndexOutOfBoundsException.class);
        h.add(4);
    }

    @Test
    public void t09_Heap_remove_exception()
    {
        Heap h = AB1.newHeap(3);

        thrown.expect(NoSuchElementException.class);
        h.removeMax();
    }

    @Test
    public void t10_Heap_max_exception()
    {
        Heap h = AB1.newHeap(3);

        h.add(1);
        h.clear();

        thrown.expect(NoSuchElementException.class);
        h.max();
    }

    @Test
    public void t11_HeapSort_small()
    {
        test_Sorter_small(AB1.newHeapSort(5));
    }

    @Test
    public void t12_HeapSort_large()
    {
        test_Sorter_large(AB1.newHeapSort(ARRAY_SIZE));
    }
}