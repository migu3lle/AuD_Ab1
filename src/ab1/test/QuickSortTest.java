package ab1.test;

import ab1.impl.Nachnamen.QuickSort;
import org.junit.Before;
import org.junit.Test;

public class QuickSortTest {

    QuickSort quickSort = new QuickSort();
    int[] array = {4, 7, 5, 1, 1, 0, 9, 2, 5};


    @Test
    public void printSorted(){
        quickSort.sort(array);
    }
}
